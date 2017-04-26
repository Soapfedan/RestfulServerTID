package beacon;

import java.sql.SQLException;

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
	public Response insertvalue(){
		return null;
		
	}
	
}
