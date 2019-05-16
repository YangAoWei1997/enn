package com.enn.commodity.synergistic.entity;

public class Queuing {
	
	private Integer infoid;
	
	private Integer listcount;
	
	private String orgid;
	
	private String dataSourceId;
	
	private String dataSourceName;
	
	private String goodsType;

	
	public Integer getInfoid() {
		return infoid;
	}

	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}

	public Integer getListcount() {
		return listcount;
	}

	public void setListcount(Integer listcount) {
		this.listcount = listcount;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
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

	

	public Queuing(Integer infoid, Integer listcount, String orgid, String dataSourceId, String dataSourceName,
			String goodsType) {
		super();
		this.infoid = infoid;
		this.listcount = listcount;
		this.orgid = orgid;
		this.dataSourceId = dataSourceId;
		this.dataSourceName = dataSourceName;
		this.goodsType = goodsType;
	}

	public Queuing() {
		
	}
	
	

}
