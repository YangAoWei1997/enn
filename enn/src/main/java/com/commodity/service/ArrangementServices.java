package com.commodity.service;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commodity.util.JSONTransferUtil;
import com.commodity.util.MessageUtil;
import com.commodity.util.RestUtil;
import com.enn.commodity.synergistic.entity.Arrangement;
import com.mchain.apollo.common.entity.base.Arg;
import com.mchain.apollo.common.entity.base.Data;
import com.mchain.apollo.common.entity.base.FileInfo;
import com.mchain.apollo.common.entity.base.FormatType;
import com.mchain.apollo.common.entity.base.Message;
import com.mchain.apollo.common.utils.BeanUtils;
import com.mchain.apollo.common.utils.FormDataUtils;
import com.mchain.apollo.common.utils.JacksonUtil;
import com.mchain.apollo.common.utils.ServiceUtils;

import net.sf.json.JSONObject;

//import com.mchain.model.Message.Args;
//import com.mchain.model.Message.Data;
//import com.mchain.model.Message.Message;
//import com.mchain.model.Message.MessageFactory;

@Service
public class ArrangementServices {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	@Autowired
    private RestTemplate restTemplate;
	
	private final static String API = "/api/complain";

	// get logger
	private static Logger logger = Logger.getLogger(ArrangementServices.class);
	
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

