package com.enn.commodity.synergistic.entity;

public class Item {
	
	private String key;
	private String keyName;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	public Item(String key, String keyName) {
		super();
		this.key = key;
		this.keyName = keyName;
	}
	public Item() {
		
	}
	
	
	
	

}
