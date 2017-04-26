package beacon;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beaconvaluelist")
public class BeaconValueList {
	
	private ArrayList<BeaconValue> beacons;

	public ArrayList<BeaconValue> getBeacons() {
		return beacons;
	}

	public void setBeacons(ArrayList<BeaconValue> beacons) {
		this.beacons = beacons;
	}
	
	

}
