package nu.hex.calendar.core.calendar.ostea;

import java.time.DateTimeException;
import nu.hex.calendar.core.calendar.CalendarDayOfWeek;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public enum OsteanDayOfWeekOLD implements CalendarDayOfWeek {

    GNYD,
    SLUNN,
    GLAK,
    LLASS,
    NIRA,
    KRAG,
    ODIM;

    private static final OsteanDayOfWeekOLD[] ENUMS = OsteanDayOfWeekOLD.values();

    public static CalendarDayOfWeek of(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > ENUMS.length) {
            throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
        }
        return ENUMS[dayOfWeek - 1];
    }

    @Override
    public int getValue() {
        return ordinal() + 1;
    }

    @Override
    public String getDisplayName() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    @Override
    public CalendarDayOfWeek minus(long days) {
        return plus(-(days % ENUMS.length));
    }

    @Override
    public CalendarDayOfWeek plus(long days) {
        int amount = (int) (days % ENUMS.length);
        return ENUMS[(ordinal() + (amount + ENUMS.length)) % ENUMS.length];
    }
}
