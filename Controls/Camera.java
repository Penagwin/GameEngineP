package Controls;

import org.lwjgl.util.vector.Vector3f;

import static Controls.PlayerCollisions.checkPlayer;

/**
 * Created by penagwin on 4/22/14.
 */
public class Camera {
    //3d vector to store the camera's position in
    public static float cpitch = 0f;
    public static Vector3f position = null;
    //the rotation around the Y axis of the camera
    public static float yaw = 0.0f;
    //the rotation around the X axis of the camera
    public static float pitch = 0.0f;
    public static float oldpitch = 0.0f;

    //Constructor that takes the starting x, y, z location of the camera
    public Camera(float x, float y, float z) {
        position = new Vector3f(x, y-5, z);
    }

    public Camera() { // This is made so the line FPCameraController app = new FPCameraController(); will work
    }

    public void checkmovement() {

    }

    //Controls for Mouse and Keyboard down here!
    public void yaw(float amount) {
        //increment the yaw by the amount param
        yaw += amount;
    }
    public void pitch(float amount) {
        //increment the pitch by the amount param
        pitch += amount;
    }
    public void walkForward(float distance) {
        if(checkPlayer(this)) {
            position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
            position.z += distance * (float) Math.cos(Math.toRadians(yaw));
        }
    }
    public void walkDown(float distance) {
        position.y -= distance;
    }
    public void walkUp(float distance) {
        position.y += distance;
    }
    public void walkBackwards(float distance) {
        position.x += distance * (float) Math.sin(Math.toRadians(yaw));
        position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
    }
    public void strafeLeft(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));
    }
    public void strafeRight(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
    }
}
