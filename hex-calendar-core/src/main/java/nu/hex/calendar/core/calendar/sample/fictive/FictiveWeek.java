package nu.hex.calendar.core.calendar.sample.fictive;

import java.util.ArrayList;
import java.util.List;
import nu.hex.calendar.core.calendar.rpg.old.Week;
import nu.hex.calendar.core.calendar.rpg.old.WeekDay;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class FictiveWeek implements Week {

    private List<WeekDay> weekDays = new ArrayList<>();

    @Override
    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    @Override
    public void setWeekDays(List<WeekDay> weekDays) {
        this.weekDays = weekDays;
    }

    @Override
    public void addWeekDay(WeekDay weekDay) {
        if (!weekDays.contains(weekDay)) {
            weekDays.add(weekDay);
        }
    }

    @Override
    public Integer getNumberOfDays() {
        return getWeekDays().size();
    }
}
