package nu.hex.calendar.core.calendar.rpg;

/**
 * Created 2016-nov-15
 *
 * @author hl
 */
public class DateFactory {

    private static final String SEPARATOR = "\u00A0";
    private String calendar;

    public DateFactory() {
    }

    public DateFactory(String calendar) {
        this.calendar = calendar.toLowerCase().substring(0, 3);
    }

    public RpgDate getDate(String text) {
        if (calendar == null && text.contains(SEPARATOR)) {
            calendar = text.split(SEPARATOR)[0].toLowerCase();
            text = text.split(SEPARATOR)[1];
        }
        if (calendar == null && text.contains(" ")) {
            calendar = text.split(" ")[0].toLowerCase();
            text = text.split(" ")[1];
        }
        if (calendar != null) {
            switch (calendar) {
                case "ost":
                    return new OsteanDate(text);
                case "agl":
                    return new AglianDate(text);
            }
        }
        return null;
    }

    public RpgDate getDate() {
        return getDate(null);
    }

    public static void main(String[] args) {
        String dateString;
//        dateString = new DateFactory("Ostean").getDate("3-01-01").toString();
//        System.out.println(dateString);
        System.out.println(new DateFactory().getDate("ost 1-02-03").get());
        System.out.println(new DateFactory().getDate("Ost 1-02-03").getDate());
        System.out.println(new DateFactory().getDate("Ost 1-02-03").getCalendarName());
        System.out.println(new DateFactory().getDate("Ost 1-02-03").getShortCalendarName());
        System.out.println(new DateFactory("Ostean").getDate("0001-02-03").get());
        System.out.println(new DateFactory("Ostean").getDate("0001-02-03").getDate());
        System.out.println(new DateFactory("Ostean").getDate("0001-02-03").getCalendarName());
        System.out.println(new DateFactory().getDate("Ost 1-02-03").getShortCalendarName());
        dateString = "Agl" + SEPARATOR + "1211-04-27";
        System.out.println(new DateFactory().getDate(dateString));
        dateString = "Agl 811-02-02";
        System.out.println(new DateFactory().getDate(dateString));

    }
}
