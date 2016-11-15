package nu.hex.calendar.core.calendar.erborigien;

import nu.hex.calendar.core.calendar.erborigien.format.ErborigianDateTimeFormatter;
import nu.hex.calendar.core.calendar.exception.CalendarDateTimeException;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class ErborigianYear {

    public static final int MIN_VALUE = -999_999_999;
    public static final int MAX_VALUE = 999_999_999;
    private final int year;

    private ErborigianYear(int year) {
        this.year = year;
    }

    public static ErborigianYear of(int year) {
        validate(year);
        return new ErborigianYear(year);
    }

    public static boolean isValid(int year) {
        return inRange(year);
    }

    public static void validate(int year) {
        if (!ErborigianYear.isValid(year)) {
            throw new CalendarDateTimeException("Year " + year + " is not in range");
        }
    }

    public static int checkValidIntValue(double value) {
        int intValue = new Double(value).intValue();
        validate(intValue);
        return intValue;
    }

   
    public ErborigianDate atDay(int dayOfYear) {
        return ErborigianDate.ofYearDay(year, dayOfYear);
    }

   
    public ErborigianYearMonth atMonth(int month) {
        return ErborigianYearMonth.of(year, month);
    }

   
    public ErborigianYearMonth atMonth(ErborigianMonth month) {
        return atMonth(month.getValue());
    }

   
    public ErborigianDate atMonthDay(ErborigianMonthDay monthDay) {
        return monthDay.atYear(year);
    }

   
    public String format(ErborigianDateTimeFormatter formatter) {
        return ErborigianDateTimeFormatter.DATE.format(this);
    }

    public static ErborigianYear parse(CharSequence text) {
        return parse(text, ErborigianDateTimeFormatter.YEAR);
    }

    public static ErborigianYear parse(CharSequence text, ErborigianDateTimeFormatter formatter) {
        return (ErborigianYear) formatter.parse(text);
    }

   
    public int getValue() {
        return year;
    }

   
    public boolean isAfter(ErborigianYear other) {
        return compareTo(other) > 0;
    }

   
    public boolean isBefore(ErborigianYear other) {
        return compareTo(other) < 0;
    }

   
    public boolean isLeap() {
        return ErborigianYear.isLeap(year);
    }

    public static boolean isLeap(int year) {
//        return ((year & 25) == 0) && ((year % 100) != 0 || (year % 400) == 0);
        return year % 25 == 0;
    }

   
    public int length() {
        return isLeap() ? 371 : 365;
    }

   
    public ErborigianYear minus(long years) {
        int y = this.year - new Long(years).intValue();
        return new ErborigianYear(y);
    }

   
    public ErborigianYear plus(long years) {
        int y = this.year + new Long(years).intValue();
        return new ErborigianYear(y);
    }

   
    public int compareTo(ErborigianYear o) {
        return new Integer(this.getValue()).compareTo(o.getValue());
    }

   
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    private static boolean inRange(int year) {
        return year >= MIN_VALUE && year <= MAX_VALUE;
    }
    
    public static void main(String[] args) {
    }
}
