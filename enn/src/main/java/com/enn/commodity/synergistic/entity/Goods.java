package com.enn.commodity.synergistic.entity;

import java.util.List;

public class Goods {
	
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
	
	private String buyerId;
	private String buyerName;
	private String sellerId;
	private String sellerName;
	private String ownerId;
	private String ownerName;
	private String managerId;
	private String managerName;
	private String carrierId;
	private String carrierName;
	private String plateNo;
	private String driverId;
	private String driverName;
	private String productId;
	private String productName;
	private String quantity;
	private String quantityUnit;
	private String source;
	private String destination;
	private String status;
	private String relatedPONumber;
	private String billOfLadingId;
	private String packingListIds;
	private String parentsGoodsId;
	private String type;
	private String salesPONumber;
	private String purchasePONumber;
	
	private List<Monitor>monitor;
	private List<QualityInfo>QualityInfo;
	private List<PackingList>packingList;
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
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRelatedPONumber() {
		return relatedPONumber;
	}
	public void setRelatedPONumber(String relatedPONumber) {
		this.relatedPONumber = relatedPONumber;
	}
	public String getBillOfLadingId() {
		return billOfLadingId;
	}
	public void setBillOfLadingId(String billOfLadingId) {
		this.billOfLadingId = billOfLadingId;
	}
	public String getPackingListIds() {
		return packingListIds;
	}
	public void setPackingListIds(String packingListIds) {
		this.packingListIds = packingListIds;
	}
	public String getParentsGoodsId() {
		return parentsGoodsId;
	}
	public void setParentsGoodsId(String parentsGoodsId) {
		this.parentsGoodsId = parentsGoodsId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public List<Monitor> getMonitor() {
		return monitor;
	}
	public void setMonitor(List<Monitor> monitor) {
		this.monitor = monitor;
	}
	public List<QualityInfo> getQualityInfo() {
		return QualityInfo;
	}
	public void setQualityInfo(List<QualityInfo> qualityInfo) {
		QualityInfo = qualityInfo;
	}
	public List<PackingList> getPackingList() {
		return packingList;
	}
	public void setPackingList(List<PackingList> packingList) {
		this.packingList = packingList;
	}
	public Goods(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, List<SecureSet> secureSet, String buyerId, String buyerName, String sellerId,
			String sellerName, String ownerId, String ownerName, String managerId, String managerName, String carrierId,
			String carrierName, String plateNo, String driverId, String driverName, String productId,
			String productName, String quantity, String quantityUnit, String source, String destination, String status,
			String relatedPONumber, String billOfLadingId, String packingListIds, String parentsGoodsId, String type,
			String salesPONumber, String purchasePONumber, List<Monitor> monitor,
			List<com.enn.commodity.synergistic.entity.QualityInfo> qualityInfo, List<PackingList> packingList) {
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
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.managerId = managerId;
		this.managerName = managerName;
		this.carrierId = carrierId;
		this.carrierName = carrierName;
		this.plateNo = plateNo;
		this.driverId = driverId;
		this.driverName = driverName;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
		this.source = source;
		this.destination = destination;
		this.status = status;
		this.relatedPONumber = relatedPONumber;
		this.billOfLadingId = billOfLadingId;
		this.packingListIds = packingListIds;
		this.parentsGoodsId = parentsGoodsId;
		this.type = type;
		this.salesPONumber = salesPONumber;
		this.purchasePONumber = purchasePONumber;
		this.monitor = monitor;
		QualityInfo = qualityInfo;
		this.packingList = packingList;
	}
	public Goods() {
		
	}
	
	

}
