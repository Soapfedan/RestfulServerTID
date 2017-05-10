package userposition;

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

@Path("/position")
public class UserPositionRequestHandler {

	@DELETE
	@Path("/deleteuser/{user}")
	public Response deleteUser(@PathParam("user") String ip) throws SQLException{
		if(UserPositionAdapter.deleteUser(ip)){
			//user deleted
			String result = "User deleted";
    		return Response.status(201).entity(result).build();
		}else{
			String result = "Error";
    		return Response.status(500).entity(result).build();
		}
	}
	
	
	@PUT
	@Path("/setposition")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertposition(UserPosition value){
		try {
			if(UserPositionAdapter.checkPosition(value.getUser_ID())==0){
				UserPositionAdapter.insertUser(value);
			}else{
				UserPositionAdapter.updatePosition(value);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public UserPosition test(){
		UserPosition value = new UserPosition();
		value.setUser_ID("172.175.111.255");
		value.setBeacon_ID("id");
		value.setNome("Mario");
		value.setCognome("Rossi");
		
		return value;
	}
	
	@GET
	@Path("/getpositions")
	@Produces(MediaType.APPLICATION_JSON)
	public UserPositionList getallposition(){
		UserPositionList list = new UserPositionList();
		try {
			list.setUsers(UserPositionAdapter.getallpositions());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
