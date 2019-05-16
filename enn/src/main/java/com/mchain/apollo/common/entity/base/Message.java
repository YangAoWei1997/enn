/**
 * Copyright @2019 Ming-Chain (ShenZhen) Technology Co., Ltd. All rights reserved.
 *
 * @Title: Message.java
 * @Project: apollo.core
 * @Package: com.mchain.apollo.core.entity
 * @Description: TODO
 * @author: longjinsheng  
 * @date: 18 Jan. 2019 12:28:03 pm
 * @version: V1.0  
 **/
package com.mchain.apollo.common.entity.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: Message
 * @Description: 报文实体
 * @author: longjinsheng
 * @date: 18 Jan. 2019 12:28:03 pm
 */
public class Message<T> implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = 3997798592960845812L;
	
	private String id;
	private String channel;
	private String tranCode;
	private String type;
	private String dataType;
	private String tranDate;
	private String traceNo;
	private String retCode;
	private String debug;
	private String userid;
	private String orgid;
	private Boolean hasGeneric = false;
	
	private List<Error> errors;
	private List<Arg> args;
	private List<Data> data;
	private T retObject;
	
	public Message() {
		
	}

	public Message(String retCode) {
		this.retCode = retCode;
	}
	
	public Message(T object) {
		this.hasGeneric = true;
		this.retObject = object;
		this.retCode = ReturnCodes.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public Message(Object cntObj, Boolean hasGeneric) {
		if(hasGeneric) {
			try {
				this.retObject = (T)cntObj;
				this.hasGeneric = true;
				this.retCode = ReturnCodes.SUCCESS;
			}catch(Exception e) {
				e.printStackTrace();
				this.data = null;
				this.retCode = ReturnCodes.MSG_PARSE_ERR;
				this.addError(ReturnCodes.MSG_PARSE_ERR, "Message", e.getMessage());
			}
		}else {
			this.hasGeneric = false;
			data = new ArrayList<Data>();
			Data item = new Data();
			ObjectMapper mapper = new ObjectMapper();
			String content;
			try {
				content = mapper.writeValueAsString(cntObj);
				item.setContent(content);
				item.setDataType(cntObj.getClass().getName());
				item.setFormatType(FormatType.STRING);
				this.data.add(item);
				this.retCode = ReturnCodes.SUCCESS;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				this.data = null;
				this.retCode = ReturnCodes.MSG_PARSE_ERR;
				this.addError(ReturnCodes.MSG_PARSE_ERR, this.getClass().getName(), e.getMessage());
			}
		}
	}
	
	public Message(Object cntObj, String formatType) {
		this.addDataItem(cntObj, formatType);
		this.retCode = ReturnCodes.SUCCESS;
	}
	
	private void addDataItem(Object cntObj, String formatType) {
		this.hasGeneric = false;
		Object content = cntObj;
		if(formatType.equalsIgnoreCase(FormatType.STRING)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				content = mapper.writeValueAsString(cntObj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if(this.data == null) this.data = new ArrayList<Data>();
		Data item = new Data();
		item.setContent(content);
		item.setDataType(cntObj.getClass().getName());
		item.setFormatType(formatType);
		this.data.add(item);
	}
	
	public Message(Error error) {
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		this.errors.add(error);
		this.retCode = ReturnCodes.FAIL;
	}

	public Message(List<Error> errorList) {
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		this.errors.addAll(errorList);
		this.retCode = ReturnCodes.FAIL;
	}

	public Message(String code, String function, String statement) {
		this.addError(code, function, statement);
		this.retCode = ReturnCodes.FAIL;
	}

	private void addError(String code, String function, String statement) {
		Error error = new Error();
		error.setCode(code);
		error.setFunction(function);
		error.setStatement(statement);
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		this.errors.add(error);
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getDebug() {
		return debug;
	}
	public void setDebug(String debug) {
		this.debug = debug;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	public List<Arg> getArgs() {
		return args;
	}
	public void setArgs(List<Arg> args) {
		this.args = args;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}

	public Boolean getHasGeneric() {
		return hasGeneric;
	}

	public void setHasGeneric(Boolean hasGeneric) {
		this.hasGeneric = hasGeneric;
	}

	public T getRetObject() {
		return retObject;
	}

	public void setRetObject(T retObject) {
		this.retObject = retObject;
	}
	
}
