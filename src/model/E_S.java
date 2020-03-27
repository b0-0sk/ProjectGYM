package model;

public class E_S {
	
	private String movimentsID;
	private String gymID;
	private String userID; // dniClient
	private String data;
	private String e_s;
	
	public E_S(String movimentsID, String gymID, String userID, String data, String e_s) {
		super();
		this.movimentsID = movimentsID;
		this.gymID = gymID;
		this.userID = userID;
		this.data = data;
		this.e_s = e_s;
	}

	public String getMovimentsID() {
		return movimentsID;
	}

	public void setMovimentsID(String movimentsID) {
		this.movimentsID = movimentsID;
	}

	public String getGymID() {
		return gymID;
	}

	public void setGymID(String gymID) {
		this.gymID = gymID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String dniClient) {
		this.userID = dniClient;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getE_s() {
		return e_s;
	}

	public void setE_s(String e_s) {
		this.e_s = e_s;
	}
	
	
}
