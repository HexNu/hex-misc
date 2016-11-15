package unit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import nu.hex.calendar.core.date.util.BusinessDay;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created 2016-okt-11
 *
 * @author hl
 */
public class BusinessDayTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void january_the_first_should_be_a_bank_holiday() {
        LocalDate date = LocalDate.parse("2016-01-01", DateTimeFormatter.ISO_DATE);
        BusinessDay checker = new BusinessDay();
        Assert.assertEquals(false, checker.isBusinessDay(date));
    }

    @Test
    public void may_the_fifth_2016_should_be_a_bank_holiday() {
        LocalDate date = LocalDate.parse("2016-05-05", DateTimeFormatter.ISO_DATE);
        BusinessDay checker = new BusinessDay();
        Assert.assertEquals(false, checker.isBusinessDay(date));
    }

    @Test
    public void the_first_business_day_after_may_the_fifth_2016_should_be_a_friday() {
        LocalDate date = LocalDate.parse("2016-05-05", DateTimeFormatter.ISO_DATE);
        BusinessDay checker = new BusinessDay();
        Assert.assertEquals(DayOfWeek.FRIDAY, checker.getFirstBusinessDay(date).getDayOfWeek());
    }

    @Test
    public void the_first_business_day_after_christmas_2016_should_be_a_tuesday() {
        LocalDate date = LocalDate.parse("2016-12-24", DateTimeFormatter.ISO_DATE);
        BusinessDay checker = new BusinessDay();
        Assert.assertEquals(checker.getFirstBusinessDay(date).getDayOfWeek(), DayOfWeek.TUESDAY);
    }
}
