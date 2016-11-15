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

    public AbstractRpgDate() {
        this.date = null;
    }

    public AbstractRpgDate(String text) {
        date = LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
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
}
