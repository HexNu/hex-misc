package nu.hex.calendar.core.calendar.erborigien.format;

import nu.hex.calendar.core.calendar.erborigien.ErborigianDate;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class ErborigianDateFormatter extends AbstractDateTimeFormatter<ErborigianDate> {

    @Override
    public String format(ErborigianDate date) {
        return zeroFill(date.getYear(), 1) + DATE_DELIMITER + zeroFill(date.getMonthValue(), 2) + DATE_DELIMITER + zeroFill(date.getDayOfMonth(), 2);
    }

    @Override
    public ErborigianDate parse(CharSequence text) {
        String t = text.toString();
        String[] split = t.split(DATE_DELIMITER);
        return ErborigianDate.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
    }
}
