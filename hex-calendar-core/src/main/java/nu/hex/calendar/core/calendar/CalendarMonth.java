package nu.hex.calendar.core.calendar;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public interface CalendarMonth {

    int firstDayOfYear(boolean leapYear);

    CalendarMonth firstMonthOfQuarter();

    String getDisplayName();

    int getValue();

    int maxLength();

    int minLength();

    int length(boolean leapYear);

    CalendarMonth plus(long months);

    CalendarMonth minus(long months);
}
