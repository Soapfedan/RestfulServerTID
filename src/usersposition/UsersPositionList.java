package usersposition;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beaconvaluelist")
public class UsersPositionList {
	
	private ArrayList<UsersPosition> beacons;

	public ArrayList<UsersPosition> getBeacons() {
		return beacons;
	}

	public void setBeacons(ArrayList<UsersPosition> beacons) {
		this.beacons = beacons;
	}
	
	

}
