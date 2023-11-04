package thread.threadbasic;

public class RunnableTest {

    static int threadNo = 1;
    final String testFinal = "TestFinalString";

    String testNonFinal = "TestNonFinal";

    public static void main(String[] args) {

        final String testlocalFinal = "localFinalString";

        String testLocalNonFinal = "testLocalNonFinal";

        Thread test = new Thread(() -> {
            System.out.println("Thread No is " + threadNo);
            System.out.println("Thread test final string is " + new RunnableTest().testFinal);
            System.out.println("Thread test Non final string is " + new RunnableTest().testNonFinal);

            System.out.println("Thread test final string is " + testlocalFinal);

            System.out.println("Thread test Non final string is " + testLocalNonFinal);

            System.out.println("Thread name is " + Thread.currentThread().getName());

        }, "MyRunnableTestThread");

        test.start();
    }

}
