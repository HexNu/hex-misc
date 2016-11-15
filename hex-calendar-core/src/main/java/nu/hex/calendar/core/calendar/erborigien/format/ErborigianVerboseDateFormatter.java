package nu.hex.calendar.core.calendar.erborigien.format;

import nu.hex.calendar.core.calendar.erborigien.ErborigianCalendar;
import nu.hex.calendar.core.calendar.erborigien.ErborigianDate;
import nu.hex.calendar.core.calendar.erborigien.ErborigianMonth;
import nu.hex.calendar.core.calendar.erborigien.ErborigianYearMonth;
import nu.hex.calendar.core.calendar.format.CalendarTextStyle;

/**
 * Created 2016-nov-03
 *
 * @author hl
 */
public class ErborigianVerboseDateFormatter implements ErborigianDateTimeFormatter<ErborigianDate> {

    @Override
    public String format(ErborigianDate date) {
        String result = date.getDayOfWeek().getDisplayName(CalendarTextStyle.FULL_STANDALONE);
        result += ", den ";
        if (date.getDayOfMonth() == date.getMonth().length(date.isLeapYear())) {
            result += "sista ";
        } else {
            result += date.getDayOfMonth() + ":";
            switch (date.getDayOfMonth()) {
                case 1:
                case 2:
                case 21:
                case 22:
                case 31:
                case 32:
                    result += "a ";
                    break;
                default:
                    result += "e ";
                    break;
            }
        }
        result += "dagen i " + date.getMonth().getDisplayName(CalendarTextStyle.FULL_STANDALONE) + " år " + date.getYear();
        return result;
    }

    @Override
    public ErborigianDate parse(CharSequence text) {
        String txt = text.toString();
        int month = 0;
        for (ErborigianMonth m : ErborigianMonth.values()) {
            String compareString = txt.toUpperCase(ErborigianCalendar.DEFAULT_LOCALE);
            if (compareString.contains(m.name())) {
                month = m.ordinal() + 1;
                break;
            }
        }
        if (month == 0) {
            throw new IllegalArgumentException("No valid month found");
        }
        int year = Integer.valueOf(txt.substring(txt.lastIndexOf(" år ")).replaceAll("\\D", ""));
        int dayOfMonth;
        if (txt.contains(" sista ")) {
            dayOfMonth = ErborigianYearMonth.of(year, month).lengthOfMonth();
        } else {
            dayOfMonth = Integer.valueOf(txt.substring(0, txt.lastIndexOf("dagen")).replaceAll("\\D", ""));
        }
        return ErborigianDate.of(year, month, dayOfMonth);
    }

    public static void main(String[] args) {
        ErborigianDate parse = new ErborigianVerboseDateFormatter().parse("Tors dag, den sista dagen i Sumri år 650");
        System.out.println(parse);
    }
}
