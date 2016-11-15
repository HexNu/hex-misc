package nu.hex.calendar.core.calendar.erborigien;

import java.time.DateTimeException;
import java.util.Locale;
import nu.hex.calendar.core.calendar.format.CalendarTextStyle;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public enum ErborigianDayOfWeek {

    MÅNENS_DAG,
    TYRS_DAG,
    ODENS_DAG,
    TORS_DAG,
    FREJS_DAG,
    LÖGARDAGEN,
    SOLENS_DAG;
    private static final Locale DEFAULT_LOCALE = new Locale("sv");
    private static final ErborigianDayOfWeek[] ENUMS = ErborigianDayOfWeek.values();

    public static ErborigianDayOfWeek of(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > ENUMS.length) {
            throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
        }
        return ENUMS[dayOfWeek - 1];
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getDisplayName(CalendarTextStyle textStyle) {
        String fullName = name().toLowerCase(DEFAULT_LOCALE).replaceAll("_", "\u00A0");
        String shortName = fullName.substring(0, name().startsWith("OD") || name().startsWith("FR") ? 4 : 3);
        String narrowName = fullName.substring(0, 2);
        switch (textStyle) {
            case NARROW:
                return narrowName;
            case NARROW_STANDALONE:
                return narrowName.substring(0, 1).toUpperCase(DEFAULT_LOCALE) + narrowName.substring(1);
            case SHORT:
                return shortName;
            case SHORT_STANDALONE:
                return shortName.substring(0, 1).toUpperCase(DEFAULT_LOCALE) + shortName.substring(1);
            case FULL_STANDALONE:
                return fullName.substring(0, 1).toUpperCase(DEFAULT_LOCALE) + fullName.substring(1);
            case FULL:
            default:
                return fullName;
        }
    }

    public String getDisplayName() {
        return getDisplayName(CalendarTextStyle.FULL);
    }

    public ErborigianDayOfWeek minus(long days) {
        return plus(-(days % ENUMS.length));
    }

    public ErborigianDayOfWeek plus(long days) {
        int amount = (int) (days % ENUMS.length);
        return ENUMS[(ordinal() + (amount + ENUMS.length)) % ENUMS.length];
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

}
