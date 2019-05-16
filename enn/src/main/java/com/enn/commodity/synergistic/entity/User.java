package com.enn.commodity.synergistic.entity;

public class User {
	
	private Integer userindex;
	
	private String userId;
	
	private String username;
	
	private String organizationId;
	
	private String showname;
	
	private String password;
	
	private String mail;
	
	private String contactinfo;
	
	private String organizationName;
	
	private String userstatus;
	
	private String usersconnect;
	
	private String userno;
	
	private String plateno;

	

	

	public Integer getUserindex() {
		return userindex;
	}

	public void setUserindex(Integer userindex) {
		this.userindex = userindex;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContactinfo() {
		return contactinfo;
	}

	public void setContactinfo(String contactinfo) {
		this.contactinfo = contactinfo;
	}

	

	

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	
	

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getUsersconnect() {
		return usersconnect;
	}

	public void setUsersconnect(String usersconnect) {
		this.usersconnect = usersconnect;
	}

	

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	
	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}
	

	

	public User(Integer userindex, String userId, String username, String organizationId, String showname,
			String password, String mail, String contactinfo, String organizationName, String userstatus,
			String usersconnect, String userno, String plateno) {
		super();
		this.userindex = userindex;
		this.userId = userId;
		this.username = username;
		this.organizationId = organizationId;
		this.showname = showname;
		this.password = password;
		this.mail = mail;
		this.contactinfo = contactinfo;
		this.organizationName = organizationName;
		this.userstatus = userstatus;
		this.usersconnect = usersconnect;
		this.userno = userno;
		this.plateno = plateno;
	}

	public User(Integer userindex, String userId) {
		super();
		this.userindex = userindex;
		this.userId = userId;
	}

	public User() {
		
	}
	
	
	
	
	
	

}
