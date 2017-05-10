package room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnection;

public class RoomAdapter {

	
	//insert
	public static void insertValue(Room room,String building) throws SQLException{
		
		String sql ="insert into rooms(x,y,floor,width,room,building)"
				+ " values(?,?,?,?,?,?)";
		
		Connection conn = DatabaseConnection.connect();
  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
    
        // set the value
        pstmt.setString(1,room.getX());
        pstmt.setString(2,room.getY());
        pstmt.setString(3,room.getFloor());
        pstmt.setString(4,room.getWidth());
        pstmt.setString(5,room.getRoom());
        pstmt.setString(6,building);
        
        pstmt.executeUpdate();
        
	}

	//select all
	public static ArrayList<Room> getallvalues(String building) throws SQLException{
		
		String sql = "select x,y,floor,width,room from rooms";
		Connection conn = DatabaseConnection.connect();
	 	
	 	ResultSet rs = null;
	 	ArrayList<Room> values = new ArrayList<>();
		try {
	  	  	PreparedStatement pstmt  = conn.prepareStatement(sql);
	    
	        // set the value
	        //pstmt.setString(1,building);
            rs  = pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Room room = null;
		while (rs.next()) {
			room = new Room(rs.getString("x"),
            						 rs.getString("y"),
            						 rs.getString("floor"),
            						 rs.getString("width"),
            						 rs.getString("room"));
        
            values.add(room);
        }
        return values;
		
	}
}
