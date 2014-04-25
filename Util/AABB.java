package Util;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by penagwin on 4/25/14.
 */
public class AABB {
    public Vector3D center;
    public float r[];

    public AABB(final float width, final float height) {
        center = new Vector3D();
        r = new float[2];
        r[0] = width * 0.5f;
        r[1] = height * 0.5f;
    }

    public void update(final Vector3D position) {
        center.x = position.x;
        center.y = position.y;
    }
}