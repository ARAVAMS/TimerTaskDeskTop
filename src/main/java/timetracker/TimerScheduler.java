package timetracker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public final class TimerScheduler {

    public static void main(String... aArgs) throws InterruptedException {
        log("Main started.");
        TimerScheduler timerScheduler = new TimerScheduler(3, 5,"testProject","testTask","12:00:2");
        timerScheduler.activateScreenCaptureTimer();
        log("Main ended.");
    }

    TimerScheduler(long aInitialDelay, long aDelayBetweenBeeps,String selectedProject, String selectedTask,String taskStartTime){
        fInitialDelay = aInitialDelay;
        fDelayBetweenRuns = aDelayBetweenBeeps;
        fSelectedProject = selectedProject;
        fSelectedTask = selectedTask;
        fTaskStartTime = taskStartTime;
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
    }


    void activateScreenCaptureTimer(){
        Runnable activateScreenCaptureTimerScheduler = new ActivateScreenCaptureTimerScheduler();
        fScheduler.scheduleWithFixedDelay(
                activateScreenCaptureTimerScheduler, fInitialDelay, fDelayBetweenRuns, TimeUnit.MINUTES
        );
    }

    public final class ActivateScreenCaptureTimerScheduler implements Runnable {
        @Override public void run() {
            ++fCount;
            log("beep " + fCount);
            CaptureScreen.captureScreen(System.currentTimeMillis(), fSelectedProject, fSelectedTask,fTaskStartTime, "AP50864", "ssAP50864");

        }

        private int fCount;
    }

    public void stopTask() {
        fScheduler.shutdown();
    }

    // PRIVATE
    private final ScheduledExecutorService fScheduler;
    private final long fInitialDelay;
    private final long fDelayBetweenRuns;
    private String fSelectedProject="";
    private String fSelectedTask="";
    private String fTaskStartTime="";

    private static void log(String aMsg){
        System.out.println(aMsg +"----"+System.currentTimeMillis());
    }

    /** If invocations might overlap, you can specify more than a single thread.*/
    private static final int NUM_THREADS = 1;
}