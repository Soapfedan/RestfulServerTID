package beacon.node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import user.UserProfile;
import database.DatabaseConnection;


public class BeaconNodeAdapter {


	//select all
	public static ArrayList<BeaconNode> getallvalues(String floor) throws SQLException{
		
		String sql = "select * from beacons where floor = ?";
		Connection conn = DatabaseConnection.connect();
	 	
	 	ResultSet rs = null;
	 	ArrayList<BeaconNode> values = new ArrayList<>();
		try {
	  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
	    
	        // set the value
	        pstmt.setString(1,floor);
            rs  = pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeaconNode beacval = null;
		while (rs.next()) {
			beacval = new BeaconNode(rs.getString("beacon_ID"),
            						 rs.getString("floor"),
            						 rs.getString("x"),
            						 rs.getString("y"));
        
            values.add(beacval);
        }
        return values;
		
	}
}
