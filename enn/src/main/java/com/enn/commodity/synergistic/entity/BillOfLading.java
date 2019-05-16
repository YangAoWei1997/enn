package com.enn.commodity.synergistic.entity;

import java.util.List;

public class BillOfLading {
	
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
	
	private String salesPONumber;
	private String buyerId;
	private String buyerName;
	private String sellerId;
	private String sellerName;
	private String barCode;
	private String issuedDate;
	private String cardDate;
	private String productId;
	private String productName;
	private String productType;
	private String quantity;
	private String quantityUnit;
	private String status;
	private String carryPONumber;
	private String carrierId;
	private String carrierName;
	private String plateNo;
	private String driverId;
	private String driverName;
	private String relatedPONumber;
	private String carWeight;
	private String weight;
	private String emptyCarWeight;
	private String purchasePONumber;
	private String pickUpDate;
	
	
	public String getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
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
	public String getSalesPONumber() {
		return salesPONumber;
	}
	public void setSalesPONumber(String salesPONumber) {
		this.salesPONumber = salesPONumber;
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
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getCardDate() {
		return cardDate;
	}
	public void setCardDate(String cardDate) {
		this.cardDate = cardDate;
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getRelatedPONumber() {
		return relatedPONumber;
	}
	public void setRelatedPONumber(String relatedPONumber) {
		this.relatedPONumber = relatedPONumber;
	}
	public String getCarWeight() {
		return carWeight;
	}
	public void setCarWeight(String carWeight) {
		this.carWeight = carWeight;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getEmptyCarWeight() {
		return emptyCarWeight;
	}
	public void setEmptyCarWeight(String emptyCarWeight) {
		this.emptyCarWeight = emptyCarWeight;
	}
	public String getPurchasePONumber() {
		return purchasePONumber;
	}
	public void setPurchasePONumber(String purchasePONumber) {
		this.purchasePONumber = purchasePONumber;
	}
	
	
	public BillOfLading(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, String dataSourceId, String dataSourceName, List<SecureSet> secureSet,
			String salesPONumber, String buyerId, String buyerName, String sellerId, String sellerName, String barCode,
			String issuedDate, String cardDate, String productId, String productName, String productType,
			String quantity, String quantityUnit, String status, String carryPONumber, String carrierId,
			String carrierName, String plateNo, String driverId, String driverName, String relatedPONumber,
			String carWeight, String weight, String emptyCarWeight, String purchasePONumber, String pickUpDate) {
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
		this.salesPONumber = salesPONumber;
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.barCode = barCode;
		this.issuedDate = issuedDate;
		this.cardDate = cardDate;
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
		this.status = status;
		this.carryPONumber = carryPONumber;
		this.carrierId = carrierId;
		this.carrierName = carrierName;
		this.plateNo = plateNo;
		this.driverId = driverId;
		this.driverName = driverName;
		this.relatedPONumber = relatedPONumber;
		this.carWeight = carWeight;
		this.weight = weight;
		this.emptyCarWeight = emptyCarWeight;
		this.purchasePONumber = purchasePONumber;
		this.pickUpDate = pickUpDate;
	}
	public BillOfLading() {
		
	}
	
	

}
