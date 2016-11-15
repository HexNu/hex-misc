package nu.hex.calendar.core.calendar.rpg.old;

import java.util.List;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public interface Year {

    List<Month> getMonths();

    void setMonths(List<Month> months);

    void addMonth(Month month);

    Integer getNumberOfWeeks();

    Integer getNumberOfDays();
    
    void setNumberOfDays(Integer numberOfDays);

    Double getLeapDays();

    void setLeapDays(Double leapDays);

}
