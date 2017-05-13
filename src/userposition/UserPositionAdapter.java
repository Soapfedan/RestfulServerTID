package userposition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import beacon.value.BeaconValue;
import user.UserProfile;
import database.DatabaseConnection;


public class UserPositionAdapter {

	//delete
	public static boolean deleteUser(String ip) throws SQLException{
		
		boolean flag = false;
		String sql = "delete from userposition where user_ID = ?";
		
		Connection conn = DatabaseConnection.connect();
  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
    
        // set the value
        pstmt.setString(1,ip);
        if(pstmt.executeUpdate()==1){
        	flag = true;
        }
        return flag;
	}
	
	
	//insert
		public static void insertUser(UserPosition position) throws SQLException{
			
			String sql ="insert into userposition(beacon_ID,user_ID,nome,cognome)"
					+ "values(?,?,?,?)";
			
			Connection conn = DatabaseConnection.connect();
	  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
	    
	        // set the value
	        pstmt.setString(1,position.getBeacon_ID());
	        pstmt.setString(2,position.getUser_ID());
	        pstmt.setString(3,position.getNome());
	        pstmt.setString(4,position.getCognome());

	       
	        
	        pstmt.executeUpdate();
	        
		}

	//select all
	public static ArrayList<UserPosition> getallpositions() throws SQLException{
		
		String sql = "select * from userposition";
		Connection conn = DatabaseConnection.connect();
	 	Statement stat;
	 	ResultSet rs = null;
	 	ArrayList<UserPosition> values = new ArrayList<>();
		try {
			stat = conn.createStatement();
			rs  = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserPosition beacval = null;
		while (rs.next()) {
			beacval = new UserPosition(rs.getString("beacon_ID"),
            						 rs.getString("user_ID"),
            						 rs.getString("nome"),
            						 rs.getString("cognome"));
            values.add(beacval);
        }
        return values;
		
		
	}
	
	public static int checkPosition(String ip) throws SQLException{
	    	
	    	int count = 0;
	    	String sql = "SELECT user_id FROM userposition WHERE user_ID = ?";
	          try {
	        	  Connection conn = DatabaseConnection.connect();
	        	  PreparedStatement pstmt  = conn.prepareStatement(sql);
	          
	              // set the value
	              pstmt.setString(1,ip);
	              //
	              ResultSet rs  = pstmt.executeQuery();
	              
	              // loop through the result set
	              while (rs.next()) {
	            	    count++;
	            	    // Get data from the current row and use it
	            	}
	              
	          }finally{
	        	  return count;
	          }
	          
	    }
	public static boolean updatePosition(UserPosition pos) {
	  
	    String sql = "update userposition set beacon_ID =?,nome = ?,"
	    		+ "cognome = ? where user_ID = ?";
	    try (Connection conn = DatabaseConnection.connect();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
				pstmt.setString(1,pos.getBeacon_ID());
				pstmt.setString(2,pos.getNome());
				pstmt.setString(3,pos.getCognome());
				pstmt.setString(4,pos.getUser_ID());
	 
	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
}
