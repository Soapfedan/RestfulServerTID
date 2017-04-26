package beacon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import user.UserProfile;
import database.DatabaseConnection;


public class BeaconValueAdapter {

	
	//insert
	public static void insertValue(BeaconValue beacon) throws SQLException{
		
		String sql ="insert into beaconValue(beacon_ID,user_ID,dt,temperature,"
				+ "luxometer,barometer,accx,accy,accz) values(?,?,?,?,?,?,?,?,?)";
		
		Connection conn = DatabaseConnection.connect();
  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
    
        // set the value
        pstmt.setString(1,beacon.getBeacon_ID());
        pstmt.setString(2,beacon.getUser_ID());
        pstmt.setString(3,beacon.getDt());
        pstmt.setDouble(4,beacon.getTemperature());
        pstmt.setDouble(5,beacon.getLuxometer());
        pstmt.setDouble(6,beacon.getBarometer());
        pstmt.setDouble(7,beacon.getAccx());
        pstmt.setDouble(8,beacon.getAccy());
        pstmt.setDouble(9,beacon.getAccz());
        
        pstmt.executeUpdate();
        
	}
	

	//select all
	public static ArrayList<BeaconValue> getallvalues() throws SQLException{
		
		String sql = "select * from beaconValue";
		Connection conn = DatabaseConnection.connect();
	 	Statement stat;
	 	ResultSet rs = null;
	 	ArrayList<BeaconValue> values = new ArrayList<>();
		try {
			stat = conn.createStatement();
			rs  = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeaconValue beacval = null;
		while (rs.next()) {
			beacval = new BeaconValue(rs.getString("beacon_ID"),
            						 rs.getString("user_ID"),
            						 rs.getString("dt"),
            						 rs.getDouble("temperature"),
            						 rs.getDouble("luxometer"),
            						 rs.getDouble("barometer"),
            						 rs.getDouble("accx"),
            						 rs.getDouble("accy"),
            						 rs.getDouble("accz"));
            values.add(beacval);
        }
        return values;
		
	}
}
