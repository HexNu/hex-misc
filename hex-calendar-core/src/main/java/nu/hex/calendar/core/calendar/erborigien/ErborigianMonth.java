package nu.hex.calendar.core.calendar.erborigien;

import java.time.DateTimeException;
import java.util.Locale;
import nu.hex.calendar.core.calendar.exception.CalendarDateTimeException;
import nu.hex.calendar.core.calendar.format.CalendarTextStyle;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public enum ErborigianMonth {
    VETRI,
    SKARETID,
    HAVATING,
    FÖRSTESÅDD,
    SUNNIFANG,
    SUMRI,
    TYSTETID,
    LIETING,
    LÖVBRAND,
    VÄTTEVIND,
    STARRFROST,
    YOLATID;
    private static final Locale DEFAULT_LOCALE = new Locale("sv");
    private static final ErborigianMonth[] ENUMS = ErborigianMonth.values();

    public int firstDayOfYear(boolean leapYear) {
        int leap = leapYear ? 6 : 0;
        switch (this) {
            case VETRI:
                return 1;
            case SKARETID:
                return 32;
            case HAVATING:
                return 60;
            case FÖRSTESÅDD:
                return 91;
            case SUNNIFANG:
                return 121;
            case SUMRI:
                return 152;
            case TYSTETID:
                return 182 + leap;
            case LIETING:
                return 213 + leap;
            case LÖVBRAND:
                return 244 + leap;
            case VÄTTEVIND:
                return 274 + leap;
            case STARRFROST:
                return 305 + leap;
            case YOLATID:
            default:
                return 335 + leap;
        }
    }

    public ErborigianMonth firstMonthOfQuarter() {
        return ENUMS[(ordinal() / 3) * 3];
    }

    public static ErborigianMonth of(int month) {
        if (isValid(month)) {
            throw new DateTimeException("Invalid value for MonthOfYear: " + month);
        }
        return ENUMS[month - 1];
    }

    public static boolean isValid(int month) {
        return month < 1 || month > ENUMS.length;
    }

    public String getDisplayName(CalendarTextStyle textStyle) {
        String fullName = name().toLowerCase(DEFAULT_LOCALE);
        String shortName = fullName.substring(0, 5);
        switch (this) {
            case HAVATING:
            case TYSTETID:
                shortName = shortName.substring(0, 4);
                break;
            case LIETING:
            case LÖVBRAND:
            case YOLATID:
                shortName = shortName.substring(0, 3);
        }
        switch (textStyle) {
            case NARROW:
                return fullName.substring(0, 3);
            case NARROW_STANDALONE:
                return fullName.substring(0, 1).toUpperCase(DEFAULT_LOCALE) + fullName.substring(1, 3);
            case SHORT:
                return shortName;
            case SHORT_STANDALONE:
                return shortName.substring(0, 1).toUpperCase(DEFAULT_LOCALE) + shortName.substring(1);
            case FULL:
                return fullName;
            case FULL_STANDALONE:
            default:
                return fullName.substring(0, 1).toUpperCase(DEFAULT_LOCALE) + fullName.substring(1);
        }
    }

    public String getDisplayName() {
        return getDisplayName(CalendarTextStyle.FULL);
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public int maxLength() {
        switch (this) {
            case SKARETID:
                return 28;
            case SUMRI:
                return 36;
            case FÖRSTESÅDD:
            case LÖVBRAND:
            case STARRFROST:
                return 30;
            default:
                return 31;
        }
    }

    public int minLength() {
        switch (this) {
            case SKARETID:
                return 28;
            case SUMRI:
            case FÖRSTESÅDD:
            case LÖVBRAND:
            case STARRFROST:
                return 30;
            default:
                return 31;
        }
    }

    public int length(boolean leapYear) {
        switch (this) {
            case SKARETID:
                return 28;
            case SUMRI:
                return (leapYear ? 36 : 30);
            case FÖRSTESÅDD:
            case LÖVBRAND:
            case STARRFROST:
                return 30;
            default:
                return 31;
        }
    }

    public ErborigianMonth plus(long months) {
        int amount = (int) (months % ENUMS.length);
        return ENUMS[(ordinal() + (amount + ENUMS.length)) % ENUMS.length];
    }

    public ErborigianMonth minus(long months) {
        return plus(-(months % ENUMS.length));
    }

    public static void validate(int month) {
        if (!isValid(month)) {
            throw new CalendarDateTimeException("Month " + month + " is out of range");
        }
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
