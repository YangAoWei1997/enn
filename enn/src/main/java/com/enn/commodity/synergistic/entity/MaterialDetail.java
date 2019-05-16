package com.enn.commodity.synergistic.entity;

import java.util.List;

public class MaterialDetail {
	
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
	
	private String quantity;
	private String quantityUnit;
	private String priceNoTax;
	private String priceWithTax;
	private String totalPriceNoTax;
	private String totalPriceWithTax;
	private String priceQualityUnit;
	private String taxRate;
	private String tax;
	private String masterId;
	private String realTotalPrice;
	private String realPrice;
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
	public String getPriceNoTax() {
		return priceNoTax;
	}
	public void setPriceNoTax(String priceNoTax) {
		this.priceNoTax = priceNoTax;
	}
	public String getPriceWithTax() {
		return priceWithTax;
	}
	public void setPriceWithTax(String priceWithTax) {
		this.priceWithTax = priceWithTax;
	}
	public String getTotalPriceNoTax() {
		return totalPriceNoTax;
	}
	public void setTotalPriceNoTax(String totalPriceNoTax) {
		this.totalPriceNoTax = totalPriceNoTax;
	}
	public String getTotalPriceWithTax() {
		return totalPriceWithTax;
	}
	public void setTotalPriceWithTax(String totalPriceWithTax) {
		this.totalPriceWithTax = totalPriceWithTax;
	}
	public String getPriceQualityUnit() {
		return priceQualityUnit;
	}
	public void setPriceQualityUnit(String priceQualityUnit) {
		this.priceQualityUnit = priceQualityUnit;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getRealTotalPrice() {
		return realTotalPrice;
	}
	public void setRealTotalPrice(String realTotalPrice) {
		this.realTotalPrice = realTotalPrice;
	}
	public String getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}
	public MaterialDetail(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, List<SecureSet> secureSet, String quantity, String quantityUnit, String priceNoTax,
			String priceWithTax, String totalPriceNoTax, String totalPriceWithTax, String priceQualityUnit,
			String taxRate, String tax, String masterId, String realTotalPrice, String realPrice) {
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
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
		this.priceNoTax = priceNoTax;
		this.priceWithTax = priceWithTax;
		this.totalPriceNoTax = totalPriceNoTax;
		this.totalPriceWithTax = totalPriceWithTax;
		this.priceQualityUnit = priceQualityUnit;
		this.taxRate = taxRate;
		this.tax = tax;
		this.masterId = masterId;
		this.realTotalPrice = realTotalPrice;
		this.realPrice = realPrice;
	}
	public MaterialDetail() {
		
	}
	
	

}
