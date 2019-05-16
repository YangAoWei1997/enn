package com.enn.commodity.synergistic.entity;

public class Location {
	
	private String address;
	private String contactName;
	private String contactPhone;
	private String ipId;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	public String getIpId() {
		return ipId;
	}
	public void setIpId(String ipId) {
		this.ipId = ipId;
	}
	
	public Location(String address, String contactName, String contactPhone, String ipId) {
		super();
		this.address = address;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.ipId = ipId;
	}
	public Location() {
		
	}
	
	

}
