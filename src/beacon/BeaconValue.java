package beacon;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeaconValue {

	private String beacon_ID;
	private String user_ID;
	private Timestamp dt;
	private float temperature;
	private float luxometer;
	private float barometer;
	private float accx;
	private float accy;
	private float accz;
	
	public BeaconValue(){
		
	}

	public BeaconValue(String beacon_ID, String user_ID, Timestamp dt,
			float temperature, float luxometer, float barometer, float accx,
			float accy, float accz) {
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

	public Timestamp getDt() {
		return dt;
	}

	public void setDt(Timestamp dt) {
		this.dt = dt;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getLuxometer() {
		return luxometer;
	}

	public void setLuxometer(float luxometer) {
		this.luxometer = luxometer;
	}

	public float getBarometer() {
		return barometer;
	}

	public void setBarometer(float barometer) {
		this.barometer = barometer;
	}

	public float getAccx() {
		return accx;
	}

	public void setAccx(float accx) {
		this.accx = accx;
	}

	public float getAccy() {
		return accy;
	}

	public void setAccy(float accy) {
		this.accy = accy;
	}

	public float getAccz() {
		return accz;
	}

	public void setAccz(float accz) {
		this.accz = accz;
	}
	
	
	
	
	

}
