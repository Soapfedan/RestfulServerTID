package notification;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
}
