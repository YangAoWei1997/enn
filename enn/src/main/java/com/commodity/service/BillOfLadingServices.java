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
import com.mchain.model.Message.Data;
import com.mchain.model.Message.Args;
import com.mchain.model.Message.Message;
import com.mchain.model.Message.MessageFactory;;

@Service
public class BillOfLadingServices {

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	// get logger
	private static Logger logger = Logger.getLogger(BillOfLadingServices.class);
	
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

		return "BillOfLading APIs OK";
	}
	
	/**
	 * 提货单录入
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CreateBillOfLading")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createBillOfLading(String billOfLading){
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Headers",
//				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateBillOfLading  parameter###");
		logger.info(billOfLading);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateBillOfLading");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfBillOfLading = MessageFactory.eINSTANCE.createData();
		dataOfBillOfLading.setContent(billOfLading);
		dataOfBillOfLading.setDataType("BillOfLading");
		
		message.getData().add(dataOfBillOfLading);
		
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
	 * 提货单更新
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/UpdateBillOfLading")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String updateBillOfLading(String billOfLadingId, String plateNo,String driverId,
			String driverName, String carrierId,  String carrierName){
		
		
		logger.info("###UpdateBillOfLading  parameter###");
		logger.info(billOfLadingId);
		logger.info("###UpdateBillOfLading  parameter###");
		logger.info(plateNo);
		logger.info("###UpdateBillOfLading  parameter###");
		logger.info(driverId);
		logger.info("###UpdateBillOfLading  parameter###");
		logger.info(driverName);
		logger.info("###UpdateBillOfLading  parameter###");
		logger.info(carrierId);
		logger.info("###UpdateBillOfLading  parameter###");
		logger.info(carrierName);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("UpdateBillOfLading");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfBillOfLadingId = MessageFactory.eINSTANCE.createArgs();
		argsOfBillOfLadingId.setName("BillOfLadingId");
		argsOfBillOfLadingId.setValue(billOfLadingId);
		
		Args argsOfPlateNo = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNo.setName("PlateNo");
		argsOfPlateNo.setValue(plateNo);
		
		Args argsOfDriverId = MessageFactory.eINSTANCE.createArgs();
		argsOfDriverId.setName("DriverId");
		argsOfDriverId.setValue(driverId);
		
		Args argsOfDriverName = MessageFactory.eINSTANCE.createArgs();
		argsOfDriverName.setName("DriverName");
		argsOfDriverName.setValue(driverName);
		
		Args argsOfCarrierId = MessageFactory.eINSTANCE.createArgs();
		argsOfCarrierId.setName("CarrierId");
		argsOfCarrierId.setValue(carrierId);
		
		Args argsOfCarrierName = MessageFactory.eINSTANCE.createArgs();
		argsOfCarrierName.setName("CarrierName");
		argsOfCarrierName.setValue(carrierName);
		
		message.getArgs().add(argsOfBillOfLadingId);
		message.getArgs().add(argsOfPlateNo);
		message.getArgs().add(argsOfDriverId);
		message.getArgs().add(argsOfDriverName);
		message.getArgs().add(argsOfCarrierId);
		message.getArgs().add(argsOfCarrierName);
		
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
	 * 提货单查询 
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/QueryBillOfLading")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryBillOfLading(String billOfLadingId, String userOrgId){
		
		
		logger.info("###QueryBillOfLading  parameter###");
		logger.info(billOfLadingId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryBillOfLading");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfBillOfLadingId = MessageFactory.eINSTANCE.createArgs();
		argsOfBillOfLadingId.setName("BillOfLadingId");
		argsOfBillOfLadingId.setValue(billOfLadingId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfBillOfLadingId);
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
	 * 提货单选择1
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectBillOfLadingByPlateNoAndIssDate")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectBillOfLadingByPlateNoAndIssDate(String plateNumber,String userOrgId,String pickUpDate){
		
		
		logger.info("###SelectBillOfLadingByPlateNoAndIssDate  parameter###");
		logger.info(plateNumber);
		logger.info("###SelectBillOfLadingByPlateNoAndIssDate  parameter###");
		logger.info(userOrgId);
		logger.info("###SelectBillOfLadingByPlateNoAndIssDate  parameter###");
		logger.info(pickUpDate);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectBillOfLadingByPlateNoAndIssDate");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfPickUpDate = MessageFactory.eINSTANCE.createArgs();
		argsOfPickUpDate.setName("PickUpDate");
		argsOfPickUpDate.setValue(pickUpDate);
		
		message.getArgs().add(argsOfPlateNumber);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfPickUpDate);
		
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
	 * 基于buyer身份查询提货单选择1
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectBillOfLadingByPlateNoAndIssDateOfBuyerId")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectBillOfLadingByPlateNoAndIssDateOfBuyerId(String plateNumber,  String userOrgId, String pickUpDate){
		
		
		logger.info("###SelectBillOfLadingByPlateNoAndIssDateOfBuyerId  parameter###");
		logger.info(plateNumber);
		logger.info("###SelectBillOfLadingByPlateNoAndIssDateOfBuyerId  parameter###");
		logger.info(userOrgId);
		logger.info("###SelectBillOfLadingByPlateNoAndIssDateOfBuyerId  parameter###");
		logger.info(pickUpDate);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectBillOfLadingByPlateNoAndIssDateOfBuyerId");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfPickUpDate = MessageFactory.eINSTANCE.createArgs();
		argsOfPickUpDate.setName("PickUpDate");
		argsOfPickUpDate.setValue(pickUpDate);
		
		message.getArgs().add(argsOfPlateNumber);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfPickUpDate);
		
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
	 * 提货单选择2
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectBillOfLadingBySalesCompanyAndBillId")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectBillOfLadingBySalesCompanyAndBillId(String salesCompanyId, String userOrgId, String billOfLadingId){
		
		
		logger.info("###SelectBillOfLadingByPlateNoAndIssDate  parameter###");
		logger.info(salesCompanyId);
		logger.info("###SelectBillOfLadingByPlateNoAndIssDate  parameter###");
		logger.info(userOrgId);
		logger.info("###SelectBillOfLadingByPlateNoAndIssDate  parameter###");
		logger.info(billOfLadingId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectBillOfLadingBySalesCompanyAndBillId");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfSalesCompanyName = MessageFactory.eINSTANCE.createArgs();
		argsOfSalesCompanyName.setName("SalesCompanyId");
		argsOfSalesCompanyName.setValue(salesCompanyId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfBillOfLadingId = MessageFactory.eINSTANCE.createArgs();
		argsOfBillOfLadingId.setName("BillOfLadingId");
		argsOfBillOfLadingId.setValue(billOfLadingId);
		
		message.getArgs().add(argsOfSalesCompanyName);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfBillOfLadingId);
		
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
	 * 提货单选择4
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectBillOfLadingBySalesPurchaseAndId")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectBillOfLadingBySalesPurchaseAndId(String salesCompanyId, String userOrgId, String billOfLadingId,
			String purchaseCompanyId){
		
		
		logger.info("###SelectBillOfLadingBySalesPurchaseAndId  parameter###");
		logger.info(salesCompanyId);
		logger.info("###SelectBillOfLadingBySalesPurchaseAndId  parameter###");
		logger.info(userOrgId);
		logger.info("###SelectBillOfLadingBySalesPurchaseAndId  parameter###");
		logger.info(billOfLadingId);
		logger.info("###SelectBillOfLadingBySalesPurchaseAndId  parameter###");
		logger.info(purchaseCompanyId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectBillOfLadingBySalesPurchaseAndId");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfSalesCompanyName = MessageFactory.eINSTANCE.createArgs();
		argsOfSalesCompanyName.setName("SalesCompanyId");
		argsOfSalesCompanyName.setValue(salesCompanyId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfBillOfLadingId = MessageFactory.eINSTANCE.createArgs();
		argsOfBillOfLadingId.setName("BillOfLadingId");
		argsOfBillOfLadingId.setValue(billOfLadingId);
		
		Args argsOfPurchaseCompanyName = MessageFactory.eINSTANCE.createArgs();
		argsOfPurchaseCompanyName.setName("PurchaseCompanyId");
		argsOfPurchaseCompanyName.setValue(purchaseCompanyId);
		
		message.getArgs().add(argsOfSalesCompanyName);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfBillOfLadingId);
		message.getArgs().add(argsOfPurchaseCompanyName);
		
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
	 * 车辆选择
	 *
	 * @param  billOfLading
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/SelectCar")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String selectCar(String plateNumber, String purchaseOrderId, String userOrgId){
		
		
		logger.info("###SelectCar  parameter###");
		logger.info(plateNumber);
		logger.info("###SelectCar  parameter###");
		logger.info(purchaseOrderId);
		logger.info("###SelectCar  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("SelectCar");
		message.setType("0");
		message.setDataType("BillOfLading");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfPurchaseOrderId = MessageFactory.eINSTANCE.createArgs();
		argsOfPurchaseOrderId.setName("PurchaseOrderId");
		argsOfPurchaseOrderId.setValue(purchaseOrderId);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfPlateNumber);
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
}
