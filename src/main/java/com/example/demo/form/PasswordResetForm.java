package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordResetForm {
	
	@NotBlank
	@Size(min = 1, max = 50, message = "ユーザー名は1文字以上50文字以下で入力してください")
	private String userName;
	
	@NotBlank
	@Size(min = 6, max = 20, message = "パスワードは6文字以上20文字以下で入力してください")	
	private String password;
	@NotBlank
	@Size(min = 6, max = 20, message = "パスワードは6文字以上20文字以下で入力してください")
    private String passwordConf;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConf() {
		return passwordConf;
	}
	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
