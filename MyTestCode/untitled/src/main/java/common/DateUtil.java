package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getNowTime() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        String s = f.format(new Date());
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getNowTime());
    }
}
