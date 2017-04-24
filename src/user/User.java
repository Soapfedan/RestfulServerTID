package user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User{
	private String email;
	private String password;
	
	public User(String email,String pass){
		this.email = email;
		this.password = pass;
	}
	
	public User(){
		
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}