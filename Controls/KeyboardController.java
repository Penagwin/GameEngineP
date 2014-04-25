package Controls;

import Blocks.Block;
import Chunks.ChunkManager;
import Util.BlockIterator;
import Util.Vect3;
import Util.Vector3D;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import static Util.Log.println;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by penagwin on 4/22/14.
 */
public class KeyboardController {
    public Camera camera;
    float movementSpeed = 10.0f; //move 10 units per second
    public float movementmultiplayer = 0.05f;

    public KeyboardController(Camera ncamera) {
        camera = ncamera;
    }

    //Go over what keys do what
    public void checks() {
        if (Mouse.isButtonDown(0)) {
            Vector3D vector = new Vector3D(0, 0, 0);
            vector.y = ((float) -Math.sin(Math.toRadians(camera.pitch)));
            float v = (float) Math.cos(Math.toRadians(camera.pitch));
            vector.x = ((float) (Math.sin(Math.toRadians(camera.yaw)) * -v));
            vector.z = ((float) (Math.cos(Math.toRadians(camera.yaw)) * v));
            BlockIterator iter = new BlockIterator(vector, new Vector3D(camera.position), 0, 20);
            Block block = iter.next();
            ChunkManager.setBlockAt(block.x, block.y, block.z);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            camera.walkForward(movementSpeed * movementmultiplayer);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            camera.walkBackwards(movementSpeed * movementmultiplayer);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            camera.strafeLeft(movementSpeed * movementmultiplayer);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            camera.strafeRight(movementSpeed * movementmultiplayer);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            camera.walkUp(movementSpeed * movementmultiplayer);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            camera.walkDown(movementSpeed * movementmultiplayer);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            println(ChunkManager.blockcount());
        }
    }
}
