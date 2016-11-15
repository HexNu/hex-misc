package nu.hex.calendar.core.calendar.ostea.format;

import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;

/**
 * Created 2016-nov-02
 *
 * @author hl
 * @param <T>
 */
public abstract class AbstractDateTimeFormatter<T> implements CalendarDateTimeFormatter<T> {

    protected static final String DATE_DELIMITER = "-";

    protected static String zeroFill(int input, int numberOfDigits) {
        String result = String.valueOf(input);
        for (int i = result.length(); i < numberOfDigits; i++) {
            result = "0" + result;
        }
        return result;
    }

}
