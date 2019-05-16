/**
 * Copyright @2019 Ming-Chain (ShenZhen) Technology Co., Ltd. All rights reserved.
 *
 * @Title: Error.java
 * @Project: apollo.core
 * @Package: com.mchain.apollo.core.entity
 * @Description: TODO
 * @author: longjinsheng  
 * @date: 18 Jan. 2019 12:36:32 pm
 * @version: V1.0  
 **/
package com.mchain.apollo.common.entity.base;

import java.io.Serializable;

/**
 * @ClassName: Error
 * @Description: TODO
 * @author: longjinsheng
 * @date: 18 Jan. 2019 12:36:32 pm
 */
public class Error implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = -2163327354546780529L;
	
	private String statement;
	private String function;
	private String code;
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
