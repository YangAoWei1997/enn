package com.enn.commodity.synergistic.controller;

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

import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.SystemOrg;
import com.enn.commodity.synergistic.service.ArrangementService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;
import com.mchain.apollo.common.entity.base.Message;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Arrangement")
@CrossOrigin
public class ArrangementController {
	
	public static Logger logger = Logger.getLogger(ArrangementController.class);
	
	@Autowired
	private ArrangementService arrangementService;
			
	@Autowired
	private JsonUtil jsonUtil;
	
	public static String path="http://192.168.138.86:8080/commodity/service";
	
	@ResponseBody
	@RequestMapping(value = "/CreateArrangement", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateArrangement(@RequestBody JSONObject arrangementJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(arrangementJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(arrangementJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+arrangementJson.toString());
//		
//		
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				JSONObject arrangementDetail=new JSONObject();
////				JSONObject reqplanJson=new JSONObject();
//				//组装json
////				String contentStr="time:"+planJson.getString("time")+";"+"enorder:"+planJson.getString("enorder")+";"+"ordered:"+planJson.getString("ordered");
////				reqplanJson.put("content", contentStr);
////				reqplanJson.put("date", planJson.getString("date"));
//				arrangementDetail.put("quantity", arrangementJson.optString("quantity"));
//				arrangementDetail.put("priceWithTax", arrangementJson.optString("priceWithTax"));
//				arrangementDetail.put("totalPriceWithTax", arrangementJson.optString("totalPriceWithTax"));
//				arrangementJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
//				arrangementJson.put("name", arrangementJson.optString("agreementno"));
//				arrangementJson.put("dataType", "Arrangement");
//				arrangementJson.put("arrangementDetail", arrangementDetail);
//				requestJson.put("Arrangement", arrangementJson.toString());
////				requestJson.put("UserOrgId", planJson.getString("orgid"));
//
//				
//				
//				
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/Arrangement/CreateArrangement", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				resultJson=arrangementService.CreateArrangement(requestJson);
//				
//				if(resultJson==null) {
//					responseObject.put("errno", 43);
//					responseObject.put("error", "区块链service层返回数据为空");
//					responseObject.put("data", new JSONObject());
//				}
//				
//				
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
//				
////		      2.判断返回状态码
//				//获取error信息
//				
//				if(jsonObject.get("errors") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
//					responseObject.put("data", new JSONObject());
//				}else {
//					
//					JSONArray errorArray=jsonObject.getJSONArray("error");
//					//判断errorArray.size是否为0  2019-1-2 refine
//					if(errorArray.size()==0) {
//						throw new MyException(71,"error数组长度为0");
//					}
//					
//					JSONObject errorMap=errorArray.getJSONObject(0);
//
//					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//					responseObject.put("data", new JSONObject());
//					
//				}
//				
//				logger.info("返回给前端的json数据:"+responseObject.toString());	
//
//				return responseObject;
		
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(arrangementJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(arrangementJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+arrangementJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					arrangementJson.put("dataType", "Arrangement");
					arrangementJson.put("id", arrangementJson.optString("agreementno"));
				
					JSONObject materialdetail=new JSONObject();
					materialdetail.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
					materialdetail.put("totalPriceNoTax", arrangementJson.optString("totalPriceNoTax"));
					materialdetail.put("totalPriceWithTax", arrangementJson.optString("totalPriceWithTax"));
					materialdetail.put("priceNoTax", arrangementJson.optString("priceNoTax"));
					materialdetail.put("priceWithTax", arrangementJson.optString("priceWithTax"));
					materialdetail.put("quantity", arrangementJson.optString("quantity"));
					arrangementJson.put("arrangementdetail", materialdetail);
       Arrangement arrangement=(Arrangement)JSONObject.toBean(arrangementJson,Arrangement.class);
       
       responseObject=arrangementService.CreateArrangement(arrangement);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/QueryArrangement", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryArrangement(@RequestBody JSONObject arrangementJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(arrangementJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(arrangementJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+arrangementJson.toString());
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				//组装json
//				requestJson.put("OrgId", arrangementJson.optString("orgid"));
//				requestJson.put("ArrangementId", arrangementJson.optString("agreementno"));
//				requestJson.put("UserOrgId", arrangementJson.optString("organizationId"));
//				
//				
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				resultJson=arrangementService.QueryArrangement(requestJson);
//				
//				
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
////		      2.判断返回状态码
//				//获取error信息
//				
//				if(jsonObject.get("errors") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
//				}else {
//					
//					JSONArray errorArray=jsonObject.getJSONArray("errors");
//					
//					//判断errorArray.size是否为0  2019-1-2 refine
//					if(errorArray.size()==0) {
//						throw new MyException(71,"error数组长度为0");
//					}
//					JSONObject errorMap=errorArray.getJSONObject(0);
//
//					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//					
//					return responseObject;
//				}
//				//获取content信息(contentMap)
//				if(jsonObject.get("data") instanceof JSONNull) {
//					responseObject.put("errno", 70);
//					responseObject.put("error", "查询结果为空");
//					responseObject.put("data", new JSONObject());
//					
//				}else {
//					
//					JSONArray agreementlist=new JSONArray();
//					JSONArray contentArray=jsonObject.getJSONArray("data");
//					if(contentArray.size()==0) {
//		            	logger.info("data数组长度为0");
//		            	throw new MyException(72,"data数组长度为0");
//		            }
//					
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject agreementmap=contentArray.getJSONObject(i);
//						
//						//判断dataMap.getString是否为空 1-2 refine 为""或 null
//						if(!agreementmap.has("content")) {
//							logger.info("data中缺失content信息");
//			            	throw new MyException(73,"data中缺失content信息");
//						}
//						String contentStr=agreementmap.getString("content");
//						//将json中的null转换为""
//						contentStr=jsonUtil.fromObject(contentStr);
//						if(!jsonUtil.isJson(contentStr)) {
//							logger.info("格式不是json");
//							throw new MyException(74,"格式不是json");
//						}
//						
//						JSONObject agreementObject=JSONObject.fromObject(contentStr);
//						agreementObject.put("agreementno", agreementObject.optString("name"));
//						
//						if(agreementObject.getJSONObject("arrangementDetail")==null) {
//							logger.info("未获取到预期的jsonObject信息");
//							agreementObject.put("quantity", "");
//							agreementObject.put("priceWithTax", "");
//							agreementObject.put("totalPriceWithTax", "");
//						}
//						
//						agreementObject.put("quantity", agreementObject.getJSONObject("arrangementDetail").optString("quantity"));
//						agreementObject.put("priceWithTax", agreementObject.getJSONObject("arrangementDetail").optString("priceWithTax"));
//						agreementObject.put("totalPriceWithTax", agreementObject.getJSONObject("arrangementDetail").optString("totalPriceWithTax"));
//						agreementlist.add(agreementObject);
//					}
//					
//                    JSONObject agreementlistObject=new JSONObject();
//                    agreementlistObject.put("agreementlist", agreementlist);
//					responseObject.put("data", agreementlistObject);
//
//				}
//				
//				logger.info("返回给前端的json数据:"+responseObject.toString());	
//
//				return responseObject;
		
		
		if(arrangementJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(arrangementJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+arrangementJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		Arrangement arrangeForQuery=(Arrangement)JSONObject.toBean(arrangementJson, Arrangement.class);
		
		
		List <Arrangement> arrangementList = arrangementService.SelectArrangementByObject(arrangeForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(arrangementList==null||arrangementList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (Arrangement arrangement : arrangementList) {
				
				dataArray.add(JsonUtil.fromObject(arrangement));
				
			}
			dataObject.put("arrangementList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryArrangementByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryArrangementByObject(@RequestBody JSONObject arrangementJson) {
		
		if(arrangementJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(arrangementJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+arrangementJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		Arrangement arrangementForQuery=(Arrangement)JSONObject.toBean(arrangementJson, Arrangement.class);
		
		
		List <Arrangement> arrangementList = arrangementService.SelectArrangementByObject(arrangementForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(arrangementList==null||arrangementList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (Arrangement arrangement : arrangementList) {
				
				dataArray.add(JsonUtil.fromObject(arrangement));
				
			}
			dataObject.put("arrangementList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());	
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryArrangementListByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryArrangementListByObject(@RequestBody JSONObject arrangementJson) {
		
		if(arrangementJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(arrangementJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+arrangementJson.toString());
		
		JSONObject responseObject=new JSONObject();
		Map<String,String> mapForQuery=new HashMap<String,String>();
		
		mapForQuery.put("buyerId", arrangementJson.optString("orgId"));
		mapForQuery.put("sellerId", arrangementJson.optString("orgId"));
		if(("").equals(arrangementJson.optString("totalPriceWithTaxStart"))) {
			mapForQuery.put("totalPriceWithTaxStart", "0");
		}else {
			mapForQuery.put("totalPriceWithTaxStart", arrangementJson.optString("totalPriceWithTaxStart"));
		}
			
		
		if(("").equals(arrangementJson.optString("totalPriceWithTaxEnd"))) {
			mapForQuery.put("totalPriceWithTaxEnd", "99999999999999999");
		}else {
			mapForQuery.put("totalPriceWithTaxEnd", arrangementJson.optString("totalPriceWithTaxEnd"));
		}
		
		if(("").equals(arrangementJson.optString("issuedDateStart"))) {
			mapForQuery.put("issuedDateStart", "0000-00-00");
		}else {
			mapForQuery.put("issuedDateStart", arrangementJson.optString("issuedDateStart"));
		}
		if(("").equals(arrangementJson.optString("issuedDateEnd"))) {
			mapForQuery.put("issuedDateEnd", "9999-12-31");
		}else {
			mapForQuery.put("issuedDateEnd", arrangementJson.optString("issuedDateEnd"));
		}
		
//		poJson.put("materialdetail", materialdetail);
		mapForQuery.put("productName", arrangementJson.optString("productName"));
//		mapForQuery.put("poType", arrangementJson.optString("poType"));
//		PO poForQuery=(PO)JSONObject.toBean(poJson, PO.class);
//		poForQuery.setOrganizationId("");
//		poForQuery.setOrganizationName("");
		
		List <Arrangement> arrangementList = arrangementService.SelectArrangementByObjectIn(mapForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(arrangementList==null||arrangementList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (Arrangement arrangement : arrangementList) {
				
				dataArray.add(JsonUtil.fromObject(arrangement));
				
			}
			dataObject.put("arrangementList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}

}
