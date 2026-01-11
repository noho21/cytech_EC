package jp.co.sss.cytech.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
private Integer id;
private String userName;
private String userNameKana;
private String email;
private String phone;
private String userAddress;

public UserBean() {
	
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserNameKana() {
	return userNameKana;
}

public void setUserNameKana(String userNameKana) {
	this.userNameKana = userNameKana;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getUserAddress() {
	return userAddress;
}

public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}

}
