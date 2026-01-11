package jp.co.sss.cytech.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRegisterForm {
	@NotBlank(message = "ユーザー名を入力してください")	
	private String userName;
	@NotBlank(message = "かなを入力してください")
	@Pattern(
			  regexp = "^$|^[ぁ-ん]+$",
			  message = "ひらがなで入力してください"
			)
	private String userNameKana;
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	@NotBlank(message = "電話番号を入力してください")
	@Pattern(
			  regexp = "^$|^(0\\d{1,4}-?\\d{1,4}-?\\d{4})$",
			  message = "正しい電話番号形式で入力してください"
			)
	private String phone;
	@NotBlank(message = "住所を入力してください")
	private String userAddress;
	
	private String buildingAddress;
	@NotBlank(message = "パスワードを入力してください")
	@Pattern(
			  regexp = "^$|^[a-zA-Z0-9]{8,}$",
			  message = "パスワードは英数字8文字以上で入力してください"
			)
	private String password;
	@NotBlank(message = "パスワード（確認）を入力してください")
	private String passwordConfirm;
	
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
	public String getBuildingAddress() {
		return buildingAddress;
	}
	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
