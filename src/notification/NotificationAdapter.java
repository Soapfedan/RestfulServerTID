package notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beacon.value.BeaconValue;
import database.DatabaseConnection;

public class NotificationAdapter {
	
	//create notification
	public static void createNot(Notification not){
		
		String sql = "insert into notifications(id,cod_cat,floor,room)"
				+ "values(?,?,?,?)";
		 
	        	try (Connection conn = DatabaseConnection.connect();
	                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        		
	        		pstmt.setInt(1, getNewId()+1);
	        	    pstmt.setInt(2,not.getCod_cat());
	        	    pstmt.setString(3, not.getFloor());
	        	    pstmt.setString(4, not.getRoom());
	        		
	                pstmt.executeUpdate();
				
	        	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	//get new id
	private static int getNewId(){
		
		String sql = "select max(id) as idmax from notifications";
		 Statement stat = null;
		 ResultSet rs = null;
		 int id = 0;
	        	try (Connection conn = DatabaseConnection.connect()){
	                stat = conn.createStatement();
	                rs = stat.executeQuery(sql);
	        		
	                while(rs.next()){
	                	id = rs.getInt("idmax");
	                }
	        	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	return id;
	}
	
	public static ArrayList<Notification> getAllNot(){
		
			String sql = "select * from notifications";
			Connection conn = DatabaseConnection.connect();
		 	Statement stat;
		 	ResultSet rs = null;
		 	ArrayList<Notification> values = new ArrayList<>();
			try {
				stat = conn.createStatement();
				rs  = stat.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Notification not = null;
			try {
				while (rs.next()) {
					not = new Notification(rs.getInt("id"),
				    						 rs.getInt("cod_cat"),
				    						 rs.getString("floor"),
				    						 rs.getString("room"));
				    values.add(not);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return values;
			
		
	}
	
}
