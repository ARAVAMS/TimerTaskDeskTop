package epat2.timerscheduler;

import epat2.mq.SenderImages;
import timetracker.ProjectLiterals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public final class UploadImageTimerScheduler {




    public static void main(String... aArgs) throws InterruptedException {
        UploadImageTimerScheduler timerScheduler = new UploadImageTimerScheduler(1, 2);
        timerScheduler.activateUploadImageProcess();
       /* File folder = new File("c:\\Tempsiva\\AP50864___ Project B___Task A___1406530415511.png");
        fileMoveToBackUpFolder(folder);*/
    }

    public UploadImageTimerScheduler(long aInitialDelay, long aDelayBetweenBeeps){
        fInitialDelay = aInitialDelay;
        fDelayBetweenRuns = aDelayBetweenBeeps;
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
    }


    public void activateUploadImageProcess(){
        Runnable activateUploadImageProcess = new ActivateScreenCaptureTimerScheduler();
        fScheduler.scheduleWithFixedDelay(
                activateUploadImageProcess, fInitialDelay, fDelayBetweenRuns, TimeUnit.MINUTES
        );
    }

    public File[] getListofFile() {

        File folder = new File(ProjectLiterals.IMAGE_SOURCE_PATH);
        File[] listOfFiles = folder.listFiles();

        return listOfFiles;
    }

    private final class ActivateScreenCaptureTimerScheduler implements Runnable {
        @Override
        public void run() {
            File[] fileList = getListofFile();

            for(File file : fileList){
                Boolean isImageSent = SenderImages.sendMessage(file);
                if(isImageSent)
                    fileMoveToBackUpFolder(file);
            }
        }


    }

    private static void fileMoveToBackUpFolder(File file) {
        try {

            Files.move(file.toPath(),new File(ProjectLiterals.BACK_UP_IMAGE_FILE_STORAGE +file.getName()).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void stopTask() {
        fScheduler.shutdown();
    }

    // PRIVATE
    private final ScheduledExecutorService fScheduler;
    private final long fInitialDelay;
    private final long fDelayBetweenRuns;


    /** If invocations might overlap, you can specify more than a single thread.*/
    private static final int NUM_THREADS = 1;
}