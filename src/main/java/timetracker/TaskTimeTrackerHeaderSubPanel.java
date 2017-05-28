package timetracker;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ap50864 on 7/28/14.
 */
public class TaskTimeTrackerHeaderSubPanel extends JPanel {

    TaskTimeTrackerHeaderSubPanel me;

    private JLabel projectNameJLabel;

    private JLabel taskNameJLabel;

    private JLabel timeJLabel;

    public TaskTimeTrackerHeaderSubPanel() {

        super();

        me = this;

        projectNameJLabel = new JLabel();

        taskNameJLabel = new JLabel();

        timeJLabel = new JLabel();

        JLabel deleteLable= new JLabel();

        projectNameJLabel.setText("Project Name: ");

        taskNameJLabel.setText("Task Name :");

        timeJLabel.setText(" Time (HH:MM:SS): ");

        deleteLable.setText(" ");

        projectNameJLabel.setPreferredSize(new Dimension(240,40));

        taskNameJLabel.setPreferredSize(new Dimension(240,40));

        timeJLabel.setPreferredSize(new Dimension(160,40));

        deleteLable.setPreferredSize(new Dimension(20,40));

        add(projectNameJLabel);

        add(taskNameJLabel);

        add(timeJLabel);

        add(deleteLable);

    }

}
