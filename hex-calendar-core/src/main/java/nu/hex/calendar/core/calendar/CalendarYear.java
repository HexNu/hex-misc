package nu.hex.calendar.core.calendar;

import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public interface CalendarYear extends Comparable<CalendarYear> {

    CalendarDate atDay(int dayOfYear);

    CalendarYearMonth atMonth(int month);

    CalendarYearMonth atMonth(CalendarMonth month);

    CalendarDate atMonthDay(CalendarMonthDay monthDay);

    String format(CalendarDateTimeFormatter formatter);
    
    int getValue();
    
    boolean isAfter(CalendarYear other);

    boolean isBefore(CalendarYear other);

    boolean isLeap();

    int length();

    CalendarYear minus(long years);

    CalendarYear plus(long years);
}
