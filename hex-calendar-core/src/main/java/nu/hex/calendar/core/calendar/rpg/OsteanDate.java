package nu.hex.calendar.core.calendar.rpg;

/**
 * Created 2016-nov-15
 *
 * @author hl
 */
public class OsteanDate extends AbstractRpgDate {

    public OsteanDate() {
    }

    public OsteanDate(String text) {
        super(text);
    }

    @Override
    protected int getDiff() {
        return OSTEAN_DIFF;
    }

    @Override
    public String getCalendarName() {
        return OSTEAN_CALENDAR;
    }
}
