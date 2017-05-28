package epat2.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by sivaprakash on 11/4/14.
 */
@Component
public class Utility {

    final static Charset ENCODING = StandardCharsets.UTF_8;

    public static  String getLastLineInFile() throws IOException {
        List<String> filedata;
        filedata = readSmallTextFile("C:\\bench\\TimerTaskDeskTop\\src\\test\\java\\epat2\\util\\Ap50864___ Project B___Task A.txt");
        return filedata.get(filedata.size()-1);
    }
    /**
     Note: the javadoc of Files.readAllLines says it's intended for small
     files. But its implementation uses buffering, so it's likely good
     even for fairly large files.
     */
    private static List<String> readSmallTextFile(String aFileName) throws IOException {

        Path path = Paths.get(aFileName);

        return Files.readAllLines(path, ENCODING);
    }
}
