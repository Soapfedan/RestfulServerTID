package database;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameters {

	private int scanPeriodNormal;
	private int scanPeriodSearching;
	private int scanPeriodEmergency;
	private int periodBetweenScanNormal;
	private int periodBetweenScanSearching;
	private int periodBetweenScanEmergency;
	
	public Parameters(){
		
	}	
	
	public Parameters(int scanPeriodNormal, int scanPeriodSearching,
			int scanPeriodEmergency, int periodBetweenScanNormal,
			int periodBetweenScanSearching, int periodBetweenScanEmergency) {		
		this.scanPeriodNormal = scanPeriodNormal;
		this.scanPeriodSearching = scanPeriodSearching;
		this.scanPeriodEmergency = scanPeriodEmergency;
		this.periodBetweenScanNormal = periodBetweenScanNormal;
		this.periodBetweenScanSearching = periodBetweenScanSearching;
		this.periodBetweenScanEmergency = periodBetweenScanEmergency;
	}

	public int getScanPeriodNormal() {
		return scanPeriodNormal;
	}

	public void setScanPeriodNormal(int scanPeriodNormal) {
		this.scanPeriodNormal = scanPeriodNormal;
	}

	public int getScanPeriodSearching() {
		return scanPeriodSearching;
	}

	public void setScanPeriodSearching(int scanPeriodSearching) {
		this.scanPeriodSearching = scanPeriodSearching;
	}

	public int getScanPeriodEmergency() {
		return scanPeriodEmergency;
	}

	public void setScanPeriodEmergency(int scanPeriodEmergency) {
		this.scanPeriodEmergency = scanPeriodEmergency;
	}

	public int getPeriodBetweenScanNormal() {
		return periodBetweenScanNormal;
	}

	public void setPeriodBetweenScanNormal(int periodBetweenScanNormal) {
		this.periodBetweenScanNormal = periodBetweenScanNormal;
	}

	public int getPeriodBetweenScanSearching() {
		return periodBetweenScanSearching;
	}

	public void setPeriodBetweenScanSearching(int periodBetweenScanSearching) {
		this.periodBetweenScanSearching = periodBetweenScanSearching;
	}

	public int getPeriodBetweenScanEmergency() {
		return periodBetweenScanEmergency;
	}

	public void setPeriodBetweenScanEmergency(int periodBetweenScanEmergency) {
		this.periodBetweenScanEmergency = periodBetweenScanEmergency;
	}
	
	
	
	
}
