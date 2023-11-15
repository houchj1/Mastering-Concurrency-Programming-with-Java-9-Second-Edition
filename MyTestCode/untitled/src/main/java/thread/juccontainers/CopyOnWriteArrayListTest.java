package thread.juccontainers;

import org.openjdk.jol.vm.VM;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * JUC page 320, test CopyOnWriteArrayList
 *
 */
public class CopyOnWriteArrayListTest extends CopyOnWriteArrayList{


    static Object getArray(CopyOnWriteArrayList cowList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = CopyOnWriteArrayList.class.getDeclaredMethod("getArray");
        m.setAccessible(true);
        Object arr = m.invoke(cowList);
        return arr;
    }
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        CopyOnWriteArrayList cowList = new CopyOnWriteArrayList();
        cowList.addAll(list);

        System.out.println(cowList.hashCode());
        System.out.println("cowList address before write:" + VM.current().addressOf(cowList));
        System.out.println("cowList.array address before write:" + VM.current().addressOf(getArray(cowList)));

//        cowList.add(7);
        cowList.remove(0);

        System.out.println("cowList address after write:" + VM.current().addressOf(cowList));
        System.out.println("cowList.array address after write:" + VM.current().addressOf(getArray(cowList)));
        System.out.println(cowList.hashCode());
        System.out.println(cowList);

        /**
         *TODO: after write to the cowList, the hashCode of the cowList changed
         * but interestingly the address is not changed. Why? The answer is that it changed the internal array address
         * FOLLOWING example of String, the address is changed, that's correct for COW concept
         */

        String a = "before";
        System.out.println("string address before write:" + VM.current().addressOf(a));

        a = "after";
        System.out.println("string address after write:" + VM.current().addressOf(a));



    }


}
