package com.enn.commodity.synergistic.entity;

public class Function {
	
	private String funname;
	
	private String funid;
	
	private Integer funindex;
	
	private String funurl;
	
	private String poweridfy;
	
	private String fundetail;

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public String getFunid() {
		return funid;
	}

	public void setFunid(String funid) {
		this.funid = funid;
	}

	public Integer getFunindex() {
		return funindex;
	}

	public void setFunindex(Integer funindex) {
		this.funindex = funindex;
	}

	public String getFunurl() {
		return funurl;
	}

	public void setFunurl(String funurl) {
		this.funurl = funurl;
	}

	public String getPoweridfy() {
		return poweridfy;
	}

	public void setPoweridfy(String poweridfy) {
		this.poweridfy = poweridfy;
	}

	public String getFundetail() {
		return fundetail;
	}

	public void setFundetail(String fundetail) {
		this.fundetail = fundetail;
	}

	public Function(String funname, String funid, Integer funindex, String funurl, String poweridfy, String fundetail) {
		super();
		this.funname = funname;
		this.funid = funid;
		this.funindex = funindex;
		this.funurl = funurl;
		this.poweridfy = poweridfy;
		this.fundetail = fundetail;
	}

	public Function() {
		
	}
	
	
	

}
