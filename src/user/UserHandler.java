package user;
/**
 * 
 * This class handle the communication which involves the user, it use
 * the CRUD model
 * @author Federico-PC
 *
 */

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
import javax.xml.bind.annotation.XmlRootElement;

import database.DatabaseConnection;
import database.UserAdapter;

@Path("/user")
public class UserHandler {

	@POST
	@Path("/createuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(UserProfile profile){
		
	}
	
	@GET
	@Path("/getuser/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserProfile getUserProfile(@PathParam("email") String mail){
		return null;
	}
	
	@PUT
	@Path("/updateuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProfile(UserProfile profile){
		
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user){
		
		//TODO DEVO INSERIRE L'UTENTE SE CONNESSO DENTRO LA TABELLA
		// DEGLI UTENTI CONNESSI
		
		String pass = UserAdapter.getCredential(user.getEmail());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(pass);
		if(pass.equals(user.getPassword()) ){
			//login
			String result = "User logged";
    		return Response.status(201).entity(result).build();
		}else{
			String result = "Error";
    		return Response.status(404).entity(result).build();
		}
		
	}
	
	   
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public User createTodo(){
		User u =  new User();
		u.setEmail("prova@lilm.it");
		u.setPassword("bla2");
		return u;
    }
    
    @GET
    @Path("/allusers")
    @Produces(MediaType.APPLICATION_JSON)
    public Userlist returnUsers(){
		Userlist u = new Userlist();
		try {
			u.setUsers(UserAdapter.getAllUsers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
    }
	
	
}
