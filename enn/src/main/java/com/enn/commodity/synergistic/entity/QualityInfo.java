package com.enn.commodity.synergistic.entity;

import java.util.List;

public class QualityInfo {
	
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
	private String dataSourceId;
	private String dataSourceName;
	
	private List<SecureSet> secureSet;
	
	private String validator;
	private String validatorName;
	private String qualityDate;
	private String serialNumber;
	private String quality;
	private String productId;
	private String plateNo;
	private String orgname;
	private String productName;
	private String qualityType;
	private String orgId;
	private String reportQualityDate;
	private String source;
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
	
	public String getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public List<SecureSet> getSecureSet() {
		return secureSet;
	}
	public void setSecureSet(List<SecureSet> secureSet) {
		this.secureSet = secureSet;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidator(String validator) {
		this.validator = validator;
	}
	public String getValidatorName() {
		return validatorName;
	}
	public void setValidatorName(String validatorName) {
		this.validatorName = validatorName;
	}
	public String getQualityDate() {
		return qualityDate;
	}
	public void setQualityDate(String qualityDate) {
		this.qualityDate = qualityDate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQualityType() {
		return qualityType;
	}
	public void setQualityType(String qualityType) {
		this.qualityType = qualityType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getReportQualityDate() {
		return reportQualityDate;
	}
	public void setReportQualityDate(String reportQualityDate) {
		this.reportQualityDate = reportQualityDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public QualityInfo(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, String dataSourceId, String dataSourceName, List<SecureSet> secureSet, String validator,
			String validatorName, String qualityDate, String serialNumber, String quality, String productId,
			String plateNo, String orgname, String productName, String qualityType, String orgId,
			String reportQualityDate, String source) {
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
		this.dataSourceId = dataSourceId;
		this.dataSourceName = dataSourceName;
		this.secureSet = secureSet;
		this.validator = validator;
		this.validatorName = validatorName;
		this.qualityDate = qualityDate;
		this.serialNumber = serialNumber;
		this.quality = quality;
		this.productId = productId;
		this.plateNo = plateNo;
		this.orgname = orgname;
		this.productName = productName;
		this.qualityType = qualityType;
		this.orgId = orgId;
		this.reportQualityDate = reportQualityDate;
		this.source = source;
	}
	public QualityInfo() {
		
	}

	
}
