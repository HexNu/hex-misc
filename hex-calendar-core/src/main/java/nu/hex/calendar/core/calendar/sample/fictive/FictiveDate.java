package nu.hex.calendar.core.calendar.sample.fictive;

import nu.hex.calendar.core.calendar.rpg.old.HexDate;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class FictiveDate implements HexDate {

    private static final int YEAR = 0, MONTH = 1, DAY = 2;
    private final Integer year;
    private final Integer month;
    private final Integer day;

    public FictiveDate(String dateString) {
        String[] parts = dateString.split("-");
        this.year = Integer.valueOf(parts[YEAR]);
        this.month = Integer.valueOf(parts[MONTH]);
        this.day = Integer.valueOf(parts[DAY]);
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public Integer getMonth() {
        return month;
    }

    @Override
    public Integer getWeek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getDay() {
        return day;
    }

    @Override
    public Integer getDayInYear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
