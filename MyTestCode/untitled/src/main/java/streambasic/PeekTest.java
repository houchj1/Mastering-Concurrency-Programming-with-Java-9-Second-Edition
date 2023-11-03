package streambasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeekTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 10, 3, 7, 5);

        Integer ret = list.stream().peek( num -> System.out.print(num + " "))
                .filter(x -> x > 5)
                .findAny()
                .get();

        System.out.println(ret);

    }
}
