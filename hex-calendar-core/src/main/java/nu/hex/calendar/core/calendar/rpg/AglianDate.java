package nu.hex.calendar.core.calendar.rpg;

/**
 * Created 2016-nov-15
 *
 * @author hl
 */
public class AglianDate extends AbstractRpgDate {

    public AglianDate() {
    }

    public AglianDate(String text) {
        super(text);
    }

    @Override
    protected int getDiff() {
        return AGLIAN_DIFF;
    }

    @Override
    public String getCalendarName() {
        return "Aglian Calendar";
    }
}
