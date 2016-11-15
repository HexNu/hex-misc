package unit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import junit.framework.Assert;
import nu.hex.calendar.core.date.util.BankHoliday;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created 2016-okt-11
 *
 * @author hl
 */
public class BankHolidayTest {

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
    public void bank_holidays_for_any_given_year_should_return_a_map_with_16_keys() {
        BankHoliday bankHoliday = new BankHoliday();
        Assert.assertEquals(16, bankHoliday.get().size());
    }

    @Test
    public void easter_sunday_2016_should_be_on_mars_27() {
        BankHoliday bankHoliday = new BankHoliday(2016);
        Assert.assertEquals(LocalDate.parse("2016-03-27"), bankHoliday.findBankHoliday("Påskdagen"));
    }

    @Test
    public void pentecost_2016_should_be_may_15() {
        BankHoliday bankHoliday = new BankHoliday(2016);
        Assert.assertEquals(LocalDate.parse("2016-05-15"), bankHoliday.findBankHoliday("Pingst"));
    }

    @Test
    public void boxingday_2016_should_be_a_monday() {
        BankHoliday bankHoliday = new BankHoliday(2016);
        Assert.assertEquals(DayOfWeek.MONDAY, bankHoliday.findBankHoliday("Annandag jul").getDayOfWeek());
    }

    @Test
    public void find_non_existing_holiday_should_return_null() {
        BankHoliday bankHoliday = new BankHoliday(2016);
        Assert.assertEquals(null, bankHoliday.findBankHoliday("Tredjedag jul"));
    }
}
