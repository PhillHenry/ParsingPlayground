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
        checkNoTimeComponentIn(timestampFrom(dateOf("07/10/1999 00:00")));
    }

    private Timestamp timestampFrom(Date x) {
        return new Timestamp(x.getTime());
    }

    private void checkNoTimeComponentIn(Timestamp t) {
        assertEquals(0L, t.getTime() % 86400);
    }

    private Date dateOf(String x) throws Exception {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return new Date(parser.parse(x).getTime());
    }
}
