package com.enn.commodity.synergistic.entity;

public class NodeOrg {
	
	private String ID;
	
	private String name;
	
	private String comments;
	
	private String IP;
	
	private String port;
	
	private String orgID;
	
	private String orgName;

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

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		this.IP = iP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public NodeOrg(String iD, String name, String comments, String iP, String port, String orgID, String orgName) {
		super();
		this.ID = iD;
		this.name = name;
		this.comments = comments;
		this.IP = iP;
		this.port = port;
		this.orgID = orgID;
		this.orgName = orgName;
	}

	public NodeOrg() {
		
	}

	
	
	

}
