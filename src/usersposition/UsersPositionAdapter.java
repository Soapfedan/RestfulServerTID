package usersposition;

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


public class UsersPositionAdapter {

	//delete
	public static boolean deleteUser(String ip) throws SQLException{
		
		boolean flag = false;
		String sql = "delete from usersposition where user_ID = ?";
		
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
		public static void insertUser(UsersPosition position) throws SQLException{
			
			String sql ="insert into usersposition(user_ID,floor,x,y)"
					+ "values(?,?,?,?)";
			
			Connection conn = DatabaseConnection.connect();
	  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
	    
	        // set the value
	        pstmt.setString(1,position.getUser_ID());
	        pstmt.setString(2,position.getFloor());
	        pstmt.setString(3,position.getX());
	        pstmt.setString(4,position.getY());
	       
	        
	        pstmt.executeUpdate();
	        
		}

	//select all
	public static ArrayList<UsersPosition> getallpositions() throws SQLException{
		
		String sql = "select * from usersposition";
		Connection conn = DatabaseConnection.connect();
	 	Statement stat;
	 	ResultSet rs = null;
	 	ArrayList<UsersPosition> values = new ArrayList<>();
		try {
			stat = conn.createStatement();
			rs  = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UsersPosition beacval = null;
		while (rs.next()) {
			beacval = new UsersPosition(rs.getString("user_ID"),
            						 rs.getString("floor"),
            						 rs.getString("x"),
            						 rs.getString("y"));
            values.add(beacval);
        }
        return values;
		
		
	}
	
	public static int checkPosition(String ip) throws SQLException{
	    	
	    	int count = 0;
	    	String sql = "SELECT user_id FROM usersposition WHERE user_ID = ?";
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
	public static boolean updatePosition(UsersPosition pos) {
	  
	    String sql = "update usersposition set floor =? ," +
	            " x=?, y=?  where user_ID = ?";
	    try (Connection conn = DatabaseConnection.connect();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
				pstmt.setString(1,pos.getFloor());
				pstmt.setString(2,pos.getX());
				pstmt.setString(3,pos.getY());
				pstmt.setString(4,pos.getUser_ID());
	 
	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
}
