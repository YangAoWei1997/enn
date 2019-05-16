package com.commodity.util;

import java.io.IOException;

import org.emfjson.jackson.annotations.EcoreIdentityInfo;
import org.emfjson.jackson.annotations.EcoreReferenceInfo;
import org.emfjson.jackson.module.EMFModule;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mchain.model.Message.Message;
import com.mchain.model.Message.MessageFactory;

public class JSONTransferUtil {
	/**
	 * emf对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String transferToJSONStr(Object obj){
		String jsonInString = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			EMFModule module = new EMFModule();
			module.configure(EMFModule.Feature.OPTION_USE_ID, false);
			module.configure(EMFModule.Feature.OPTION_SERIALIZE_TYPE, false);
			module.setIdentityInfo(new EcoreIdentityInfo("Eid"));
			module.setReferenceInfo(new EcoreReferenceInfo("Eref"));
			mapper.registerModule(module);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonInString;
	}
	
	/**
	 * json字符串转换成emf对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Message transferToMessage(String jsonString){
		try {
			ObjectMapper mapper = new ObjectMapper();
			EMFModule module = new EMFModule();
			module.configure(EMFModule.Feature.OPTION_USE_ID, true); 
			
			mapper.registerModule(module);
			ObjectNode on=  (ObjectNode) mapper.reader().readTree(jsonString);
			Message bobj = MessageFactory.eINSTANCE.createMessage();
			mapper.readerForUpdating(bobj).treeToValue(on, Message.class); 
			//从Json 模型转换到ecore模型，但是 compositekey的 reference到field不能自动产生，因此使用虾面的方法处理一下
		
			return bobj;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
