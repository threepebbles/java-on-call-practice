package oncall.util;

public class Validator {
    public static void validateInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닙니다.");
        }
    }
}
