package nu.hex.calendar.core.calendar.erborigien.format;

/**
 * Created 2016-nov-02
 *
 * @author hl
 * @param <T>
 */
abstract class AbstractDateTimeFormatter<T> implements ErborigianDateTimeFormatter<T> {

    protected static String zeroFill(int input, int numberOfDigits) {
        String result = String.valueOf(input);
        for (int i = result.length(); i < numberOfDigits; i++) {
            result = "0" + result;
        }
        return result;
    }
}
