package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

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

import user.User;


public class prova
{
    private static String dbURL = "jdbc:derby://localhost:1527/dbingsoft;create=true;user=APP;password=APP";
    private static String tableName = "utenti";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args)
    {
        createConnection();
        insertRestaurants(5, "LaVals", "Berkeley");
        
		//	selectRestaurants();
		 shutdown();
    }
    
    private static Connection createConnection()
    {
        try
        {
        	Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		return conn;
    }
    
    private static void insertRestaurants(int id, String restName, String cityName)
    {
        try
        {
        	
            stmt = conn.createStatement();
            //stmt.execute("truncate table userposition");
           
            //stmt.execute("create table csvVersion(version int primary key)");
            stmt.execute("insert into csvVersion(version) values(1)");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    @GET
	@Path("/prova")
	@Produces(MediaType.TEXT_PLAIN)
    public static String selectRestaurants() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
    	String prova="";
        try
        {
        	Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");
            
            while(results.next())
            {
            	for (int i=1; i<=numberCols; i++)
                {
                    //print Column Names
                    //System.out.print(results.getString(i)+"\t\t");
                    prova = prova + " " + results.getString(i)+" ";
                }
            		
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        System.out.println(prova);
        return prova;
    }
    
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
    /*
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public User createTodo(){
		User u =  new User();
		u.setEmail("prova@lilm.it");
		u.setPassword("bla2");
		return u;
    }*/
}