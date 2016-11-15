package nu.hex.calendar.core.calendar;

/**
 * Created 2016-okt-31
 *
 * @author hl
 */
public interface CalendarEra {

    String getDisplayName();

    void setDisplayName(String displayName);

    void setName(String name);

    Integer getValue();

    void setValue(Integer value);

}
