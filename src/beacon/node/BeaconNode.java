package beacon.node;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeaconNode {

	private String beacon_ID;
	private String floor;
	private String x;
	private String y;
	
	public BeaconNode(){
		
	}
	
	

	public BeaconNode(String beacon_ID, String floor, String x, String y) {
		super();
		this.beacon_ID = beacon_ID;
		this.floor = floor;
		this.x = x;
		this.y = y;
	}



	public String getBeacon_ID() {
		return beacon_ID;
	}

	public void setBeacon_ID(String beacon_ID) {
		this.beacon_ID = beacon_ID;
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
