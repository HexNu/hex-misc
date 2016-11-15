package nu.hex.calendar.core.calendar.ostea.format;

import nu.hex.calendar.core.calendar.CalendarDate;
import nu.hex.calendar.core.calendar.ostea.OsteanDateOLD;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class OsteanDateFormatter extends AbstractDateTimeFormatter<CalendarDate> {

    @Override
    public String format(CalendarDate date) {
        return zeroFill(date.getYear(), 1) + DATE_DELIMITER + zeroFill(date.getMonthValue(), 2) + DATE_DELIMITER + zeroFill(date.getDayOfMonth(), 2);
    }

    @Override
    public CalendarDate parse(CharSequence text) {
        String t = text.toString();
        String[] split = t.split(DATE_DELIMITER);
        return OsteanDateOLD.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
    }
}
