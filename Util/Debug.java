package Util;


/**
 * Created by penagwin on 3/31/14.
 * Visit dev.penagw.in
 * Email paul@penagw.in
 */
public class Debug {
    public static Boolean debug = true;


    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        if (debug)
            System.out.println(o);
    }


}
