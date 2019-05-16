package com.enn.commodity.synergistic.entity;

public class Role {
	
	private Integer roleindex;
	
	private String roleId;
	
	private String organizationId;
	
	private String organizationName;
	
	private String rolename;
	
	private String roledetail;
	
	private String userId;

	public Integer getRoleindex() {
		return roleindex;
	}

	public void setRoleindex(Integer roleindex) {
		this.roleindex = roleindex;
	}

	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledetail() {
		return roledetail;
	}

	public void setRoledetail(String roledetail) {
		this.roledetail = roledetail;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	

	

	public Role(Integer roleindex, String roleId, String organizationId, String organizationName, String rolename,
			String roledetail, String userId) {
		super();
		this.roleindex = roleindex;
		this.roleId = roleId;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.rolename = rolename;
		this.roledetail = roledetail;
		this.userId = userId;
	}

	public Role() {
	
	}
	
	

}
