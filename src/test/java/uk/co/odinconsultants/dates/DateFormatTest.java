package uk.co.odinconsultants.dates;

import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class DateFormatTest {

    static {
        TimeZone ApplicationTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(ApplicationTimeZone);
    }

    @Test
    public void datesShouldBeTranslatedToTimestamps() throws Exception {
        Date _7October1999   = toDate("07/10/1999 00:00");
        Date _17December2005 = toDate("17/12/2005 00:00");

        checkNoTimeComponent(new Timestamp(_7October1999.getTime()));
        checkNoTimeComponent(new Timestamp(_17December2005.getTime()));
    }

    private void checkNoTimeComponent(Timestamp t) {
        assertEquals(0L, t.getTime() % 86400);
    }

    private Date toDate(String x) throws Exception {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return new Date(parser.parse(x).getTime());
    }
}
