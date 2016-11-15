package nu.hex.calendar.core.calendar.ostea;

import java.util.Objects;
import nu.hex.calendar.core.calendar.CalendarDate;
import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;
import nu.hex.calendar.core.calendar.CalendarYearMonth;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class OsteanYearMonthOLD implements CalendarYearMonth {

    private final int year;
    private final int month;

    private OsteanYearMonthOLD(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public static OsteanYearMonthOLD of(int year, int month) {
        OsteanYearOLD of = OsteanYearOLD.of(year);
        OsteanMonthOLD of1 = OsteanMonthOLD.of(month);
        return new OsteanYearMonthOLD(year, month);
    }

    public static OsteanYearMonthOLD of(int year, OsteanMonthOLD month) {
        Objects.requireNonNull(month, "month");
        return of(year, month.getValue());
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonthValue() {
        return month;
    }

    @Override
    public OsteanMonthOLD getMonth() {
        return OsteanMonthOLD.of(month);
    }

    @Override
    public boolean isLeapYear() {
        return OsteanYearOLD.of(year).isLeap();
    }

    @Override
    public boolean isValidDay(int dayOfMonth) {
        return dayOfMonth >= 1 && dayOfMonth <= lengthOfMonth();
    }

    @Override
    public int lengthOfMonth() {
        return getMonth().length(isLeapYear());
    }

    @Override
    public int lengthOfYear() {
        return (isLeapYear() ? 366 : 365);
    }

    @Override
    public OsteanYearMonthOLD plusYears(int yearsToAdd) {
        if (yearsToAdd == 0) {
            return this;
        }
        OsteanYearOLD of = OsteanYearOLD.of(year + yearsToAdd);
        return new OsteanYearMonthOLD(of.getValue(), month);
    }

    @Override
    public OsteanYearMonthOLD plusMonths(int monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = year * 12L + (month - 1);
        long calcMonths = monthCount + monthsToAdd;  // safe overflow
        int newYear = OsteanYearOLD.of(new Long(Math.floorDiv(calcMonths, 12)).intValue()).getValue();
        int newMonth = (int) Math.floorMod(calcMonths, 12) + 1;
        return new OsteanYearMonthOLD(newYear, newMonth);
    }

    @Override
    public OsteanYearMonthOLD minusYears(int yearsToSubtract) {
        return plusYears(-yearsToSubtract);
    }

    @Override
    public OsteanYearMonthOLD minusMonths(int monthsToSubtract) {
        return plusMonths(-monthsToSubtract);
    }

    @Override
    public String format(CalendarDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    @Override
    public CalendarDate atDay(int dayOfMonth) {
        return OsteanDateOLD.of(year, month, dayOfMonth);
    }

    @Override
    public CalendarDate atEndOfMonth() {
        return OsteanDateOLD.of(year, month, lengthOfMonth());
    }

    @Override
    public int compareTo(CalendarYearMonth other) {
        int cmp = (year - other.getYear());
        if (cmp == 0) {
            cmp = (month - other.getMonthValue());
        }
        return cmp;

    }

}
