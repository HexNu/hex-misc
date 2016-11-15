package nu.hex.calendar.core.calendar.ostea;

import java.time.DateTimeException;
import nu.hex.calendar.core.calendar.CalendarMonth;
import nu.hex.calendar.core.calendar.exception.CalendarDateTimeException;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public enum OsteanMonthOLD implements CalendarMonth {
    VLETIM,
    TOTES,
    UCAPS,
    CALIPS,
    JIMN,
    LLULK,
    JILM,
    HOLOT,
    TROVA,
    HUSK,
    DEMEK,
    GRAG;
    private static final OsteanMonthOLD[] ENUMS = OsteanMonthOLD.values();

    @Override
    public int firstDayOfYear(boolean leapYear) {
        int leap = leapYear ? 1 : 0;
        switch (this) {
            case VLETIM:
                return 1;
            case TOTES:
                return 32;
            case UCAPS:
                return 60 + leap;
            case CALIPS:
                return 91 + leap;
            case JIMN:
                return 121 + leap;
            case LLULK:
                return 152 + leap;
            case JILM:
                return 182 + leap;
            case HOLOT:
                return 213 + leap;
            case TROVA:
                return 244 + leap;
            case HUSK:
                return 274 + leap;
            case DEMEK:
                return 305 + leap;
            case GRAG:
            default:
                return 335 + leap;
        }
    }

    @Override
    public CalendarMonth firstMonthOfQuarter() {
        return ENUMS[(ordinal() / 3) * 3];
    }

    public static OsteanMonthOLD of(int month) {
        if (isValid(month)) {
            throw new DateTimeException("Invalid value for MonthOfYear: " + month);
        }
        return ENUMS[month - 1];
    }

    public static boolean isValid(int month) {
        return month < 1 || month > ENUMS.length;
    }

    @Override
    public String getDisplayName() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    @Override
    public int getValue() {
        return ordinal() + 1;
    }

    @Override
    public int maxLength() {
        switch (this) {
            case TOTES:
                return 29;
            case CALIPS:
            case LLULK:
            case TROVA:
            case DEMEK:
                return 30;
            default:
                return 31;
        }
    }

    @Override
    public int minLength() {
        switch (this) {
            case TOTES:
                return 28;
            case CALIPS:
            case LLULK:
            case TROVA:
            case DEMEK:
                return 30;
            default:
                return 31;
        }
    }

    @Override
    public int length(boolean leapYear) {
        switch (this) {
            case TOTES:
                return (leapYear ? 29 : 28);
            case CALIPS:
            case LLULK:
            case TROVA:
            case DEMEK:
                return 30;
            default:
                return 31;
        }
    }

    @Override
    public OsteanMonthOLD plus(long months) {
        int amount = (int) (months % ENUMS.length);
        return ENUMS[(ordinal() + (amount + ENUMS.length)) % ENUMS.length];
    }

    @Override
    public OsteanMonthOLD minus(long months) {
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
