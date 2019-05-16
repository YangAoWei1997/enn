package com.commodity.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.commodity.util.JSONTransferUtil;
import com.commodity.util.MessageUtil;
import com.commodity.util.RestUtil;
import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.Group;
import com.enn.commodity.synergistic.entity.ShareData;
import com.enn.commodity.synergistic.entity.ShareDataTemplate;
import com.mchain.apollo.common.entity.base.Arg;
import com.mchain.apollo.common.entity.base.Data;
import com.mchain.apollo.common.entity.base.FormatType;
import com.mchain.apollo.common.entity.base.Message;
import com.mchain.apollo.common.utils.BeanUtils;
import com.mchain.apollo.common.utils.FormDataUtils;
import com.mchain.apollo.common.utils.JacksonUtil;
import com.mchain.apollo.common.utils.ServiceUtils;


import net.sf.json.JSONObject;

@Service
public class ShareDataServices {

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	@Autowired
    private RestTemplate restTemplate;
	
	// get logger
		private static Logger logger = Logger.getLogger(ShareDataServices.class);
		
		private final static String API = "/api/complain";
		
		private final static String path="http://192.168.138.201:8443";
		
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

			return "ShareData APIs OK";
		}
		
		/**
		 * 创建shareDataTemplate
		 * 
		 * @param shareData
		 * @return 
		 * @throws IOException
		 * */
		@POST
		@Path("/CreateShareDataTemplate")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String createShareDataTemplate(@FormParam("ShareDataTemplateJson") String shareDataTemplateJson){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###CreateShareDataTemplate  parameter###");
//			logger.info(shareDataTemplateJson);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("CreateShareDataTemplate");
//			message.setType("0");
//			message.setDataType("ShareData");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Data dataOfShareDataTemplate = MessageFactory.eINSTANCE.createData();
//			dataOfShareDataTemplate.setContent(shareDataTemplateJson);
//			dataOfShareDataTemplate.setDataType("ShareData");
//			
//			message.getData().add(dataOfShareDataTemplate);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("createShareDataTemplate,请求Service报文:"+shareDataTemplateJson);
			Message<List<FileSystemResource>> message = null;
			Message<ShareDataTemplate> reqMsg = new Message<ShareDataTemplate>();
			ShareDataTemplate shareDataTemplate=new ShareDataTemplate();
			try {
				message = JacksonUtil.getInstance().json2pojo(shareDataTemplateJson, Message.class);			
		    	List<Data> datas = message.getData();
		    	List<Arg> args =  message.getArgs();
				String pathCreate = null;	
				String sdkUrl = "http://192.168.138.201:8443";
				pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//				List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
				shareDataTemplate = (ShareDataTemplate) ServiceUtils.getInstance(datas,ShareDataTemplate.class);
				for (Data data : datas) {
					//COMPLAIN是与web层约定的值
					if (data.getDataType().equals("ShareDataTemplate")) {
						data.setContent(JSONObject.fromObject(shareDataTemplate).toString());
						data.setDataType("java.lang.String");
						data.setFormatType(FormatType.STRING);
					}
				}
				// 判断是否带有附件
				
					LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
					form = FormDataUtils.getMessage(message);
					logger.info("createShareDataTemplate不带附件新增，请求SDK报文:"+form.toString());
					String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
					logger.info(result);
					return result;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reqMsg = new Message<ShareDataTemplate>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				logger.info(result);
				return result;
			}
		}
		
		/**
		 * 删除shareDataTemplate
		 * 
		 * @param shareData
		 * @return 
		 * @throws IOException
		 * */
//		@POST
//		@Path("/DeleteShareDataTemplate")
//		@Produces("text/plain;charset=utf-8")
//		@Consumes("application/x-www-form-urlencoded")
//		public String deleteShareDataTemplate(@FormParam("ShareDataTemplateId") String shareDataTemplateId) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###DeleteShareDataTemplate  parameter###");
//			logger.info(shareDataTemplateId);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("DeleteShareDataTemplate");
//			message.setType("0");
//			message.setDataType("ShareDataTemplate");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Args argsOfShareDataTemplate = MessageFactory.eINSTANCE.createArgs();
//			argsOfShareDataTemplate.setName("ShareDataTemplateId");
//			argsOfShareDataTemplate.setValue(shareDataTemplateId);
//			
//			message.getArgs().add(argsOfShareDataTemplate);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
//		}
		
		/**
		 * 更新shareDataTemplate
		 * 
		 * @param shareData
		 * @return 
		 * @throws IOException
		 * */
