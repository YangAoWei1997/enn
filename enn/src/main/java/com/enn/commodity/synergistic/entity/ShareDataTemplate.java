package com.enn.commodity.synergistic.entity;

public class ShareDataTemplate {
	
	private String ID;
	private String name;
	private String comments;
	private String shareDataTemplateTypeID;
	private String ownerID;
	private String ownerName;
	private String createdDate;
	private String status;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
	public String getShareDataTemplateTypeID() {
		return shareDataTemplateTypeID;
	}
	public void setShareDataTemplateTypeID(String shareDataTemplateTypeID) {
		this.shareDataTemplateTypeID = shareDataTemplateTypeID;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ShareDataTemplate(String iD, String name, String comments, String shareDataTemplateTypeID, String ownerID,
			String ownerName, String createdDate, String status) {
		super();
		ID = iD;
		this.name = name;
		this.comments = comments;
		this.shareDataTemplateTypeID = shareDataTemplateTypeID;
		this.ownerID = ownerID;
		this.ownerName = ownerName;
		this.createdDate = createdDate;
		this.status = status;
	}
	public ShareDataTemplate() {
		
	}
	
	

}
