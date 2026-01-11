package jp.co.sss.cytech.bean;

import java.io.Serializable;

public class CompanyBean implements Serializable{
	private Integer id;
	private String companyName;
	private String streetAddress;
	private String representativeName;
	
   
	public CompanyBean() {
		
	}

	public Integer getId() { return id; }

	public void setId(Integer id) {
		this.id = id;
	}

	 public String getCompanyName() { return companyName; }
	 
	 public void setCompanyName(String companyName) { this.companyName = companyName; }


	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	
}
