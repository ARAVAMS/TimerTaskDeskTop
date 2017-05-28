package epat2.util;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sivaprakash on 11/4/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class UtilityTest extends TestCase {
    @Autowired
    private Utility utility;
    @Test
    public void testGetCurrentTime() {

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expected = java.util.TimeZone.getDefault().getDisplayName()+"("+java.util.TimeZone.getDefault().getID()+") : "+df.format(date);
        Assert.assertEquals(expected, DateUtil.getCurrentDateTime());
    }

    @Test
    public void testGetLastLineInFile() throws IOException {
        utility.getLastLineInFile();
    }
}
