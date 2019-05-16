package com.enn.commodity.synergistic.entity;

import java.util.List;

public class IP {
	
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
	
	private String contactID;
	private String contactName;
	private String contactPhone;
	private String loginUser;
	private String loginPass;
	private String legal;
	private String registeredLocation;
	private String scopeOfBusiness;
	private String registeredCapital;
	private String abbreviation;
	private String publicKey;
	
	
	private List<Product> product;
	private List<Location>location;
	
	private List<Ability> ability;

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

	public String getContactID() {
		return contactID;
	}

	public void setContactID(String contactID) {
		this.contactID = contactID;
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

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getRegisteredLocation() {
		return registeredLocation;
	}

	public void setRegisteredLocation(String registeredLocation) {
		this.registeredLocation = registeredLocation;
	}

	public String getScopeOfBusiness() {
		return scopeOfBusiness;
	}

	public void setScopeOfBusiness(String scopeOfBusiness) {
		this.scopeOfBusiness = scopeOfBusiness;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	
	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

	

	public List<Ability> getAbility() {
		return ability;
	}

	public void setAbility(List<Ability> ability) {
		this.ability = ability;
	}

	

	public IP(String id, String name, String version, String comments, String organizationId, String organizationName,
			String userId, String userName, String privateId, String dataType, String goodsType,
			List<SecureSet> secureSet, String contactID, String contactName, String contactPhone, String loginUser,
			String loginPass, String legal, String registeredLocation, String scopeOfBusiness, String registeredCapital,
			String abbreviation, String publicKey, List<Product> product, List<Location> location,
			List<Ability> ability) {
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
		this.contactID = contactID;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.loginUser = loginUser;
		this.loginPass = loginPass;
		this.legal = legal;
		this.registeredLocation = registeredLocation;
		this.scopeOfBusiness = scopeOfBusiness;
		this.registeredCapital = registeredCapital;
		this.abbreviation = abbreviation;
		this.publicKey = publicKey;
		this.product = product;
		this.location = location;
		this.ability = ability;
	}

	public IP() {
		
	}
	
	

}
