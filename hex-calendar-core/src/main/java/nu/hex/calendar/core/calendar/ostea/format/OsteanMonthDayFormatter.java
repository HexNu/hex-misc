package nu.hex.calendar.core.calendar.ostea.format;

import nu.hex.calendar.core.calendar.CalendarMonthDay;
import nu.hex.calendar.core.calendar.ostea.OsteanMonthDayOLD;

/**
 * Created 2016-nov-02
 *
 * @author hl
 */
public class OsteanMonthDayFormatter extends AbstractDateTimeFormatter<CalendarMonthDay> {

    @Override
    public String format(CalendarMonthDay date) {
        return DATE_DELIMITER + DATE_DELIMITER + zeroFill(date.getMonthValue(), 2) + DATE_DELIMITER + zeroFill(date.getDay(), 2);
    }

    @Override
    public CalendarMonthDay parse(CharSequence text) {
        String t = text.toString();
        while (t.startsWith(DATE_DELIMITER)) {
            t = t.substring(1);
        }
        String[] split = t.split(DATE_DELIMITER);
        return OsteanMonthDayOLD.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }
}
