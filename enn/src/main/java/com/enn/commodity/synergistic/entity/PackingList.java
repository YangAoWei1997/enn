package com.enn.commodity.synergistic.entity;

import java.util.List;

public class PackingList {
	
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
	
	private String productId;
	private String productName;
	private String quantity;
	private String quantityUnit;
	private String buyerId;
	private String buyerName;
	private String sellerId;
	private String sellerName;
	private String plateNo;
	private String billOfLadingId;
	private String source;
	private String destination;
	private String date;
	private String relatedPONumber;
	private String packingType;
	private String carrierName;
	private String carrierId;
	private String leavefirsttime;
	private String leavefirstweight;
	private String leavesecondtime;
	private String leavesecondweight;
	private String leavedDate;
	private String reachedDate;
	private String driverName;
	private String driverId;
	private String weight;
	private String emptyCarWeight;
	private String carWeight;
	private String qualityId;
	private String salesPONumber;
	private String purchasePONumber;
	
	private QualityInfo qualityinfo;
	
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
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getBillOfLadingId() {
		return billOfLadingId;
	}
	public void setBillOfLadingId(String billOfLadingId) {
		this.billOfLadingId = billOfLadingId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRelatedPONumber() {
		return relatedPONumber;
	}
	public void setRelatedPONumber(String relatedPONumber) {
		this.relatedPONumber = relatedPONumber;
	}
	public String getPackingType() {
		return packingType;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	public String getLeavefirsttime() {
		return leavefirsttime;
	}
	public void setLeavefirsttime(String leavefirsttime) {
		this.leavefirsttime = leavefirsttime;
	}
	public String getLeavefirstweight() {
		return leavefirstweight;
	}
	public void setLeavefirstweight(String leavefirstweight) {
		this.leavefirstweight = leavefirstweight;
	}
	public String getLeavesecondtime() {
		return leavesecondtime;
	}
	public void setLeavesecondtime(String leavesecondtime) {
		this.leavesecondtime = leavesecondtime;
	}
	public String getLeavesecondweight() {
		return leavesecondweight;
	}
	public void setLeavesecondweight(String leavesecondweight) {
		this.leavesecondweight = leavesecondweight;
	}
	public String getLeavedDate() {
		return leavedDate;
	}
	public void setLeavedDate(String leavedDate) {
		this.leavedDate = leavedDate;
	}
	public String getReachedDate() {
		return reachedDate;
	}
	public void setReachedDate(String reachedDate) {
		this.reachedDate = reachedDate;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
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
	public String getCarWeight() {
		return carWeight;
	}
	public void setCarWeight(String carWeight) {
		this.carWeight = carWeight;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
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
	
	public QualityInfo getQualityinfo() {
		return qualityinfo;
	}
	public void setQualityinfo(QualityInfo qualityinfo) {
		this.qualityinfo = qualityinfo;
	}
	
	
	public PackingList(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, String dataSourceId, String dataSourceName, List<SecureSet> secureSet, String productId,
			String productName, String quantity, String quantityUnit, String buyerId, String buyerName, String sellerId,
			String sellerName, String plateNo, String billOfLadingId, String source, String destination, String date,
			String relatedPONumber, String packingType, String carrierName, String carrierId, String leavefirsttime,
			String leavefirstweight, String leavesecondtime, String leavesecondweight, String leavedDate,
			String reachedDate, String driverName, String driverId, String weight, String emptyCarWeight,
			String carWeight, String qualityId, String salesPONumber, String purchasePONumber,
			QualityInfo qualityinfo) {
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
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.plateNo = plateNo;
		this.billOfLadingId = billOfLadingId;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.relatedPONumber = relatedPONumber;
		this.packingType = packingType;
		this.carrierName = carrierName;
		this.carrierId = carrierId;
		this.leavefirsttime = leavefirsttime;
		this.leavefirstweight = leavefirstweight;
		this.leavesecondtime = leavesecondtime;
		this.leavesecondweight = leavesecondweight;
		this.leavedDate = leavedDate;
		this.reachedDate = reachedDate;
		this.driverName = driverName;
		this.driverId = driverId;
		this.weight = weight;
		this.emptyCarWeight = emptyCarWeight;
		this.carWeight = carWeight;
		this.qualityId = qualityId;
		this.salesPONumber = salesPONumber;
		this.purchasePONumber = purchasePONumber;
		this.qualityinfo = qualityinfo;
	}
	public PackingList() {
		
	}
	
	
	

}
