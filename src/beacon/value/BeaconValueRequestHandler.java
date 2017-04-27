package beacon.value;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/beaconvalue")
public class BeaconValueRequestHandler {

	@POST
	@Path("/insertvalue")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertvalue(BeaconValue value){
		try {
			BeaconValueAdapter.insertValue(value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public BeaconValue test(){
		BeaconValue value = new BeaconValue();
		value.setUser_ID("provauser");
		value.setBeacon_ID("provabeacon");
		value.setDt("1962-09-23 03:23:34.234");
		value.setTemperature(18.93558545);
		value.setLuxometer(28.951999999999998);
		value.setBarometer(854.79424);
		value.setAccx(0.012255859375);
		value.setAccy(-0.989697265625);
		value.setAccz(-0.00532226562);
		
		return value;
	}
	
	@GET
	@Path("/getallvalue")
	@Produces(MediaType.APPLICATION_JSON)
	public BeaconValueList getallvalues(){
		BeaconValueList list = new BeaconValueList();
		try {
			list.setBeacons(BeaconValueAdapter.getallvalues());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
