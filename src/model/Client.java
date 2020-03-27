package model;

public class Client {
	
	private String dniClient;
	private String user;
	private String surname;
	private String password;
	private String telf;
	private String cp;
	private String province;
	private String email;
	private String iban;
	private String goodPayer;


	public Client(String dniClient, String user, String surname, String password, String telf, String cp, String province,
			String email, String iban, String goodPayer) {
		super();
		this.dniClient = dniClient;
		this.user = user;
		this.surname = surname;
		this.password = password;
		this.telf = telf;
		this.cp = cp;
		this.province = province;
		this.email = email;
		this.iban = iban;
		this.goodPayer = goodPayer;
	}


	public String getDniClient() {
		return dniClient;
	}


	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTelf() {
		return telf;
	}


	public void setTelf(String telf) {
		this.telf = telf;
	}


	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}


	public String getGoodPayer() {
		return goodPayer;
	}


	public void setGoodPayer(String goodPayer) {
		this.goodPayer = goodPayer;
	}
	
	

}
