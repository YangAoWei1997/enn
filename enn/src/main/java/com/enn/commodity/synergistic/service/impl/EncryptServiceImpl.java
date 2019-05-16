package com.enn.commodity.synergistic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.controller.ShareDataController;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.service.EncryptService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONObject;

@Service
public class EncryptServiceImpl implements EncryptService{
	
	 public static Logger logger = Logger.getLogger(EncryptServiceImpl.class);
	 
	 public static String path="";
	 
	 @Autowired
	 private JsonUtil jsonUtil;

	@Override
	public String Encrypt(JSONObject requestJson) {
		//区块链返回的json字符串
				String resultJson=null;
				
				JSONObject requestJsonForEncrypt=new JSONObject();
				requestJsonForEncrypt.put("methodName", "Encrypt");
				requestJsonForEncrypt.put("argumentJson", requestJson.toString());
				
				try {
					resultJson = new RestUtil().httpPost(path+"/Arrangement/CreateArrangement", requestJsonForEncrypt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
					if(resultJson==null) {
						logger.info("管理控制台返回null");
						
						throw new MyException(81,"管理控制台返回null");
					}
					if(!jsonUtil.isJson(resultJson)) {
						
						logger.info("管理控制台返回null返回格式不是json");
						
						throw new MyException(23,"管理控制台返回null返回格式不符合预期要求");
						
					}
					//将null转换为""
//					resultJson=jsonUtil.fromObject(resultJson);
					
					return resultJson;
	}

	@Override
	public String Decrypt(JSONObject requestJson) {
		// TODO Auto-generated method stub
		return null;
	}

}
