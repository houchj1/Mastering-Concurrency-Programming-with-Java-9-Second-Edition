package streambasic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        try {
            list.stream().collect(
                    Collectors.maxBy(    /** the Comparator object by lambda  */
                            (s1, s2) -> s1.length() - s2.length()
                    )
            ).get();   /** @see Optional.get(), if no value in the optional object, it will throw exception */
        } catch (RuntimeException e) {
            System.out.println("Expect to throw NoSuchElementException");
        }

        list.stream().collect(
                Collectors.maxBy(
                        (s1, s2) -> s1.length() - s2.length()
                )
        ).orElse("No value");    /** @see Optional.orElse()   */



    }
}
