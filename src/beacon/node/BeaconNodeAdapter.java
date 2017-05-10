package beacon.node;

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


public class BeaconNodeAdapter {

	
	//insert
	public static void insertValue(BeaconNode beacon,String building) throws SQLException{
		
		String sql ="insert into beacons(beacon_ID,building,floor,x,y)"
				+ " values(?,?,?,?,?)";
		
		Connection conn = DatabaseConnection.connect();
  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
    
        // set the value
        pstmt.setString(1,beacon.getBeacon_ID());
        pstmt.setString(2,building);
        pstmt.setString(3,beacon.getFloor());
        pstmt.setString(4,beacon.getX());
        pstmt.setString(5,beacon.getY());
        
        pstmt.executeUpdate();
        
	}

	//select all
	public static ArrayList<BeaconNode> getallvalues(String building) throws SQLException{
		
		String sql = "select beacon_ID,floor,x,y from beacons where building = ?";
		Connection conn = DatabaseConnection.connect();
	 	
	 	ResultSet rs = null;
	 	ArrayList<BeaconNode> values = new ArrayList<>();
		try {
	  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
	    
	        // set the value
	        pstmt.setString(1,building);
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
