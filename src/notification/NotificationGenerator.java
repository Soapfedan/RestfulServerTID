package notification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

import userposition.UserPosition;
import userposition.UserPositionAdapter;
import userposition.UserPositionList;

public class NotificationGenerator {

	private static Thread t;
	private static boolean running = true;
	private static NotificationList notlist;
	private static UserPositionList userlist;
	private final static String USER_AGENT = "Mozilla/5.0";

	
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
					if(notlist.getList()!=null){
						notlist.getList().clear();
					}
					notlist.setList(NotificationAdapter.getAllNot());
					
					
					String json = "{ \"notifications\": [ ";
					for(int k=0;k<notlist.getList().size();k++){
						json = json + "{ "
								+"\"id\" :" + "\""+notlist.getList().get(k).getId()+"\","
								+"\"cod_cat\" :" + "\"" + notlist.getList().get(k).getCod_cat()+"\","
								+"\"floor \" :" + "\""+ notlist.getList().get(k).getRoom()+"\","
								+"\"room\" :" + "\""+ notlist.getList().get(k).getRoom() +"\" },";
					}
					json = json + " ]"
							+ "}";
					
					//System.out.println(json);
					if(userlist.getUsers()!=null){
						userlist.getUsers().clear();
					}
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
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					running=false;
				}
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		notificationThread();
		t.start();
	}
	
	// HTTP POST request
		private static void sendPost(String url,String json) throws Exception {

			//String url = "https://selfsolve.apple.com/wcResults.do";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			//wr.writeBytes(urlParameters);
			wr.writeChars("prova funziona");
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + json);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

		}


}
