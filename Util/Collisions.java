package Util;

/**
 * Created by penagwin on 4/25/14.
 */
public class Collisions {

    public static boolean testAABBAABB(final AABB box1, final AABB box2) {
        if (Math.abs(box1.center.x - box2.center.x) > (box1.r[0] + box2.r[0])) return false;
        if (Math.abs(box1.center.y - box2.center.y) > (box1.r[1] + box2.r[1])) return false;
        if (Math.abs(box1.center.z - box2.center.z) > (box1.r[2] + box2.r[2])) return false;
        return true;
    }
}