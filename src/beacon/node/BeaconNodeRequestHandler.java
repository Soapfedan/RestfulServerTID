package beacon.node;

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

@Path("/beaconnode")
public class BeaconNodeRequestHandler {

	
	@POST
	@Path("/insertnode/{building}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertvalue(BeaconNode value,@PathParam("building") String building){
		try {
			BeaconNodeAdapter.insertValue(value,building);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public BeaconNode test(){
		BeaconNode value = new BeaconNode();
		value.setBeacon_ID("provabeacon");
		value.setFloor("150");
		value.setX("320");
		value.setY("114");
		
		return value;
	}
	
	@GET
	@Path("/getallnodes/{building}")
	@Produces(MediaType.APPLICATION_JSON)
	public BeaconNodeList getallvalues(@PathParam("building") String building){
		BeaconNodeList list = new BeaconNodeList();
		try {
			list.setBeacons(BeaconNodeAdapter.getallvalues(building));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
