package nu.hex.calendar.core.calendar.rpg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created 2016-nov-15
 *
 * @author hl
 */
public abstract class AbstractRpgDate implements RpgDate {

    private final LocalDate date;
    private static final String SEPARATOR = "\u00A0";

    public AbstractRpgDate() {
        this.date = null;
    }

    public AbstractRpgDate(String text) {
        if (text != null) {
            date = LocalDate.parse("0000000000".substring(text.length()) + text, DateTimeFormatter.ISO_DATE);
        } else {
            date = null;
        }
    }

    protected abstract int getDiff();

    @Override
    public LocalDate get() {
        return date == null ? now() : date;
    }

    @Override
    public LocalDate now() {
        return LocalDate.now().minusYears(getDiff());
    }

    @Override
    public LocalDate of(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    @Override
    public LocalDate parse(String text) {
        return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String getDate() {
        return toString().substring(4);
    }

    @Override
    public String getShortCalendarName() {
        return getCalendarName().substring(0, 3);
    }

    @Override
    public String toString() {
        String dateString = get().format(DateTimeFormatter.ISO_DATE);
        return getShortCalendarName() + SEPARATOR + dateString.replaceFirst("^0+(?!$)", "");
    }
}
