package Main;

import Chunks.Chunk;
import Chunks.ChunkManager;
import Controls.Camera;
import Controls.KeyboardController;
import Renderer.Renderer;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static Util.Log.println;

//First Person Camera Controller
public class Main {
    Camera camera = new Camera(0, 1, 0);

    double currenttick = 0;

    //translates and rotate the matrix so that it looks through the camera
    //this dose basic what gluLookAt() does
    public void lookThrough() {
        //rotate the pitch around the X axis
        GL11.glRotatef(camera.pitch, 1.0f, 0.0f, 0.0f);
        //rotate the yaw around the Y axis
        GL11.glRotatef(camera.yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        GL11.glTranslatef(camera.position.x, camera.position.y, camera.position.z);
    }

    private static boolean gameRunning = true;
    private static int targetWidth = 800;
    private static int targetHeight = 600;

    private void init() {
        //load textures here and other things
    }

    /**
     * The texture that’s been loaded
     */

    private static void initDisplay(boolean fullscreen) {

        DisplayMode chosenMode = null;

        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            for (int i = 0; i < modes.length; i++) {
                if ((modes[i].getWidth() == targetWidth) && (modes[i].getHeight() == targetHeight)) {
                    chosenMode = modes[i];
                    break;
                }
            }
        } catch (LWJGLException e) {
            Sys.alert("Error", "Unable to determine display modes.");
            System.exit(0);
        }

        // at this point if we have no mode there was no appropriate, let the user know
        // and give up
        if (chosenMode == null) {
            Sys.alert("Error", "Unable to find appropriate display mode.");
            System.exit(0);
        }

        try {
            Display.setDisplayMode(chosenMode);
            Display.setFullscreen(fullscreen);
            Display.setTitle("Secret Title");
            Display.create();

        } catch (LWJGLException e) {
            Sys.alert("Error", "Unable to create display.");
            System.exit(0);
        }

    }

    private static boolean initGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

//        Calculate the aspect ratio of the window
        GLU.gluPerspective(45.0f, ((float) targetWidth) / ((float) targetHeight), 0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glEnable(GL11.GL_TEXTURE_2D);                                    // Enable Texture Mapping ( NEW )
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
        return true;
    }


    private void run() {
        KeyboardController keyboard = new KeyboardController(camera);
        float dx, dy, time = 0.0f;
        float mouseSensitivity = 0.15f;
        //hide the mouse
        Mouse.setGrabbed(true);
        int oldx = 0;
        int oldz = 0;
        ChunkManager.addLoaded(ChunkManager.generateChunk(0, 1, 0));
        while (gameRunning) {
            Renderer.render();
            Display.update();


            if (currenttick % 5 == 0) {

                int x = (int) (camera.position.getX() / 8 + .5);
                int z = (int) (camera.position.getZ() / 8);

                if (oldx != x || oldz != z) {
                    //Chunk Loading/unloading
                    for (int i = x - 3; i < x + 3; i++) {
                        for (int k = z - 3; k < z + 3; k++) {
                            Boolean found = false;
                            for (Chunk chunk : ChunkManager.LoadedChunksList) {
                                if (chunk.x == -i * 6 && chunk.z == -k * 6) {
                                    found = true;
                                    //ChunkManager.Keeploaded.add(chunk);
                                    break;
                                }
                            }
                            if (!found) {
                                ChunkManager.addLoaded(ChunkManager.generateChunk(-i, 1, -k));
                            }
                        }
                    }
                    //ChunkManager.flushLoaded();
                }

                oldx = x;
                oldz = z;
            }


            time = Sys.getTime();

            //distance in mouse movement from the last getDX() and getDY() calls.
            dx = Mouse.getDX();
            dy = Mouse.getDY();

            //control camera yaw from x movement from the mouse
            camera.yaw(dx * mouseSensitivity);
            //control camera pitch from y movement from the mouse
            camera.cpitch = (Math.abs(-dy * mouseSensitivity + camera.pitch + camera.pitch) / 360);
            camera.cpitch -= (int) camera.cpitch;
            if (camera.oldpitch < .5 && camera.cpitch < .5) {
                camera.pitch(-dy * mouseSensitivity);
            }
            camera.oldpitch = camera.cpitch;
            camera.checkmovement();


            //set the modelview matrix back to the identity
            GL11.glLoadIdentity();
            //look through the camera before you draw anything
            lookThrough();
            keyboard.checks();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glTranslatef(0.0f, 0.0f, -5.0f);                              // Move Into The Screen 5 Units
            Renderer.render();

            // finally check if the user has requested that the display be
            // shutdown
            if (Display.isCloseRequested()) {
                gameRunning = false;
                Display.destroy();
                System.exit(0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Sys.alert("Close", "To continue, press ESCAPE on your keyboard or OK on the screen.");
                System.exit(0);

            }
            currenttick++;
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        initDisplay(false);
        initGL();
        main.init();
        main.run();
    }
}