package com.hk.po;
public class Student {

	private String username;
	private String password;
	private String phone;
	private String code;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "user [username=" + username + ", password=" + password + ", phone=" + phone + ", code="
				+ code + "]";
	}
}

