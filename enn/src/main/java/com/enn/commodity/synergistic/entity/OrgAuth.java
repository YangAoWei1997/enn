package com.enn.commodity.synergistic.entity;

import java.util.List;

public class OrgAuth {
	
	private String id;
	private String name;
	private String version;
	private String comments;
	private String organizationId;
	private String organizationName;
	private String userId;
	private String userName;
	private String privateId;
	private String dataType;
	private String goodsType;
	
	private List<SecureSet> secureSet;
	
	private String grantor;
	private String grantee;
	private String authType;
	private String content;
	private String authDate;
	private String authStartTime;
	private String authEndTime;
	private String key;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrivateId() {
		return privateId;
	}
	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public List<SecureSet> getSecureSet() {
		return secureSet;
	}
	public void setSecureSet(List<SecureSet> secureSet) {
		this.secureSet = secureSet;
	}
	public String getGrantor() {
		return grantor;
	}
	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}
	public String getGrantee() {
		return grantee;
	}
	public void setGrantee(String grantee) {
		this.grantee = grantee;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getAuthStartTime() {
		return authStartTime;
	}
	public void setAuthStartTime(String authStartTime) {
		this.authStartTime = authStartTime;
	}
	public String getAuthEndTime() {
		return authEndTime;
	}
	public void setAuthEndTime(String authEndTime) {
		this.authEndTime = authEndTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public OrgAuth(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, List<SecureSet> secureSet, String grantor, String grantee, String authType,
			String content, String authDate, String authStartTime, String authEndTime, String key) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.comments = comments;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.userId = userId;
		this.userName = userName;
		this.privateId = privateId;
		this.dataType = dataType;
		this.goodsType = goodsType;
		this.secureSet = secureSet;
		this.grantor = grantor;
		this.grantee = grantee;
		this.authType = authType;
		this.content = content;
		this.authDate = authDate;
		this.authStartTime = authStartTime;
		this.authEndTime = authEndTime;
		this.key = key;
	}
	public OrgAuth() {
		
	}
	
	

}
