package user;

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

@Path("/todo")
public class TodoResource {
        // This method is called if XMLis request
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Todo getJSON() {
                Todo todo = new Todo();
                todo.setSummary("This is my first todo");
                todo.setDescription("This is my first todo");
                return todo;
        }
        
        @POST
        @Path("/create")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createTodo(Todo to){
        	String result = "Track saved : " + to.getDescription()+" "+to.getSummary();
    		return Response.status(201).entity(result).build();
        }
        
        @DELETE
        @Path("/delete/{id}")
        public Response deleteTodo(@PathParam("id") int i){
        	String result = "Track deleted : "+i;
    		return Response.status(201).entity(result).build();
        }
        
        @PUT
        @Path("/put")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Todo putTodo(Todo to){
            Todo todo = new Todo();
            todo.setSummary(to.getSummary());
            todo.setDescription(to.getDescription());
            return todo;
        }

}