//		@POST
//		@Path("/UpdateShareDataTemplate")
//		@Produces("text/plain;charset=utf-8")
//		@Consumes("application/x-www-form-urlencoded")
//		public String updateShareDataTemplate(@FormParam("ShareDataTemplateJson") String shareDataTemplateJson){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###UpdateShareDataTemplate  parameter###");
//			logger.info(shareDataTemplateJson);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("UpdateShareDataTemplate");
//			message.setType("0");
//			message.setDataType("ShareDataTemplate");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Data dataOfShareDataTemplate = MessageFactory.eINSTANCE.createData();
//			dataOfShareDataTemplate.setContent(shareDataTemplateJson);
//			dataOfShareDataTemplate.setDataType("ShareDataTemplate");
//			
//			message.getData().add(dataOfShareDataTemplate);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
//		}
		
		/**
		 * 创建shareData
		 * 
		 * @param shareData
		 * @return 
		 * @throws IOException
		 * */
		@POST
		@Path("/CreateShareData")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String createShareData(@FormParam("ShareDataJson") String shareDataJson) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###CreateShareData  parameter###");
//			logger.info(shareDataJson);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("CreateShareData");
//			message.setType("0");
//			message.setDataType("ShareData");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Data dataOfShareData = MessageFactory.eINSTANCE.createData();
//			dataOfShareData.setContent(shareDataJson);
//			dataOfShareData.setDataType("ShareData");
//			
//			message.getData().add(dataOfShareData);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			
			logger.info("createGroup,请求Service报文:"+shareDataJson);
			Message<List<FileSystemResource>> message = null;
			Message<ShareData> reqMsg = new Message<ShareData>();
			ShareData shareData=new ShareData();
			try {
				message = JacksonUtil.getInstance().json2pojo(shareDataJson, Message.class);			
		    	List<Data> datas = message.getData();
		    	List<Arg> args =  message.getArgs();
				String pathCreate = null;	
				String sdkUrl = "http://localhost:8443";
				pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//				List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
				shareData = (ShareData) ServiceUtils.getInstance(datas,ShareData.class);
				for (Data data : datas) {
					//COMPLAIN是与web层约定的值
					if (data.getDataType().equals("ShareData")) {
						data.setContent(JSONObject.fromObject(shareData).toString());
						data.setDataType("java.lang.String");
						data.setFormatType(FormatType.STRING);
					}
				}
				// 判断是否带有附件
				
					LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
					form = FormDataUtils.getMessage(message);
					logger.info("createGroup不带附件新增，请求SDK报文:"+form.toString());
					String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
					logger.info(result);
					return result;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reqMsg = new Message<ShareData>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				logger.info(result);
				return result;
			}
		}
		
		/**
		 * 删除shareData
		 * 
		 * @param shareData
		 * @return 
		 * @throws IOException
		 * */
//		@POST
//		@Path("/DeleteShareData")
//		@Produces("text/plain;charset=utf-8")
//		@Consumes("application/x-www-form-urlencoded")
//		public String deleteShareData(@FormParam("ShareDataId") String shareDataId) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###DeleteShareData  parameter###");
//			logger.info(shareDataId);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("DeleteShareData");
//			message.setType("0");
//			message.setDataType("ShareData");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Args argsOfShareDataId = MessageFactory.eINSTANCE.createArgs();
//			argsOfShareDataId.setName("ShareDataId");
//			argsOfShareDataId.setValue(shareDataId);
//			
//			message.getArgs().add(argsOfShareDataId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
//		}
		
		/**
		 * 更新shareData
		 * 
		 * @param shareData
		 * @return 
		 * @throws IOException
		 * */
		@POST
		@Path("/UpdateShareData")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String updateShareData(@FormParam("ShareDataJson") String shareDataJson) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###UpdateShareData  parameter###");
