package beacon.node;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beaconvaluelist")
public class BeaconNodeList {
	
	private ArrayList<BeaconNode> beacons;

	public ArrayList<BeaconNode> getBeacons() {
		return beacons;
	}

	public void setBeacons(ArrayList<BeaconNode> beacons) {
		this.beacons = beacons;
	}
	
	

}
