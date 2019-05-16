package com.enn.commodity.synergistic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.controller.PurchaseOrderController;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.service.AuthService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONObject;
@Service
public class AuthServiceImpl implements AuthService{
	
	 public static Logger logger = Logger.getLogger(PurchaseOrderController.class);
		
	 public static String path="http://192.168.138.86:8080/commodity/service";
		
	 @Autowired
	 private JsonUtil jsonUtil;

	@Override
	public String CreateAuth(JSONObject requestJson) {

		//区块链返回的json字符串
		String resultJson=null;
		try {
			resultJson = new RestUtil().httpPost(path+"/IP/CreatePermissionInformation", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			if(resultJson==null) {
				logger.info("区块链返回null");
				
				throw new MyException(81,"区块链返回null");
			}
			if(!jsonUtil.isJson(resultJson)) {
				
				logger.info("区块链返回格式不是json");
				
				throw new MyException(23,"区块链返回格式不符合预期要求");
				
			}
			//将null转换为""
//			resultJson=jsonUtil.fromObject(resultJson);
			
			return resultJson;
	}
	
	

}
