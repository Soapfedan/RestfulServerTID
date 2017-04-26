package database;
 
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
import user.User;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class DatabaseConnection {
	
    private static String dbURL = "jdbc:derby://localhost:1527/dbingsoft;create=true;user=APP;password=APP";
    private static String tableName = "utenti";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
	private static User us;
     /**
     * Connect to a sample database
     */
	
	 public static Connection connect()
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
	 
	 
	 
	 
	 /*
    public static void createDB() {
    	  // SQLite connection string
        String url = "jdbc:sqlite:db/dbingsoft.db";
        
        // SQL statement for creating a new table
        String sql = "create table User (email text primary key, password text not null," +
            " nome text not null, cognome text not null, data_nascita text, " +
            "luogo_nascita text , provincia text , stato text ," +
            " telefono text , sesso text , cod_fis text);";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewDatabase(String fileName) {
    	 
        String url = "jdbc:sqlite:db/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Connection connect() {
        Connection conn = null;
  
            // db parameters
            String url = "jdbc:sqlite:db/dbingsoft.db";
            
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
            
     
    }
 
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
    	/*
    	User user =  new User();
		user.setEmail("prova@lilm.it");
		user.setPassword("bla2");
    	String pass = UserAdapter.getCredential(user.getEmail());
		if(pass.equals(user.getPassword()) && !pass.equals("") ){
			System.out.println("bla");
		}else{
			System.out.println("blimm");
		}
		Userlist u = new Userlist();
		try {
			u.setUsers(UserAdapter.getAllUsers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<u.getUsers().size();i++){
			u.getUsers().get(i).printProfile();
		}
    	/*
        UserAdapter.updateProfile("prova@lilm.it", "bla2", "taaaa", "tiomod",
				"", "", "", "", "", "", "codfis");
		try {
			if(UserAdapter.getUserProfile("prova@lilm.it")!=null)
				UserAdapter.getUserProfile("prova@lilm.it").printProfile();;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			if(UserAdapter.createUser("prova@lilm.it", "bla", "tio", "tio",
					"", "", "", "", "", "", "")){
				System.out.println("ok inserito 1");
			}else{
				System.out.println("errore 1");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*
    	ArrayList<UserProfile> user = new ArrayList<>();
    	try {
			 user = UserAdapter.getAllUsers();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(int i =0;i<user.size();i++){
    		System.out.println("indice: "+i);
    		user.get(i).printProfile();
    	}
    }

	public static User getUser() {
		return us;
	}

	public static void setUser(User user) {
		us = user;
	}
	
	public static Response login(User user){
		String pass = UserAdapter.getCredential(user.getEmail());
		if(pass.equals(user.getPassword()) ){
			//login
			String result = "User logged";
    		return Response.status(201).entity(result).build();
		}else{
			String result = "Error";
    		return Response.status(405).entity(result).build();
		}
	}*/
    
}