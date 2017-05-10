package userposition;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserPosition {

	private String beacon_ID;
	private String user_ID;
	private String nome;
	private String cognome;


	public UserPosition(){
		
	}


	public UserPosition(String beacon_ID, String user_ID, String nome,
			String cognome) {
		super();
		this.beacon_ID = beacon_ID;
		this.user_ID = user_ID;
		this.nome = nome;
		this.cognome = cognome;
	}


	public String getBeacon_ID() {
		return beacon_ID;
	}


	public void setBeacon_ID(String beacon_ID) {
		this.beacon_ID = beacon_ID;
	}


	public String getUser_ID() {
		return user_ID;
	}


	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	
	




	

}
