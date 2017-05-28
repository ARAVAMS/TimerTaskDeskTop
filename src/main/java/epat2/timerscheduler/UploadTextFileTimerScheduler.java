package epat2.timerscheduler;

import epat2.mq.SenderTextFiles;
import timetracker.ProjectLiterals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public final class UploadTextFileTimerScheduler {




    public static void main(String... aArgs) throws InterruptedException {
        UploadTextFileTimerScheduler timerScheduler = new UploadTextFileTimerScheduler(1, 2);
      //  timerScheduler.activateUploadTextFileProcess();
       /* File folder = new File("c:\\Tempsiva\\AP50864___ Project B___Task A___1406530415511.png");
        fileMoveToBackUpFolder(folder);*/

        timerScheduler.getListofFile();
    }

    public UploadTextFileTimerScheduler(long aInitialDelay, long aDelayBetweenRuns){
        fInitialDelay = aInitialDelay;
        fDelayBetweenRuns = aDelayBetweenRuns;
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
    }


    public void activateUploadTextFileProcess(){
      Runnable activateUploadTextFileProcess = new ActivateScreenCaptureTimerScheduler();
        fScheduler.scheduleWithFixedDelay(
                activateUploadTextFileProcess, fInitialDelay, fDelayBetweenRuns, TimeUnit.MINUTES
        );
    }

   public File[] getListofFile() {

        File folder = new File(ProjectLiterals.TEXTFILE_SOURCE_PATH);
        File[] listOfFiles = folder.listFiles();

        return listOfFiles;
    }

   /* public File[] getListofFile() {

        // Creating a DirectoryStream which accepts only filenames ending with '.exe'
        Path pp = FileSystems.getDefault().getPath(ProjectLiterals.TEXTFILE_SOURCE_PATH);
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(pp, "*.txt")) {
            for (Path p : ds) {
                // Iterate over the paths in the directory and print filenames
                System.out.println(p.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    private final class ActivateScreenCaptureTimerScheduler implements Runnable {
        int i=0;

        @Override
        public void run() {
            File[] fileList = getListofFile();

            for(File file : fileList){
              boolean isTextFileSent = SenderTextFiles.sendTextFileMessage(file);
                if(isTextFileSent)
                    fileMoveToBackUpFolder(file);

            }
        }

    }

    private static void fileMoveToBackUpFolder(File file) {
        try {
//TODO file already exist system should move data and delete file from source path
            Files.move(file.toPath(),new File(ProjectLiterals.BACK_UP_TEXT_FILE_STORAGE +"_"+System.currentTimeMillis()+file.getName()).toPath());
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