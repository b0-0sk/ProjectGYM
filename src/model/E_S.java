package model;

public class E_S {
	
	private String movimentsID;
	private String gymID;
	private String dniClient;
	private String data;
	private String e_s;
	
	public E_S(String movimentsID, String gymID, String dniClient, String data, String e_s) {
		super();
		this.movimentsID = movimentsID;
		this.gymID = gymID;
		this.dniClient = dniClient;
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

	public String getDniClient() {
		return dniClient;
	}

	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
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
