package com.xu.model;

import com.xu.entity.User;

public class LoginResult {
	private User user;
	private String code;
	public LoginResult() {
		
	}
	public LoginResult(String code,User user) {
		this.code = code;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
