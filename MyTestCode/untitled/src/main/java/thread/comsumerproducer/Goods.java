package thread.comsumerproducer;

import java.util.Random;

public class Goods implements IGoods{

    public static IGoods produceOne() {
        return new Goods();
    }

    String name;
    public Goods() {
        name = "商品" + new Random().nextInt(100);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
