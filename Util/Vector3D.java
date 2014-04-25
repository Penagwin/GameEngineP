package Util;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by penagwin on 4/25/14.
 */
public class Vector3D {
        public float x;
        public float y;
        public float z;

        public Vector3D() {
            x = 0.0f;
            y = 0.0f;
            z = 0.0f;
        }
        public Vector3D(int nx, int ny, int nz){
            x = nx;
            y = ny;
            z = nz;
        }
    public Vector3D(Vector3f vect){
        x = vect.x;
        y = vect.y;
        z = vect.z;
    }
        // returns the (squared) distance between this Vector and another
        public float distSQ(final Vector3D vec) {
            float distX = x - vec.x;
            float distY = y - vec.y;
            float distZ = z - vec.z;
            return distX * distX + distY * distY * distZ * distZ;
        }
}
