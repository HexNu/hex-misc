package nu.hex.calendar.core.calendar.rpg.old;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public interface Month {

    Integer getIndex();

    void setIndex(Integer index);

    String getName();

    void setName(String name);

    Integer getNumberOfDays();

    void setNumberOfDays(Integer numberOfDays);

}
