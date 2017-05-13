package notification;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import userposition.UserPositionAdapter;

@Path("/notification")
public class NotificationRequestHandler {

	@POST
	@Path("/insertnotification")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createNotification(Notification not){
		
		NotificationAdapter.createNot(not);
	}
	
	@GET
	@Path("/getallnot")
	@Produces(MediaType.APPLICATION_JSON)
	public NotificationList getallnot(){
		
		NotificationList list = new NotificationList();
		list.setList(NotificationAdapter.getAllNot());
		return list;
	}
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Notification test(){
		
		Notification not = new Notification();
		not.setId(1);
		not.setCod_cat(1);
		not.setFloor("145");
		not.setRoom("145A5");
		
		return not;
		
	}
	
	@DELETE
	@Path("/deletenot/{id}")
	public Response deleteUser(@PathParam("id") String id) throws SQLException{
		if(NotificationAdapter.deleteNot(id)){
			//user deleted
			String result = "User deleted";
    		return Response.status(201).entity(result).build();
		}else{
			String result = "Error";
    		return Response.status(500).entity(result).build();
		}
	}
	
}
