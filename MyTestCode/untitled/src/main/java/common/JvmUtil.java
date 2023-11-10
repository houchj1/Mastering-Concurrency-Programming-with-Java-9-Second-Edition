package common;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * JUC page 161, for the Unsafe actions
 */
public class JvmUtil {

    public static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
