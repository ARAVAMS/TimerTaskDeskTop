package timetracker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ap50864 on 6/24/14.
 */
public class CaptureScreen {
    public static void main(String[] args) {
        captureScreen(System.currentTimeMillis(), "ap50864", "1", "1");
    }

    public static void captureScreen(long l, String projectID, String taskID,String taskStartTime,String... userID) {
        try {

            Robot robot = new Robot();
            // Capture the whole screen
            Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(area);
            // Write generated image to a file

            try {
                // Save as PNG
                File file = new File(ProjectLiterals.IMAGE_SOURCE_PATH+userID[0]+"___"+projectID+"___"+taskID+"___"+taskStartTime+"___"+l+".png");
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                System.out.println("Could not save full screenshot " + e.getMessage());
            }
        } catch (AWTException e) {
            System.out.println("Could not capture screen " + e.getMessage());
        }
    }

}
