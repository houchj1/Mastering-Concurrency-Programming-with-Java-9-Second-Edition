package streambasic;

import java.util.List;

public class StreamCounting {

    static class Counting {
        int count = 0;
    }

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5);

        Counting myCounting = new Counting();
        list.stream().forEach(a -> {
            myCounting.count++;
        });
        System.out.println("count is " + myCounting.count);
    }

}
