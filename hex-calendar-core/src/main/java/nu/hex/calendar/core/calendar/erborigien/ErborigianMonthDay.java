package nu.hex.calendar.core.calendar.erborigien;

import java.util.Objects;
import nu.hex.calendar.core.calendar.erborigien.format.ErborigianDateTimeFormatter;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class ErborigianMonthDay {

    private final int month;
    private final int day;

    private ErborigianMonthDay(int month, int dayOfMonth) {
        this.month = month;
        this.day = dayOfMonth;
    }

    public static ErborigianMonthDay of(int month, int dayOfMonth) {
        return new ErborigianMonthDay(month, dayOfMonth);
    }

    public static ErborigianMonthDay of(ErborigianMonth month, int dayOfMonth) {
        return of(month.getValue(), dayOfMonth);
    }

    public String format() {
        return format(ErborigianDateTimeFormatter.MONTH_DAY);
    }

    public String format(ErborigianDateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public static ErborigianMonthDay parse(CharSequence text) {
        return parse(text, ErborigianDateTimeFormatter.MONTH_DAY);
    }

    public static ErborigianMonthDay parse(CharSequence text, ErborigianDateTimeFormatter formatter) {
        return (ErborigianMonthDay) formatter.parse(text);
    }

    public int getMonthValue() {
        return month;
    }

    public ErborigianMonth getMonth() {
        return ErborigianMonth.of(month);
    }

    public int getDay() {
        return day;
    }

    public boolean isValidYear(int year) {
        return (getDay() == 36 && getMonthValue() == 6 && !ErborigianYear.isLeap(year)) == false;
    }

    public ErborigianMonthDay withMonth(int month) {
        return withMonth(ErborigianMonth.of(month));
    }

    public ErborigianMonthDay withMonth(ErborigianMonth month) {
        Objects.requireNonNull(month, "month");
        if (month.getValue() == this.month) {
            return this;
        }
        int d = Math.min(this.day, month.maxLength());
        return of(month.getValue(), d);

    }

    public ErborigianMonthDay withDayOfMonth(int dayOfMonth) {
        if (dayOfMonth == this.day) {
            return this;
        }
        return of(month, dayOfMonth);
    }

    public ErborigianDate atYear(int year) {
        return ErborigianDate.of(year, month, isValidYear(year) ? day : 30);
    }

    public boolean isAfter(ErborigianMonthDay other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isBefore(ErborigianMonthDay other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int compareTo(ErborigianMonthDay other) {
        int cmp = (month - other.getMonthValue());
        if (cmp == 0) {
            cmp = (day - other.getDay());
        }
        return cmp;
    }

    @Override
    public String toString() {
        return getDay() + " of " + getMonth().getDisplayName();
    }
}
