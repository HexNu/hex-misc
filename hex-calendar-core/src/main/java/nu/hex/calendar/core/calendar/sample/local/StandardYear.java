package nu.hex.calendar.core.calendar.sample.local;

import java.util.ArrayList;
import java.util.List;
import nu.hex.calendar.core.calendar.rpg.old.Month;
import nu.hex.calendar.core.calendar.rpg.old.Year;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class StandardYear implements Year {

    private List<Month> months = new ArrayList<>();

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
        return Math.round(getNumberOfDays() / new StandardWeek().getNumberOfDays());
    }

    @Override
    public Integer getNumberOfDays() {
        return 365;
    }

    @Override
    public void setNumberOfDays(Integer numberOfDays) {
    }

    @Override
    public Double getLeapDays() {
        return 0.25;
    }

    @Override
    public void setLeapDays(Double leapDays) {
    }
}
