package nu.hex.calendar.core.timeline;

import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created 2016-okt-31
 *
 * @author hl
 */
public interface Event extends Comparable<Event> {

    String getLabel();

    void setLabel();

    String getDescription();

    void setDescription(String description);

    Image getSymbol();

    void setSymbol(Image icon);

    LocalDate getStartDate();

    LocalDateTime getStartDateTime();

    void setStart(LocalDate start);

    void setStart(LocalDateTime start);

    LocalDate getEndDate();

    LocalDateTime getEndDateTime();

    void setEnd(LocalDate start);

    void setEnd(LocalDateTime start);

}
