package epat2.fileprocess;

import epat2.util.DateUtil;
import timetracker.ProjectLiterals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sivaprakash on 7/1/14.
 */
public class TaskProjectFileWriter {




    public static void writeTaskHours(String project, String task,String time,String requestFrom, String... empIDs) {
        String fileDirectory = ProjectLiterals.TEXTFILE_SOURCE_PATH;
        String fileName = empIDs[0] + "___" + project + "___" + task + ".txt";

        try {

            prepareDirectoryFile(fileName,fileDirectory, empIDs);

            FileWriter outFile = new FileWriter(fileDirectory+fileName,true);

            PrintWriter out = new PrintWriter(outFile);

            out.println(requestFrom+" -- "+ DateUtil.getCurrentDateTime()+" -- "+time);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static void prepareDirectoryFile(String fileName,String fileDirectory, String... employeeIds) throws IOException {
        File f;
        File fDirectory ;
        try{
            f = new File(fileDirectory.concat(fileName));

            if(!f.exists()){
                fDirectory = new File(fileDirectory);

                if(!fDirectory.isDirectory()){
                    System.out.println(" file directory exist"+fDirectory.toPath());
                    fDirectory.mkdir();
                }

                f.createNewFile();
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception ew) {
            ew.printStackTrace();
        }finally{
            f = null;
            fDirectory = null;
        }
    }

    public static  void  main(String arg[]) throws IOException {

        writeTaskHours("testP","testTssw","ap50864","","");

    }

}
