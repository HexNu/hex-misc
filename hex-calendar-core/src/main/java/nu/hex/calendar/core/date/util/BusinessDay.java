package nu.hex.calendar.core.date.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author hl
 */
public class BusinessDay {

    private Map<LocalDate, String> bankHolidays;

    public boolean isBusinessDay(LocalDate date) {
        bankHolidays = new BankHoliday(date.getYear()).get();
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return false;
        } else if (bankHolidays.containsKey(date)) {
            return false;
        }
        return true;
    }

    public LocalDate getFirstBusinessDay(LocalDate date) {
        if (isBusinessDay(date)) {
            return date;
        } else {
            return getFirstBusinessDay(date.plusDays(1l));
        }
    }
//
//    public static void main(String[] args) {
//        BusinessDayChecker businessDayChecker = new BusinessDayChecker();
//        for (int i = 1964; i <= 2064; i++) {
//            LocalDate date = LocalDate.parse(i + "-10-02");
//            System.out.print(businessDayChecker.getFirstBusinessDay(date) + " ");
//            System.out.println(businessDayChecker.getFirstBusinessDay(date).getDayOfWeek().getDisplayName(TextStyle.FULL, DateUtilConstants.LOCALE));
//        }
//
//    }
}
