package com.commodity.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;

import com.commodity.util.JSONTransferUtil;
import com.commodity.util.MessageUtil;
import com.commodity.util.PropertiesUtil;

//import com.mchain.sdk.app.Helper;
//import com.mchain.sdk.app.Router;
//import com.mchain.sdk.entity.SampleOrg;
//import com.mchain.sdk.entity.Result;
//import com.mchain.sdk.entity.ResultConstants;
//import com.mchain.sdk.util.JacksonUtil;
//import com.mchain.sdk.util.ServiceUtil;
//import com.mchain.sdk.util.ConfigUtil;

import com.ibm.fabric.sdk.app.Helper;
import com.ibm.fabric.sdk.app.Router;
import com.ibm.fabric.sdk.entity.SampleOrg;
import com.ibm.fabric.sdk.service.entity.Error;
import com.ibm.fabric.sdk.service.entity.ErrorList;
import com.ibm.fabric.sdk.service.entity.Result;
import com.ibm.fabric.sdk.service.entity.ResultConstants;
import com.ibm.fabric.sdk.service.util.JacksonUtil;
import com.ibm.fabric.sdk.service.util.ValidateUtil;
import com.ibm.fabric.sdk.util.ConfigUtil;

import com.mchain.model.Message.Message;

import net.sf.json.JSONObject;

public class FabricServices {
	private static Logger logger = Logger.getLogger(FabricServices.class);

	private static Map<String, String> orgPeerLocationMap;

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	/* Service Check */
	@GET
	@Path("/check")
	@Consumes(MediaType.APPLICATION_JSON)
	public String check() {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");

		return "OK";
	}

	/* Query Information */
	public static String query(String reqMsgJson) {

		String resMsgJson = "";
		String SN = JacksonUtil.extractJsonValue(reqMsgJson, "traceNo");

		logger.info(String.format("请求报文: ## %s ## - %s", SN, reqMsgJson));
		
		String validMsg = validMsg(reqMsgJson);
		if(validMsg==null || validMsg.trim().isEmpty()){
			String orgid = JacksonUtil.extractJsonValue(reqMsgJson, "orgid");
			
			boolean mock = PropertiesUtil.getInstance().getAppPropertyBoolean(PropertiesUtil.Mock);
			if(!mock){
				resMsgJson = handler(reqMsgJson, "QueryChaincode", "Query", orgid)[0];
			}else{
				resMsgJson = reqMsgJson;
			}
			logger.info(String.format("返回报文: ## %s ## - %s", SN, resMsgJson));
		}

		return resMsgJson;
	}

	/* Invoke Transaction */
	public static String invoke(String reqMsgJson) {

		String resMsgJson = "";
		String SN = JacksonUtil.extractJsonValue(reqMsgJson, "traceNo"); //取到请求报文中的traceNo字段数据信息

		logger.info(String.format("请求报文: ## %s ## - %s", SN, reqMsgJson));;
		
		String validMsg = validMsg(reqMsgJson);
		
		if(validMsg==null || validMsg.trim().isEmpty()){
			String orgid = JacksonUtil.extractJsonValue(reqMsgJson, "orgid");
			
			boolean mock = PropertiesUtil.getInstance().getAppPropertyBoolean(PropertiesUtil.Mock);
			if(!mock){
				String[] resultArray = handler(reqMsgJson, "InvokeChaincode", "Invoke", orgid);
				String content = resultArray[0];
	
				resMsgJson = content;
			}else{
				Message respMsg = JSONTransferUtil.transferToMessage(reqMsgJson);
				respMsg.setRetCode("00");
				resMsgJson = JSONTransferUtil.transferToJSONStr(respMsg);
			}
			logger.info(String.format("返回报文: ## %s ## - %s", SN, resMsgJson));
		}

		return resMsgJson;
	}

	@POST
	@Path("/block")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Consumes("application/x-www-form-urlencoded")
	// @Produces("text/plain")
	public String block(@Context HttpServletRequest request) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");

		String methodName = "QueryChannel";
		String orgName = "org1";
		String userName = PropertiesUtil.getInstance().getUserNameByOrgName(orgName);
		String functionName = "query";
		String reqMsgJson = "{\"queryType\":\"ChannelInfo\",\"argType\":\"ChannelInfo\",\"arg\":\"ChannelInfo\"}";

