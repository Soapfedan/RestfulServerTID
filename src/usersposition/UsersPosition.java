package usersposition;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsersPosition {

	private String user_ID;
	private String floor;
	private String x;
	private String y;
	
	public UsersPosition(){
		
	}
	
	

	public UsersPosition(String user_ID, String floor, String x, String y) {
		super();
		this.user_ID = user_ID;
		this.floor = floor;
		this.x = x;
		this.y = y;
	}



	public String getUser_ID() {
		return user_ID;
	}



	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}



	public String getFloor() {
		return floor;
	}



	public void setFloor(String floor) {
		this.floor = floor;
	}



	public String getX() {
		return x;
	}



	public void setX(String x) {
		this.x = x;
	}



	public String getY() {
		return y;
	}



	public void setY(String y) {
		this.y = y;
	}




	

}
