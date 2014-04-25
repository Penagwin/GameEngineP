package Util;

/**
 * Created by penagwin on 4/25/14.
 */
public class Vector2D {
    public float x;
    public float y;

    public Vector2D() {
        x = 0.0f;
        y = 0.0f;
    }

    // returns the (squared) distance between this Vector and another
    public float distSQ(final Vector2D vec) {
        float distX = x - vec.x;
        float distY = y - vec.y;

        return distX * distX + distY * distY;
    }
}