		return "Arrangement APIs OK";
	}
	
	/**
	 * 公共删除接口
	 * 
	 * @param arrangementInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/Delete")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String delete(@FormParam("Id") String id){
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Headers",
//				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//		response.addHeader("X-Powered-By", "3.2.1");
//		
//		logger.info("###Delete  parameter###");
//		logger.info(id);
//		
//		Message message = MessageFactory.eINSTANCE.createMessage();
//		message.setId(UUID.randomUUID().toString());
//		message.setChannel("WEB");
//		message.setTranCode("Delete");
//		message.setType("0");
//		message.setDataType("");
//		message.setTranDate(dateformat.format(new Date()));
//		
//		Args argsOfId = MessageFactory.eINSTANCE.createArgs();
//		argsOfId.setName("Id");
//		argsOfId.setValue(id);
//		
//		message.getArgs().add(argsOfId);
//		
//		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//		
//		String respMsg = null;
//		
//		if (method.equals("Service")) {
//			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//		}else {
//			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//		}
		
		return null;
	}
	
	/**
	 * 创建长协信息接口
	 * 
	 * @param arrangementInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/CreateArrangement")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String createArrangement(String arrangement){
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Headers",
//				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//		response.addHeader("X-Powered-By", "3.2.1");
		
//		logger.info("###CreateArrangement  parameter###");
//		logger.info(arrangement);
//		
//		Message message = MessageFactory.eINSTANCE.createMessage();
//		message.setId(UUID.randomUUID().toString());
//		message.setChannel("WEB");
//		message.setTranCode("CreateArrangement");
//		message.setType("0");
//		message.setDataType("Arrangement");
//		message.setTranDate(dateformat.format(new Date()));
//		
//		Data dataOfArrangement = MessageFactory.eINSTANCE.createData();
//		dataOfArrangement.setContent(arrangement);
//		dataOfArrangement.setDataType("Arrangement");
//		
//		message.getData().add(dataOfArrangement);
//		
//		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//		
//		String respMsg = null;
//		
//		if (method.equals("Service")) {
//			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//		}else {
//			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//		}
//		
//		
//		return respMsg;
		
		
		
		
		
		logger.info("CreateArrangement,请求Service报文:"+arrangement);
		Message<List<FileSystemResource>> message = null;
		Message<Arrangement> reqMsg = new Message<Arrangement>();
		Arrangement arr = new Arrangement();
		try {
			message = JacksonUtil.getInstance().json2pojo(arrangement, Message.class);			
	    	List<Data> datas = message.getData();
	    	List<Arg> args =  message.getArgs();
			String pathCreate = null;	
			String sdkUrl = "http://localhost:8443";
			pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//			List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
			arr = (Arrangement) ServiceUtils.getInstance(datas,Arrangement.class);
			for (Data data : datas) {
				//COMPLAIN是与web层约定的值
				if (data.getDataType().equals("Arrangement")) {
					data.setContent(arr);
					data.setDataType("Arrangement");
					data.setFormatType(FormatType.STRING);
				}
			}
			// 判断是否带有附件
			
				LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
				form = FormDataUtils.getMessage(message);
				logger.info("AddComplain不带附件新增，请求SDK报文:"+form.toString());
				String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
				logger.info(result);
				return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reqMsg = new Message<Arrangement>("500", String.format("POST %s", API), e.getMessage());
			BeanUtils.merge(reqMsg, message);
			String result=JSONObject.fromObject(reqMsg).toString();
			logger.info(result);
			return result;
		}
		
		
		
		
		
		
		
		
	}
	
	/**
	 * 更新长协信息接口
	 * 
	 * @param arrangementInfo
	 * 
	 * @return 
	 * @throws
	 * */
	@POST
	@Path("/UpdateArrangement")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String updateArrangement(String arrangement) {
		
		
//		logger.info("###UpdateArrangement  parameter###");
//		logger.info(arrangement);
//		
//		Message message = MessageFactory.eINSTANCE.createMessage();
//		message.setId(UUID.randomUUID().toString());
//		message.setChannel("WEB");
//		message.setTranCode("UpdateArrangement");
//		message.setType("0");
//		message.setDataType("Arrangement");
//		message.setTranDate(dateformat.format(new Date()));
//		
//		Data dataOfArrangement = MessageFactory.eINSTANCE.createData();
//		dataOfArrangement.setContent(arrangement);
//		dataOfArrangement.setDataType("Arrangement");
//		
//		message.getData().add(dataOfArrangement);
//		
//		Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//		
//		String respMsg = null;
//		
//		if (method.equals("Service")) {
//			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//		}else {
//			respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//		}
		
		return null;
	}
	
	/**
	 * 长协信息获得接口
	 * 
	 * @param arrangementId
	 * 
	 * @return ipInfo
	 * @throws
	 * */
	@POST
	@Path("/QueryArrangement")
	@Produces("text/plain;charset=utf-8")
	@Consumes("application/x-www-form-urlencoded")
	public String queryArrangement(String orgId, String userOrgId, String arrangementId) {
		
		
//		logger.info("###QueryArrangement  parameter###");
//		logger.info(orgId);
//		logger.info("###QueryArrangement  parameter###");
//		logger.info(userOrgId);
//		logger.info("###QueryArrangement  parameter###");
//		logger.info(arrangementId);
//		
//		Message message = MessageFactory.eINSTANCE.createMessage();
//		message.setId(UUID.randomUUID().toString());
//		message.setChannel("WEB");
//		message.setTranCode("QueryArrangement");
//		message.setType("0");
//		message.setDataType("Arrangement");
//		message.setTranDate(dateformat.format(new Date()));
//		
//		Args argsOfArrangementId = MessageFactory.eINSTANCE.createArgs();
//		argsOfArrangementId.setName("ArrangementId");
//		argsOfArrangementId.setValue(arrangementId);
//		
//		Args argsOfOrgId = MessageFactory.eINSTANCE.createArgs();
//		argsOfOrgId.setName("OrgId");
//		argsOfOrgId.setValue(orgId);
//		
//		Args argsOfUserOrgId = MessageFactory.eINSTANCE.createArgs();
//		argsOfUserOrgId.setName("UserOrgId");
//		argsOfUserOrgId.setValue(userOrgId);
//
//		message.getArgs().add(argsOfArrangementId);
//		message.getArgs().add(argsOfUserOrgId);
//		message.getArgs().add(argsOfOrgId);
//		
//		Message newMessage = MessageUtil.completeMessage(message, "Query");
//		
//		String respMsg = null;
//		
//		if (method.equals("Service")) {
//			respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//		}else {
//			respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//		}
		
		return null;
	}
}
