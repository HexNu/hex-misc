package nu.hex.calendar.core.calendar.sample.fictive;

import java.util.ArrayList;
import java.util.List;
import nu.hex.calendar.core.calendar.rpg.old.Month;
import nu.hex.calendar.core.calendar.rpg.old.Week;
import nu.hex.calendar.core.calendar.rpg.old.Year;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class FictiveYear implements Year {

    private List<Month> months = new ArrayList<>();
    private Double leapDays = 0D;
    private final Week week;

    public FictiveYear(Week week) {
        this.week = week;
    }

    @Override
    public List<Month> getMonths() {
        return months;
    }

    @Override
    public void setMonths(List<Month> months) {
        this.months = months;
    }

    @Override
    public void addMonth(Month month) {
        if (!months.contains(month)) {
            months.add(month);
        }
    }

    @Override
    public Integer getNumberOfWeeks() {
        return Math.round(getNumberOfDays() / week.getNumberOfDays());
    }

    @Override
    public Integer getNumberOfDays() {
        int result = 0;
        result = getMonths().stream().map((month) -> month.getNumberOfDays()).reduce(result, Integer::sum);
        return result;
    }

    @Override
    public void setNumberOfDays(Integer numberOfDays) {
    }

    @Override
    public Double getLeapDays() {
        return leapDays;
    }

    @Override
    public void setLeapDays(Double leapDays) {
        this.leapDays = leapDays;
    }
}
