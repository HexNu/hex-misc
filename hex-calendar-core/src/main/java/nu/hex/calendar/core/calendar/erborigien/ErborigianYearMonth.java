package nu.hex.calendar.core.calendar.erborigien;

import java.util.Objects;
import nu.hex.calendar.core.calendar.erborigien.format.ErborigianDateTimeFormatter;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class ErborigianYearMonth  {

    private final int year;
    private final int month;

    private ErborigianYearMonth(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public static ErborigianYearMonth of(int year, int month) {
        ErborigianYear ey = ErborigianYear.of(year);
        ErborigianMonth em = ErborigianMonth.of(month);
        return new ErborigianYearMonth(ey.getValue(), em.getValue());
    }

    public static ErborigianYearMonth of(int year, ErborigianMonth month) {
        Objects.requireNonNull(month, "month");
        return of(year, month.getValue());
    }

    public int getYear() {
        return year;
    }

    public int getMonthValue() {
        return month;
    }

    public ErborigianMonth getMonth() {
        return ErborigianMonth.of(month);
    }

    public boolean isLeapYear() {
        return ErborigianYear.of(year).isLeap();
    }

    public boolean isValidDay(int dayOfMonth) {
        return dayOfMonth >= 1 && dayOfMonth <= lengthOfMonth();
    }

    public int lengthOfMonth() {
        return getMonth().length(isLeapYear());
    }

    public int lengthOfYear() {
        return ErborigianYear.of(year).length();
    }

    public ErborigianYearMonth plusYears(int yearsToAdd) {
        if (yearsToAdd == 0) {
            return this;
        }
        ErborigianYear of = ErborigianYear.of(year + yearsToAdd);
        return new ErborigianYearMonth(of.getValue(), month);
    }

    public ErborigianYearMonth plusMonths(int monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = year * 12L + (month - 1);
        long calcMonths = monthCount + monthsToAdd;  // safe overflow
        int newYear = ErborigianYear.of(new Long(Math.floorDiv(calcMonths, 12)).intValue()).getValue();
        int newMonth = (int) Math.floorMod(calcMonths, 12) + 1;
        return new ErborigianYearMonth(newYear, newMonth);
    }

    public ErborigianYearMonth minusYears(int yearsToSubtract) {
        return plusYears(-yearsToSubtract);
    }

    public ErborigianYearMonth minusMonths(int monthsToSubtract) {
        return plusMonths(-monthsToSubtract);
    }

    public String format(ErborigianDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public ErborigianDate atDay(int dayOfMonth) {
        return ErborigianDate.of(year, month, dayOfMonth);
    }

    public ErborigianDate atEndOfMonth() {
        return ErborigianDate.of(year, month, lengthOfMonth());
    }

    public int compareTo(ErborigianYearMonth other) {
        int cmp = (year - other.getYear());
        if (cmp == 0) {
            cmp = (month - other.getMonthValue());
        }
        return cmp;

    }
}
