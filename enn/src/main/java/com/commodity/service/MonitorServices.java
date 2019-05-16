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
public class MonitorServices {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	// get logger
	private static Logger logger = Logger.getLogger(MonitorServices.class);
	
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

		return "Monitor APIs OK";
	}
	
	/**
	 * 创建预警信息接口
	 * 
	 * @param ipInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/CreateMonitor")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createMonitor(@FormParam("PlateNumber") String plateNumber, @FormParam("Source") String source, @FormParam("Monitor") String monitor) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###CreateMonitor  parameter###");
		logger.info(plateNumber);
		logger.info("###CreateMonitor  parameter###");
		logger.info(source);
		logger.info("###CreateMonitor  parameter###");
		logger.info(monitor);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("CreateMonitor");
		message.setType("0");
		message.setDataType("Monitor");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfPlateNumber = MessageFactory.eINSTANCE.createArgs();
		argsOfPlateNumber.setName("PlateNumber");
		argsOfPlateNumber.setValue(plateNumber);
		
		Args argsOfSource = MessageFactory.eINSTANCE.createArgs();
		argsOfSource.setName("Source");
		argsOfSource.setValue(source);
		
		message.getArgs().add(argsOfSource);
		message.getArgs().add(argsOfPlateNumber);
		
		Data dataOfMonitor = MessageFactory.eINSTANCE.createData();
		dataOfMonitor.setContent(monitor);
		dataOfMonitor.setDataType("Monitor");
		
		message.getData().add(dataOfMonitor);
		
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
	 * 预警信息结果获得接口
	 * 
	 * @param userid userOrgId
	 * 
	 * @return ipInfo
	 * @throws
	 * */
	@POST
	@Path("/QueryMonitor")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryMonitor(@FormParam("UserOrgId") String userOrgId, @FormParam("Source") String source){
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("###QueryMonitor  parameter###");
		logger.info(userOrgId);
		logger.info("###QueryMonitor  parameter###");
		logger.info(source);
		
		Message message = MessageFactory.eINSTANCE.createMessage();
		message.setId(UUID.randomUUID().toString());
		message.setChannel("WEB");
		message.setTranCode("QueryMonitor");
		message.setType("0");
		message.setDataType("Monitor");
		message.setTranDate(dateformat.format(new Date()));
		
		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
		argsOfUserOrgId.setName("UserOrgId");
		argsOfUserOrgId.setValue(userOrgId);
		
		Args argsOfSource = MessageFactory.eINSTANCE.createArgs();
		argsOfSource.setName("Source");
		argsOfSource.setValue(source);
		
		message.getArgs().add(argsOfUserOrgId);
		message.getArgs().add(argsOfSource);
		
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
