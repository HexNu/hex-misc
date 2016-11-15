package nu.hex.calendar.core.calendar;

import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;

/**
 * Created 2016-okt-31
 *
 * @author hl
 */
public interface CalendarDate extends Comparable<CalendarDate> {

    int getDayOfMonth();

    CalendarDayOfWeek getDayOfWeek();

    int getDayOfYear();

    CalendarMonth getMonth();

    int getMonthValue();

    int getYear();

    long toEpochDay();

    boolean isLeapYear();

    String format();

    String format(CalendarDateTimeFormatter formatter);

    boolean isAfter(CalendarDate other);

    boolean isBefore(CalendarDate other);

    boolean isEqual(CalendarDate other);

    int lengthOfMonth();

    int lengthOfYear();

    CalendarDate withYear(int year);

    CalendarDate withMonth(int month);

    CalendarDate withDayOfMonth(int dayOfMonth);

    CalendarDate minusDays(long daysToSubtract);

    CalendarDate minusMonths(long monthsToSubtract);

    CalendarDate minusWeeks(long weeksToSubtract);

    CalendarDate minusYears(long yearsToSubtract);

    CalendarDate plusDays(long daysToSubtract);

    CalendarDate plusMonths(long monthsToSubtract);

    CalendarDate plusWeeks(long weeksToSubtract);

    CalendarDate plusYears(long yearsToSubtract);

//        System.out.println(date.getDayOfMonth());
//        System.out.println(date.getDayOfWeek());
//        System.out.println(date.getDayOfYear());
//        System.out.println(date.getEra());
//        System.out.println(date.getMonth());
//        System.out.println(date.getMonthValue());
//        System.out.println(date.getYear());
}
