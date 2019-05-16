package com.enn.commodity.synergistic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.service.AuthService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Auth")
@CrossOrigin
public class AuthController {
	

	private static Logger logger = Logger.getLogger(BillOfLadingController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private AuthService authService;
	
	@ResponseBody
	@RequestMapping(value = "/CreateAuth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateAuth(@RequestBody JSONObject authJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(authJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(authJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+authJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		authJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
		authJson.put("dataType", "OrgAuth");
		authJson.put("grantor", authJson.optString("organizationId"));
		authJson.put("authDate", df.format(day));

		if(authJson.optString("authType").equals("true")&&authJson.optString("content").contains("->")) {
			String basicContent=StringUtils.substringBefore(authJson.optString("content"), "->");
			String content=authJson.optString("content");
			content+=";"+basicContent+"->Id";
			content+=";"+basicContent+"->Name";
			content+=";"+basicContent+"->Version";
			content+=";"+basicContent+"->Comments";
			content+=";"+basicContent+"->OrganizationId";
			content+=";"+basicContent+"->OrganizationName";
			content+=";"+basicContent+"->UserId";
			content+=";"+basicContent+"->UserName";
			content+=";"+basicContent+"->DataType";
			content+=";"+basicContent+"->PrivateId";
			authJson.put("content", content);
			
		}
		requestJson.put("Permission", authJson.toString());
		
		
		
		//result:区块链接口返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/Arrangement/CreateArrangement", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		resultJson=authService.CreateAuth(requestJson);
		
		
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
		
		
//      2.判断返回状态码
		//获取error信息
		
		if(jsonObject.get("errors") instanceof JSONNull) {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}else {
			
			JSONArray errorArray=jsonObject.getJSONArray("error");
			//判断errorArray.size是否为0  2019-1-2 refine
			if(errorArray.size()==0) {
				throw new MyException(71,"error数组长度为0");
			}
			
			JSONObject errorMap=errorArray.getJSONObject(0);

			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
			responseObject.put("data", new JSONObject());
			
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());	

		return responseObject;

}
		
		
	

}
