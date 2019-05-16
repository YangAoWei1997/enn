package com.enn.commodity.synergistic.entity;

public class Plan {
	
	private String ordertime;
	
	private String orderdate;
	
	private String enorder;
	
	private String ordered;
	
	private String orgid;
	
	private int planindex;

	

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getEnorder() {
		return enorder;
	}

	public void setEnorder(String enorder) {
		this.enorder = enorder;
	}

	public String getOrdered() {
		return ordered;
	}

	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public int getPlanindex() {
		return planindex;
	}

	public void setPlanindex(int planindex) {
		this.planindex = planindex;
	}

	

	public Plan(String ordertime, String orderdate, String enorder, String ordered, String orgid, int planindex) {
		super();
		this.ordertime = ordertime;
		this.orderdate = orderdate;
		this.enorder = enorder;
		this.ordered = ordered;
		this.orgid = orgid;
		this.planindex = planindex;
	}

	public Plan() {
		
	}
	
	

}
