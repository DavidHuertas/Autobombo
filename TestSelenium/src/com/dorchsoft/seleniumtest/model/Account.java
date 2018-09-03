package com.dorchsoft.seleniumtest.model;

public class Account {
	
	private String user;
	private String pwd;
	
	public Account(){}
	
	public Account(String account, String pwd) {
		super();
		this.user = account;
		this.pwd = pwd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Account =>\tuser: " + user + "\t pwd: " + pwd;
	}
	
}
