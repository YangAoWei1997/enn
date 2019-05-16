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
import com.mchain.model.Message.MessageFactory;;

@Service
public class PackingListServices {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	// get logger
	private static Logger logger = Logger.getLogger(PackingListServices.class);
	
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

		return "PackingList APIs OK";
	}
	
	/**
	 * 创建装箱单 
	 *
	 * @param  
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CreatePackingList")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createPackingList(@FormParam("PackingList") String packingList){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreatePackingList  parameter###");
		logger.info(packingList);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreatePackingList");
		message.setType("0");
		message.setDataType("PackingList");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfPackingList = MessageFactory.eINSTANCE.createData();
		dataOfPackingList.setContent(packingList);
		dataOfPackingList.setDataType("PackingList");
		
		message.getData().add(dataOfPackingList);
		
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
	 * 更新装箱单 
	 *
	 * @param  packingList
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/UpdatePackingList")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String updatePackingList(@FormParam("PackingList") String packingList){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###UpdatePackingList  parameter###");
		logger.info(packingList);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("UpdatePackingList");
		message.setType("0");
		message.setDataType("PackingList");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfPackingList = MessageFactory.eINSTANCE.createData();
		dataOfPackingList.setContent(packingList);
		dataOfPackingList.setDataType("PackingList");
		
		message.getData().add(dataOfPackingList);
		
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
	 * 装箱单查询 
	 *
	 * @param  packingListId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/QueryPackingList")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryBillOfLading(@FormParam("PackingListId") String packingListId, @FormParam("PlateNumber") String plateNumber, 
			@FormParam("UserOrgId") String userOrgId, @FormParam("LeaveDate") String leaveDate){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QueryPackingList  parameter###");
		logger.info(packingListId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryPackingList");
		message.setType("0");
		message.setDataType("PackingList");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPackingListId = MessageFactory.eINSTANCE.createArgs();
		argsOfPackingListId.setName("PackingListId");
		argsOfPackingListId.setValue(packingListId);
		
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfLeaveDate = MessageFactory.eINSTANCE.createArgs();
		argsOfLeaveDate.setName("LeaveDate");
		argsOfLeaveDate.setValue(leaveDate);
		
		message.getArgs().add(argsOfPackingListId);
		message.getArgs().add(argsOfPlateNumber);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfLeaveDate);
		
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
	 * 创建质检单  
	 *
	 * @param  
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CreateQualityInfo")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createQualityInfo(@FormParam("QualityInfo") String qualityInfo){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateQualityInfo  parameter###");
		logger.info(qualityInfo);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateQualityInfo");
		message.setType("0");
		message.setDataType("QualityInfo");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfQualityInfo = MessageFactory.eINSTANCE.createData();
		dataOfQualityInfo.setContent(qualityInfo);
		dataOfQualityInfo.setDataType("QualityInfo");
		
		message.getData().add(dataOfQualityInfo);
		
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
	 * 质检结果比较
	 *
	 * @param  plateNumber, arriveDate, purchasingOrderNo
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CompareQualityInfo")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String compareQualityInfo(@FormParam("PlateNumber") String plateNumber, @FormParam("ArriveDate") String arriveDate, 
			@FormParam("PurchasingOrderNo") String purchasingOrderNo, @FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(plateNumber);
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(arriveDate);
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(purchasingOrderNo);
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(userOrgId);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CompareQualityInfo");
		message.setType("0");
		message.setDataType("QualityInfo");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfArriveDate = MessageFactory.eINSTANCE.createArgs();
		argsOfArriveDate.setName("ArriveDate");
		argsOfArriveDate.setValue(arriveDate);
		
		Args argsOfPurchaseOrderNo = MessageFactory.eINSTANCE.createArgs();
		argsOfPurchaseOrderNo.setName("PurchaseOrderId");
		argsOfPurchaseOrderNo.setValue(purchasingOrderNo);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfPlateNumber);
		message.getArgs().add(argsOfArriveDate);
		message.getArgs().add(argsOfPurchaseOrderNo);
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
	 * 出入场信息比较
	 *
	 * @param  purchaseOrderId, userOrgId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CompareInOutFactory")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String compareInOutFactory(@FormParam("ArriveDate") String arriveDate, @FormParam("LeaveDate") String leaveDate, @FormParam("PlateNumber") String plateNumber,
			@FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(plateNumber);
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(arriveDate);
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(leaveDate);
		logger.info("###CompareQualityInfo  parameter###");
		logger.info(userOrgId);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CompareInOutFactory");
		message.setType("0");
		message.setDataType("PackingList");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfArriveDate = MessageFactory.eINSTANCE.createArgs();
		argsOfArriveDate.setName("ArriveDate");
		argsOfArriveDate.setValue(arriveDate);
		
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfLeaveDate = MessageFactory.eINSTANCE.createArgs();
		argsOfLeaveDate.setName("LeaveDate");
		argsOfLeaveDate.setValue(leaveDate);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		message.getArgs().add(argsOfPlateNumber);
		message.getArgs().add(argsOfArriveDate);
		message.getArgs().add(argsOfLeaveDate);
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
	 * 创建到货单
	 *
	 * @param  
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/CreateArrivalList")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createArrivalList(@FormParam("ArrivalList") String arrivalList){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateArrivalList  parameter###");
		logger.info(arrivalList);

		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateArrivalList");
		message.setType("0");
		message.setDataType("ArrivalList");
		message.setTranDate(dateformat.format(new Date()));
	
		Data dataOfArrivalList = MessageFactory.eINSTANCE.createData();
		dataOfArrivalList.setContent(arrivalList);
		dataOfArrivalList.setDataType("ArrivalList");
		
		message.getData().add(dataOfArrivalList);
		
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
	 * 到货单查询
	 *
	 * @param  arrivalListId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/QueryArrivalList")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryArrivalList(@FormParam("ArrivalListId") String arrivalListId, @FormParam("PlateNumber") String plateNumber, 
			@FormParam("UserOrgId") String userOrgId, @FormParam("ReachDate") String reachDate){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QueryArrivalList  parameter###");
		logger.info(arrivalListId);
		logger.info("###PlateNumber  parameter###");
		logger.info(plateNumber);
		logger.info("###UserOrgId  parameter###");
		logger.info(userOrgId);
		logger.info("###LeaveDate  parameter###");
		logger.info(reachDate);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryArrivalList");
		message.setType("0");
		message.setDataType("ArrivalList");
		message.setTranDate(dateformat.format(new Date()));
	
		Args argsOfArrivalListId = MessageFactory.eINSTANCE.createArgs();
		argsOfArrivalListId.setName("ArrivalListId");
		argsOfArrivalListId.setValue(arrivalListId);
		
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfReachDate = MessageFactory.eINSTANCE.createArgs();
		argsOfReachDate.setName("ReachDate");
		argsOfReachDate.setValue(reachDate);
		
		message.getArgs().add(argsOfArrivalListId);
		message.getArgs().add(argsOfPlateNumber);
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfReachDate);
		
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
	 * 在途信息查询
	 *
	 * @param  arrivalListId
	 * @return
	 * @throws IOException
	 * */
	@POST
	@Path("/OnPassageInfo")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String onPassageInfo(@FormParam("UserOrgId") String userOrgId){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###UserOrgId  parameter###");
		logger.info(userOrgId);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("OnPassageInfo");
		message.setType("0");
		message.setDataType("ArrivalList");
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
}
