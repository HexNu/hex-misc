package nu.hex.calendar.core.calendar.ostea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import nu.hex.calendar.core.calendar.CalendarDate;
import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;
import nu.hex.calendar.core.calendar.CalendarDayOfWeek;
import nu.hex.calendar.core.calendar.CalendarMonth;
import nu.hex.calendar.core.calendar.exception.CalendarDateTimeException;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class OsteanDateOLD implements CalendarDate {

    private final int year;
    private final short month;
    private final short day;
    public static final CalendarDate MIN = OsteanDateOLD.of(OsteanYearOLD.MIN_VALUE, 1, 1);
    public static final CalendarDate MAX = OsteanDateOLD.of(OsteanYearOLD.MAX_VALUE, 12, 31);
    private static final int DAYS_PER_CYCLE = 146097;
    static final long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    private OsteanDateOLD(int year, int month, int day) {
        this.year = year;
        this.month = (short) month;
        this.day = (short) day;
    }

    private static OsteanDateOLD create(int year, int month, int dayOfMonth) {
        if (dayOfMonth > 28) {
            int dom = 31;
            switch (month) {
                case 2:
                    dom = (OsteanYearOLD.of(year).isLeap() ? 29 : 28);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dom = 30;
                    break;
            }
            if (dayOfMonth > dom) {
                if (dayOfMonth == 29) {
                    throw new CalendarDateTimeException("Invalid date 'February 29' as '" + year + "' is not a leap year");
                } else {
                    throw new CalendarDateTimeException("Invalid date '" + OsteanMonthOLD.of(month).name() + " " + dayOfMonth + "'");
                }
            }
        }
        return new OsteanDateOLD(year, month, dayOfMonth);

    }

    private static OsteanDateOLD resolvePreviousValid(int year, int month, int day) {
        switch (month) {
            case 2:
                day = Math.min(day, OsteanYearOLD.of(year).isLeap() ? 29 : 28);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = Math.min(day, 30);
                break;
        }
        return create(year, month, day);
    }

    private long getProlepticMonth() {
        return (year * 12L + month - 1);
    }

    public static OsteanDateOLD of(int year, int moth, int dayOfMonth) {
        return create(year, moth, dayOfMonth);
    }

    public static OsteanDateOLD ofYearDay(int year, int dayOfYear) {
        OsteanYearOLD oYear = OsteanYearOLD.of(year);
        boolean leap = oYear.isLeap();
        if (dayOfYear == 366 && leap == false) {
            throw new IllegalArgumentException("Invalid date 'DayOfYear 366' as '" + year + "' is not a leap year");
        }
        OsteanMonthOLD moy = OsteanMonthOLD.of((dayOfYear - 1) / 31 + 1);
        int monthEnd = moy.firstDayOfYear(leap) + moy.length(leap) - 1;
        if (dayOfYear > monthEnd) {
            moy = moy.plus(1);
        }
        int dom = dayOfYear - moy.firstDayOfYear(leap) + 1;
        return OsteanDateOLD.of(year, moy.getValue(), dom);
    }

    @Override
    public int getDayOfMonth() {
        return day;
    }

    @Override
    public CalendarDayOfWeek getDayOfWeek() {
        int dow0 = (int) Math.floorMod(toEpochDay() + 3, 7);
        return OsteanDayOfWeekOLD.of(dow0 + 1);
    }

    @Override
    public long toEpochDay() {
        long y = year;
        long m = month;
        long total = 0;
        total += 365 * y;
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        total += ((367 * m - 362) / 12);
        total += day - 1;
        if (m > 2) {
            total--;
            if (isLeapYear() == false) {
                total--;
            }
        }
        return total - DAYS_0000_TO_1970;
    }

    @Override
    public int getDayOfYear() {
        return getMonth().firstDayOfYear(isLeapYear()) + day - 1;
    }

    @Override
    public CalendarMonth getMonth() {
        return OsteanMonthOLD.of((int) month);
    }

    @Override
    public int getMonthValue() {
        return month;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public boolean isLeapYear() {
        return OsteanYearOLD.of(year).isLeap();
    }

    @Override
    public String format() {
        return format(CalendarDateTimeFormatter.OSTEAN_DATE);
    }

    @Override
    public String format(CalendarDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public static CalendarDate parse(CharSequence txt) {
        return (CalendarDate) CalendarDateTimeFormatter.OSTEAN_DATE.parse(txt);
    }

    public static CalendarDate parse(CharSequence txt, CalendarDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return (CalendarDate) formatter.parse(txt);
    }

    @Override
    public boolean isAfter(CalendarDate other) {
        return compareTo(other) > 0;
    }

    @Override
    public boolean isBefore(CalendarDate other) {
        return compareTo(other) < 0;
    }

    @Override
    public boolean isEqual(CalendarDate other) {
        return compareTo(other) == 0;
    }

    @Override
    public int lengthOfMonth() {
        switch (month) {
            case 2:
                return (isLeapYear() ? 29 : 28);
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    @Override
    public int lengthOfYear() {
        return (isLeapYear() ? 366 : 365);
    }

    @Override
    public CalendarDate withYear(int year) {
        if (this.year == year) {
            return this;
        }
        OsteanYearOLD.validate(year);
        return resolvePreviousValid(year, month, day);

    }

    @Override
    public CalendarDate withMonth(int month) {
        if (this.month == month) {
            return this;
        }
        OsteanMonthOLD.validate(month);
        return resolvePreviousValid(year, month, day);
    }

    @Override
    public CalendarDate withDayOfMonth(int dayOfMonth) {
        if (this.day == dayOfMonth) {
            return this;
        }
        return of(year, month, dayOfMonth);
    }

    public CalendarDate withDayOfYear(int dayOfYear) {
        if (this.getDayOfYear() == dayOfYear) {
            return this;
        }
        return ofYearDay(year, dayOfYear);
    }

    @Override
    public CalendarDate minusDays(long daysToSubtract) {
        return (daysToSubtract == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-daysToSubtract));
    }

    @Override
    public CalendarDate minusMonths(long monthsToSubtract) {
        return (monthsToSubtract == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtract));
    }

    @Override
    public CalendarDate minusWeeks(long weeksToSubtract) {
        return (weeksToSubtract == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeksToSubtract));
    }

    @Override
    public CalendarDate minusYears(long yearsToSubtract) {
        return (yearsToSubtract == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-yearsToSubtract));
    }

    @Override
    public CalendarDate plusDays(long daysToAdd) {
        if (daysToAdd == 0) {
            return this;
        }
        long mjDay = Math.addExact(toEpochDay(), daysToAdd);
        return OsteanDateOLD.ofEpochDay(mjDay);
    }

    @Override
    public CalendarDate plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = year * 12L + (month - 1);
        long calcMonths = monthCount + monthsToAdd;  // safe overflow
        int newYear = OsteanYearOLD.checkValidIntValue(Math.floorDiv(calcMonths, 12));
        int newMonth = (int) Math.floorMod(calcMonths, 12) + 1;
        return resolvePreviousValid(newYear, newMonth, day);
    }

    @Override
    public CalendarDate plusWeeks(long weeksToAdd) {
        return plusDays(Math.multiplyExact(weeksToAdd, 7));
    }

    @Override
    public CalendarDate plusYears(long yearsToAdd) {
        if (yearsToAdd == 0) {
            return this;
        }
        int newYear = OsteanYearOLD.of(year + new Long(yearsToAdd).intValue()).getValue();
        return resolvePreviousValid(newYear, month, day);
    }

    public static CalendarDate ofEpochDay(long epochDay) {
        long zeroDay = epochDay + DAYS_0000_TO_1970;
        zeroDay -= 60;
        long adjust = 0;
        if (zeroDay < 0) {
            long adjustCycles = (zeroDay + 1) / DAYS_PER_CYCLE - 1;
            adjust = adjustCycles * 400;
            zeroDay += -adjustCycles * DAYS_PER_CYCLE;
        }
        long yearEst = (400 * zeroDay + 591) / DAYS_PER_CYCLE;
        long doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
        if (doyEst < 0) {
            yearEst--;
            doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
        }
        yearEst += adjust;
        int marchDoy0 = (int) doyEst;

        int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
        int month = (marchMonth0 + 2) % 12 + 1;
        int dom = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;
        yearEst += marchMonth0 / 10;

        int year = OsteanYearOLD.checkValidIntValue(yearEst);
        return of(year, month, dom);
    }

    @Override
    public int compareTo(CalendarDate o) {
        int cmp = (year - o.getYear());
        if (cmp == 0) {
            cmp = (month - o.getMonthValue());
            if (cmp == 0) {
                cmp = (day - o.getDayOfMonth());
            }
        }
        return cmp;
    }

    public static CalendarDate now() {
        LocalDate now = LocalDate.now().minusYears(1087);
        return OsteanDateOLD.parse(now.format(DateTimeFormatter.ISO_DATE), CalendarDateTimeFormatter.OSTEAN_DATE);
    }

    @Override
    public String toString() {
        return format();
    }

    public static void main(String[] args) {
//        System.out.println(OsteanDate.now().format(CalendarDateTimeFormatter.OSTEAN_DATE));
        final long year = 2016;
        final long month = 11;
        final long day = 5;
        final boolean isLeapYear = true;
        long y = year;
        long m = month;
        long total = 0;
        total += 365 * y;
        System.out.println(total);
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        System.out.println(total);
        total += ((367 * m - 362) / 12);
        System.out.println(total);
        total += day - 1;
        System.out.println(total);
        if (m > 2) {
            total--;
            System.out.println(total);
            if (isLeapYear == false) {
                total--;
                System.out.println(total);
            }
        }
        System.out.println(total);
        System.out.println(LocalDate.now().toEpochDay());

    }
}
