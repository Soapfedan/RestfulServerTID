package userposition;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UsersPositionList")
public class UserPositionList {
	
	private ArrayList<UserPosition> users;

	public ArrayList<UserPosition> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserPosition> users) {
		this.users = users;
	}

	
	
	

}
