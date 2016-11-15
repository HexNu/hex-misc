package nu.hex.calendar.core.calendar.ostea;

import java.time.LocalDate;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(OsteanDateOLD.now().minusYears(18));
        System.out.println(LocalDate.now().minusYears(531));
        for (OsteanMonthOLD m : OsteanMonthOLD.values()) {
            System.out.println(m.getValue() + " " + m.getDisplayName());
        }
//        String pattern = "[A-Z][A-Z\\d]*(_([A-Z\\d]*_?))*[A-Z\\d]+";
//        String a = "DAYS_0000_TO_1970";
//        System.out.println(a.matches(pattern));
//        String b;
//        b = "DAYS";
//        System.out.println(b.matches(pattern));
//        b = "DAYS_TO_GO";
//        System.out.println(b.matches(pattern));
//        b = "11_DAYS_TO_GO";
//        System.out.println(b.matches(pattern));
        
//        CalendarDate date = OsteanDate.now();
//        System.out.println(date.getDayOfMonth());
//        System.out.println(date.getDayOfWeek().getDisplayName());
//        System.out.println(date.getMonth().getDisplayName());
//        System.out.println(date.getYear());
//        System.out.println(date.format(CalendarDateTimeFormatter.OSTEAN_DATE));
    }
}
