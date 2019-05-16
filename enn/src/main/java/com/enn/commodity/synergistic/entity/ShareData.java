package com.enn.commodity.synergistic.entity;

public class ShareData {
	
	private String ID;
	
	private String name;
	
	private String comments;
	
	private String dataType;
	
	private String goodsType;
	
	private String resourceOrgID;
	
	private String resourceOrgName;
	
	private String resourceOrgType;
	
	private String resourceSystemID;
	
	private String resourceSystemName;
	
	private String destinations;
	
	private String productID;
	
	private String productName;
	
	private String requestType;
	
	private String price;
	
	private String quantity;
	
	private String quantityUnit;
	
	private String detail;
	
	private String expired;
	
	private String status;
	
	private String issuedDate;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getResourceOrgID() {
		return resourceOrgID;
	}

	public void setResourceOrgID(String resourceOrgID) {
		this.resourceOrgID = resourceOrgID;
	}

	public String getResourceOrgName() {
		return resourceOrgName;
	}

	public void setResourceOrgName(String resourceOrgName) {
		this.resourceOrgName = resourceOrgName;
	}

	public String getResourceOrgType() {
		return resourceOrgType;
	}

	public void setResourceOrgType(String resourceOrgType) {
		this.resourceOrgType = resourceOrgType;
	}

	public String getResourceSystemID() {
		return resourceSystemID;
	}

	public void setResourceSystemID(String resourceSystemID) {
		this.resourceSystemID = resourceSystemID;
	}

	public String getResourceSystemName() {
		return resourceSystemName;
	}

	public void setResourceSystemName(String resourceSystemName) {
		this.resourceSystemName = resourceSystemName;
	}

	public String getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public ShareData(String iD, String name, String comments, String dataType, String goodsType, String resourceOrgID,
			String resourceOrgName, String resourceOrgType, String resourceSystemID, String resourceSystemName,
			String destinations, String productID, String productName, String requestType, String price,
			String quantity, String quantityUnit, String detail, String expired, String status, String issuedDate) {
		super();
		this.ID = iD;
		this.name = name;
		this.comments = comments;
		this.dataType = dataType;
		this.goodsType = goodsType;
		this.resourceOrgID = resourceOrgID;
		this.resourceOrgName = resourceOrgName;
		this.resourceOrgType = resourceOrgType;
		this.resourceSystemID = resourceSystemID;
		this.resourceSystemName = resourceSystemName;
		this.destinations = destinations;
		this.productID = productID;
		this.productName = productName;
		this.requestType = requestType;
		this.price = price;
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
		this.detail = detail;
		this.expired = expired;
		this.status = status;
		this.issuedDate = issuedDate;
	}

	public ShareData() {
		
	}
	
	

}
