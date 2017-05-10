package room;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beacon.node.BeaconNode;
import beacon.node.BeaconNodeAdapter;
import beacon.node.BeaconNodeList;
import beacon.value.BeaconValue;

@Path("/room")
public class RoomRequestHandler {


	
	@POST
	@Path("/insertroom/{building}")
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
	public Room test(){
		Room value = new Room();
		value.setX("158");
		value.setY("300");
		value.setFloor("145");
		value.setWidth("1.8");
		value.setRoom("145DICEA");
		
		
		return value;
	}
	
	@GET
	@Path("/getallrooms/{building}")
	@Produces(MediaType.APPLICATION_JSON)
	public RoomList getallvalues(@PathParam("building") String building){
		RoomList list = new RoomList();
		try {
			list.setRooms((RoomAdapter.getallvalues(building)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