		String res1 = Router.routeMethods(methodName, orgName, userName, functionName, reqMsgJson, null);
		
		orgName = "org2";
		String res2 = Router.routeMethods(methodName, orgName, userName, functionName, reqMsgJson, null);
		
		orgName = "org3";
		String res3 = Router.routeMethods(methodName, orgName, userName, functionName, reqMsgJson, null);
		
		orgName = "org4";
		String res4 = Router.routeMethods(methodName, orgName, userName, functionName, reqMsgJson, null);
		
		List<String> results = new ArrayList<String>();
		results.add(res1);
		results.add(res2);
		results.add(res3);
		results.add(res4);
		
		String res = JacksonUtil.toJSon(results);

		return res;
	}

	public static String validMsg(String reqMsgJson) {
		String resMsgJson = "";
		String SN = JacksonUtil.extractJsonValue(reqMsgJson, "traceNo");
		
		String channel = JacksonUtil.extractJsonValue(reqMsgJson, "channel");

		if (channel.equals("api")) {
			ErrorList errorList = preProcessApi(reqMsgJson); //组装一个错误集合对象类型
			if (errorList.getError().size() != 0) { //说明有错误产生
				resMsgJson = MessageUtil.insertMsgError(reqMsgJson, errorList);
				JSONObject jsonObject = new JSONObject().fromObject(resMsgJson);
				if (jsonObject.containsKey("retCode"))
					jsonObject.replace("retCode", ResultConstants.STATE_FAIL);
				else
					jsonObject.element("retCode", ResultConstants.STATE_FAIL);

				resMsgJson = jsonObject.toString();
				logger.info(String.format("返回报文: ## %s ## - %s", SN, resMsgJson));

				return resMsgJson;
			}
		} else if (channel.equals("WEB")) {
			ErrorList errorList = preProcessWeb(reqMsgJson);
			if (errorList.getError().size() != 0) {
				resMsgJson = MessageUtil.insertMsgError(reqMsgJson, errorList);
				JSONObject jsonObject = new JSONObject().fromObject(resMsgJson);
				if (jsonObject.containsKey("retCode"))
					jsonObject.replace("retCode", ResultConstants.STATE_FAIL);
				else
					jsonObject.element("retCode", ResultConstants.STATE_FAIL);

				resMsgJson = jsonObject.toString();
				logger.info(String.format("返回报文: ## %s ## - %s", SN, resMsgJson));

				return resMsgJson;
			}
		} else {
			ErrorList errorList = new ErrorList();
			List<Error> errors = new ArrayList<Error>();
			Error error = new Error();
			error.setCode(PropertiesUtil.getInstance().getErrId(PropertiesUtil.ArgMissMappingId));
			error.setStatement(PropertiesUtil.getInstance().getErrMsg(PropertiesUtil.ArgMissMappingMsg,
					new String[] { "'channel'" }));
			errors.add(error);
			errorList.setError(errors);

			resMsgJson = MessageUtil.insertMsgError(reqMsgJson, errorList);
			JSONObject jsonObject = new JSONObject().fromObject(resMsgJson);
			if (jsonObject.containsKey("retCode"))
				jsonObject.replace("retCode", ResultConstants.STATE_FAIL);
			else
				jsonObject.element("retCode", ResultConstants.STATE_FAIL);

			resMsgJson = jsonObject.toString();
			logger.info(String.format("返回报文: ## %s ## - %s", SN, resMsgJson));

			return resMsgJson;
		}

//		String orgid = JacksonUtil.extractJsonValue(reqMsgJson, "orgid");
//		if (orgid == null || orgid.trim().isEmpty()) {
//			ErrorList errorList = new ErrorList();
//			List<Error> errors = new ArrayList<Error>();
//			Error error = new Error();
//			error.setCode(PropertiesUtil.getInstance().getErrId(PropertiesUtil.OrgEmptyId));
//			error.setStatement(PropertiesUtil.getInstance().getErrMsg(PropertiesUtil.OrgEmptyMsg));
//			errors.add(error);
//			errorList.setError(errors);
//
//			resMsgJson = MessageUtil.insertMsgError(reqMsgJson, errorList);
//			JSONObject jsonObject = new JSONObject().fromObject(resMsgJson);
//			if (jsonObject.containsKey("retCode"))
//				jsonObject.replace("retCode", ResultConstants.STATE_FAIL);
//			else
//				jsonObject.element("retCode", ResultConstants.STATE_FAIL);
//
//			resMsgJson = jsonObject.toString();
//			logger.info(String.format("返回报文: ## %s ## - %s", SN, resMsgJson));
//
//			return resMsgJson;
//		}
		
		return resMsgJson;
	}

	public static String[] handler(String reqMsgJson, String methodName, String functionName, String orgid) {
													//invokeChaincode		Invoke
		ErrorList errorList = new ErrorList();
		List<Error> javaErrors = new ArrayList<Error>();
		Error error = new Error();

		String[] resArray = new String[2];
		String resMsgJson = "";
		String content = "";
		String message = "";

		String orgName = "";
		if (orgid != null && !orgid.trim().isEmpty()) { //如果orgid不为空
			orgName = PropertiesUtil.getInstance().getOrgNameByOrgId(orgid); //根据orgid去配置文件中读取对应的值
		} else {//如果没有orgid，则随机创建一个出来
			// Random choose one prg/peer
			String[] orgNames = ConfigUtil.getConfig().getOrgNames(); 
			int totalCount = orgNames.length;

			Random r = new Random();
			orgName = orgNames[r.nextInt(totalCount)];
		}

		// choose available peer/org in case the preferred one is unavailable
		try {
			orgName = chooseAvailableOrg(orgName);
		} catch (Exception e) {
			logger.error(String.format("选择可用区块链节点时异常 : %s", e.getMessage().toString()));
			orgName = PropertiesUtil.getInstance().getOrgName(JacksonUtil.extractJsonValue(reqMsgJson, "orgid"));
		}
		String userName = PropertiesUtil.getInstance().getUserNameByOrgName(orgName); //会到yuanyong.properties配置文件中读取该节点对应
		logger.info(String.format("选择的区块链节点为 : %s. 执行用户为 : %s", orgName, userName));

		if (orgName.equals("") || userName.equals("")) {
			error.setCode(PropertiesUtil.getInstance().getErrId(PropertiesUtil.ArgMissMappingId));
			error.setStatement(PropertiesUtil.getInstance().getErrMsg(PropertiesUtil.ArgMissMappingMsg,
					new String[] { "OrgName or UserName" }));
			javaErrors.add(error);
			logger.error(String.format("错误: %s - %s", error.getCode(), error.getStatement()));

			errorList.setError(javaErrors);

			resMsgJson = MessageUtil.insertMsgError(reqMsgJson, errorList);

			JSONObject jsonObject = new JSONObject().fromObject(resMsgJson);
			if (jsonObject.containsKey("retCode"))
				jsonObject.replace("retCode", ResultConstants.STATE_FAIL);
			else
				jsonObject.element("retCode", ResultConstants.STATE_FAIL);

			resMsgJson = jsonObject.toString();

			resArray[0] = resMsgJson;
			resArray[1] = message;
			return resArray;
		}

		String res = Router.routeMethods(methodName, orgName, userName, functionName, reqMsgJson, null);
									//invokeChaincode						Invoke
		Result result = (Result) JacksonUtil.readValue(res, Result.class);
		javaErrors = result.getErrors();
		content = result.getContent().toString();
		if (result.getMessage() != null)
			message = result.getMessage();

		// Show chaincode errors
		ErrorList ccErrorList = JacksonUtil.extractJsonArray(content, "error", ErrorList.class);
		if (ccErrorList != null && ccErrorList.getError() != null && ccErrorList.getError().size() != 0) {
			for (Error e : ccErrorList.getError()) {
				logger.error(String.format("错误: %s - %s", e.getCode(), e.getStatement()));
			}
		}

		// Add java errors to returned message
		errorList.setError(javaErrors);

		if (javaErrors.size() != 0) {
			if (!content.equals("{}"))
				resMsgJson = MessageUtil.insertMsgError(content, errorList);
			else {
				resMsgJson = MessageUtil.insertMsgError(reqMsgJson, errorList);
				JSONObject jsonObject = new JSONObject().fromObject(resMsgJson);
				if (jsonObject.containsKey("retCode"))
					jsonObject.replace("retCode", ResultConstants.STATE_FAIL);
				else
					jsonObject.element("retCode", ResultConstants.STATE_FAIL);

				resMsgJson = jsonObject.toString();
			}
		} else
			resMsgJson = content;

		resArray[0] = resMsgJson;
		resArray[1] = message;
		return resArray;
	}

	//ErrorList数据类型是里面包含了许多错误集合的大集合对象类型
	private static ErrorList preProcessApi(String reqMsgJson) {
		ErrorList errorList = new ErrorList();
		List<Error> errors = new ArrayList<Error>();
		Error error = new Error();

		// 校验渠道传递的报文格式是否规范
		// String validateResult = ValidateUtil.validate(reqMsgJson);
		// if(validateResult != null) {
		// error.setCode(PropertiesUtil.getInstance().getErrId(PropertiesUtil.OtherExceptionId));
		// error.setStatement(PropertiesUtil.getInstance().getErrMsg(PropertiesUtil.OtherExceptionMsg,
		// new String[]{"API请求报文校验", validateResult}));
		// errors.add(error);
		// }

		errorList.setError(errors);

		return errorList;
	}

	private static ErrorList preProcessWeb(String reqMsgJson) {
		ErrorList errorList = new ErrorList();
		List<Error> errors = new ArrayList<Error>();
		
//		String[] orgNames = ConfigUtil.getConfig().getOrgNames();
//		int totalCount = orgNames.length;
//
//		Random r = new Random();
//		String orgName = orgNames[r.nextInt(totalCount)];
//		// choose available peer/org in case the preferred one is unavailable
//		try {
//			orgName = chooseAvailableOrg(orgName);
//		} catch (Exception e) {
//			logger.error(String.format("选择可用区块链节点时异常 : %s", e.getMessage().toString()));
//			orgName = PropertiesUtil.getInstance().getOrgName(JacksonUtil.extractJsonValue(reqMsgJson, "orgid"));
//		}
//		String userName = PropertiesUtil.getInstance().getUserNameByOrgName(orgName);
//		logger.info(String.format("选择的区块链节点为 : %s. 执行用户为 : %s", orgName, userName));

		errorList.setError(errors);

		return errorList;
	}

	private static String chooseAvailableOrg(String preferredOrgName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
			MalformedURLException, CryptoException, InvalidArgumentException {
		String chosenOrgName = "";

		if (orgPeerLocationMap == null) {
			orgPeerLocationMap = new HashMap<String, String>();

			for (SampleOrg org : Helper.getOrgs()) {
				String orgName = org.getName();
				Set<String> peerNames = org.getPeerNames();
				String peerName = "";
				for (String pName : peerNames) {
					peerName = pName;
					break;
				}
				String peerLocation = org.getPeerLocation(peerName);
				peerLocation = peerLocation.replaceAll("grpc://", "").replaceAll("grpcs://", "");

				logger.debug(String.format("orgName: %s peerLocation: %s", orgName, peerLocation));
				orgPeerLocationMap.put(orgName, peerLocation);
			}
		}

		if (ValidateUtil.isHostConnectable(orgPeerLocationMap.get(preferredOrgName).split(":")[0],
				Integer.parseInt(orgPeerLocationMap.get(preferredOrgName).split(":")[1])))
			chosenOrgName = preferredOrgName;
		else {
			for (Entry<String, String> entry : orgPeerLocationMap.entrySet()) {
				String strkey = entry.getKey();
				String strval = entry.getValue();

				if (strkey.equals(preferredOrgName))
					continue;
				else if (ValidateUtil.isHostConnectable(strval.split(":")[0], Integer.parseInt(strval.split(":")[1]))) {
					chosenOrgName = strkey;
					break;
				}
			}
		}

		logger.debug(String.format("chosenOrgName: %s", chosenOrgName));
		return chosenOrgName;
	}

}
