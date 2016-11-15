package nu.hex.calendar.core.calendar;

import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public interface CalendarYearMonth extends Comparable<CalendarYearMonth> {

    String format(CalendarDateTimeFormatter formatter);

    CalendarMonth getMonth();

    int getMonthValue();

    int getYear();

    boolean isLeapYear();

    boolean isValidDay(int dayOfMonth);

    int lengthOfMonth();

    int lengthOfYear();

    CalendarYearMonth minusMonths(int monthsToSubtract);

    CalendarYearMonth minusYears(int yearsToSubtract);

    CalendarYearMonth plusMonths(int monthsToAdd);

    CalendarYearMonth plusYears(int yearsToAdd);

    CalendarDate atDay(int dayOfMonth);

    CalendarDate atEndOfMonth();
}
