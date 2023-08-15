package javabasic;

public class StringEquals {

    public static void main(String[] args) {
        String s1= "aa";
        String s2 = "aa";
        System.out.println(s1 == s2);

        s1= "aa";
        s2 = new String("aa");
        System.out.println(s1 == s2);

        s1= new String("aa");
        s2 = new String("aa");
        System.out.println(s1 == s2);


        Integer int1 = 1000;
        Integer int2 = 1000;
        System.out.println(int1 == int2);

    }

}
