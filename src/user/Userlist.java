package user;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Userlist {

	private ArrayList<UserProfile> users;

	public Userlist(){
		
	}
	public ArrayList<UserProfile> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserProfile> users) {
		this.users = users;
	}
	
	
}
