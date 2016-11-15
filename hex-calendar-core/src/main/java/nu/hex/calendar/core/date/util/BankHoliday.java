package nu.hex.calendar.core.date.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author hl
 *
 * Based on Swedish Statute Book <i>SFS 1989:253</i>
 */
public class BankHoliday {

    private static final Map<String, String> FIXED_DATES = new TreeMap<>();
    private static final Map<String, String> FIRST_SATURDAY_DATES = new TreeMap<>();
    private final int year;

    /**
     * <p>
     * Based on Swedish Statute Book <i>SFS 1989:253</i>
     * </p>
     * <p>
     * The default constructor will generate dates for the current year
     * </p>
     */
    public BankHoliday() {
        this(LocalDate.now().getYear());
    }

    /**
     * <p>
     * Based on Swedish Statute Book <i>SFS 1989:253</i>
     * </p>
     * <p>
     * This constructor will generate dates for the given year (nnnn)
     * </p>
     *
     * @param year
     */
    public BankHoliday(int year) {
        this.year = year;
        FIXED_DATES.put("01-01", "Nyårsdagen");
        FIXED_DATES.put("01-06", "Trettondagen");
        FIXED_DATES.put("05-01", "Första maj");
        FIXED_DATES.put("06-06", "Nationaldagen");
        FIXED_DATES.put("12-24", "Julafton");
        FIXED_DATES.put("12-25", "Juldagen");
        FIXED_DATES.put("12-26", "Annandag jul");
        FIXED_DATES.put("12-31", "Nyårsafton");
        FIRST_SATURDAY_DATES.put("06-20", "Midsommardagen");
        FIRST_SATURDAY_DATES.put("10-31", "Allhelgonadagen");
    }

    /**
     * Get a map for all bank holidays for the given year
     *
     * @return
     */
    public Map<LocalDate, String> get() {
        Map<LocalDate, String> result = new TreeMap<>();
        FIXED_DATES.keySet().stream().forEach((date) -> {
            result.put(LocalDate.parse(year + "-" + date, DateTimeFormatter.ISO_DATE), FIXED_DATES.get(date));
        });
        FIRST_SATURDAY_DATES.keySet().stream().map((monthDay) -> createDayFromMontDay(monthDay)).forEach((date) -> {
            String name = FIRST_SATURDAY_DATES.get(date.format(DateTimeFormatter.ISO_DATE).substring(5));
            LocalDate firstSaturday = getFirstSaturday(date);
            result.put(firstSaturday, name);
            if (firstSaturday.getMonth().equals(Month.JUNE)) {
                result.put(firstSaturday.minusDays(1l), "Midsommarafton");
            }
        });
        result.putAll(getEasterDates());
        return result;
    }

    /**
     *
     * <h3>Swedish bank holidays</h3>
     * <p>
     * The swedish bank holidays (in swedish) are:
     * </p>
     * <ul>
     * <li>Nyårsdagen (New Years Day)</li>
     * <li>Trettondagen (Epiphany)</li>
     * <li>Långfredag (Good Friday)</li>
     * <li>Påskdagen (Easter Sunday)</li>
     * <li>Annandag påsk (Easter Monday)</li>
     * <li>Första maj (Labour Day)</li>
     * <li>Nationaldagen (Swedish National Day)</li>
     * <li>Kristi himmelsfärd (Ascension Day)</li>
     * <li>Pingst (Pentecost)</li>
     * <li>Midsommarafton (Midsummers Eve)</li>
     * <li>Midsommardagen (Midsummers Day)</li>
     * <li>Allhelgonadagen (All Saints' Day)</li>
     * <li>Julafton (Christmas Eve)</li>
     * <li>Juldagen (Christmas Day)</li>
     * <li>Annandag jul (Boxing Day)</li>
     * <li>Nyårsafton (New Years Eve)</li>
     * </ul>
     *
     * @param name
     * @return
     */
    public LocalDate findBankHoliday(String name) {
        if (name != null) {
            Map<LocalDate, String> dayMap = get();
            for (LocalDate d : dayMap.keySet()) {
                if (name.equalsIgnoreCase(dayMap.get(d))) {
                    return d;
                }
            }
        }
        return null;
    }

    private Map<LocalDate, String> getEasterDates() {
        Map<LocalDate, String> easterDates = new TreeMap<>();
        LocalDate sunday = new GaussEasterCalculator(year).getEasterSunday();
        easterDates.put(sunday.minusDays(2l), "Långfredag");
        easterDates.put(sunday, "Påskdagen");
        easterDates.put(sunday.plusDays(1l), "Annandag påsk");
        easterDates.put(sunday.plusDays(4l).plusWeeks(5), "Kristi himmelsfärd");
        easterDates.put(sunday.plusWeeks(7l), "Pingst");
        return easterDates;
    }

    private LocalDate createDayFromMontDay(String monthDay) {
        return LocalDate.parse(year + "-" + monthDay, DateTimeFormatter.ISO_DATE);
    }

    /**
     * Returns the LocalDate for the first Saturday as from the provided date.
     *
     * @param date
     * @return
     */
    private LocalDate getFirstSaturday(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return date;
        } else {
            LocalDate nextDate = date.plusDays(1l);
            return getFirstSaturday(nextDate);
        }
    }

    public static void main(String[] args) {
        BankHoliday bankHoliday;
        for (int i = 2016; i < 2017; i++) {
            bankHoliday = new BankHoliday(i);
            for (LocalDate d : bankHoliday.get().keySet()) {
                System.out.print(d);
                System.out.print(" - ");
                System.out.print(d.getDayOfWeek().getDisplayName(TextStyle.FULL, DateUtilConstants.LOCALE));
                System.out.print("\n");
                System.out.println(bankHoliday.get().get(d));

            }
        }
    }
}
