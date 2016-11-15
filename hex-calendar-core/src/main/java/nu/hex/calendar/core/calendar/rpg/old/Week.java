package nu.hex.calendar.core.calendar.rpg.old;

import java.util.List;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public interface Week {

    List<WeekDay> getWeekDays();
    
    void setWeekDays(List<WeekDay> weekDays);
    
    void addWeekDay(WeekDay weekDay);
    
    Integer getNumberOfDays();
}
