package epat2.timerscheduler;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sivaprakash on 11/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class UploadTextFileTimerSchedulerTest extends TestCase {

    @Autowired
    private UploadTextFileTimerScheduler uploadTextFileTimerScheduler;
    public void testMain() throws Exception {

    }

    public void testActivateUploadTextFileProcess() throws Exception {

    }

    @Test
    public void testGetListofFile() throws Exception {
        uploadTextFileTimerScheduler.getListofFile();

    }

    public void testStopTask() throws Exception {

    }
}
