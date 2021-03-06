package serverui;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class Debug{
	private static JTextArea output;
	
	public static void setOutputDestination(JTextArea component){
		output = component;
	}
	
	public static void log(String str){
		DateFormat format = new SimpleDateFormat("[HH:mm:ss]");
		Date date = new Date();
		output.setForeground(Color.BLACK);
		output.append(format.format(date) + " " + str + "\r\n");
		output.setCaretPosition(output.getText().length());
	}
	
	public static void err(String str){
		DateFormat format = new SimpleDateFormat("[HH:mm:ss]");
		Date date = new Date();
		output.setForeground(Color.RED);
		output.append(format.format(date) + " ERROR: " + str + "\r\n");
		output.setCaretPosition(output.getText().length());
	}
}
