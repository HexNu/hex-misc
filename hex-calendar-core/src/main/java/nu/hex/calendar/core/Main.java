package nu.hex.calendar.core;

import nu.hex.calendar.core.calendar.rpg.old.Month;
import nu.hex.calendar.core.calendar.rpg.old.Week;
import nu.hex.calendar.core.calendar.rpg.old.WeekDay;
import nu.hex.calendar.core.calendar.rpg.old.Year;
import nu.hex.calendar.core.calendar.sample.fictive.FictiveMonth;
import nu.hex.calendar.core.calendar.sample.fictive.FictiveWeek;
import nu.hex.calendar.core.calendar.sample.fictive.FictiveWeekDay;
import nu.hex.calendar.core.calendar.sample.fictive.FictiveYear;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
        
//        tryOut();
    }

    public static void tryOut() {
        WeekDay day1 = new FictiveWeekDay(0, "Monkeyday");
        WeekDay day2 = new FictiveWeekDay(1, "Tryday");
        WeekDay day3 = new FictiveWeekDay(2, "Whenday");
        WeekDay day4 = new FictiveWeekDay(3, "Thurstyday");
        WeekDay day5 = new FictiveWeekDay(4, "Freeday");
        WeekDay day6 = new FictiveWeekDay(5, "Stunday");
        WeekDay day7 = new FictiveWeekDay(6, "Septday");
        WeekDay day8 = new FictiveWeekDay(7, "Octoday");
        Week fw = new FictiveWeek();
        fw.addWeekDay(day1);
        fw.addWeekDay(day2);
        fw.addWeekDay(day3);
        fw.addWeekDay(day4);
        fw.addWeekDay(day5);
        fw.addWeekDay(day6);
        fw.addWeekDay(day7);
        fw.addWeekDay(day8);
        Month month1 = new FictiveMonth();
        month1.setIndex(0);
        month1.setName("Apemonth");
        month1.setNumberOfDays(25);
        
        Month month2 = new FictiveMonth();
        month2.setIndex(1);
        month2.setName("Badwindmonth");
        month2.setNumberOfDays(26);
        
        Month month3 = new FictiveMonth();
        month3.setIndex(2);
        month3.setName("Cyclemonth");
        month3.setNumberOfDays(24);
        
        Month month4 = new FictiveMonth();
        month4.setIndex(3);
        month4.setName("Dungmonth");
        month4.setNumberOfDays(26);
        
        Month month5 = new FictiveMonth();
        month5.setIndex(4);
        month5.setName("Everymonth");
        month5.setNumberOfDays(25);
        
        Month month6 = new FictiveMonth();
        month6.setIndex(5);
        month6.setName("Floodmonth");
        month6.setNumberOfDays(25);
        
        Month month7 = new FictiveMonth();
        month7.setIndex(6);
        month7.setName("GladMonth");
        month7.setNumberOfDays(26);
        
        Month month8 = new FictiveMonth();
        month8.setIndex(7);
        month8.setName("Highmonth");
        month8.setNumberOfDays(25);
        
        Month month9 = new FictiveMonth();
        month9.setIndex(8);
        month9.setName("Invermonth");
        month9.setNumberOfDays(26);
        
        Month month10 = new FictiveMonth();
        month10.setIndex(9);
        month10.setName("Jugglemonth");
        month10.setNumberOfDays(25);
        
        Year fictiveYear = new FictiveYear(fw);
        fictiveYear.addMonth(month1);
        fictiveYear.addMonth(month2);
        fictiveYear.addMonth(month3);
        fictiveYear.addMonth(month4);
        fictiveYear.addMonth(month5);
        fictiveYear.addMonth(month6);
        fictiveYear.addMonth(month7);
        fictiveYear.addMonth(month8);
        fictiveYear.addMonth(month9);
        fictiveYear.addMonth(month10);
        System.out.println(fictiveYear.getNumberOfDays());
        System.out.println(fictiveYear.getNumberOfWeeks());
        System.out.println(fictiveYear.getMonths().get(2).getName());
    }
}
