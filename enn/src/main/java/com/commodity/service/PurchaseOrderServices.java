package com.commodity.service;

import java.io.IOException;
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
public class PurchaseOrderServices {

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	// get logger
	private static Logger logger = Logger.getLogger(PurchaseOrderServices.class);
	
	//读取配置文件中，访问sdk的方式
	ResourceBundle resourceBundle = ResourceBundle.getBundle("method");
	String method = resourceBundle.getString("method");
						
	//sdk的invoke类型接口的restAPI路径
	String invokeUrl = resourceBundle.getString("invokeUrl");
						
	//sdk的query类型接口的restAPI路径
	String queryUrl = resourceBundle.getString("queryUrl");

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
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

		return "PurchaseOrder APIs OK";
	}
	
	/**
	 * 销售订单录入
	 *
	 * @param  saleOrder
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CreateSalesOrder")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createSalesOrder(@FormParam("SalesOrder") String salesOrder){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateSalesOrder  parameter###");
		logger.info(salesOrder);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateSalesOrder");
		message.setType("0");
		message.setDataType("SalesOrder");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfSalesOrder = MessageFactory.eINSTANCE.createData();
		dataOfSalesOrder.setContent(salesOrder);
		dataOfSalesOrder.setDataType("SalesOrder");
		
		message.getData().add(dataOfSalesOrder);
		
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
	 * 销售订单查询 
	 *
	 * @param  salesOrderId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/QuerySalesOrder")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryBillOfLading(@FormParam("SalesOrderId") String salesOrderId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QuerySalesOrder  parameter###");
		logger.info(salesOrderId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QuerySalesOrder");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfSalesOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfSalesOrderId.setName("SalesOrderId");
		argsOfSalesOrderId.setValue(salesOrderId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfSalesOrderId);
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
	 * 销售订单选择 
	 *
	 * @param  buyerId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectSalesOrder")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectSalesOrder(@FormParam("BuyerId") String buyerId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###SelectSalesOrder  parameter###");
		logger.info(buyerId);
		logger.info("###SelectSalesOrder  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectSalesOrder");
		message.setType("0");
		message.setDataType("SalesOrder");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfBuyerId = MessageFactory.eINSTANCE.createArgs();
		argsOfBuyerId.setName("BuyerId");
		argsOfBuyerId.setValue(buyerId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfBuyerId);
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
	 * 采购订单录入
	 *
	 * @param  saleOrder
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CreatePurchaseOrder")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createPurchaseOrder(@FormParam("PurchaseOrder") String purchaseOrder){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreatePurchaseOrder  parameter###");
		logger.info(purchaseOrder);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreatePurchaseOrder");
		message.setType("0");
		message.setDataType("PurchaseOrder");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfPurchaseOrder = MessageFactory.eINSTANCE.createData();
		dataOfPurchaseOrder.setContent(purchaseOrder);
		dataOfPurchaseOrder.setDataType("PurchaseOrder");
		
		message.getData().add(dataOfPurchaseOrder);
		
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
	 * 采购订单查询
	 *
	 * @param  buyerId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/QueryPurchaseOrder")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryPurchaseOrder(@FormParam("PurchaseOrderId") String purchaseOrderId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QueryPurchaseOrder  parameter###");
		logger.info(purchaseOrderId);
		logger.info("###QueryPurchaseOrder  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryPurchaseOrder");
		message.setType("0");
		message.setDataType("PurchaseOrder");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPurchaseOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfPurchaseOrderId.setName("PurchaseOrderId");
		argsOfPurchaseOrderId.setValue(purchaseOrderId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfPurchaseOrderId);
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
	 * 采购企业选择采购订单 
	 *
	 * @param  purchaseOrderId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectPurchaseOrderOfBuyer")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectPurchaseOrder(@FormParam("SalerId") String salerId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###SelectPurchaseOrder  parameter###");
		logger.info(salerId);
		logger.info("###SelectPurchaseOrder  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectPurchaseOrderOfBuyer");
		message.setType("0");
		message.setDataType("PurchaseOrder");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfSalerId = MessageFactory.eINSTANCE.createArgs();
		argsOfSalerId.setName("SalerId");
		argsOfSalerId.setValue(salerId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfSalerId);
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
	 * 销售企业选择采购订单 
	 *
	 * @param  purchaseOrderId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectPurchaseOrderOfSaler")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectPurchaseOrderOfSaler(@FormParam("BuyerId") String buyerId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###SelectPurchaseOrderOfSaler  parameter###");
		logger.info(buyerId);
		logger.info("###SelectPurchaseOrderOfSaler  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectPurchaseOrderOfSaler");
		message.setType("0");
		message.setDataType("PurchaseOrder");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfBuyerId = MessageFactory.eINSTANCE.createArgs();
		argsOfBuyerId.setName("BuyerId");
		argsOfBuyerId.setValue(buyerId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfBuyerId);
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
	 * 订单状态查询 
	 *
	 * @param  purchaseOrderId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/QueryStatusOfPO")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryStatusOfPO(@FormParam("PurchaseOrderId") String purchaseOrderId, @FormParam("SalesOrderId") String salesOrderId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QueryStatusOfPO  parameter###");
		logger.info(purchaseOrderId);
		logger.info("###QueryStatusOfPO  parameter###");
		logger.info(salesOrderId);
		logger.info("###QueryStatusOfPO  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryStatusOfPO");
		message.setType("0");
		message.setDataType("PO");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPurchaseOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfPurchaseOrderId.setName("PurchaseOrderId");
		argsOfPurchaseOrderId.setValue(purchaseOrderId);
		
		Args argsOfSalesOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfSalesOrderId.setName("SalesOrderId");
		argsOfSalesOrderId.setValue(salesOrderId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfPurchaseOrderId);
		message.getArgs().add(argsOfSalesOrderId);
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
	 * 采购订单号取得查询 
	 *
	 * @param  userId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/GetPurchaseOrderNoInfo")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String getPurchaseOrderNoInfo(@FormParam("UserId") String userId, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###GetPurchaseOrderNoInfo  parameter###");
		logger.info(userId);
		logger.info("###GetPurchaseOrderNoInfo  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("GetPurchaseOrderNoInfo");
		message.setType("0");
		message.setDataType("PO");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfUserId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserId.setName("UserId");
		argsOfUserId.setValue(userId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfUserId);
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
	 * 订单比较
	 *
	 * @param  purchaseOrderId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/ComparePO")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String compareOrder(@FormParam("SalesOrderId") String salesOrderId, @FormParam("PurchaseOrderId") String purchaseOrderId, @FormParam("UserOrgId") String userOrgId,
			@FormParam("ContractNumber") String contractNumber){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###ComparePO  parameter###");
		logger.info(salesOrderId);
		logger.info("###ComparePO  parameter###");
		logger.info(purchaseOrderId);
		logger.info("###ComparePO  parameter###");
		logger.info(userOrgId);
		logger.info("###ComparePO  parameter###");
		logger.info(contractNumber);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("ComparePO");
		message.setType("0");
		message.setDataType("PO");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfSalesOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfSalesOrderId.setName("SalesOrderId");
		argsOfSalesOrderId.setValue(salesOrderId);
		
		Args argsOfPurchaseOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfPurchaseOrderId.setName("PurchaseOrderId");
		argsOfPurchaseOrderId.setValue(purchaseOrderId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfContractNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfContractNumber.setName("ContractNumber");
		argsOfContractNumber.setValue(contractNumber);
		
		message.getArgs().add(argsOfSalesOrderId);
		message.getArgs().add(argsOfPurchaseOrderId);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfContractNumber);
		
		Message newMessage = MessageUtil.completeMessage(message, "Query");
		
		String respMsg = null;
		
		if (method.equals("Service")) {
			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
		}else {
			respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
		}	
		
		return respMsg;
	}
}
