package room;

public class Room {

	private String x;
	private String y;
	private String floor;
	private String width;
	private String room;
	
	public Room(){
		
	}

	public Room(String x, String y, String floor, String width, String room) {
		super();
		this.x = x;
		this.y = y;
		this.floor = floor;
		this.width = width;
		this.room = room;
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

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
