package Controls;

import org.lwjgl.input.Keyboard;

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
    }
}
