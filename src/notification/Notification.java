package notification;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification {

	private int id;
	private int cod_cat;
	private String floor;
	private String room;
	
	public Notification(){
		
	}
	
	public Notification(int id, int cod_cat, String floor, String room) {
		super();
		this.id = id;
		this.cod_cat = cod_cat;
		this.floor = floor;
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCod_cat() {
		return cod_cat;
	}

	public void setCod_cat(int cod_cat) {
		this.cod_cat = cod_cat;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
	
}
