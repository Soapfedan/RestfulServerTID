package user;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@Path("/hello")
public class HandShaking{

  
  @GET
  @Consumes("text/plain")
  @Produces("text/plain")
  public String welcome() {
    return "bla";
  }
}
