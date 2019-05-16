package com.commodity.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.commodity.util.JSONTransferUtil;
import com.commodity.util.MessageUtil;
import com.commodity.util.RestUtil;
import com.mchain.model.Message.Args;
import com.mchain.model.Message.Data;
import com.mchain.model.Message.Message;
import com.mchain.model.Message.MessageFactory;

@Service
public class IPServices {

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	// get logger
	private static Logger logger = Logger.getLogger(IPServices.class);
	
	//读取配置文件中，访问sdk的方式
	ResourceBundle resourceBundle = ResourceBundle.getBundle("method");
	String method = resourceBundle.getString("method");
			
	//sdk的invoke类型接口的restAPI路径
	String invokeUrl = resourceBundle.getString("invokeUrl");
			
	//sdk的query类型接口的restAPI路径
	String queryUrl = resourceBundle.getString("queryUrl");

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	FabricServices fs = new FabricServices();
	
	/* Service Check */
	@GET
	@Path("/check")
	@Consumes("application/x-www-form-urlencoded")
	public String check() {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");

		return "IP APIs OK";
	}

	
	/**
	 * 创建企业信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/CreateIPInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createIPInformation(String IPInformation) {
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Headers",
//				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateIPInformation  parameter###");
		logger.info(IPInformation);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateIPInformation");
		message.setType("0");
		message.setDataType("IP");
		message.setTranDate(dateformat.format(new Date()));
		
		Data dataOfIP = MessageFactory.eINSTANCE.createData();
		dataOfIP.setContent(IPInformation);
		dataOfIP.setDataType("IP");
		
		message.getData().add(dataOfIP);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 更新企业信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/UpdateIPInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String updateIPInformation(String IPInformation) {
		
		
		logger.info("###UpdateIPInformation  parameter###");
		logger.info(IPInformation);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("UpdateIPInformation");
		message.setType("0");
		message.setDataType("IP");
		message.setTranDate(dateformat.format(new Date()));
		
		Data dataOfIP = MessageFactory.eINSTANCE.createData();
		dataOfIP.setContent(IPInformation);
		dataOfIP.setDataType("IP");
		
		message.getData().add(dataOfIP);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 企业名称获得接口
	 * 
	 * @param UserOrgId: "org1"
	 * 
	 * @return ipInfo
	 * @throws
	 * */
	@POST
	@Path("/GetIPInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String getIPInformation(String userOrgId){
		
		
		logger.info("###GetIPInformation  parameter###");
		logger.info(userOrgId);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("GetIPInformation");
		message.setType("0");
		message.setDataType("IP");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserOrgId);
		
		Message newMessage = MessageUtil.completeMessage(message, "Query");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 获取到所有企业信息的接口（有权限配置）
	 * 
	 * @param UserOrgId
	 * 
	 * @return ipInfo
	 * */
	@POST
	@Path("/GetAllIPInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String getAllIPInformation(String userOrgId){
		
		
		logger.info("###GetAllIPInformation  parameter###");
		logger.info(userOrgId);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("GetAllIPInformation");
		message.setType("0");
		message.setDataType("IP");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserOrgId);
		
		Message newMessage = MessageUtil.completeMessage(message, "Query");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 创建物料信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/CreateProductInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createGoodsInformation(@FormParam("GoodsInformation") String goodsInformation, @FormParam("IPid") String IPid) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateGoodsInformation  parameter###");
		logger.info(goodsInformation);
		logger.info("###CreateGoodsInformation  parameter###");
		logger.info(IPid);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateProductInformation");
		message.setType("0");
		message.setDataType("Goods");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfIPid = MessageFactory.eINSTANCE.createArgs();
		argsOfIPid.setName("IPid");
		argsOfIPid.setValue(IPid);
		
		message.getArgs().add(argsOfIPid);
		
		Data dataOfIP = MessageFactory.eINSTANCE.createData();
		dataOfIP.setContent(goodsInformation);
		dataOfIP.setDataType("Goods");
		
		message.getData().add(dataOfIP);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 更新物料 信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/UpdateProductInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String updateGoodsInformation(@FormParam("GoodsInformation") String goodsInformation) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###UpdateGoodsInformation  parameter###");
		logger.info(goodsInformation);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("UpdateProductInformation");
		message.setType("0");
		message.setDataType("Goods");
		message.setTranDate(dateformat.format(new Date()));
		
		Data dataOfGoods = MessageFactory.eINSTANCE.createData();
		dataOfGoods.setContent(goodsInformation);
		dataOfGoods.setDataType("Goods");
		
		message.getData().add(dataOfGoods);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 物料名称获得接口
	 * 
	 * @param userid userOrgId
	 * 
	 * @return ipInfo
	 * @throws
	 * */
	@POST
	@Path("/GetProductInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String getGoodsInformation(@FormParam("UserOrgId") String userOrgId) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Meethods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###GetGoodsInformation  parameter###");
		logger.info(userOrgId);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("GetProductInformation");
		message.setType("0");
		message.setDataType("IP");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserOrgId);
		
		Message newMessage = MessageUtil.completeMessage(message, "Query");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	
	/**
	 * 创建计划信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/CreatePlan")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createPlan(@FormParam("UserOrgId") String userOrgId, @FormParam("Plan") String plan) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreatePlan  parameter###");
		logger.info(userOrgId);
		logger.info("###CreatePlan  parameter###");
		logger.info(plan);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreatePlan");
		message.setType("0");
		message.setDataType("Plan");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserOrgId);
		
		Data dataOfPlan = MessageFactory.eINSTANCE.createData();
		dataOfPlan.setContent(plan);
		dataOfPlan.setDataType("Plan");
		
		message.getData().add(dataOfPlan);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	
	/**
	 * 更新计划信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/UpdatePlan")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String updatePlan(@FormParam("UserOrgId") String userOrgId, @FormParam("Plan") String plan) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###UpdatePlan  parameter###");
		logger.info(userOrgId);
		logger.info("###UpdatePlan  parameter###");
		logger.info(plan);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("UpdatePlan");
		message.setType("0");
		message.setDataType("Plan");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserOrgId);
		
		Data dataOfPlan = MessageFactory.eINSTANCE.createData();
		dataOfPlan.setContent(plan);
		dataOfPlan.setDataType("Plan");
		
		message.getData().add(dataOfPlan);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 查询计划信息接口
	 * 
	 * @param userid userOrgId
	 * 
	 * @return ipInfo
	 * @throws
	 * */
	@POST
	@Path("/QueryPlan")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryPlan(@FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QueryPlan  parameter###");
		logger.info(userOrgId);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryPlan");
		message.setType("0");
		message.setDataType("IP");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserOrgId);
		
		Message newMessage = MessageUtil.completeMessage(message, "Query");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
	
	/**
	 * 创建权限信息接口
	 * 
	 * @param orgAuth
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/CreatePermissionInformation")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createPermissionInformation(@FormParam("Permission") String permission) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreatePermissionInformation  parameter###");
		logger.info(permission);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreatePermissionInformation");
		message.setType("0");
		message.setDataType("OrgAuth");
		message.setTranDate(dateformat.format(new Date()));
		
		Data dataOfPermission = MessageFactory.eINSTANCE.createData();
		dataOfPermission.setContent(permission);
		dataOfPermission.setDataType("OrgAuth");
		
		message.getData().add(dataOfPermission);
		
		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}
		
		return respMsg;
	}
}