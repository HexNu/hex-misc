package nu.hex.calendar.core.calendar.erborigien.format;

import nu.hex.calendar.core.calendar.erborigien.ErborigianYear;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class ErborigianYearFormatter extends AbstractDateTimeFormatter<ErborigianYear> {

    @Override
    public String format(ErborigianYear year) {
        return zeroFill(year.getValue(), 1);
    }

    @Override
    public ErborigianYear parse(CharSequence text) {
        if (text.toString().matches("\\d*")) {
            return ErborigianYear.of(Integer.valueOf(text.toString()));
        }
        String t = text.toString();
        String[] split = t.split(DATE_DELIMITER);
        return ErborigianYear.of(Integer.valueOf(split[0]));
    }
}
