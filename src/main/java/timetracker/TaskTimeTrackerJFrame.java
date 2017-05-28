package timetracker;

/**
 * Created by sivaprakash on 6/29/14.
 * epat2 : Employee project activity time tracker
 */

import epat2.timerscheduler.UploadImageTimerScheduler;
import epat2.timerscheduler.UploadTextFileTimerScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskTimeTrackerJFrame extends JFrame {

	static TaskTimeTrackerJFrame myFrame;

	TimerScheduler timerScheduler;

	JPanel mainPanel;

	public TaskTimeTrackerJFrame(String s) {

		super(s);
		// start Image upload process on application startup
		UploadImageTimerScheduler timerScheduler = new UploadImageTimerScheduler(
				1, 2);
		timerScheduler.activateUploadImageProcess();

		UploadTextFileTimerScheduler textFileTimerScheduler = new UploadTextFileTimerScheduler(
				1, 2);
		textFileTimerScheduler.activateUploadTextFileProcess();
		initComponents();

	}

	private void initComponents() {

		System.out.println(System.getProperty("os.name"));

		manageMenu();

		prepareUI();

		pack();

	}

	private void prepareUI() {

		mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// mainPanel.setMaximumSize(new Dimension(450, 450));

		mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		mainPanel.add(new TaskTimeTrackerHeaderSubPanel());

		mainPanel.add(new TaskTimeTrackerSubPanel(this));

		getContentPane().add(mainPanel, BorderLayout.CENTER);

	}

	private void manageMenu() {

		jMenu1 = new JMenu();

		jMenuBar1 = new JMenuBar();

		jMenuItem1 = new JMenuItem();

		jMenuItem2 = new JMenuItem();

		jMenu1.setText("File");

		jMenuItem1.setText("AddNewTask");

		jMenuItem1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				jMenuItem1ActionPerformed(evt);

			}

		});

		jMenu1.add(jMenuItem1);

		jMenuItem2.setText("RemoveTasks");

		jMenuItem2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				jMenuItem2ActionPerformed(evt);

			}

		});

		jMenu1.add(jMenuItem2);

		jMenuBar1.add(jMenu1);

		setJMenuBar(jMenuBar1);

	}

	private void jMenuItem1ActionPerformed(ActionEvent evt) {

		mainPanel.add(new TaskTimeTrackerSubPanel(myFrame));

		myFrame.pack();

		/*
		 * if(totalTasks==9){
		 * 
		 * // JScrollPane scrollBar=new
		 * JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
		 * ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 * 
		 * JScrollPane scrollBar=new
		 * JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
		 * ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); myFrame.add(scrollBar);
		 * 
		 * myFrame.setPreferredSize(new Dimension(740,380));
		 * 
		 * }
		 */

		myFrame.pack();

	}

	private void jMenuItem2ActionPerformed(ActionEvent evt) {

		// TODO add your handling code here:

	}

	/**
	 * 
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args)

	{
		/* Create and display the form */

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				createAndShowGUI();

			}

		});

	}

	private static void createAndShowGUI() {

		myFrame = new TaskTimeTrackerJFrame(" Resource Task Time Tracker : ");

		myFrame.setResizable(false);

		// myFrame.setSize(500, 500);

		Insets insets1 = myFrame.getInsets();

		myFrame.setSize(300 + insets1.left + insets1.right,

		125 + insets1.top + insets1.bottom);

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myFrame.pack();

		myFrame.setVisible(true);

	}

	// Variables declaration - do not modify

	private JMenu jMenu1;

	private JMenuBar jMenuBar1;

	private JMenuItem jMenuItem1;

	private JMenuItem jMenuItem2;

}
