package nu.hex.calendar.core.calendar.ostea.format;

import nu.hex.calendar.core.calendar.CalendarYear;
import nu.hex.calendar.core.calendar.ostea.OsteanYearOLD;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class OsteanYearFormatter extends AbstractDateTimeFormatter<CalendarYear> {

    @Override
    public String format(CalendarYear year) {
        return zeroFill(year.getValue(), 1);
    }

    @Override
    public CalendarYear parse(CharSequence text) {
        if (text.toString().matches("\\d*")) {
            return OsteanYearOLD.of(Integer.valueOf(text.toString()));
        }
        String t = text.toString();
        String[] split = t.split(DATE_DELIMITER);
        return OsteanYearOLD.of(Integer.valueOf(split[0]));
    }
}
