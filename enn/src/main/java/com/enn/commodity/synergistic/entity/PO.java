package com.enn.commodity.synergistic.entity;

import java.util.List;

public class PO {
	
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
	private String arrangementId;
	private String customerArrangementId;
	private String relatedPONumber;
	private String issuedDate;
	private String status;
	private String productId;
	private String productName;
	private String productType;
	private String carryPONumber;
	private String carrierId;
	private String carrierName;
	private String discontRate;
	private String payType;
	private String poType;
	private String plateno;
	private String payTypeName;
	private String salesPONumber;
	private String purchasePONumber;
	private String completeWeight;
	private String carNumber;
	
	private MaterialDetail materialdetail;
	
	private List<Payment> payment;

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

	public String getArrangementId() {
		return arrangementId;
	}

	public void setArrangementId(String arrangementId) {
		this.arrangementId = arrangementId;
	}

	public String getCustomerArrangementId() {
		return customerArrangementId;
	}

	public void setCustomerArrangementId(String customerArrangementId) {
		this.customerArrangementId = customerArrangementId;
	}

	public String getRelatedPONumber() {
		return relatedPONumber;
	}

	public void setRelatedPONumber(String relatedPONumber) {
		this.relatedPONumber = relatedPONumber;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCarryPONumber() {
		return carryPONumber;
	}

	public void setCarryPONumber(String carryPONumber) {
		this.carryPONumber = carryPONumber;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getDiscontRate() {
		return discontRate;
	}

	public void setDiscontRate(String discontRate) {
		this.discontRate = discontRate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getSalesPONumber() {
		return salesPONumber;
	}

	public void setSalesPONumber(String salesPONumber) {
		this.salesPONumber = salesPONumber;
	}

	public String getPurchasePONumber() {
		return purchasePONumber;
	}

	public void setPurchasePONumber(String purchasePONumber) {
		this.purchasePONumber = purchasePONumber;
	}

	

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	
	

	public String getCompleteWeight() {
		return completeWeight;
	}

	public void setCompleteWeight(String completeWeight) {
		this.completeWeight = completeWeight;
	}
	
	

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public MaterialDetail getMaterialdetail() {
		return materialdetail;
	}

	public void setMaterialdetail(MaterialDetail materialdetail) {
		this.materialdetail = materialdetail;
	}


	public PO(String id, String name, String version, String comments, String organizationId, String organizationName,
			String userId, String userName, String privateId, String dataType, String goodsType, String dataSourceId,
			String dataSourceName, List<SecureSet> secureSet, String buyerId, String buyerName, String sellerId,
			String sellerName, String type, String arrangementId, String customerArrangementId, String relatedPONumber,
			String issuedDate, String status, String productId, String productName, String productType,
			String carryPONumber, String carrierId, String carrierName, String discontRate, String payType,
			String poType, String plateno, String payTypeName, String salesPONumber, String purchasePONumber,
			String completeWeight, String carNumber, MaterialDetail materialdetail, List<Payment> payment) {
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
		this.arrangementId = arrangementId;
		this.customerArrangementId = customerArrangementId;
		this.relatedPONumber = relatedPONumber;
		this.issuedDate = issuedDate;
		this.status = status;
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.carryPONumber = carryPONumber;
		this.carrierId = carrierId;
		this.carrierName = carrierName;
		this.discontRate = discontRate;
		this.payType = payType;
		this.poType = poType;
		this.plateno = plateno;
		this.payTypeName = payTypeName;
		this.salesPONumber = salesPONumber;
		this.purchasePONumber = purchasePONumber;
		this.completeWeight = completeWeight;
		this.carNumber = carNumber;
		this.materialdetail = materialdetail;
		this.payment = payment;
	}

	public PO() {
		
	}
	
	

}
