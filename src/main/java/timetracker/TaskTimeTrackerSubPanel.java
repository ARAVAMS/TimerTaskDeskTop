package timetracker;

import epat2.fileprocess.TaskProjectFileReader;
import epat2.fileprocess.TaskProjectFileWriter;
import epat2.util.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ap50864 on 7/28/14.
 */
public class TaskTimeTrackerSubPanel extends JPanel {

    TaskTimeTrackerSubPanel me;
    JLabel time = new JLabel();
    JButton startTaskJButton = new JButton();
    JButton stopDeleteTaskJButton = null;
    TimerScheduler timerScheduler = null;
    static TaskTimeTrackerJFrame myFrame;

    private boolean isTaskInprogress = false;
    private boolean isTaskPause = false;

    private CountTimer cntd ;

    Image image = null;

    class CountTimer implements ActionListener {

        private static final int ONE_SECOND = 1000;
        private int count = 0;
        private boolean isTimerActive = false;
        private Timer tmr = new Timer(ONE_SECOND, this);

        public CountTimer() {
            count=0;
            time.setText(TimeFormat(count));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isTimerActive) {
                count++;
                time.setText(TimeFormat(count));
            }
        }

        public void start(String selectedProject, String selectedTask,String time) {
            count = 0;
            isTimerActive = true;

            writeTask(selectedProject,selectedTask,time,"Start Time ::");
            captureImageStart(selectedProject,selectedTask, DateUtil.getCurrentDateTimeForImage());

            tmr.start();
        }

        public void resume(String projectName, String taskName, String time) {
            isTimerActive = true;
            writeTask(projectName, taskName, time, "Resume Time::");
            tmr.restart();
        }

        public void stop(String projectName, String taskName, String time) {
            writeTask(projectName, taskName, time, "End Time   ::");
            captureImageStop();
            tmr.stop();
        }

        public void pause(String selectedProject, String selectedTask,String time) {

            writeTask(selectedProject,selectedTask,time,"Pause Time ::");
            captureImageStop();
            isTimerActive = false;
        }

        public void reset() {
            count = 0;
            isTimerActive = true;
            tmr.restart();

        }

    }

    private void writeTask(String projectName, String taskName, String time, String fromStop) {

         TaskProjectFileWriter.writeTaskHours(projectName, taskName, time, fromStop, "Ap50864");
    }

    private void captureImageStart(String selectedProject, String selectedTask,String taskStartTime) {
        // image capture - start
        timerScheduler = new TimerScheduler(1, 1,selectedProject,selectedTask,taskStartTime);
        timerScheduler.activateScreenCaptureTimer();
        // image capture - end
    }

    private void captureImageStop() {
        // image capture - start
        timerScheduler.stopTask();
        // image capture - end
    }


    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count-hours*3600)/60;
        int seconds = count-minutes*60;

        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

    public TaskTimeTrackerSubPanel(TaskTimeTrackerJFrame myFrame_arg) {

        super();
        myFrame = myFrame_arg;

        cntd = new CountTimer();
        me = this;

        final JComboBox  projectsJComboBox = new JComboBox();

        projectsJComboBox.setPreferredSize(new Dimension(220,25));

        String projectsList = TaskProjectFileReader.getProjects("pu00741");


        projectsJComboBox.setModel(new DefaultComboBoxModel(projectsList.split(",")));

        projectsJComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.println(projectsJComboBox.getSelectedItem().toString());

                projectsJComboBoxActionPerformed(evt);

            }

        });



        final JComboBox  taskJComboBox = new JComboBox();

        taskJComboBox.setPreferredSize(new Dimension(220,20));

        String taskList = TaskProjectFileReader.getTasks("pu00741");

        taskJComboBox.setModel(new DefaultComboBoxModel(taskList.split(",")));


        taskJComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.println(taskJComboBox.getSelectedItem().toString());

                projectsJComboBoxActionPerformed(evt);

            }

        });



        time.setText(ProjectLiterals.DEFAULT_TIME);

        time.setPreferredSize(new Dimension(200,20));

        me.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));

        add(projectsJComboBox);

        add(taskJComboBox);

        add(time);


        //start task button

        startTaskJButton = new JButton();
        stopDeleteTaskJButton = new JButton();

        startTaskJButton.setToolTipText("Start Task");

        startTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/startImage.gif"))));

        startTaskJButton.setPreferredSize(new Dimension(20, 20));

        startTaskJButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {


                isTaskInprogress = true;
                if (!isTaskPause) {
                    startTaskJButton.setToolTipText("Pause Task");

                    startTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/pauseImage.gif"))));

                    if (time.getText().equalsIgnoreCase(ProjectLiterals.DEFAULT_TIME)) {
                        //  count++;
                        //  time.setText(TimeFormat(count));


                        isTaskPause = true;
                        cntd.start(projectsJComboBox.getModel().getSelectedItem().toString(),taskJComboBox.getModel().getSelectedItem().toString(),time.getText());
                    } else {

                        isTaskPause = true;
                        cntd.resume(projectsJComboBox.getModel().getSelectedItem().toString(),taskJComboBox.getModel().getSelectedItem().toString(),time.getText());
                    }
                } else {
                    isTaskPause = false;

                    startTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/resumeImage.gif"))));

                    startTaskJButton.setToolTipText("Resume Task");
                    cntd.pause(projectsJComboBox.getModel().getSelectedItem().toString(),taskJComboBox.getModel().getSelectedItem().toString(),time.getText());
                }

                stopDeleteTaskJButton.setToolTipText("Stop Task");



                stopDeleteTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/stopImage.gif"))));

                myFrame.pack();

            }

            /* private static final int ONE_SECOND = 1000;
             private int count = 0;
             private boolean isTimerActive = false;
             private Timer tmr = new Timer(ONE_SECOND, this);*/



        });

        add(startTaskJButton);
        // end start task
//            JButton stopDeleteTaskJButton = new JButton();

        stopDeleteTaskJButton.setToolTipText("Delete Task");

        stopDeleteTaskJButton.setPreferredSize(new Dimension(20, 20));



        stopDeleteTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/deleteImage.gif"))));

        stopDeleteTaskJButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {


                if (!isTaskInprogress) {

                    me.getParent().remove(me);

                    myFrame.pack();
                } else
                {
                     cntd.stop(projectsJComboBox.getModel().getSelectedItem().toString(), taskJComboBox.getModel().getSelectedItem().toString(),time.getText());

                    isTaskPause = false;
                    time.setText(ProjectLiterals.DEFAULT_TIME);

                    stopDeleteTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/deleteImage.gif"))));


                    stopDeleteTaskJButton.setToolTipText("Delete Task");


                    startTaskJButton.setToolTipText("Start Task");


                    startTaskJButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/startImage.gif"))));

                    isTaskInprogress = false;
                }

            }

        });

        add(stopDeleteTaskJButton);

    }
    private void projectsJComboBoxActionPerformed(ActionEvent evt) {

        // TODO add your handling code here:

    }

    private void jComboBox4ActionPerformed(ActionEvent evt) {

        // TODO add your handling code here:

    }
}