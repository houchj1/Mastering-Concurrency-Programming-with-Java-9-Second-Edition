package javabasic;

public class ReturnInCatchException {

    static void testReturnInCatch(int a) {

        try {
            if (a == 1)
                throw new Exception("test exception for return");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("return in above catch will return the function, and skip this");
        System.out.println("this will compile error because of " +
                "line 10 return clause if try will always throw exception");
    }

    public static void main(String[] args) {
        testReturnInCatch(0);

        testReturnInCatch(1);
    }

}
