package epat2.fileprocess;

import timetracker.ProjectLiterals;

import java.io.BufferedReader;
import java.io.FileReader;
/**
 * Created by sivaprakash on 7/1/14.
 */
public class TaskProjectFileReader   {

    public static String getProjects(String empID){
        //Name of the file
        String fileName= ProjectLiterals.projectTaskFileLocation +empID+"_projects.txt";
        StringBuilder projects = new StringBuilder();
        try{

            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            bufferReader.toString();
            //Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null)   {
                projects.append(line);
            }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){
            System.out.println("Error while reading file line by line:"
                    + e.getMessage());
        }
        return projects.toString();
    }

    public static String getTasks(String empID){
        //Name of the file
        String fileName=ProjectLiterals.projectTaskFileLocation+empID+"_tasks.txt";
        StringBuilder projects = new StringBuilder();
        try{

            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null)   {
                projects.append(line);
            }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){
            System.out.println("Error while reading file line by line:"
                    + e.getMessage());
        }
        return projects.toString();
    }

}
