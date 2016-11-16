package nu.hex.calendar.core.calendar.rpg;

import java.time.LocalDate;

/**
 * Created 2016-nov-15
 *
 * @author hl
 */
public interface RpgDate {

    public static final int AGLIAN_DIFF = 531;
    public static final int OSTEAN_DIFF = 1087;
    public static final String AGLIAN_CALENDAR = "Aglian Calendar";
    public static final String OSTEAN_CALENDAR = "Ostean Calendar";
    public static final String[] CALENDARS = new String[]{AGLIAN_CALENDAR, OSTEAN_CALENDAR};

    LocalDate get();

    LocalDate now();

    LocalDate of(int year, int month, int dayOfMonth);

    LocalDate parse(String text);

    String getDate();

    String getCalendarName();

    String getShortCalendarName();
}
