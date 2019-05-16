package com.enn.commodity.synergistic.entity;

import java.util.List;





public class Arrangement {
	
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
	
	private String buyerId;
	private String buyerName;
	private String sellerId;
	private String sellerName;
	private String type;
	private String issuedDate;
	private String expireDate;
	private String expiredDate;
	private String productId;
	private String productName;
	private String customerArrangementId;
	private String startDate;
	private String endDate;
	
	
	private MaterialDetail arrangementdetail;
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
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
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerArrangementId() {
		return customerArrangementId;
	}
	public void setCustomerArrangementId(String customerArrangementId) {
		this.customerArrangementId = customerArrangementId;
	}
	
	public MaterialDetail getArrangementdetail() {
		return arrangementdetail;
	}
	public void setArrangementdetail(MaterialDetail arrangementdetail) {
		this.arrangementdetail = arrangementdetail;
	}
	
	
	
	public Arrangement(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, String dataSourceId, String dataSourceName, List<SecureSet> secureSet, String buyerId,
			String buyerName, String sellerId, String sellerName, String type, String issuedDate, String expireDate,
			String expiredDate, String productId, String productName, String customerArrangementId, String startDate,
			String endDate, MaterialDetail arrangementdetail) {
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
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.type = type;
		this.issuedDate = issuedDate;
		this.expireDate = expireDate;
		this.expiredDate = expiredDate;
		this.productId = productId;
		this.productName = productName;
		this.customerArrangementId = customerArrangementId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.arrangementdetail = arrangementdetail;
	}
	public Arrangement() {
		
	}
	
	
	
	

}
