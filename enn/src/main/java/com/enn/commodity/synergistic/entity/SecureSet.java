package com.enn.commodity.synergistic.entity;

public class SecureSet {
	
	private String object;
	private String authOrg;
	private String auth;
	private String org;
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getAuthOrg() {
		return authOrg;
	}
	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public SecureSet(String object, String authOrg, String auth, String org) {
		super();
		this.object = object;
		this.authOrg = authOrg;
		this.auth = auth;
		this.org = org;
	}
	public SecureSet() {
		
	}
	
	

}
