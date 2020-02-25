package model;

public class GYMS {
	
	private String gymID;
	private String name;
	private int cp;
	private String province;
	
	public GYMS(String gymID, String name, int cp, String province) {
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

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	
	
	
}
