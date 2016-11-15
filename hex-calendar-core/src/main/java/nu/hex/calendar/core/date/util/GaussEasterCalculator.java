package nu.hex.calendar.core.date.util;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author hl
 */
class GaussEasterCalculator {

    private final int year;
    private static final int M = 24, N = 5;

    public GaussEasterCalculator(int year) {
        this.year = year;
    }

    public LocalDate getEasterSunday() {
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (19 * a + M) % 30;
        int e = (2 * b + 4 * c + 6 * d + N) % 7;
        int f = 22 + d + e;
        LocalDate result = LocalDate.parse(year + "-03-01").plusDays(Long.valueOf(f - 1));
        if ((result.getMonth().equals(Month.APRIL) && result.getDayOfMonth() == 26 && d == 29 && e == 6)
                || (result.getMonth().equals(Month.APRIL) && result.getDayOfMonth() == 25 && d == 28 && e == 6 && a > 10)) {
            result = result.minusDays(7);
        }
        return result;
    }
}
