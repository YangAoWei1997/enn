package com.enn.commodity.synergistic.entity;

public class MineralResources {
	
	private Integer localityInfoId;
	
	private String locality;
	
	private String productName;
	
	private String unitprice;
	
	private String commitdate;
	
	private String phonenumber;
	
	private String userId;
	
	private String img;
	
	private String showname;
	
	private String location;
	
	private String combustionvalue;
	
	private Integer isCompany;
	
	private String dataSourceId;
	
	private String dataSourceName;
	
	private String goodsType;

	public Integer getLocalityInfoId() {
		return localityInfoId;
	}

	public void setLocalityInfoId(Integer localityInfoId) {
		this.localityInfoId = localityInfoId;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	

	public String getCommitdate() {
		return commitdate;
	}

	public void setCommitdate(String commitdate) {
		this.commitdate = commitdate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCombustionvalue() {
		return combustionvalue;
	}

	public void setCombustionvalue(String combustionvalue) {
		this.combustionvalue = combustionvalue;
	}
	
	

	

	public Integer getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(Integer isCompany) {
		this.isCompany = isCompany;
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

	

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	

	public MineralResources(Integer localityInfoId, String locality, String productName, String unitprice,
			String commitdate, String phonenumber, String userId, String img, String showname, String location,
			String combustionvalue, Integer isCompany, String dataSourceId, String dataSourceName, String goodsType) {
		super();
		this.localityInfoId = localityInfoId;
		this.locality = locality;
		this.productName = productName;
		this.unitprice = unitprice;
		this.commitdate = commitdate;
		this.phonenumber = phonenumber;
		this.userId = userId;
		this.img = img;
		this.showname = showname;
		this.location = location;
		this.combustionvalue = combustionvalue;
		this.isCompany = isCompany;
		this.dataSourceId = dataSourceId;
		this.dataSourceName = dataSourceName;
		this.goodsType = goodsType;
	}

	public MineralResources() {
	
	}
	
	

}
