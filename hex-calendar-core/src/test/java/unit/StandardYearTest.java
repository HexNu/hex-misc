package unit;

import junit.framework.Assert;
import nu.hex.calendar.core.calendar.rpg.old.Year;
import nu.hex.calendar.core.calendar.sample.local.StandardYear;
import org.junit.Test;

/**
 * Created 2016-okt-12
 *
 * @author hl
 */
public class StandardYearTest {

    @Test
    public void standardYearGetNumberOfWeeksShouldReturn52() {
        Year year = new StandardYear();
        Integer expected = 52;
        Assert.assertEquals(expected, year.getNumberOfWeeks());
    }
}
