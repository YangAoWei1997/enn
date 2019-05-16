package com.commodity.util;

import com.ibm.fabric.sdk.service.entity.Error;
import com.ibm.fabric.sdk.service.entity.ErrorList;
import com.ibm.fabric.sdk.service.util.JacksonUtil;
import com.mchain.model.Message.Args;
import com.mchain.model.Message.Message;
import com.mchain.model.Message.MessageFactory;

import net.sf.json.JSONObject;

public class MessageUtil {
	
	public static String insertMsgError(String inMsg, ErrorList errorList) {
		String outMsg = "";

		JSONObject jsonObject = new JSONObject().fromObject(inMsg);
		ErrorList inList = JacksonUtil.extractJsonArray(inMsg, "error", ErrorList.class);

		if (inList == null) {
			jsonObject.element("error", errorList.getError());
		} else {
			for (Error e : errorList.getError())
				jsonObject.accumulate("error", e);
		}

		outMsg = jsonObject.toString();

		return outMsg;
	}
	
	//丰富message报文的args参数信息，这个信息在开发平台之后，就需要用到了这些个参数
	public static Message completeMessage(Message oldMessage, String functionName) {
		Args argsOfChannel = MessageFactory.eINSTANCE.createArgs();
		argsOfChannel.setName("channelName");
		argsOfChannel.setValue("mychannel");
		
		Args argsOfCC = MessageFactory.eINSTANCE.createArgs();
		argsOfCC.setName("chaincodeName");
		argsOfCC.setValue("exmaple");
		
		Args argsOfFunctionName = MessageFactory.eINSTANCE.createArgs();
		argsOfFunctionName.setName("functionName");
		argsOfFunctionName.setValue(functionName);
		
		Args argsOfFile = MessageFactory.eINSTANCE.createArgs();
		argsOfFile.setName("hasFile");
		argsOfFile.setValue("false");
		
		Args argsOfAsyn = MessageFactory.eINSTANCE.createArgs();
		argsOfAsyn.setName("asyncInvoke");
		argsOfAsyn.setValue("DISABLE");
		
		Args argsOfBusiness = MessageFactory.eINSTANCE.createArgs();
		argsOfBusiness.setName("otherBusinessArg");
		argsOfBusiness.setValue("argument");
		
		oldMessage.getArgs().add(argsOfAsyn);
		oldMessage.getArgs().add(argsOfChannel);
		oldMessage.getArgs().add(argsOfCC);
		oldMessage.getArgs().add(argsOfFunctionName);
		oldMessage.getArgs().add(argsOfFile);
		oldMessage.getArgs().add(argsOfBusiness);
		
		return oldMessage;
	}
}
