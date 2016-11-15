package nu.hex.calendar.core.calendar.sample.local;

import java.util.ArrayList;
import java.util.List;
import nu.hex.calendar.core.calendar.rpg.old.Week;
import nu.hex.calendar.core.calendar.rpg.old.WeekDay;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class StandardWeek implements Week {

    private static final List<WeekDay> weekDays = new ArrayList<>();

    static {
        weekDays.add(0, new StandardWeekDay(0, "Monday"));
        weekDays.add(1, new StandardWeekDay(0, "Tuesday"));
        weekDays.add(2, new StandardWeekDay(0, "Wednesday"));
        weekDays.add(3, new StandardWeekDay(0, "Thursday"));
        weekDays.add(4, new StandardWeekDay(0, "Friday"));
        weekDays.add(5, new StandardWeekDay(0, "Saturday"));
        weekDays.add(6, new StandardWeekDay(0, "Sunday"));
    }

    @Override
    public Integer getNumberOfDays() {
        return getWeekDays().size();
    }

    @Override
    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    @Override
    public void setWeekDays(List<WeekDay> weekDays) {
    }

    @Override
    public void addWeekDay(WeekDay weekDay) {
    }
}
