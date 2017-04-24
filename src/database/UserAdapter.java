package database;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import user.UserProfile;

public class UserAdapter {



    private DatabaseConnection dbHelper;

    // Database fields
    private static final String DATABASE_TABLE      = "User";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME = "nome";
    public static final String KEY_SURNAME = "cognome";
    public static final String KEY_BIRTH_DATE = "data_nascita";
    public static final String KEY_BIRTH_CITY = "luogo_nascita";
    public static final String KEY_PROVINCE = "provincia";
    public static final String KEY_STATE = "stato";
    public static final String KEY_TELEPHONE = "telefono";
    public static final String KEY_SEX = "sesso";
    public static final String KEY_PERSONAL_NUMBER = "cod_fis";



    private static ArrayList<String> createContentValues(String email,String password, String name, String surname, String birth_date,
                                              String birth_city, String province, String state, String telephone, String sex,
                                              String personal_number) {
    	ArrayList<String> values = new ArrayList<>();
        values.add(email);
        values.add(password );
        values.add( name );
        values.add(surname );
        values.add( birth_date );
        values.add( birth_city );
        values.add( province );
        values.add( state );
        values.add( telephone );
        values.add( sex );
        values.add( personal_number );

        return values;
    }

    //create an user
    public static boolean createUser(String email,String password, String name, String surname, String birth_date,
                           String birth_city, String province, String state, String telephone, String sex,
                           String personal_number ) throws SQLException {
    	ArrayList<String> initialValues = createContentValues(email, password, name, surname, birth_date, birth_city,
                province, state, telephone, sex, personal_number);
        //invertiti i rami
        String sql = "insert into User(email, password ," +
            " nome, cognome, data_nascita, " +
            "luogo_nascita, provincia, stato," +
            " telefono, sesso, cod_fis) values(?,?,?,?,?,?,?,?,?,?,?);";
        if(checkNewUser(email)==0){
        	try (Connection conn = DatabaseConnection.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
        		
        		int i = 1;
        		for(int j=0;j<initialValues.size();j++){
        			pstmt.setString(i, initialValues.get(j));
        	        i++;
        	    }
        	        
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }else {
            return false;
        }
    }

    //update an user
    public static boolean updateProfile(String email,String password, String name, String surname, String birth_date,
                                 String birth_city, String province, String state, String telephone, String sex,
                                 String personal_number ) {
        ArrayList<String> updateValues = createContentValues(email, password, name, surname, birth_date, birth_city,
                province, state, telephone, sex, personal_number);
        String sql = "update User set password =? ," +
                " nome=?, cognome=?, data_nascita=?, " +
                "luogo_nascita=?, provincia=?, stato=?," +
                " telefono=?, sesso=?, cod_fis=? where email = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		
    		int i = 1;
    		for(int j=1;j<updateValues.size();j++){
    			pstmt.setString(i, updateValues.get(j));
    	        i++;
    	    }
    		pstmt.setString(i,updateValues.get(0));
    	        
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
/*
    //delete an user
    public boolean deleteProfile(String email) {
        return database.delete(DATABASE_TABLE, KEY_EMAIL + "='"+ email + "'", null) > 0;
    }
*/

    //get a profile by his mail, if he doesn't exist, the function return 0, otherwise 1
    @SuppressWarnings("finally")
	public static int checkNewUser(String mail) throws SQLException{
    	
    	int count = 0;
    	String sql = "SELECT email FROM User WHERE email = ?";
          try {
        	  Connection conn = DatabaseConnection.connect();
        	  PreparedStatement pstmt  = conn.prepareStatement(sql);
          
              // set the value
              pstmt.setString(1,mail);
              //
              ResultSet rs  = pstmt.executeQuery();
              
              // loop through the result set
              while (rs.next()) {
            	    count++;
            	    // Get data from the current row and use it
            	}
              
          }finally{
        	  return count;
          }
          
    }


    //get all user properties
    public static UserProfile getUserProfile(String mail) throws SQLException{
       String sql = "SELECT email,password,nome,cognome,data_nascita"
       		+ ",luogo_nascita,provincia,stato,telefono,sesso,cod_fis FROM User WHERE email = ?";
       		UserProfile profile = null; 
       try {
    	   Connection conn = DatabaseConnection.connect();
        	
        	PreparedStatement pstmt  = conn.prepareStatement(sql);
            
            // set the value
            pstmt.setString(1,mail);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            
			while (rs.next()) {
                profile = new UserProfile(rs.getString("email"),
                							rs.getString("password"),
                							rs.getString("nome"),
                							rs.getString("cognome"),
                							rs.getString("data_nascita"),
                							rs.getString("luogo_nascita"),
                							rs.getString("provincia"),
                							rs.getString("stato"),
                							rs.getString("telefono"),
                							rs.getString("sesso"),
                							rs.getString("cod_fis"));
                							
                  }
        }finally{
        	return profile;
        }
        
    }

    //get email and password
    
	public static String getCredential(String mail){
    	String sql = "SELECT password FROM User WHERE email = ?";
    	String result = "";
    	boolean empty = true;
        try{
        	Connection conn = DatabaseConnection.connect();
      	  
      	  PreparedStatement pstmt  = conn.prepareStatement(sql);
        
            // set the value
            pstmt.setString(1,mail);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
            	// Get data from the current row and use it
            	result = rs.getString("password");
          	    empty = false;
          	    
          	}
            
        }catch(SQLException e){
        	e.printStackTrace();
        }
          if(empty){
        	 return "";
          }else{
        	  return result;
      	  }
        
    }

    //get all users
    public static ArrayList<UserProfile> getAllUsers() throws SQLException{
    	
    	ArrayList<UserProfile> users = new ArrayList<>();
    	String sql = "SELECT email,password,nome,cognome,data_nascita"
           		+ ",luogo_nascita,provincia,stato,telefono,sesso,cod_fis FROM User";
            try (Connection conn = DatabaseConnection.connect()){
            	
            	Statement stat = conn.createStatement();
                //
                ResultSet rs  = stat.executeQuery(sql);
                
                // loop through the result set
                UserProfile profile = null;
    			while (rs.next()) {
                    profile = new UserProfile(rs.getString("email"),
                    							rs.getString("password"),
                    							rs.getString("nome"),
                    							rs.getString("cognome"),
                    							rs.getString("data_nascita"),
                    							rs.getString("luogo_nascita"),
                    							rs.getString("provincia"),
                    							rs.getString("stato"),
                    							rs.getString("telefono"),
                    							rs.getString("sesso"),
                    							rs.getString("cod_fis"));
                    
                    users.add(profile);
                }
    		}
         return users;
                    						        

    }
}