//			logger.info(shareDataJson);
			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("UpdateShareData");
//			message.setType("0");
//			message.setDataType("ShareData");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Data dataOfShareData = MessageFactory.eINSTANCE.createData();
//			dataOfShareData.setContent(shareDataJson);
//			dataOfShareData.setDataType("ShareData");
//			
//			message.getData().add(dataOfShareData);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("createGroup,请求Service报文:"+shareDataJson);
			Message<List<FileSystemResource>> message = null;
			Message<ShareData> reqMsg = new Message<ShareData>();
			ShareData shareData=new ShareData();
			try {
				message = JacksonUtil.getInstance().json2pojo(shareDataJson, Message.class);			
		    	List<Data> datas = message.getData();
		    	List<Arg> args =  message.getArgs();
				String pathCreate = null;	
				String sdkUrl = "http://localhost:8443";
				pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//				List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
				shareData = (ShareData) ServiceUtils.getInstance(datas,ShareData.class);
				for (Data data : datas) {
					//COMPLAIN是与web层约定的值
					if (data.getDataType().equals("ShareData")) {
						data.setContent(JSONObject.fromObject(shareData).toString());
						data.setDataType("java.lang.String");
						data.setFormatType(FormatType.STRING);
					}
				}
				// 判断是否带有附件
				
					LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
					form = FormDataUtils.getMessage(message);
					logger.info("createGroup不带附件新增，请求SDK报文:"+form.toString());
					String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
					logger.info(result);
					return result;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reqMsg = new Message<ShareData>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				logger.info(result);
				return result;
			}
		}
		
		/**
		 * 新增group
		 * 
		 * @param group
		 * @return 
		 * @throws IOException
		 * */
		@POST
		@Path("/CreateGroup")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String createGroup(@FormParam("GroupJson") String groupJson) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###CreateGroup  parameter###");
//			logger.info(groupJson);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("CreateGroup");
//			message.setType("0");
//			message.setDataType("Group");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Data dataOfGroup = MessageFactory.eINSTANCE.createData();
//			dataOfGroup.setContent(groupJson);
//			dataOfGroup.setDataType("Group");
//			
//			message.getData().add(dataOfGroup);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			
			logger.info("createGroup,请求Service报文:"+groupJson);
			Message<List<FileSystemResource>> message = null;
			Message<Group> reqMsg = new Message<Group>();
			Group group = new Group();
			try {
				message = JacksonUtil.getInstance().json2pojo(groupJson, Message.class);			
		    	List<Data> datas = message.getData();
		    	List<Arg> args =  message.getArgs();
				String pathCreate = null;	
				String sdkUrl = "http://192.168.138.201:8443";
				pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//				List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
				group = (Group) ServiceUtils.getInstance(datas,Group.class);
				for (Data data : datas) {
					//COMPLAIN是与web层约定的值
					if (data.getDataType().equals("Group")) {
//						data.setContent(group);
						data.setContent(JSONObject.fromObject(group).toString());
						data.setDataType("java.lang.String");
						data.setFormatType(FormatType.STRING);
					}
				}
				// 判断是否带有附件
				
					LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
					form = FormDataUtils.getMessage(message);
					logger.info("createGroup不带附件新增，请求SDK报文:"+form.toString());
					String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
					logger.info(result);
					return result;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reqMsg = new Message<Group>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				logger.info(result);
				return result;
			}
		}
		
		/**
		 * 删除group
		 * 
		 * @param group
		 * @return 
		 * @throws IOException
		 * */
