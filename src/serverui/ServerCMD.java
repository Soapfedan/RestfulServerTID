package serverui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.transaction.Transactional.TxType;

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
		//scroll = new JScrollPane();
		JScrollPane scroll = new JScrollPane(textArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );

		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setLineWrap(true);
		scroll.setViewportView(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    frame.getContentPane().add(scroll);
	    scroll.setVisible(true);
		textArea.setEditable(false);
	//	textArea.setRows(500);
		scroll.setBounds(50, 50, 500, 180);
		//frame.getContentPane().add(textArea);
		frame.setResizable(false);
		Debug.setOutputDestination(textArea);
		
		JButton start_B = new JButton("Start Server");
		start_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ServerCMD.getTextArea().setText("");
				Debug.log("Inizio a inviare le notifiche");
				NotificationGenerator.startThread(false);
			}
		});
		JButton btn_end_emergency = new JButton("Stop Emergency");
		btn_end_emergency.setBounds(220, 310, 140, 50);
		frame.getContentPane().add(btn_end_emergency);
		
		btn_end_emergency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(NotificationGenerator.isWorking()){
					Debug.log("Aspetta che il server termini l'esecuzione");
				}else{					
					Debug.log("Avverto gli utenti della fine del pericolo");
					NotificationGenerator.startThread(true);
				}	
				
			}
		});
		
		start_B.setBounds(118, 255, 120, 50);
		frame.getContentPane().add(start_B);
		
		JButton stop_B = new JButton("Stop Server");
		stop_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				NotificationGenerator.setStopRequest(true);
				if(NotificationGenerator.isWorking()){
					Debug.log("Termino la richiesta e  spengo il server");
				}else{					
					Debug.log("Il server e' stato stoppato");
					check = true;
				}	
				NotificationGenerator.stopThread();
				// per evitare due messaggi uguali e distinguere i casi
				
				
			}
		});
		stop_B.setBounds(344, 255, 120, 50);
		frame.getContentPane().add(stop_B);
		
		JLabel lblServerLog = new JLabel("Server Log:");
		lblServerLog.setBounds(70, 21, 78, 24);
		frame.getContentPane().add(lblServerLog);
		frame.setVisible(true);
		
	}


	


	public static JTextArea getTextArea() {
		return textArea;
	}

	
}
