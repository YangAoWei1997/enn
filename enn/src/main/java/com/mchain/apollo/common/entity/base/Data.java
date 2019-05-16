/**
 * Copyright @2019 Ming-Chain (ShenZhen) Technology Co., Ltd. All rights reserved.
 *
 * @Title: Data.java
 * @Project: apollo.core
 * @Package: com.mchain.apollo.core.entity
 * @Description: TODO
 * @author: longjinsheng  
 * @date: 18 Jan. 2019 12:40:11 pm
 * @version: V1.0  
 **/
package com.mchain.apollo.common.entity.base;

import java.io.Serializable;

/**
 * @ClassName: Data
 * @Description: TODO
 * @author: longjinsheng
 * @date: 18 Jan. 2019 12:40:11 pm
 */
public class Data implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = 8825392858371297978L;
	/**
	 * 数据实体
	 */
	private Object content;
	/**
	 * 数据格式
	 */
	private String formatType;
	/**
	 * 数据类型类名
	 */
	private String dataType;
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFormatType() {
		return formatType;
	}
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}
}
