package javabasic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示了 Effectively Final（只有在局部变量才有Effectively Final，其他情况没有）
 * 1. 只有局部变量有这个effective final概念，其他类型的变量没有
 * 2. 只有在lambda里面修改局部变量，才会触发著名的提示： Variable used in lambda expression should be final or effectively final, ConvertTo AtomicInteger
 *    不修改，只读这个变量的话，是不会提示的，意味着Lambda里面可以只读任何变量（只要有一般权限），但是修改的话必须是Effectively Final
 *
 *  TODO: 但是下面的MyTestClass里面的成员变量（不是局部变量）却可以在Lambda里面使用testMemberCount1++; ？why 不是局部变量才有Effectively Final吗？
 */
public class LambdaAccessScope {

    static int staticMemberCount = 0;
    public static void main(String[] args) throws InterruptedException {
        //局部变量不是effectively final，所以如果没有final修饰的话是不可以用在lambda里面的
        int count = 0;
        Thread t1 = new Thread(()-> {
            //@see https://blog.csdn.net/llainannan/article/details/135988718
            count++;  //Variable used in lambda expression should be final or effectively final, ConvertTo AtomicInteger
            System.out.println(count);
            args[0] = "";
            System.out.println(args[0]);
            staticMemberCount++;
            System.out.println("in thread");
        }, ""+count++);
        staticMemberCount++;

        t1.start();
//        t1.join();

        System.out.println("in main thread");

        MyTestClass testClass = new MyTestClass();
    }
}

class MyTestClass {

    int testMemberCount1=0;
    public void test(String[] args, String arg1, int arg2, AtomicInteger arg3){
        int arg2Copy = arg2; //TODO：网上的文章是错的，原始类型的局部变量不是Effectively Final，即使是方法参数的
        Thread t1 = new Thread(() -> {
            System.out.println(args);
            System.out.println(arg1);
            System.out.println(arg2);

            args[0] = "changes";
//            arg2++;
            arg2Copy++;
            System.out.println(arg2Copy);
            arg3.getAndIncrement();

            System.out.println(testMemberCount1);
            testMemberCount1++;
        });
    }
}
