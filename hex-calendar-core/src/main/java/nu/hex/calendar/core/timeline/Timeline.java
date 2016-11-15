package nu.hex.calendar.core.timeline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created 2016-okt-31
 *
 * @author hl
 */
public interface Timeline {

    List<Event> getEvents();

    List<Event> getEvents(LocalDate start, LocalDate end);

    List<Event> getEvents(LocalDateTime start, LocalDateTime end);

    void setEvents(List<Event> events);

    void addEvent(Event event);

}
