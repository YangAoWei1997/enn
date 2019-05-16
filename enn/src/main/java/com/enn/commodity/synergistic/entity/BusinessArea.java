package com.enn.commodity.synergistic.entity;

public class BusinessArea {
	
	private String ID;
	
	private String name;
	
	private String comments;
	
	private String channelName;
	
	private String chaincodePackageName;
	
	private String chaincodePackageVersion;
	
	private String chaincodeDeployedName;
	
	private String chaincodeDeployedVersion;
	
	private String lastDeployedDateTime;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChaincodePackageName() {
		return chaincodePackageName;
	}

	public void setChaincodePackageName(String chaincodePackageName) {
		this.chaincodePackageName = chaincodePackageName;
	}

	public String getChaincodePackageVersion() {
		return chaincodePackageVersion;
	}

	public void setChaincodePackageVersion(String chaincodePackageVersion) {
		this.chaincodePackageVersion = chaincodePackageVersion;
	}

	public String getChaincodeDeployedName() {
		return chaincodeDeployedName;
	}

	public void setChaincodeDeployedName(String chaincodeDeployedName) {
		this.chaincodeDeployedName = chaincodeDeployedName;
	}

	public String getChaincodeDeployedVersion() {
		return chaincodeDeployedVersion;
	}

	public void setChaincodeDeployedVersion(String chaincodeDeployedVersion) {
		this.chaincodeDeployedVersion = chaincodeDeployedVersion;
	}

	public String getLastDeployedDateTime() {
		return lastDeployedDateTime;
	}

	public void setLastDeployedDateTime(String lastDeployedDateTime) {
		this.lastDeployedDateTime = lastDeployedDateTime;
	}

	

	public BusinessArea(String iD, String name, String comments, String channelName, String chaincodePackageName,
			String chaincodePackageVersion, String chaincodeDeployedName, String chaincodeDeployedVersion,
			String lastDeployedDateTime) {
		super();
		ID = iD;
		this.name = name;
		this.comments = comments;
		this.channelName = channelName;
		this.chaincodePackageName = chaincodePackageName;
		this.chaincodePackageVersion = chaincodePackageVersion;
		this.chaincodeDeployedName = chaincodeDeployedName;
		this.chaincodeDeployedVersion = chaincodeDeployedVersion;
		this.lastDeployedDateTime = lastDeployedDateTime;
	}

	public BusinessArea() {
		
	}

	
	
	

}
