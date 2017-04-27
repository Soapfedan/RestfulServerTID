package beacon.value;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeaconValue {

	private String beacon_ID;
	private String user_ID;
	private String dt;
	private double temperature;
	private double luxometer;
	private double barometer;
	private double accx;
	private double accy;
	private double accz;
	
	public BeaconValue(){
		
	}

	public BeaconValue(String beacon_ID, String user_ID, String dt,
			double temperature, double luxometer, double barometer, double accx,
			double accy, double accz) {
		this.beacon_ID = beacon_ID;
		this.user_ID = user_ID;
		this.dt = dt;
		this.temperature = temperature;
		this.luxometer = luxometer;
		this.barometer = barometer;
		this.accx = accx;
		this.accy = accy;
		this.accz = accz;
	}

	public String getBeacon_ID() {
		return beacon_ID;
	}

	public void setBeacon_ID(String beacon_ID) {
		this.beacon_ID = beacon_ID;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getLuxometer() {
		return luxometer;
	}

	public void setLuxometer(double luxometer) {
		this.luxometer = luxometer;
	}

	public double getBarometer() {
		return barometer;
	}

	public void setBarometer(double barometer) {
		this.barometer = barometer;
	}

	public double getAccx() {
		return accx;
	}

	public void setAccx(double accx) {
		this.accx = accx;
	}

	public double getAccy() {
		return accy;
	}

	public void setAccy(double accy) {
		this.accy = accy;
	}

	public double getAccz() {
		return accz;
	}

	public void setAccz(double accz) {
		this.accz = accz;
	}
	
	
	
	
	

}
