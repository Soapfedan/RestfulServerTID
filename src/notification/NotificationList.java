package notification;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationList {

	private ArrayList<Notification> list;
	
	public NotificationList(){
		list = new ArrayList<>();
	}

	public ArrayList<Notification> getList() {
		return list;
	}

	public void setList(ArrayList<Notification> list) {
		this.list = list;
	}
	
	
	
}
