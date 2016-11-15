package nu.hex.calendar.core.calendar.format;

import nu.hex.calendar.core.calendar.ostea.format.OsteanDateFormatter;
import nu.hex.calendar.core.calendar.ostea.format.OsteanMonthDayFormatter;
import nu.hex.calendar.core.calendar.ostea.format.OsteanYearFormatter;

/**
 * Created 2016-okt-31
 *
 * @author hl
 * @param <T>
 */
public interface CalendarDateTimeFormatter<T> {

    public static final CalendarDateTimeFormatter OSTEAN_DATE = new OsteanDateFormatter();
    public static final CalendarDateTimeFormatter OSTEAN_YEAR = new OsteanYearFormatter();
    public static final CalendarDateTimeFormatter OSTEAN_MONTH_DAY = new OsteanMonthDayFormatter();

    String format(T date);

    T parse(CharSequence text);
}
