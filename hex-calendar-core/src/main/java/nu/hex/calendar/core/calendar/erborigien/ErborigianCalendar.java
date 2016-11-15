package nu.hex.calendar.core.calendar.erborigien;

import java.util.Locale;
import nu.hex.calendar.core.calendar.erborigien.format.ErborigianDateTimeFormatter;

/**
 * Created 2016-nov-03
 *
 * @author hl
 */
public class ErborigianCalendar {
    public static final Locale DEFAULT_LOCALE = new Locale("sv");
    public ErborigianDayOfWeek weekStartsOn() {
        return ErborigianDayOfWeek.MÅNENS_DAG;
    }

    public ErborigianDate now() {
        return ErborigianDate.now();
    }

    public static void main(String[] args) {
        System.out.println(new ErborigianCalendar().now());
        System.out.println(ErborigianDate.of(1, 1, 1).format(ErborigianDateTimeFormatter.DATE));
        System.out.println(ErborigianDate.of(1, 1, 1).format(ErborigianDateTimeFormatter.VERBOSE_DATE));
        System.out.println(ErborigianDate.of(650, 6, 36).format(ErborigianDateTimeFormatter.VERBOSE_DATE));
        System.out.println(ErborigianDate.now().format(ErborigianDateTimeFormatter.VERBOSE_DATE));
        System.out.println(ErborigianDate.of(1, 1, 1).getDayOfWeek());
    }
}
