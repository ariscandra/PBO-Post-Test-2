package util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidasiUtil {
    public static boolean hanyaHurufDanSpasi(String input) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean hanyaAngka(String input) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}