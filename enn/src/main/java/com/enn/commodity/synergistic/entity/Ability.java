package com.enn.commodity.synergistic.entity;

public class Ability {
	
	private String name;
	private String value;
	private String ipId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getIpId() {
		return ipId;
	}
	public void setIpId(String ipId) {
		this.ipId = ipId;
	}
	
	public Ability(String name, String value, String ipId) {
		super();
		this.name = name;
		this.value = value;
		this.ipId = ipId;
	}
	public Ability() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
