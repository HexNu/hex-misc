package nu.hex.calendar.core.calendar.sample.local;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import nu.hex.calendar.core.calendar.rpg.old.Day;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class StandardDay implements Day {

    @Override
    public Integer getNumberOfHours() {
        return 24;
    }

    @Override
    public void setNumberOfHours(Integer numberOfHours) {
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
//        date.getDayOfWeek().
        Year year = Year.of(2016);
        YearMonth yearMonth = year.atMonth(Month.MARCH);
        
        Integer a = 10;
        Integer b = 5;
        System.out.println(a.compareTo(b));
//        yearMonth.
//        date.format(DateTimeFormatter.ISO_DATE);
    }
}
