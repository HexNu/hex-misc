package nu.hex.calendar.core.calendar.sample.fictive;

import nu.hex.calendar.core.calendar.rpg.old.WeekDay;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class FictiveWeekDay implements WeekDay {

    private Integer index;
    private String name;

    public FictiveWeekDay() {
    }

    public FictiveWeekDay(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
