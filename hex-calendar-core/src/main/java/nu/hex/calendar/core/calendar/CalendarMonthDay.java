package nu.hex.calendar.core.calendar;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public interface CalendarMonthDay extends Comparable<CalendarMonthDay> {

    int getMonthValue();

    CalendarMonth getMonth();

    int getDay();

    boolean isValidYear(int year);

    CalendarMonthDay withMonth(int month);

    CalendarMonthDay withMonth(CalendarMonth month);

    CalendarMonthDay withDayOfMonth(int dayOfMonth);

    CalendarDate atYear(int year);

    boolean isAfter(CalendarMonthDay other);

    boolean isBefore(CalendarMonthDay other);

}