//		@POST
//		@Path("/DeleteGroup")
//		@Produces("text/plain;charset=utf-8")
//		@Consumes("application/x-www-form-urlencoded")
//		public String deleteGroup(@FormParam("GroupId") String groupId) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###DeleteGroup  parameter###");
//			logger.info(groupId);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("DeleteGroup");
//			message.setType("0");
//			message.setDataType("Group");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Args argsOfGroupId = MessageFactory.eINSTANCE.createArgs();
//			argsOfGroupId.setName("GroupId");
//			argsOfGroupId.setValue(groupId);
//			
//			message.getArgs().add(argsOfGroupId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
//		}
		
		/**
		 * 更新group
		 * 
		 * @param group
		 * @return 
		 * @throws IOException
		 * */
		@POST
		@Path("/UpdateGroup")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String updateGroup(@FormParam("GroupJson") String groupJson) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###UpdateGroup  parameter###");
//			logger.info(groupJson);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("UpdateGroup");
//			message.setType("0");
//			message.setDataType("Group");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Data dataOfGroup = MessageFactory.eINSTANCE.createData();
//			dataOfGroup.setContent(groupJson);
//			dataOfGroup.setDataType("Group");
//			
//			message.getData().add(dataOfGroup);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("createGroup,请求Service报文:"+groupJson);
			Message<List<FileSystemResource>> message = null;
			Message<Group> reqMsg = new Message<Group>();
			Group group = new Group();
			try {
				message = JacksonUtil.getInstance().json2pojo(groupJson, Message.class);			
		    	List<Data> datas = message.getData();
		    	List<Arg> args =  message.getArgs();
				String pathCreate = null;	
				String sdkUrl = "http://localhost:8443";
				pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//				List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
				group = (Group) ServiceUtils.getInstance(datas,Group.class);
				for (Data data : datas) {
					//COMPLAIN是与web层约定的值
					if (data.getDataType().equals("Group")) {
//						data.setContent(group);
						data.setContent(JSONObject.fromObject(group).toString());
						data.setDataType("java.lang.String");
						data.setFormatType(FormatType.STRING);
					}
				}
				// 判断是否带有附件
				
					LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
					form = FormDataUtils.getMessage(message);
					logger.info("createGroup不带附件新增，请求SDK报文:"+form.toString());
					String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
					logger.info(result);
					return result;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reqMsg = new Message<Group>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				logger.info(result);
				return result;
			}
		}
		
		/**
		 * 新增一个org到group中
		 * 
		 * @param group
		 * @return 
		 * @throws IOException
		 * */
		@POST
		@Path("/AddOrgUserToGroup")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String addOrgUserToGroup(String groupJson) {
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###AddOrgUserToGroup  parameter###");
//			logger.info(groupId);
//			logger.info("###AddOrgUserToGroup  parameter###");
//			logger.info(orgId);
//			
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("AddOrgUserToGroup");
//			message.setType("0");
//			message.setDataType("Group");
//			message.setTranDate(dateformat.format(new Date()));
//			
//			Args argsOfGroupId = MessageFactory.eINSTANCE.createArgs();
//			argsOfGroupId.setName("GroupId");
//			argsOfGroupId.setValue(groupId);
//			
//			Args argsOfOrgId = MessageFactory.eINSTANCE.createArgs();
//			argsOfOrgId.setName("OrgId");
//			argsOfOrgId.setValue(orgId);
//			
//			message.getArgs().add(argsOfGroupId);
//			message.getArgs().add(argsOfOrgId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Invoke");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.invoke(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(invokeUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("createGroup,请求Service报文:"+groupJson);
			Message<List<FileSystemResource>> message = null;
			Message<Group> reqMsg = new Message<Group>();
			Group group = new Group();
			try {
				message = JacksonUtil.getInstance().json2pojo(groupJson, Message.class);			
		    	List<Data> datas = message.getData();
		    	List<Arg> args =  message.getArgs();
				String pathCreate = null;	
				String sdkUrl = "http://localhost:8443";
				pathCreate = sdkUrl+"/api/sdk/ledger/invoke";
//				List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
				group = (Group) ServiceUtils.getInstance(datas,Group.class);
//				for (Data data : datas) {
//					//COMPLAIN是与web层约定的值
//					if (data.getDataType().equals("Group")) {
////						data.setContent(group);
//						data.setContent(JSONObject.fromObject(group).toString());
//						data.setDataType("java.lang.String");
//						data.setFormatType(FormatType.STRING);
//					}
//				}
				// 判断是否带有附件
				
					LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
					form = FormDataUtils.getMessage(message);
					logger.info("createGroup不带附件新增，请求SDK报文:"+form.toString());
					String result=JSONObject.fromObject(restTemplate.postForObject(pathCreate, form, Message.class)).toString();
					logger.info(result);
					return result;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reqMsg = new Message<Group>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				logger.info(result);
				return result;
			}
		}
		
		//=====================================Query=========================================
		
		/**
		 * 查询ShareDataTemplate 
		 *
		 * @param  shareDataTemplate
		 * @return
		 * @throws IOException
		 * */
		@POST
		@Path("/QueryShareDataTemplate")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String queryShareDataTemplate(String shareDataTemplateJson){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###QueryShareDataTemplate  parameter###");
//			logger.info(shareDataTemplateId);
//
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("QueryShareDataTemplate");
//			message.setType("0");
//			message.setDataType("ShareDataTemplate");
//			message.setTranDate(dateformat.format(new Date()));
//		
//			Args argsOfShareDataTemplateId = MessageFactory.eINSTANCE.createArgs();
//			argsOfShareDataTemplateId.setName("ShareDataTemplateId");
//			argsOfShareDataTemplateId.setValue(shareDataTemplateId);
//			
//			message.getArgs().add(argsOfShareDataTemplateId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Query");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.query(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("queryShareDataTemplate根据id查询,请求Service报文:"+shareDataTemplateJson.toString());
			Message<?> message = null;
			ShareDataTemplate shareDataTemplate = new ShareDataTemplate();
			try {
				message = JacksonUtil.getInstance().json2pojo(shareDataTemplateJson, Message.class);
				//message = GetParamUtils.getParam(resMsg);
				List<Data> datas = message.getData();
				String pathGet = null;
				String sdkUrl = "http://192.168.138.201:8443";
				pathGet = sdkUrl+"/api/sdk/ledger/query";
//				if (datas != null && !"".equals(datas)) {
//					complain = (Complain) ServiceUtils.getInstance(datas);
//					for (Data data : datas) {
//						//COMPLAIN是与web层约定的值
//						if (data.getDataType().equals("Complain")) {
//							HashMap<String,String> map = new HashMap<>();
//							map.put("id", complain.getId());
//							data.setContent(JacksonUtil.getInstance().toJSON(map));
//							data.setDataType("java.lang.String");
//							data.setFormatType(FormatType.STRING);
//						}
//					}
//				}		
				logger.info("queryShareDataTemplate根据id查询,请求SDK报文:"+JacksonUtil.getInstance().toJSON(message));
				String result=JSONObject.fromObject(restTemplate.postForObject(pathGet, message, Message.class)).toString();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				Message<ShareDataTemplate> reqMsg = new Message<ShareDataTemplate>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				return result;
			}
			
		}
		
		/**
		 * 查询ShareData
		 *
		 * @param  shareDataTemplate
		 * @return
		 * @throws IOException
		 * */
		@POST
		@Path("/QueryShareDataById")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String queryShareDataById(String shareDataJson){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###QueryShareDataById  parameter###");
//			logger.info(shareDataId);
//
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("QueryShareDataById");
//			message.setType("0");
//			message.setDataType("ShareData");
//			message.setTranDate(dateformat.format(new Date()));
//		
//			Args argsOfShareDataId = MessageFactory.eINSTANCE.createArgs();
//			argsOfShareDataId.setName("ShareDataId");
//			argsOfShareDataId.setValue(shareDataId);
//			
//			message.getArgs().add(argsOfShareDataId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Query");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.query(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("queryShareData根据id查询,请求Service报文:"+shareDataJson.toString());
			Message<?> message = null;
			ShareData shareData = new ShareData();
			try {
				message = JacksonUtil.getInstance().json2pojo(shareDataJson, Message.class);
				//message = GetParamUtils.getParam(resMsg);
				List<Data> datas = message.getData();
				String pathGet = null;
				String sdkUrl = "http://192.168.138.201:8443";
				pathGet = sdkUrl+"/api/sdk/ledger/query";
//				if (datas != null && !"".equals(datas)) {
//					complain = (Complain) ServiceUtils.getInstance(datas);
//					for (Data data : datas) {
//						//COMPLAIN是与web层约定的值
//						if (data.getDataType().equals("Complain")) {
//							HashMap<String,String> map = new HashMap<>();
//							map.put("id", complain.getId());
//							data.setContent(JacksonUtil.getInstance().toJSON(map));
//							data.setDataType("java.lang.String");
//							data.setFormatType(FormatType.STRING);
//						}
//					}
//				}		
				logger.info("queryShareData根据id查询,请求SDK报文:"+JacksonUtil.getInstance().toJSON(message));
				String result=JSONObject.fromObject(restTemplate.postForObject(pathGet, message, Message.class)).toString();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				Message<ShareData> reqMsg = new Message<ShareData>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				return result;
			}
		}
		
		
		/**
		 * 查询Group
		 *
		 * @param  groupId
		 * @return
		 * @throws IOException
		 * */
		@POST
		@Path("/QueryGroup")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String queryGroup(@FormParam("GroupId") String groupJson){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###QueryGroup  parameter###");
//			logger.info(groupId);
//
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("QueryGroup");
//			message.setType("0");
//			message.setDataType("Group");
//			message.setTranDate(dateformat.format(new Date()));
//		
//			Args argsOfGroupId = MessageFactory.eINSTANCE.createArgs();
//			argsOfGroupId.setName("GroupId");
//			argsOfGroupId.setValue(groupId);
//			
//			message.getArgs().add(argsOfGroupId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Query");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.query(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("SelectComplain根据id查询,请求Service报文:"+groupJson.toString());
			Message<?> message = null;
			Group group = new Group();
			try {
				message = JacksonUtil.getInstance().json2pojo(groupJson, Message.class);
				//message = GetParamUtils.getParam(resMsg);
				List<Data> datas = message.getData();
				String pathGet = null;
				String sdkUrl = "http://192.168.138.201:8443";
				pathGet = sdkUrl+"/api/sdk/ledger/query";
//				if (datas != null && !"".equals(datas)) {
//					complain = (Complain) ServiceUtils.getInstance(datas);
//					for (Data data : datas) {
//						//COMPLAIN是与web层约定的值
//						if (data.getDataType().equals("Complain")) {
//							HashMap<String,String> map = new HashMap<>();
//							map.put("id", complain.getId());
//							data.setContent(JacksonUtil.getInstance().toJSON(map));
//							data.setDataType("java.lang.String");
//							data.setFormatType(FormatType.STRING);
//						}
//					}
//				}		
				logger.info("SelectComplain根据id查询,请求SDK报文:"+JacksonUtil.getInstance().toJSON(message));
				String result=JSONObject.fromObject(restTemplate.postForObject(pathGet, message, Message.class)).toString();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				Message<Group> reqMsg = new Message<Group>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				return result;
			}
		}
		
		/**
		 * 通过组织用户查询Group
		 *
		 * @param  groupId
		 * @return
		 * @throws IOException
		 * */
		@POST
		@Path("/QueryGroupByOrgId")
		@Produces("text/plain;charset=utf-8")
		@Consumes("application/x-www-form-urlencoded")
		public String queryGroupByOrgId(String groupJson){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###QueryGroupByOrgId  parameter###");
//			logger.info(orgId);
//
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("QueryGroupByOrgId");
//			message.setType("0");
//			message.setDataType("Group");
//			message.setTranDate(dateformat.format(new Date()));
//		
//			Args argsOfOrgId = MessageFactory.eINSTANCE.createArgs();
//			argsOfOrgId.setName("OrgId");
//			argsOfOrgId.setValue(orgId);
//			
//			message.getArgs().add(argsOfOrgId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Query");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.query(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
			logger.info("SelectComplain根据id查询,请求Service报文:"+groupJson.toString());
			Message<?> message = null;
			Group group = new Group();
			try {
				message = JacksonUtil.getInstance().json2pojo(groupJson, Message.class);
				//message = GetParamUtils.getParam(resMsg);
				List<Data> datas = message.getData();
				String pathGet = null;
				String sdkUrl = "http://192.168.138.201:8443";
				pathGet = sdkUrl+"/api/sdk/ledger/query";
//				if (datas != null && !"".equals(datas)) {
//					complain = (Complain) ServiceUtils.getInstance(datas);
//					for (Data data : datas) {
//						//COMPLAIN是与web层约定的值
//						if (data.getDataType().equals("Complain")) {
//							HashMap<String,String> map = new HashMap<>();
//							map.put("id", complain.getId());
//							data.setContent(JacksonUtil.getInstance().toJSON(map));
//							data.setDataType("java.lang.String");
//							data.setFormatType(FormatType.STRING);
//						}
//					}
//				}		
				logger.info("SelectComplain根据id查询,请求SDK报文:"+JacksonUtil.getInstance().toJSON(message));
				String result=JSONObject.fromObject(restTemplate.postForObject(pathGet, message, Message.class)).toString();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				Message<Group> reqMsg = new Message<Group>("500", String.format("POST %s", API), e.getMessage());
				BeanUtils.merge(reqMsg, message);
				String result=JSONObject.fromObject(reqMsg).toString();
				return result;
			}
		}
		
		/**
		 * 通过组织用户查询ShareDataTemplate
		 *
		 * @param  orgId
		 * @return
		 * @throws IOException
		 * */
