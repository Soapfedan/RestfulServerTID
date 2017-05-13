package serverui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

import notification.NotificationGenerator;
import notification.NotificationList;

public class ServerCMD {

	private static JFrame frame;
	private static JTextArea textArea;
	private static JScrollPane scroll;

	
	public static void startApplication(){
		frame = new JFrame("Server");
		frame.setSize(600, 400);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setWrapStyleWord(true);
		textArea.setText("");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(50, 50, 500, 180);
		scroll = new JScrollPane();
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll.setViewportView(textArea);
	    frame.getContentPane().add(scroll);
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		Debug.setOutputDestination(textArea);
		
		JButton start_B = new JButton("Send notifications");
		start_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ServerCMD.getTextArea().setText("");
				Debug.log("Inizio a inviare le notifiche");
				NotificationGenerator.startThread();
			}
		});
		start_B.setBounds(118, 255, 120, 50);
		frame.getContentPane().add(start_B);
		
		JButton stop_B = new JButton("Stop");
		stop_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotificationGenerator.stopThread();
				Debug.log("Server stoppato");
			}
		});
		stop_B.setBounds(344, 255, 120, 50);
		frame.getContentPane().add(stop_B);
		
		JLabel lblServerLog = new JLabel("Server Log:");
		lblServerLog.setBounds(131, 21, 78, 24);
		frame.getContentPane().add(lblServerLog);
		frame.setVisible(true);
		
	}


	


	public static JTextArea getTextArea() {
		return textArea;
	}

	
}
