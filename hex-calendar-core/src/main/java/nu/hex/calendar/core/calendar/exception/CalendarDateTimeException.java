package nu.hex.calendar.core.calendar.exception;

/**
 * Created 2016-nov-01
 *
 * @author hl
 */
public class CalendarDateTimeException extends RuntimeException {

    public CalendarDateTimeException(String message) {
        super(message);
    }
    
    public CalendarDateTimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CalendarDateTimeException(Throwable cause) {
        super(cause);
    }
}
