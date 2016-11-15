package nu.hex.calendar.core.calendar.erborigien.format;

import nu.hex.calendar.core.calendar.erborigien.ErborigianMonthDay;

/**
 * Created 2016-nov-02
 *
 * @author hl
 */
public class ErborigianMonthDayFormatter extends AbstractDateTimeFormatter<ErborigianMonthDay> {

    @Override
    public String format(ErborigianMonthDay date) {
        return DATE_DELIMITER + DATE_DELIMITER + zeroFill(date.getMonthValue(), 2) + DATE_DELIMITER + zeroFill(date.getDay(), 2);
    }

    @Override
    public ErborigianMonthDay parse(CharSequence text) {
        String t = text.toString();
        while (t.startsWith(DATE_DELIMITER)) {
            t = t.substring(1);
        }
        String[] split = t.split(DATE_DELIMITER);
        return ErborigianMonthDay.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }
}
