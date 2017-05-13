package notification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
	private static NotificationList notlist;
	private static UserPositionList userlist;
	private final static String USER_AGENT = "Mozilla/5.0";
	private static boolean wait = false;
	
	
	private static void notificationThread(){
		notlist = new NotificationList();
		userlist = new UserPositionList();
		t = new Thread(new Runnable() {
			
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
					
					
					if(notlist.getList()!=null){
						notlist.getList().clear();
					}
					notlist.setList(NotificationAdapter.getAllNot());
					
					
					String json = "{ \"notifications\": [ ";
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
					 
					json = json + " ]"
							+ "}";
					
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
					
					for(int i=0;i<userlist.getUsers().size();i++){
						String ip = userlist.getUsers().get(i).getUser_ID();
						ip = "http://"+ip+":8888";
						try {
							sendPost(ip, json);
						}catch (ConnectException e1) {
							Debug.err("Utente non raggiungibile ip: "+ip);
							continue;
						}catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					//running=false;
						
					}
					//we have to reblock sending of messages
					running=false;
					
					
				
				}
			}
		});
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

		public static void startThread(){			
			notificationThread();
			t.start();
		}

		public static void stopThread(){
			t.interrupt();
			running = false;
			//System.out.println("running"+running);
		}
}
