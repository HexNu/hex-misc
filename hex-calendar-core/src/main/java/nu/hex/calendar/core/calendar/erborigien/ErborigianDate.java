package nu.hex.calendar.core.calendar.erborigien;

import java.time.LocalDate;
import java.util.Objects;
import nu.hex.calendar.core.calendar.erborigien.format.ErborigianDateTimeFormatter;
import nu.hex.calendar.core.calendar.exception.CalendarDateTimeException;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class ErborigianDate implements Comparable<ErborigianDate> {

    private final int year;
    private final short month;
    private final short day;
    public static final ErborigianDate MIN = ErborigianDate.of(ErborigianYear.MIN_VALUE, 1, 1);
    public static final ErborigianDate MAX = ErborigianDate.of(ErborigianYear.MAX_VALUE, 12, 31);
    private static final int DAYS_PER_LEAP_CYCLE = 9131;
    private static final long EPOCH_DAY_DIFF = 219875;

    private ErborigianDate(int year, int month, int day) {
        this.year = year;
        this.month = (short) month;
        this.day = (short) day;
    }

    private static ErborigianDate create(int year, int month, int dayOfMonth) {
        if (dayOfMonth > 28) {
            int dom = 31;
            switch (month) {
                case 2:
                    dom = 28;
                    break;
                case 6:
                    dom = (ErborigianYear.of(year).isLeap() ? 36 : 30);
                    break;
                case 4:
                case 9:
                case 11:
                    dom = 30;
                    break;
            }
            if (dayOfMonth > dom) {
                if (dayOfMonth == 29) {
                    throw new CalendarDateTimeException("Invalid date 'Sumbri 36' as '" + year + "' is not a leap year");
                } else {
                    throw new CalendarDateTimeException("Invalid date '" + ErborigianMonth.of(month).name() + " " + dayOfMonth + "'");
                }
            }
        }
        return new ErborigianDate(year, month, dayOfMonth);

    }

    private static ErborigianDate resolvePreviousValid(int year, int month, int day) {
        switch (month) {
            case 2:
                day = 28;
                break;
            case 6:
                day = Math.min(day, ErborigianYear.of(year).isLeap() ? 36 : 30);
                break;
            case 4:
            case 9:
            case 11:
                day = Math.min(day, 30);
                break;
        }
        return create(year, month, day);
    }

    public static ErborigianDate of(int year, int moth, int dayOfMonth) {
        return create(year, moth, dayOfMonth);
    }

    public static ErborigianDate ofYearDay(int year, int dayOfYear) {
        ErborigianYear oYear = ErborigianYear.of(year);
        boolean leap = oYear.isLeap();
        if (dayOfYear == 371 && leap == false) {
            throw new IllegalArgumentException("Invalid date 'DayOfYear 371' as '" + year + "' is not a leap year");
        }
        int min = ErborigianYear.isLeap(year) && dayOfYear < 364 ? 6 : 1;
        ErborigianMonth moy = ErborigianMonth.of((dayOfYear - min) / 31 + 1);
        int monthEnd = moy.firstDayOfYear(leap) + moy.length(leap) - 1;
        if (dayOfYear > monthEnd) {
            moy = moy.plus(1);
        }
        int dom = dayOfYear - moy.firstDayOfYear(leap) + 1;
        return ErborigianDate.of(year, moy.getValue(), dom);
    }

    public int getDayOfMonth() {
        return day;
    }

    public ErborigianDayOfWeek getDayOfWeek() {
        int dow0 = (int) Math.floorMod(toEpochDay() + 5, 7);
        return ErborigianDayOfWeek.of(dow0 + 1);
    }

    public long toEpochDay() {
        long y = year - 1;
        long total = 0;
        total += 365 * y;
        if (y >= 0) {
            total += (y / 25) * 6;
        }
        return total + getMonth().firstDayOfYear(isLeapYear()) + day - 1;
    }

    public static ErborigianDate ofEpochDay(long epochDay) {
        int y = (int) ((epochDay - 1) / DAYS_PER_LEAP_CYCLE) * 25;
        int days = y * 365;
        days += (y / 25) * 6;
        int d = (int) epochDay - days;
        int yMod = d >= DAYS_PER_LEAP_CYCLE - 6 ? 0 : 1;
        int newYear = y + (int) ((d - 1) / 365) + yMod;
        d = ((d - 1) % 365) + 1;
        if (yMod == 0) {
            d += 365;
        }
        return ErborigianDate.ofYearDay(newYear, d);
    }

    public int getDayOfYear() {
        return getMonth().firstDayOfYear(isLeapYear()) + day - 1;
    }

    public ErborigianMonth getMonth() {
        return ErborigianMonth.of((int) month);
    }

    public int getMonthValue() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isLeapYear() {
        return ErborigianYear.of(year).isLeap();
    }

    public String format() {
        return format(ErborigianDateTimeFormatter.DATE);
    }

    public String format(ErborigianDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public static ErborigianDate parse(CharSequence txt) {
        return (ErborigianDate) ErborigianDateTimeFormatter.DATE.parse(txt);
    }

    public static ErborigianDate parse(CharSequence txt, ErborigianDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return (ErborigianDate) formatter.parse(txt);
    }

    public boolean isAfter(ErborigianDate other) {
        return compareTo(other) > 0;
    }

    public boolean isBefore(ErborigianDate other) {
        return compareTo(other) < 0;
    }

    public boolean isEqual(ErborigianDate other) {
        return compareTo(other) == 0;
    }

    public int lengthOfMonth() {
        switch (month) {
            case 2:
                return 28;
            case 6:
                return (isLeapYear() ? 36 : 30);
            case 4:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    public int lengthOfYear() {
        return (isLeapYear() ? 371 : 365);
    }

    public ErborigianDate withYear(int year) {
        if (this.year == year) {
            return this;
        }
        ErborigianYear.validate(year);
        return resolvePreviousValid(year, month, day);

    }

    public ErborigianDate withMonth(int month) {
        if (this.month == month) {
            return this;
        }
        ErborigianMonth.validate(month);
        return resolvePreviousValid(year, month, day);
    }

    public ErborigianDate withDayOfMonth(int dayOfMonth) {
        if (this.day == dayOfMonth) {
            return this;
        }
        return of(year, month, dayOfMonth);
    }

    public ErborigianDate withDayOfYear(int dayOfYear) {
        if (this.getDayOfYear() == dayOfYear) {
            return this;
        }
        return ofYearDay(year, dayOfYear);
    }

    public ErborigianDate minusDays(long daysToSubtract) {
        return (daysToSubtract == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-daysToSubtract));
    }

    public ErborigianDate minusMonths(long monthsToSubtract) {
        return (monthsToSubtract == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtract));
    }

    public ErborigianDate minusWeeks(long weeksToSubtract) {
        return (weeksToSubtract == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeksToSubtract));
    }

    public ErborigianDate minusYears(long yearsToSubtract) {
        return (yearsToSubtract == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-yearsToSubtract));
    }

    public ErborigianDate plusDays(long daysToAdd) {
        if (daysToAdd == 0) {
            return this;
        }
        long mjDay = Math.addExact(toEpochDay(), daysToAdd);
        return ErborigianDate.ofEpochDay(mjDay);
    }

    public ErborigianDate plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = year * 12L + (month - 1);
        long calcMonths = monthCount + monthsToAdd;
        int newYear = ErborigianYear.checkValidIntValue(Math.floorDiv(calcMonths, 12));
        int newMonth = (int) Math.floorMod(calcMonths, 12) + 1;
        return resolvePreviousValid(newYear, newMonth, day);
    }

    public ErborigianDate plusWeeks(long weeksToAdd) {
        return plusDays(Math.multiplyExact(weeksToAdd, 7));
    }

    public ErborigianDate plusYears(long yearsToAdd) {
        if (yearsToAdd == 0) {
            return this;
        }
        int newYear = ErborigianYear.of(year + new Long(yearsToAdd).intValue()).getValue();
        return resolvePreviousValid(newYear, month, day);
    }

    @Override
    public int compareTo(ErborigianDate o) {
        int cmp = (year - o.getYear());
        if (cmp == 0) {
            cmp = (month - o.getMonthValue());
            if (cmp == 0) {
                cmp = (day - o.getDayOfMonth());
            }
        }
        return cmp;
    }

    public static ErborigianDate now() {
        return ErborigianDate.ofEpochDay(LocalDate.now().toEpochDay() + EPOCH_DAY_DIFF);
    }

    @Override
    public String toString() {
        return format();
    }

    public static void main(String[] args) {
        System.out.println(ErborigianDate.ofEpochDay(LocalDate.now().plusMonths(1).plusDays(10).toEpochDay() + EPOCH_DAY_DIFF));
        System.out.println(ErborigianDate.now().plusMonths(1).plusDays(17).format(ErborigianDateTimeFormatter.VERBOSE_DATE));
        System.out.println(ErborigianDate.now().plusMonths(1).plusDays(10).format(ErborigianDateTimeFormatter.VERBOSE_DATE));
        System.out.println(ErborigianDate.now().format(ErborigianDateTimeFormatter.VERBOSE_DATE));
    }
}
