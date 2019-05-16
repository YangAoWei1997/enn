/**
 * Copyright @2019 Ming-Chain (ShenZhen) Technology Co., Ltd. All rights reserved.
 *
 * @Title: Arg.java
 * @Project: apollo.core
 * @Package: com.mchain.apollo.core.entity
 * @Description: TODO
 * @author: longjinsheng  
 * @date: 18 Jan. 2019 12:37:59 pm
 * @version: V1.0  
 **/
package com.mchain.apollo.common.entity.base;

import java.io.Serializable;

/**
 * @ClassName: Arg
 * @Description: 查询参数
 * @author: longjinsheng
 * @date: 18 Jan. 2019 12:37:59 pm
 */
public class Arg implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = -4516235835698512695L;
	
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
