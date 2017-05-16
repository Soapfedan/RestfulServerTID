package notification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

import serverui.Debug;
import serverui.ServerCMD;
import userposition.UserPosition;
import userposition.UserPositionAdapter;
import userposition.UserPositionList;

public class NotificationGenerator {

	private static Thread t;
	private static boolean running = true;
	private final static String USER_AGENT = "Mozilla/5.0";
	private static Runnable r;
	private static boolean stopRequest=false,working=false;
	private static int count;
	
	
	private static Runnable notificationThread(boolean stop){
		NotificationList notlist = new NotificationList();
		UserPositionList userlist = new UserPositionList();
		r = new Runnable() {
			
			@Override
			public void run() {
				while(running){
				/*
				 * codice categoria
				 * 1 incendio
				 * 2 gas
				 * 3 terremoto/crollo
				 * 4 altro	
				 */
					
					//wait until the user clicks on the send button
					
					/*
					if(notlist.getList()!=null){
						notlist.getList().clear();
					}*/
					
					
					
					String json = "{ \"notifications\": [ ";
					if(!stop){
						notlist.setList(NotificationAdapter.getAllNot());
					for(int k=0;k<notlist.getList().size()-1;k++){
						json = json + "{ "
								+"\"id\" :" + "\""+notlist.getList().get(k).getId()+"\","
								+"\"cod_cat\" :" + "\"" + notlist.getList().get(k).getCod_cat()+"\","
								+"\"floor\" :" + "\""+ notlist.getList().get(k).getFloor()+"\","
								+"\"room\" :" + "\""+ notlist.getList().get(k).getRoom() +"\" },";
					}
					json = json + "{ "
							+"\"id\" :" + "\""+notlist.getList().get(notlist.getList().size()-1).getId()+"\","
							+"\"cod_cat\" :" + "\"" + notlist.getList().get(notlist.getList().size()-1).getCod_cat()+"\","
							+"\"floor\" :" + "\""+ notlist.getList().get(notlist.getList().size()-1).getFloor()+"\","
							+"\"room\" :" + "\""+ notlist.getList().get(notlist.getList().size()-1).getRoom() +"\" }";
					 
					
					}
					json = json + " ]"
							+ "}";
					System.out.println("Stop "+stop);
					System.out.println(json);
					//if(userlist.getUsers()!=null){
						//userlist.getUsers().clear();
					//}
					
					try {
						userlist.setUsers(UserPositionAdapter.getallpositions());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count = 0;
					for(int i=0;i<userlist.getUsers().size();i++){
						String ip = userlist.getUsers().get(i).getUser_ID();
						ip = "http://"+ip+":8888";
						try {
							if(running){
								Debug.log("Comunicazione con "+ip);
								count++;
								working = true;
								sendPost(ip, json);
								
							}
						}catch (ConnectException e1) {
							Debug.err("Utente non raggiungibile ip: "+ip);
							working = false;
							continue;
						}catch(SocketTimeoutException e3){
							Debug.err("Connessione scaduta con "+ip);
							working = false;
							continue;
						}catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							working = false;
						}
						working = false;
					
					//running=false;
						
					}
					//we have to reblock sending of messages
					running=false;
					//working = false;
					if(stopRequest){
						Debug.log("Il server e' stato stoppato");
					}
					if(userlist.getUsers().size()==0){
						Debug.log("Nessun utente collegato");
					}else{
						if(count==userlist.getUsers().size()){
							Debug.log("Ho terminato la lista degli utenti");
						}
					}
					//Debug.log("Terminata la procedura di invio");
					
					
				
				}
			}
		};
		return r;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//notificationThread();
		//t.start();
		ServerCMD.startApplication();
		
	}
	
	// HTTP POST request
		private static void sendPost(String url,String json) throws Exception {
			
			//String url = "https://selfsolve.apple.com/wcResults.do";
			URL obj = new URL(url);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setConnectTimeout(5 * 1000);

			//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// Send post request
			if(con!=null){
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			//wr.writeBytes(urlParameters);			
			Charset.forName("UTF-8").encode(json);
			wr.writeChars(json);
			wr.flush();
			wr.close();
			}
			int responseCode = con.getResponseCode();
			Debug.log("Sending 'POST' request to URL : " + url);
			//System.out.println("Post parameters : " + json);
			Debug.log("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			/*
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
*/
			//print result
			System.out.println(response.toString());						
			

		}

		public static void startThread(boolean s){			
			if(t != null){
				if(!t.isAlive()){
				notificationThread(s);
				t = new Thread(r);
				running = true;
				stopRequest = false;
				working = false;
				t.start();
				}
			}else{
				Runnable r = notificationThread(s);
				t = new Thread(r);
				t.start();
			}
			
			
		}

		public static void stopThread(){
			if(t!=null){
				t.interrupt();
			}
			running = false;
			stopRequest = false;
			working = false;
			//System.out.println("running"+running);
		}
		
		public static boolean getStopRequest(){
			return stopRequest;
		}
		
		public static void setStopRequest(boolean s){
			stopRequest = s;
		}
		public static boolean isWorking() {
			return working;
		}
		public static void setWorking(boolean working) {
			NotificationGenerator.working = working;
		}
		
		
		
		
}
