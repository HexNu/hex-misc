package nu.hex.calendar.core.calendar.sample.fictive;

import nu.hex.calendar.core.calendar.rpg.old.Day;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class FictiveDay implements Day {

    private Integer numberOfDays;

    @Override
    public Integer getNumberOfHours() {
        return numberOfDays;
    }

    @Override
    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfDays = numberOfHours;
    }

}
