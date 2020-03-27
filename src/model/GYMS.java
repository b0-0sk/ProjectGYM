package model;

public class GYMS {
	
	private String gymID;
	private String name;
	private String cp;
	private String province;
	
	public GYMS(String gymID, String name, String cp, String province) {
		super();
		this.gymID = gymID;
		this.name = name;
		this.cp = cp;
		this.province = province;
	}

	public String getGymID() {
		return gymID;
	}

	public void setGymID(String gymID) {
		this.gymID = gymID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	
	
}
