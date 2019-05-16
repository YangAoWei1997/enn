package com.enn.commodity.synergistic.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.BillOfLading;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.service.BillOfLadingService;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/BillOfLading")
@CrossOrigin
public class BillOfLadingController {
	
	private static Logger logger = Logger.getLogger(BillOfLadingController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BillOfLadingService billOfLadingService;
	
	/**
	 * 
	* @Title: CreateBillOfLading  
	* @Description: TODO
	* @param @return
	* @return String
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/CreateBillOfLading", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateBillOfLading(@RequestBody JSONObject billOfLadingJson) {
		
		
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(billOfLadingJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
//
//		
//		//获取
//		JSONObject requestJsonOfBill=new JSONObject();
//		JSONObject requestJsonOfPacking=new JSONObject();
//		JSONArray packinginfolist=billOfLadingJson.getJSONArray("packinginfolist");
//		JSONObject packinginfo=packinginfolist.getJSONObject(0);
//		packinginfo.put("id", packinginfo.optString("packingno"));
//		packinginfo.put("billOfLadingId", billOfLadingJson.optString("id"));
//		packinginfo.put("organizationId", billOfLadingJson.optString("organizationId"));
//		packinginfo.put("salesPONumber", billOfLadingJson.optString("salesPONumber"));
//		packinginfo.put("buyerId", billOfLadingJson.optString("buyerId"));
//		packinginfo.put("buyerName", billOfLadingJson.optString("buyerName"));
//		packinginfo.put("sellerId", billOfLadingJson.optString("sellerId"));
//		packinginfo.put("sellerName", billOfLadingJson.optString("sellerName"));
//		packinginfo.put("productId", billOfLadingJson.optString("productId"));
//		packinginfo.put("productName", billOfLadingJson.optString("productName"));
//		billOfLadingJson.put("dataType", "BillOfLading");
//		
////		billOfLadingJson.put("salesPONumber", billOfLadingJson.getString("PONumber"));
//		billOfLadingJson.put("weight", billOfLadingJson.optString("quantity"));
//		if( packinginfo.optString("packingno").equals("")) {
//			
//			billOfLadingJson.remove("packinginfolist");
//			billOfLadingJson.put("packinginfolist", new JSONArray());
//		}
//		//将车牌信息更新到提货单中
//		if(!(packinginfo.get("plateNo") instanceof JSONNull) && !packinginfo.get("plateNo").equals("")) {
//			
//			billOfLadingJson.put("plateNo", packinginfo.optString("plateNo"));
//			
//			JSONObject plateObject=new JSONObject();
//			plateObject.put("plateno", packinginfo.optString("plateNo"));
//			
//			JSONObject deiverInfo=userService.QueryDriverInfoByPlateNo(plateObject);
//			logger.info("司机信息:"+deiverInfo.toString());
//			billOfLadingJson.put("carrierId", deiverInfo.getJSONObject("data").optString("carrierId"));
//			billOfLadingJson.put("carrierName", deiverInfo.getJSONObject("data").optString("carrierName"));
//			billOfLadingJson.put("driverId", deiverInfo.getJSONObject("data").optString("driverId"));
//			billOfLadingJson.put("driverName", deiverInfo.getJSONObject("data").optString("driverName"));
//			
//			packinginfo.put("carrierId", deiverInfo.getJSONObject("data").optString("carrierId"));
//			packinginfo.put("carrierName", deiverInfo.getJSONObject("data").optString("carrierName"));
//			packinginfo.put("driverId", deiverInfo.getJSONObject("data").optString("driverId"));
//			packinginfo.put("driverName", deiverInfo.getJSONObject("data").optString("driverName"));
//			
//		}
////		billOfLadingJson.remove("packinginfolist");
//		packinginfo.put("dataType", "PackingList");
//		packinginfo.put("packingType", "packingList");
//		requestJsonOfBill.put("BillOfLading", billOfLadingJson.toString());
//		requestJsonOfPacking.put("PackingList", packinginfo.toString());
//		
//		
//		String resultJson=null;
////		try {
////			resultJsonOfBill = new RestUtil().httpPost(path+"/BillOfLading/CreateBillOfLading", requestJsonOfBill);
////			
////				
////			
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		resultJson=billOfLadingService.CreateBillOfLading(requestJsonOfBill, requestJsonOfPacking);
////		System.out.println("resultJson:"+resultJson);
//		
////		if(!(packinginfo.get("packingno") instanceof JSONNull) && !packinginfo.get("packingno").equals("")) {
////			try {
////		
//////			 Thread.sleep(8500);
////		resultJsonOfPacking=new RestUtil().httpPost(path+"/PackingList/CreatePackingList", requestJsonOfPacking);
////		}catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		}
//		
//		
//		
//		//从区块链获取的jsonObject
//		
//		JSONObject jsonObject=JSONObject.fromObject(resultJson);
//		
//		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//		
//		JSONObject jsonObjectOfBill=jsonObject.optJSONObject("resultJsonOfBill");
//		
//		JSONObject jsonObjectOfPacking=jsonObject.optJSONObject("resultJsonOfPack");
//		
//		
//		
////		JSONObject jsonObjectOfBill=JSONObject.fromObject(resultJsonOfBill);
//		
////		JSONObject jsonObjectOfPacking=JSONObject.fromObject(resultJsonOfPacking);
////		System.out.println(resultJsonOfBill);
////		System.out.println(resultJsonOfPacking);
//		
//		if(jsonObjectOfBill!=null) {
//		if(jsonObjectOfBill.get("errors") instanceof JSONNull) {
//			responseObject.put("errno", 0);
//			responseObject.put("error", "success");
//			responseObject.put("data", new JSONObject());
//		}else {
//			
//			JSONArray errorArray=jsonObjectOfBill.getJSONArray("errors");
//			//判断errorArray.size是否为0  2019-1-2 refine
//			if(errorArray.size()==0) {
//				throw new MyException(71,"error数组长度为0");
//			}
//			JSONObject errorMap=errorArray.getJSONObject(0);
//
//			responseObject=new ErrorHandler().errornoHandler(errorMap.optString("code"),responseObject);
//			responseObject.put("data", new JSONObject());
//			
//		}
//		}
//		
//		if(jsonObjectOfPacking!=null) {
//			
//			if(jsonObjectOfPacking.get("errors") instanceof JSONNull) {
//				responseObject.put("errno", 0);
//				responseObject.put("error", "success");
//				responseObject.put("data", new JSONObject());
//			}else {
//				
//				JSONArray errorArray=jsonObjectOfPacking.getJSONArray("errors");
//				//判断errorArray.size是否为0  2019-1-2 refine
//				if(errorArray.size()==0) {
//					throw new MyException(71,"error数组长度为0");
//				}
//				JSONObject errorMap=errorArray.getJSONObject(0);
//
//				responseObject=new ErrorHandler().errornoHandler(errorMap.optString("code"),responseObject);
//				responseObject.put("data", new JSONObject());
//				
//			}
//			
//		}
//		
////      2.判断返回状态码
//		//获取error信息
////		if(jsonObjectOfBill!=null) {
////		if(jsonObjectOfBill.get("error") instanceof JSONNull&&jsonObjectOfPacking.get("error") instanceof JSONNull) {
////			responseObject.put("errno", 0);
////			responseObject.put("error", "success");
////		}else if(!(jsonObjectOfBill.get("error") instanceof JSONNull)){
////			
////			JSONArray errorArray=jsonObjectOfBill.getJSONArray("error");
////			//判断errorArray.size是否为0  2019-1-2 refine
////			if(errorArray.size()==0) {
////				throw new MyException(71,"error数组长度为0");
////			}
////			
////			JSONObject errorMap=errorArray.getJSONObject(0);
////
////			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
////			
////		}else if(!(jsonObjectOfPacking.get("error") instanceof JSONNull)) {
////			JSONArray errorArray=jsonObjectOfPacking.getJSONArray("error");
////			JSONObject errorMap=errorArray.getJSONObject(0);
////
////			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
////		}
////		
////		}else {
////			
////			if(!(jsonObjectOfBill.get("error") instanceof JSONNull)){
////				
////				JSONArray errorArray=jsonObjectOfBill.getJSONArray("error");
////				JSONObject errorMap=errorArray.getJSONObject(0);
////
////				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
////			
////		}else {
////			responseObject.put("errno", 0);
////			responseObject.put("error", "success");
////		}
////		}
////		//获取content信息(contentMap)
////		if(resultJsonOfPacking!=null) {
////		if(jsonObjectOfBill.get("data") instanceof JSONNull&&jsonObjectOfPacking.get("data") instanceof JSONNull) {
////			
////			responseObject.put("data", new HashMap());
////			
////		}
////		}else {
////			
////			if(jsonObjectOfBill.get("data") instanceof JSONNull) {
////				
////				responseObject.put("data", new HashMap());
////				
////			}
////			
////		}
//
//		logger.info("返回给前端的json数据:"+responseObject.toString());
//		return responseObject;
		
		
		
		
		
		
		
		
		//responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
				//判断前端传的json是否为空
				if(billOfLadingJson==null) {
					throw new MyException(80,"前端传值为null");
				}
				if(!jsonUtil.isJson(billOfLadingJson.toString())) {
					throw new MyException(81,"前端传值不是json格式");
				}
				logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		
				String plateNo=null;
		if(billOfLadingJson.get("packinginfolist")!=null) {
			
			plateNo=billOfLadingJson.optJSONArray("packinginfolist").optJSONObject(0).optString("plateNo");
			billOfLadingJson.put("plateNo", plateNo);
			//创建packinglist
		}
		billOfLadingJson.remove("packinginfolist");
		
		BillOfLading billOfLading=(BillOfLading)JSONObject.toBean(billOfLadingJson,BillOfLading.class);
		
		responseObject=billOfLadingService.CreateBillOfLading(billOfLading);
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		return responseObject;
		
	}
	
	/**
	 * 
	* @Title: QueryBillOfLading  
	* @Description: TODO
	* @param @param billOfLadingJson
	* @param @return
	* @return String
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/QueryBillOfLading", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryBillOfLading(@RequestBody JSONObject billOfLadingJson) {
		
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(billOfLadingJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
//
//		
//		//定义访问区块链接口所需要的json
//		JSONObject requestJson=new JSONObject();
//		
//		//组装json
//		//判断前台输入的关键信息是否为空
//		if(billOfLadingJson.optString("id").equals("")) {
//			responseObject.put("errno", 23);
//			responseObject.put("error", "提货单id不能为空");
//			responseObject.put("data", new JSONObject());
//			return responseObject;
//		}
////		if(billOfLadingJson.getString("purchasePONumber").equals("")) {
////			responseObject.put("errno", 24);
////			responseObject.put("error", "关联采购订单号不能为空");
////			responseObject.put("data", new HashMap());
////			return responseObject;
////		}
////		if(billOfLadingJson.getString("purchasePONumber").equals("")) {
////			responseObject.put("errno", 24);
////			responseObject.put("error", "关联采购订单号不能为空");
////			responseObject.put("data", new HashMap());
////			return responseObject;
////		}
//		
//		requestJson.put("BillOfLadingId", billOfLadingJson.optString("id"));
//		requestJson.put("UserOrgId", billOfLadingJson.optString("organizationId"));
//		//result:区块链接口返回的json字符串
//		String resultJson=null;
//		
//		resultJson=billOfLadingService.QueryBillOfLading(requestJson);
////		try {
////			resultJson = new RestUtil().httpPost(path+"/BillOfLading/QueryBillOfLading", requestJson);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
//		JSONObject jsonObject=JSONObject.fromObject(resultJson);
//		
//		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//		
////      2.判断返回状态码
//		//获取error信息
//		
//		if(jsonObject.get("errors") instanceof JSONNull) {
//			responseObject.put("errno", 0);
//			responseObject.put("error", "success");
//		}else {
//			
//			JSONArray errorArray=jsonObject.getJSONArray("errors");
//			
//			//判断errorArray.size是否为0  2019-1-2 refine
//			if(errorArray.size()==0) {
//				throw new MyException(71,"error数组长度为0");
//			}
//			JSONObject errorMap=errorArray.getJSONObject(0);
//
//			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//			return responseObject;
//		}
//		//获取content信息(contentMap)
//		if(jsonObject.get("data") instanceof JSONNull) {
//			responseObject.put("errno", 12);
//			responseObject.put("error", "查询结果为空");
//			responseObject.put("data", new JSONObject());
//			
//		}else {
//			
//			JSONArray contentArray=jsonObject.getJSONArray("data");
//			 if(contentArray.size()==0) {
//	            	logger.info("data数组长度为0");
//	            	throw new MyException(72,"data数组长度为0");
//	            }
//			JSONObject map=new JSONObject();
//
//			//从区块链层获取的content对应的值是string类型，需要反序列化成jsonobject
//			
//			//获取提货单信息
//			JSONObject billMap=contentArray.getJSONObject(1);
//			if(!billMap.has("content")) {
//				logger.info("data中缺失content信息");
//            	throw new MyException(73,"data中缺失content信息");
//			}
//			
//			String billstr=billMap.getString("content");
//			
//			//将json中的null转换为""
//			billstr=jsonUtil.fromObject(billstr);
//			if(!jsonUtil.isJson(billstr)) {
//				logger.info("格式不是json");
//				throw new MyException(74,"格式不是json");
//			}
//			JSONObject billObject=JSONObject.fromObject(billstr);
////			if(billObject.get("relatedPONumber") instanceof JSONNull) {
////				billObject.put("relatedPONumber", "");
////			}
//////			billObject.remove("relatedPONumber");
////			if(billObject.get("serureSet") instanceof JSONNull) {
////				billObject.put("serureSet", "");
////			}
//////			billObject.remove("serureSet");
////			
////			//String contentStr=dataMap.getString("content");
//			if(contentArray.size()>2) {
//			JSONArray packinginfolist=new JSONArray();
//			for(int i=2;i<contentArray.size();i++) {
//				
//				
//				packinginfolist.add(JSONObject.fromObject(contentArray.getJSONObject(i).optString("content")));
//			}
//			//解析contentObject，并组装data
//			billObject.put("packinginfolist", packinginfolist);
//			}else {
//				billObject.put("packinginfolist", new JSONArray());
//			}
//			map=billObject;
//			JSONObject quantityMap=contentArray.getJSONObject(0);
//			//获取订单重量
//			String quality=quantityMap.optString("content");
//			map.put("weightbyorder", quality);
//			responseObject.put("data", map);
//
//		}
//		
//		logger.info("返回给前端的json数据:"+responseObject.toString());
//
//		return responseObject;
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(billOfLadingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		
		BillOfLading billOfLadingForQuery=(BillOfLading)JSONObject.toBean(billOfLadingJson,BillOfLading.class);
		billOfLadingForQuery.setOrganizationId("");
		billOfLadingForQuery.setOrganizationName("");
		
		List <BillOfLading> billOfLadingList = billOfLadingService.SelectBillOfLadingByObject(billOfLadingForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(billOfLadingList==null||billOfLadingList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (BillOfLading billOfLading : billOfLadingList) {
				
				dataArray.add(JsonUtil.fromObject(billOfLading));
				
			}
			dataObject.put("billOfLadingList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BillOfLadingSelectForDriver", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject BillOfLadingSelectForDriver (@RequestBody JSONObject billOfLadingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(billOfLadingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				requestJson.put("PickUpDate", billOfLadingJson.optString("pickUpDate"));
				requestJson.put("PlateNumber", billOfLadingJson.optString("plateNo"));
				requestJson.put("UserOrgId", billOfLadingJson.optString("organizationId"));
				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				
				resultJson=billOfLadingService.SelectBillOfLadingByPlateNoAndIssDate(requestJson);
				
//				try {
//					resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingByPlateNoAndIssDate", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("errors") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("errors");
					
					//判断errorArray.size是否为0  2019-1-2 refine
					if(errorArray.size()==0) {
						throw new MyException(71,"error数组长度为0");
					}
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					return responseObject;
				}
				//获取content信息(contentMap)
				
                if(jsonObject.get("data") instanceof JSONNull) {
                	responseObject.put("errno", 12);
					responseObject.put("error", "查询结果为空");
					responseObject.put("data", new JSONObject());
					
				}else {
					
					JSONArray billlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					if(contentArray.size()==0) {
		            	logger.info("data数组长度为0");
		            	throw new MyException(72,"data数组长度为0");
		            }
					
					for(int i=0;i<contentArray.size();i++) {
						JSONObject billmap=contentArray.getJSONObject(i);
						
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!billmap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=billmap.getString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
			
						
						JSONObject billObject=JSONObject.fromObject(contentStr);
////						billObject.remove("relatedPONumber");
////						billObject.remove("serureSet");
//						
//						if(billObject.get("relatedPONumber") instanceof JSONNull) {
//							billObject.put("relatedPONumber", "");
//						}
//
//						if(billObject.get("serureSet") instanceof JSONNull) {
//							billObject.put("serureSet", "");
//						}
						billObject.put("billno", billObject.optString("id"));
						billlist.add(billObject);
					}
                    JSONObject billOfLadingObject=new JSONObject();
                    billOfLadingObject.put("billinfolist", billlist);
					responseObject.put("data", billOfLadingObject);
					
				
					//转换
					
//					
					
					
					
				}

                logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
//				if(jsonObject.get("data") instanceof JSONNull) {
//					
//					responseObject.put("data", new JSONObject());
//					
//				}else {
//					
//					JSONArray billlist=new JSONArray();
//					JSONArray contentArray=jsonObject.getJSONArray("data");
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject billObject=new JSONObject();
//						JSONObject billmap=contentArray.getJSONObject(i);
//						JSONObject contentObject=JSONObject.fromObject(billmap.getString("content"));
//						if(contentObject.getString("dataType").equals("BillOfLading")) {
//							billObject=contentObject;
//							billObject.put("billno", contentObject.getString("id"));
//						}
//						for(int j=i+1;j<contentArray.size();j++) {
//							billmap=contentArray.getJSONObject(j);
//							contentObject=JSONObject.fromObject(billmap.getString("content"));
//							if(contentObject.getString("dataType").equals("PackingList")) {
//								billObject.put("packingno", contentObject.getString("id"));
//								billObject.put("carrierId", contentObject.getString("carrierId"));
//								billObject.put("carrierName", contentObject.getString("carrierName"));
//								billObject.put("plateNo", contentObject.getString("plateNo"));
//								billObject.put("driverName", contentObject.getString("driverName"));
//								billObject.put("driverId", contentObject.getString("driverId"));
//								billObject.put("weight", contentObject.getString("weight"));
//								billObject.put("qualityId", contentObject.getString("qualityId"));
//								
//								billlist.add(billObject);
//								
//								i+=1;
//							}else if(contentObject.getString("dataType").equals("BillOfLading")) {
//								i=j;
//								break;
//							}
//						}
//						
//					}
//                    JSONObject billOfLadingObject=new JSONObject();
//                    billOfLadingObject.put("billinfolist", billlist);
//					responseObject.put("data", billOfLadingObject);
//					
//				
//					//转换
//					
////					
//					
//					
//					
//				}
//
//				return responseObject;
				
				
				
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/BillOfLadingSelectForBuyer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject BillOfLadingSelectForBuyer  (@RequestBody JSONObject billOfLadingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(billOfLadingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				requestJson.put("BillOfLadingId", billOfLadingJson.optString("billno"));
				requestJson.put("SalesCompanyId", billOfLadingJson.optString("sellerId"));
				requestJson.put("UserOrgId", billOfLadingJson.optString("organizationId"));
				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				
				resultJson=billOfLadingService.SelectBillOfLadingBySalesCompanyAndBillId(requestJson);
				
//				try {
//					resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingBySalesCompanyAndBillId", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("errors") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("errors");
					
					//判断errorArray.size是否为0  2019-1-2 refine
					if(errorArray.size()==0) {
						throw new MyException(71,"error数组长度为0");
					}
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					return responseObject;
				}
				//获取content信息(contentMap)
				if(jsonObject.get("data") instanceof JSONNull) {
					responseObject.put("errno", 12);
					responseObject.put("error", "查询结果为空");
					responseObject.put("data", new JSONObject());
					
				}else {
					
					JSONArray billlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
					
					for(int i=0;i<contentArray.size();i++) {
						JSONObject billmap=contentArray.getJSONObject(i);
						
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!billmap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=billmap.getString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						
						JSONObject billObject=JSONObject.fromObject(contentStr);
////						billObject.remove("relatedPONumber");
////						billObject.remove("serureSet");
//						if(billObject.get("relatedPONumber") instanceof JSONNull) {
//							billObject.put("relatedPONumber", "");
//						}
//
//						if(billObject.get("serureSet") instanceof JSONNull) {
//							billObject.put("serureSet", "");
//						}
						billObject.put("billno", billObject.optString("id"));
						billlist.add(billObject);
					}
                    JSONObject billOfLadingObject=new JSONObject();
                    billOfLadingObject.put("billinfolist", billlist);
					responseObject.put("data", billOfLadingObject);
					
				
					//转换
					
//					
					
					
					
				}

				logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/BillOfLadingSelectForBuyerToReceive", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject BillOfLadingSelectForBuyerToReceive  (@RequestBody JSONObject billOfLadingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(billOfLadingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				String resultJson=null;
				
				if(billOfLadingJson.optString("plateNo").equals("")&&billOfLadingJson.optString("pickUpDate").equals("")) {
				//组装json
				requestJson.put("BillOfLadingId", billOfLadingJson.optString("billno"));
				requestJson.put("SalesCompanyId", billOfLadingJson.optString("sellerId"));
				requestJson.put("UserOrgId", billOfLadingJson.optString("organizationId"));
				
				//result:区块链接口返回的json字符串
				
//				try {
//					resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingBySalesCompanyAndBillId", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				resultJson=billOfLadingService.SelectBillOfLadingBySalesCompanyAndBillId(requestJson);
				
				}else {
					requestJson.put("PickUpDate", billOfLadingJson.optString("pickUpDate"));
					requestJson.put("PlateNumber", billOfLadingJson.optString("plateNo"));
					requestJson.put("UserOrgId", billOfLadingJson.optString("organizationId"));
					
					//result:区块链接口返回的json字符串
					
//					try {
//						resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingByPlateNoAndIssDateOfBuyerId", requestJson);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					resultJson=billOfLadingService.SelectBillOfLadingByPlateNoAndIssDateOfBuyerId(requestJson);
				}
				
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("errors") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("errors");
					
					//判断errorArray.size是否为0  2019-1-2 refine
					if(errorArray.size()==0) {
						throw new MyException(71,"error数组长度为0");
					}
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					return responseObject;
				}
				//获取content信息(contentMap)
				if(jsonObject.get("data") instanceof JSONNull) {
					responseObject.put("errno", 12);
					responseObject.put("error", "查询结果为空");
					responseObject.put("data", new JSONObject());
					
				}else {
					
					JSONArray billlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
					for(int i=0;i<contentArray.size();i++) {
						JSONObject billmap=contentArray.getJSONObject(i);
						
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!billmap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=billmap.getString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						
						JSONObject billObject=JSONObject.fromObject(contentStr);
////						billObject.remove("relatedPONumber");
////						billObject.remove("serureSet");
//						
//						if(billObject.get("relatedPONumber") instanceof JSONNull) {
//							billObject.put("relatedPONumber", "");
//						}
//
//						if(billObject.get("serureSet") instanceof JSONNull) {
//							billObject.put("serureSet", "");
//						}
						billObject.put("billno", billObject.optString("id"));
						billlist.add(billObject);
					}
                    JSONObject billOfLadingObject=new JSONObject();
                    billOfLadingObject.put("billinfolist", billlist);
					responseObject.put("data", billOfLadingObject);
					
				
					//转换
					
//					
					
					
					
				}

				logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/BillOfLadingSelectForCarrier", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject BillOfLadingSelectForCarrier   (@RequestBody JSONObject billOfLadingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(billOfLadingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				requestJson.put("BillOfLadingId", billOfLadingJson.optString("billno"));
				requestJson.put("SalesCompanyId", billOfLadingJson.optString("sellerId"));
				requestJson.put("PurchaseCompanyId", billOfLadingJson.optString("buyerId"));
				requestJson.put("UserOrgId", billOfLadingJson.optString("organizationId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingBySalesPurchaseAndId", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				resultJson=billOfLadingService.SelectBillOfLadingBySalesPurchaseAndId(requestJson);
				
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("errors") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("errors");
					//判断errorArray.size是否为0  2019-1-2 refine
					if(errorArray.size()==0) {
						throw new MyException(71,"error数组长度为0");
					}
					
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					return responseObject;
				}
				//获取content信息(contentMap)
				if(jsonObject.get("data") instanceof JSONNull) {
					responseObject.put("errno", 12);
					responseObject.put("error", "查询结果为空");
					responseObject.put("data", new JSONObject());
					
				}else {
					
					JSONArray billlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
					for(int i=0;i<contentArray.size();i++) {
						JSONObject billmap=contentArray.getJSONObject(i);
						
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!billmap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=billmap.getString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						
						JSONObject billObject=JSONObject.fromObject(contentStr);
////						billObject.remove("relatedPONumber");
////						billObject.remove("serureSet");
//						if(billObject.get("relatedPONumber") instanceof JSONNull) {
//							billObject.put("relatedPONumber", "");
//						}
//
//						if(billObject.get("serureSet") instanceof JSONNull) {
//							billObject.put("serureSet", "");
//						}
						billObject.put("billno", billObject.optString("id"));
						billlist.add(billObject);
					}
                    JSONObject billOfLadingObject=new JSONObject();
                    billOfLadingObject.put("billinfolist", billlist);
					responseObject.put("data", billOfLadingObject);

				}

				logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateBillOfLading", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateBillOfLading(@RequestBody JSONObject billOfLadingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(billOfLadingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLadingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLadingJson.toString());
		

		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		
		//组装json
		//判断前台输入的关键信息是否为空
		if(billOfLadingJson.optString("id").equals("")) {
			responseObject.put("errno", 23);
			responseObject.put("error", "提货单id不能为空");
			responseObject.put("data", new JSONObject());
			return responseObject;
		}
//		if(billOfLadingJson.getString("purchasePONumber").equals("")) {
//			responseObject.put("errno", 24);
//			responseObject.put("error", "关联采购订单号不能为空");
//			responseObject.put("data", new HashMap());
//			return responseObject;
//		}
//		if(billOfLadingJson.getString("purchasePONumber").equals("")) {
//			responseObject.put("errno", 24);
//			responseObject.put("error", "关联采购订单号不能为空");
//			responseObject.put("data", new HashMap());
//			return responseObject;
//		}
		
//		requestJson.put("BillOfLadingId", billOfLadingJson.getString("id"));
//		requestJson.put("UserOrgId", billOfLadingJson.getString("organizationId"));
//		//result:区块链接口返回的json字符串
//		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/QueryBillOfLading", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//2.定义返回前台的json，用于组装
////	        { { [ { } ] } }
//		//responseObject:方法最终返回的返回
//		
//		//从区块链获取的jsonObject
//		JSONObject jsonObject=JSONObject.fromObject(resultJson);
//		
//		System.out.println(resultJson);
//		
//		JSONObject billObject=new JSONObject();
//		
////      2.判断返回状态码
//		//获取error信息
//		
//		if(jsonObject.get("error") instanceof JSONNull) {
//			responseObject.put("errno", 0);
//			responseObject.put("error", "success");
//		}else {
//			
//			JSONArray errorArray=jsonObject.getJSONArray("error");
//			JSONObject errorMap=errorArray.getJSONObject(0);
//
//			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//			responseObject.put("data", new JSONObject());
//			return responseObject;
//			
//		}
//		//获取content信息(contentMap)
//		if(jsonObject.get("data") instanceof JSONNull) {
//			
//			responseObject.put("data", new JSONObject());
//			
//		}else {
//			
//			JSONArray contentArray=jsonObject.getJSONArray("data");
			
			

			//从区块链层获取的content对应的值是string类型，需要反序列化成jsonobject
			
			//获取提货单信息
//			JSONObject billMap=contentArray.getJSONObject(1);
//			String billstr=billMap.getString("content");
//			billObject=JSONObject.fromObject(billstr);
			//String contentStr=dataMap.getString("content");
			//获取承运商信息
			JSONObject plateObject=new JSONObject();
			plateObject.put("plateno", billOfLadingJson.optString("plateNo"));
			
			JSONObject deiverInfo=userService.QueryDriverInfoByPlateNo(plateObject);
			if(!deiverInfo.getString("error").equals("success")) {
				responseObject=deiverInfo;
				return responseObject;
			}
//			billOfLadingJson.put("plateNo", billOfLadingJson.getString("plateNo"));
//			
//			
//			billOfLadingJson.put("carrierId", deiverInfo.getJSONObject("data").getString("carrierId"));
//			billOfLadingJson.put("carrierName", deiverInfo.getJSONObject("data").getString("carrierName"));
//			billOfLadingJson.put("driverId", deiverInfo.getJSONObject("data").getString("driverId"));
//			billOfLadingJson.put("driverName", deiverInfo.getJSONObject("data").getString("driverName"));
			

//		}
		
		JSONObject requestJsonForUpdate=new JSONObject();
//		String updateStr=billObject.toString();
		requestJsonForUpdate.put("BillOfLadingId", billOfLadingJson.optString("id"));
		requestJsonForUpdate.put("PlateNo", billOfLadingJson.optString("plateNo"));
		requestJsonForUpdate.put("DriverId", deiverInfo.getJSONObject("data").optString("driverId"));
		requestJsonForUpdate.put("DriverName", deiverInfo.getJSONObject("data").optString("driverName"));
		requestJsonForUpdate.put("CarrierId", deiverInfo.getJSONObject("data").optString("carrierId"));
		requestJsonForUpdate.put("CarrierName", deiverInfo.getJSONObject("data").optString("carrierName"));
		
		String resultJsonForUpdate=null;
		
//		try {
//			resultJsonForUpdate = new RestUtil().httpPost(path+"/BillOfLading/UpdateBillOfLading", requestJsonForUpdate);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		resultJsonForUpdate=billOfLadingService.UpdateBillOfLading(requestJsonForUpdate);
		
		
		JSONObject jsonObjectForUpdate=JSONObject.fromObject(resultJsonForUpdate);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObjectForUpdate.toString());
		
		if(jsonObjectForUpdate.get("errors") instanceof JSONNull) {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}else {
			
			JSONArray errorArray=jsonObjectForUpdate.getJSONArray("error");
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
	@RequestMapping(value = "/QueryBillOfLadingByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryBillOfLadingByObject(@RequestBody JSONObject billOfLdingJson) {
		
		if(billOfLdingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLdingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLdingJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		BillOfLading billOfLadingForQuery=(BillOfLading)JSONObject.toBean(billOfLdingJson, BillOfLading.class);
		
		
		List <BillOfLading> billOfLadingList = billOfLadingService.SelectBillOfLadingByObject(billOfLadingForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(billOfLadingList==null||billOfLadingList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (BillOfLading billOfLading : billOfLadingList) {
				
				dataArray.add(JsonUtil.fromObject(billOfLading));
				
			}
			dataObject.put("billOfLadingList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());	
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryBillOfLadingListByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryBillOfLadingListByObject(@RequestBody JSONObject billOfLdingJson) {
		
		if(billOfLdingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(billOfLdingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+billOfLdingJson.toString());
		
		JSONObject responseObject=new JSONObject();
		Map<String,String> mapForQuery=new HashMap<String,String>();
		
		
			
		
		
		mapForQuery.put("buyerId", billOfLdingJson.optString("orgId"));
		mapForQuery.put("sellerId", billOfLdingJson.optString("orgId"));
		
		if(("").equals(billOfLdingJson.optString("pickUpDateStart"))) {
			mapForQuery.put("pickUpDateStart", "0000-00-00");
		}else {
			mapForQuery.put("pickUpDateStart", billOfLdingJson.optString("pickUpDateStart"));
		}
		if(("").equals(billOfLdingJson.optString("pickUpDateEnd"))) {
			mapForQuery.put("pickUpDateEnd", "9999-12-31");
		}else {
			mapForQuery.put("pickUpDateEnd", billOfLdingJson.optString("pickUpDateEnd"));
		}
		
//		poJson.put("materialdetail", materialdetail);
		mapForQuery.put("productName", billOfLdingJson.optString("productName"));
//		mapForQuery.put("poType", poJson.optString("poType"));
//		PO poForQuery=(PO)JSONObject.toBean(poJson, PO.class);
//		poForQuery.setOrganizationId("");
//		poForQuery.setOrganizationName("");
		
		List <BillOfLading> billOfLadingList = billOfLadingService.SelectBillOfLadingByObjectIn(mapForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(billOfLadingList==null||billOfLadingList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (BillOfLading billOfLading : billOfLadingList) {
				
				dataArray.add(JsonUtil.fromObject(billOfLading));
				
			}
			dataObject.put("billOfLadingList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	
	
}
