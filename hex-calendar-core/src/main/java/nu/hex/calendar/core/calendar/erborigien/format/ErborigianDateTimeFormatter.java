package nu.hex.calendar.core.calendar.erborigien.format;

/**
 * Created 2016-nov-02
 *
 * @author hl
 * @param <T>
 */
public interface ErborigianDateTimeFormatter<T> {

    public static final ErborigianDateTimeFormatter DATE = new ErborigianDateFormatter();
    public static final ErborigianDateTimeFormatter MONTH_DAY = new ErborigianMonthDayFormatter();
    public static final ErborigianDateTimeFormatter YEAR = new ErborigianYearFormatter();
    public static final ErborigianDateTimeFormatter VERBOSE_DATE = new ErborigianVerboseDateFormatter();
    static final String DATE_DELIMITER = "-";

    String format(T date);

    T parse(CharSequence text);
}
