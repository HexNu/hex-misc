package nu.hex.calendar.core.calendar;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public interface CalendarDayOfWeek {

    int getValue();

    String getDisplayName();

    CalendarDayOfWeek minus(long days);

    CalendarDayOfWeek plus(long days);
}
