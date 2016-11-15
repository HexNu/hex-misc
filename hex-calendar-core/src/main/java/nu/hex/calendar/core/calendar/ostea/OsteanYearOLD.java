package nu.hex.calendar.core.calendar.ostea;

import nu.hex.calendar.core.calendar.CalendarDate;
import nu.hex.calendar.core.calendar.format.CalendarDateTimeFormatter;
import nu.hex.calendar.core.calendar.CalendarMonth;
import nu.hex.calendar.core.calendar.CalendarMonthDay;
import nu.hex.calendar.core.calendar.CalendarYear;
import nu.hex.calendar.core.calendar.CalendarYearMonth;
import nu.hex.calendar.core.calendar.exception.CalendarDateTimeException;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class OsteanYearOLD implements CalendarYear {

    public static final int MIN_VALUE = -999_999_999;
    public static final int MAX_VALUE = 999_999_999;
    private final int year;

    private OsteanYearOLD(int year) {
        this.year = year;
    }

    public static OsteanYearOLD of(int year) {
        validate(year);
        return new OsteanYearOLD(year);
    }

    public static boolean isValid(int year) {
        return inRange(year);
    }

    public static void validate(int year) {
        if (!OsteanYearOLD.isValid(year)) {
            throw new CalendarDateTimeException("Year " + year + " is not in range");
        }
    }

    public static int checkValidIntValue(double value) {
        int intValue = new Double(value).intValue();
        validate(intValue);
        return intValue;
    }

    @Override
    public CalendarDate atDay(int dayOfYear) {
        return OsteanDateOLD.ofYearDay(year, dayOfYear);
    }

    @Override
    public CalendarYearMonth atMonth(int month) {
        return OsteanYearMonthOLD.of(year, month);
    }

    @Override
    public CalendarYearMonth atMonth(CalendarMonth month) {
        return atMonth(month.getValue());
    }

    @Override
    public CalendarDate atMonthDay(CalendarMonthDay monthDay) {
        return monthDay.atYear(year);
    }

    @Override
    public String format(CalendarDateTimeFormatter formatter) {
        return CalendarDateTimeFormatter.OSTEAN_DATE.format(this);
    }

    public static CalendarYear parse(CharSequence text) {
        return parse(text, CalendarDateTimeFormatter.OSTEAN_YEAR);
    }

    public static CalendarYear parse(CharSequence text, CalendarDateTimeFormatter formatter) {
        return (CalendarYear) formatter.parse(text);
    }

    @Override
    public int getValue() {
        return year;
    }

    @Override
    public boolean isAfter(CalendarYear other) {
        return compareTo(other) > 0;
    }

    @Override
    public boolean isBefore(CalendarYear other) {
        return compareTo(other) < 0;
    }

    @Override
    public boolean isLeap() {
        return OsteanYearOLD.isLeap(year);
    }

    public static boolean isLeap(int year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    @Override
    public int length() {
        return isLeap() ? 366 : 365;
    }

    @Override
    public CalendarYear minus(long years) {
        int y = this.year - new Long(years).intValue();
        return new OsteanYearOLD(y);
    }

    @Override
    public CalendarYear plus(long years) {
        int y = this.year + new Long(years).intValue();
        return new OsteanYearOLD(y);
    }

    @Override
    public int compareTo(CalendarYear o) {
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
        CalendarYear a = OsteanYearOLD.of(1900);
        CalendarYear b = OsteanYearOLD.of(2000);
        System.out.println(b.atDay(34).getDayOfMonth());
        System.out.println(b.atDay(34).getMonth().toString());
        System.out.println(a.isAfter(b));
        System.out.println(a.isBefore(b));
        System.out.println(a.isLeap());
        System.out.println(b.isLeap());
    }
}
