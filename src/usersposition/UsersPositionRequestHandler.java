package usersposition;

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
public class UsersPositionRequestHandler {

	@DELETE
	@Path("/deleteuser/{user}")
	public Response deleteUser(@PathParam("user") String ip) throws SQLException{
		if(UsersPositionAdapter.deleteUser(ip)){
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
	public void insertposition(UsersPosition value){
		try {
			if(UsersPositionAdapter.checkPosition(value.getUser_ID())==0){
				UsersPositionAdapter.insertUser(value);
			}else{
				UsersPositionAdapter.updatePosition(value);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public UsersPosition test(){
		UsersPosition value = new UsersPosition();
		value.setUser_ID("172.175.111.255");
		value.setFloor("150");
		value.setX("320");
		value.setY("114");
		
		return value;
	}
	
	@GET
	@Path("/getpositions")
	@Produces(MediaType.APPLICATION_JSON)
	public UsersPositionList getallposition(){
		UsersPositionList list = new UsersPositionList();
		try {
			list.setBeacons(UsersPositionAdapter.getallpositions());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
