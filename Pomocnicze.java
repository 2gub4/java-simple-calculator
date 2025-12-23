import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pomocnicze {

    public static boolean isNumeric(String str) {
        if (str.equals("0")) { return true; }
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumber(String text) {
        return (text.contains("-") && text.length() > 1) || Pomocnicze.isNumeric(text);
    }

    public static String format(double value) {
        if (value == (long) value)
            return Long.toString((long) value);
        return new BigDecimal(value)
                .setScale(8, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }
}
