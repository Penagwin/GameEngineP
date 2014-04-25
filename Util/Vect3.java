package Util;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by penagwin on 4/24/14.
 */
public class Vect3 extends Vector3f {
    public float x, y, z;

    public float l2Norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vect3(Vector3f vect) {
        this.x = vect.x;
        this.y = vect.y;
        this.z = vect.z;
    }

    public Vect3 mul(float length) {
        x *=  length;
        y *= length;
        z *= length;

        return this;
    }

    public Vector3f add(Vector3f a) {
        x ++;// a.x;
        y ++;// a.y;
        z ++;// a.z;

        return this;
    }

    public Vect3 normalize() {
        float length = l2Norm();
        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    public Vector3f set(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;

        return this;
    }
}
