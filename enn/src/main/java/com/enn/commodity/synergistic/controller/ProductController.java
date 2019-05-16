package com.enn.commodity.synergistic.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.service.ProductService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Product")
@CrossOrigin
public class ProductController {
	
    private static Logger logger = Logger.getLogger(BillOfLadingController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	* @Title: QueryPurchaseOrder  
	* @Description: TODO
	* @param @param packingListJson
	* @param @return
	* @return JSONObject
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/QueryProductList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPurchaseOrder(@RequestBody JSONObject productJson) {
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		//组装json
//		requestJson.put("UserId", productJson.getString("userId"));
		requestJson.put("UserOrgId", productJson.getString("organizationId"));
		//result:区块链接口返回的json字符串
		String resultJson=null;
		try {
			resultJson = new RestUtil().httpPost(path+"/IP/GetProductInformation", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.定义返回前台的json，用于组装
//	        { { [ { } ] } }
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//从区块链获取的jsonObject
		System.out.println(resultJson);
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		System.out.println(resultJson);
		
//      2.判断返回状态码
		//获取error信息
		
		if(jsonObject.get("error") instanceof JSONNull) {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
		}else {
			
			JSONArray errorArray=jsonObject.getJSONArray("error");
			JSONObject errorMap=errorArray.getJSONObject(0);

			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
			
		}
		//获取content信息(contentMap)
		if(jsonObject.get("data") instanceof JSONNull) {
			
			responseObject.put("data", new JSONObject());
			
		}else {
			
			JSONArray packlist=new JSONArray();
			JSONArray contentArray=jsonObject.getJSONArray("data");
			for(int i=0;i<contentArray.size();i++) {
				JSONObject packmap=contentArray.getJSONObject(i);
				
				JSONObject packObject=JSONObject.fromObject(packmap.getString("content"));
				packObject.remove("condition");
				packObject.remove("serureSet");
				packlist.add(packObject);
				System.out.println("000"+packlist.toString());
			}
            JSONObject packlistObject=new JSONObject();
            packlistObject.put("productlist", packlist);
			responseObject.put("data", packlistObject);
			
		
			//转换
			
//			
			
			
			
		}

		return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreateProduct", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateProduct(@RequestBody JSONObject productJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(productJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(productJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+productJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		
		productJson.put("id", productJson.optString("productid"));
		productJson.put("dataType", "Product");
		productJson.put("name", productJson.optString("productname"));
		

		requestJson.put("GoodsInformation", productJson.toString());
		requestJson.put("IPid", productJson.optString("productorgid"));
		
		
		//result:区块链接口返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/Arrangement/CreateArrangement", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		resultJson=productService.CreateProductInformation(requestJson);
		
		
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
		
		
//      2.判断返回状态码
		//获取error信息
		
		if(jsonObject.get("error") instanceof JSONNull) {
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
	
	@ResponseBody
	@RequestMapping(value = "/UpdateProduct", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateProduct(@RequestBody JSONObject productJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(productJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(productJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+productJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		
		productJson.put("id", productJson.optString("productid"));
		productJson.put("dataType", "Product");
		productJson.put("name", productJson.optString("productname"));

		requestJson.put("GoodsInformation", productJson.toString());
		requestJson.put("IPid", productJson.optString("productorgid"));
		
		
		//result:区块链接口返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/Arrangement/CreateArrangement", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		resultJson=productService.UpdateProduct(requestJson);
		
		
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
		
		
//      2.判断返回状态码
		//获取error信息
		
		if(jsonObject.get("error") instanceof JSONNull) {
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
	
	@ResponseBody
	@RequestMapping(value = "/QueryProductDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryProductDetail(@RequestBody JSONObject productJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(productJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(productJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+productJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		//组装json
//		requestJson.put("UserId", productJson.getString("userId"));
		requestJson.put("UserOrgId", productJson.getString("organizationId"));
		//result:区块链接口返回的json字符串
		String resultJson=null;
		try {
			resultJson = new RestUtil().httpPost("http://192.168.206.133:8080/commodity/service/IP/GetProductInformation", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
		
//      2.判断返回状态码
		//获取error信息
		
		if(jsonObject.get("error") instanceof JSONNull) {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
		}else {
			
			JSONArray errorArray=jsonObject.getJSONArray("error");
			JSONObject errorMap=errorArray.getJSONObject(0);

			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
			
		}
		//获取content信息(contentMap)
		if(jsonObject.get("data") instanceof JSONNull) {
			
			responseObject.put("data", new JSONObject());
			
		}else {
			
			JSONArray packlist=new JSONArray();
			JSONArray contentArray=jsonObject.getJSONArray("data");
			for(int i=0;i<contentArray.size();i++) {
				JSONObject packmap=contentArray.getJSONObject(i);
				
				JSONObject packObject=JSONObject.fromObject(packmap.getString("content"));
				packObject.remove("condition");
				packObject.remove("serureSet");
				packlist.add(packObject);
				System.out.println("000"+packlist.toString());
			}
            JSONObject packlistObject=new JSONObject();
            packlistObject.put("productlist", packlist);
			responseObject.put("data", packlistObject);
			
		
			//转换
			
//			
			
			
			
		}

		return responseObject;
		
	}

}