//		@POST
//		@Path("/QueryShareDataTemplateByOrgId")
//		@Produces("text/plain;charset=utf-8")
//		@Consumes("application/x-www-form-urlencoded")
//		public String queryShareDataTemplateByOrgId(@FormParam("OrgId") String orgId){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###QueryShareDataTemplateByOrgId  parameter###");
//			logger.info(orgId);
//
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("QueryShareDataTemplateByOrgId");
//			message.setType("0");
//			message.setDataType("ShareDataTemplate");
//			message.setTranDate(dateformat.format(new Date()));
//		
//			Args argsOfOrgId = MessageFactory.eINSTANCE.createArgs();
//			argsOfOrgId.setName("OrgId");
//			argsOfOrgId.setValue(orgId);
//			
//			message.getArgs().add(argsOfOrgId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Query");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.query(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
//		}
		
		/**
		 * 通过组织用户查询ShareData
		 *
		 * @param  orgId
		 * @return
		 * @throws IOException
		 * */
//		@POST
//		@Path("/QueryShareDataByOrgId")
//		@Produces("text/plain;charset=utf-8")
//		@Consumes("application/x-www-form-urlencoded")
//		public String queryShareDataByOrgId(@FormParam("OrgId") String orgId){
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Headers",
//					"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//			response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//			response.addHeader("X-Powered-By", "3.2.1");
//			
//			logger.info("###QueryShareDataByOrgId  parameter###");
//			logger.info(orgId);
//
//			Message message = MessageFactory.eINSTANCE.createMessage();
//			message.setId(UUID.randomUUID().toString());
//			message.setChannel("WEB");
//			message.setTranCode("QueryShareDataByOrgId");
//			message.setType("0");
//			message.setDataType("ShareData");
//			message.setTranDate(dateformat.format(new Date()));
//		
//			Args argsOfOrgId = MessageFactory.eINSTANCE.createArgs();
//			argsOfOrgId.setName("OrgId");
//			argsOfOrgId.setValue(orgId);
//			
//			message.getArgs().add(argsOfOrgId);
//			
//			Message newMessage = MessageUtil.completeMessage(message, "Query");
//			
//			String respMsg = null;
//			
//			if (method.equals("Service")) {
//				respMsg = FabricServices.query(JSONTransferUtil.transferToJSONStr(newMessage));
//			}else {
//				respMsg = RestUtil.HttpPostWithJson(queryUrl, JSONTransferUtil.transferToJSONStr(newMessage));
//			}
//			
//			return respMsg;
//		}
}
