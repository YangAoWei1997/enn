package com.enn.commodity.synergistic.controller;


import java.util.ArrayList;
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

import com.enn.commodity.synergistic.entity.BillOfLading;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.PackingList;
import com.enn.commodity.synergistic.service.BillOfLadingService;
import com.enn.commodity.synergistic.service.PackingListService;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/PackingList")
@CrossOrigin
public class PackingListController {
	
	private static Logger logger = Logger.getLogger(PackingListController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PackingListService packingListService;
	
	@Autowired
	private BillOfLadingService billOfLadingService;
	
	@Autowired
	private JsonUtil jsonUtil;
	/**
	 * 
	* @Title: QueryPurchaseOrder  
	* @Description: TODO
	* @param @param packingListJson
	* @param @return
	* @return String
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/QueryPackingList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPackingList(@RequestBody JSONObject packingListJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(packingListJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(packingListJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+packingListJson.toString());
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				//组装json
//				requestJson.put("LeaveDate", packingListJson.optString("leavedDate"));
//				requestJson.put("PackingListId", packingListJson.optString("id"));
//				requestJson.put("UserOrgId", packingListJson.optString("organizationId"));
//				requestJson.put("PlateNumber", packingListJson.optString("plateNo"));
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/PackingList/QueryPackingList", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				resultJson=packingListService.QueryPackingList(requestJson);
//				
//				
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
////		      2.判断返回状态码
//				//获取error信息
//				
//				if(jsonObject.get("error") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
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
//					return responseObject;
//				}
//				//获取content信息(contentMap)
//				if(jsonObject.get("data") instanceof JSONNull) {
//					responseObject.put("errno", 12);
//					responseObject.put("error", "查询结果为空");
//					responseObject.put("data", new JSONObject());
//					
//				}else {
//					
//					JSONArray packlist=new JSONArray();
//					JSONArray contentArray=jsonObject.getJSONArray("data");
//					
//					if(contentArray.size()==0) {
//		            	logger.info("data数组长度为0");
//		            	throw new MyException(72,"data数组长度为0");
//		            }
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject packmap=contentArray.getJSONObject(i);
//						
//						//判断dataMap.getString是否为空 1-2 refine 为""或 null
//						if(!packmap.has("content")) {
//							logger.info("data中缺失content信息");
//			            	throw new MyException(73,"data中缺失content信息");
//						}
//						String contentStr=packmap.getString("content");
//						//将json中的null转换为""
//						contentStr=jsonUtil.fromObject(contentStr);
//						if(!jsonUtil.isJson(contentStr)) {
//							logger.info("格式不是json");
//							throw new MyException(74,"格式不是json");
//						}
//						
//						JSONObject packObject=JSONObject.fromObject(contentStr);
//						packlist.add(packObject);
//					}
//                    JSONObject packlistObject=new JSONObject();
//                    packlistObject.put("packlist", packlist);
//					responseObject.put("data", packlistObject);
//
//				}
//
//				logger.info("返回给前端的json数据:"+responseObject.toString());
//				return responseObject;
		
		if(packingListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(packingListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+packingListJson.toString());
		
		if(packingListJson.optString("id").equals("")) {
			throw new MyException(102,"ID为必传项");
		}
		
		JSONObject responseObject=new JSONObject();
		
		PackingList packForQuery=(PackingList)JSONObject.toBean(packingListJson, PackingList.class);
		packForQuery.setOrganizationId("");
		packForQuery.setOrganizationName("");
		
		List <PackingList> packList = packingListService.SelectPackingListByObject(packForQuery);
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(packList==null||packList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (PackingList packingList : packList) {
				
				dataArray.add(JsonUtil.fromObject(packingList));
				
			}
			dataObject.put("packList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreatePackingList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreatePackingList(@RequestBody JSONObject packingListJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(packingListJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(packingListJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+packingListJson.toString());
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				
//				JSONObject billrequestJson=new JSONObject();
//				
////				String leavefirstweight=packingListJson.getString("leavefirstweight");
////				String leavesecondweight=packingListJson.getString("leavesecondweight");
//				
//				if(packingListJson.get("weight") instanceof JSONNull||packingListJson.get("weight").equals("")) {
//					if(!(packingListJson.get("leavefirstweight") instanceof JSONNull) && !packingListJson.get("leavefirstweight").equals("") && !(packingListJson.get("leavesecondweight") instanceof JSONNull) && !packingListJson.get("leavesecondweight").equals("")) {
////						int leavefirstweight=Integer.parseInt(packingListJson.getString("leavefirstweight"));
////						int leavesecondweight=Integer.parseInt(packingListJson.getString("leavesecondweight"));
////						String weight=String.valueOf((leavesecondweight-leavefirstweight));
//						String weight=JsonUtil.operationOfStr(packingListJson.getString("leavesecondweight"), packingListJson.getString("leavefirstweight"));
//						
//						packingListJson.put("weight", weight);
//					}
//				}
//				
//				
//				
//				
//				
//				//根据装箱单的提货单号查询提货单
//				
////				String billresultJson=null;
////				
////				billrequestJson.put("id", packingListJson.getString("billOfLadingId"));
////				try {
////					billresultJson = new RestUtil().httpPost(path+"/BillOfLading/QueryBillOfLading", billrequestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				//将装箱单的车牌更新到提货单上
////				JSONObject plateNoJson=new JSONObject();
////				String plateNoStr=packingListJson.getString("plateNo");
////				plateNoJson.put("plateNo", plateNoStr);
//				
//			
////				int leavefirstweight=Integer.parseInt(packingListJson.getString("leavefirstweight"));
////				int leavesecondweight=Integer.parseInt(packingListJson.getString("leavesecondweight"));
////				String weight=String.valueOf((leavesecondweight-leavefirstweight));
////				//组装json
////				
////				if(packingListJson.getString("weight").equals("")){
////					packingListJson.replace("weight","", weight);
////				}
//				
//				packingListJson.put("packingType", "packingList");
//				packingListJson.put("dataType", "PackingList");
//				packingListJson.put("id", packingListJson.getString("packingno"));
//				requestJson.put("PackingList", packingListJson.toString());
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/PackingList/CreatePackingList", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				resultJson=packingListService.CreatePackingList(requestJson);
//				
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
//				JSONObject jsonObjectOfCreate=jsonObject.optJSONObject("resultJson");
//				
//				JSONObject jsonObjectOfUpdate=jsonObject.optJSONObject("resultJsonOfUpdate");
//				
////		      2.判断返回状态码
//				//获取error信息
//				if(jsonObjectOfCreate!=null) {
//				if(jsonObjectOfCreate.get("error") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
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
//					
//					
//				}
//				
//				responseObject.put("data", new JSONObject());
//				}
//				//获取content信息(contentMap)
////				if(jsonObject.get("data") instanceof JSONNull) {
//				
////				JSONObject requestJsonForQuery=new JSONObject();
////				requestJsonForQuery.put("BillOfLadingId", packingListJson.getString("billOfLadingId"));
////				requestJsonForQuery.put("UserOrgId", packingListJson.getString("organizationId"));
////				//result:区块链接口返回的json字符串
////				String resultJsonForQuery=null;
////				try {
////					resultJsonForQuery = new RestUtil().httpPost(path+"/BillOfLading/QueryBillOfLading", requestJsonForQuery);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////				
////				JSONObject jsonObjectForQuery=JSONObject.fromObject(resultJsonForQuery);
////				
////				System.out.println(resultJsonForQuery);
////				
////				JSONObject billObject=new JSONObject();
//				
////		      2.判断返回状态码
//				//获取error信息
//				
////				if(jsonObjectForQuery.get("error") instanceof JSONNull) {
////					responseObject.put("errno", 0);
////					responseObject.put("error", "success");
////				}else {
////					
////					JSONArray errorArray=jsonObjectForQuery.getJSONArray("error");
////					JSONObject errorMap=errorArray.getJSONObject(0);
////
////					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
////					
////				}
//				//获取content信息(contentMap)
////				if(jsonObjectForQuery.get("data") instanceof JSONNull) {
////					
////					responseObject.put("data", new JSONObject());
////					
////				}else {
////					
////					JSONArray contentArray=jsonObjectForQuery.getJSONArray("data");
////					
////					
////
////					//从区块链层获取的content对应的值是string类型，需要反序列化成jsonobject
////					
////					//获取提货单信息
////					JSONObject billMap=contentArray.getJSONObject(1);
////					String billstr=billMap.getString("content");
////					billObject=JSONObject.fromObject(billstr);
////					//String contentStr=dataMap.getString("content");
////					billObject.put("plateNo", packingListJson.getString("plateNo"));
////					
////					JSONObject plateObject=new JSONObject();
////					plateObject.put("plateno", packingListJson.getString("plateNo"));
////					
////					JSONObject deiverInfo=userService.QueryDriverInfoByPlateNo(plateObject);
////					billObject.put("carrierId", deiverInfo.getJSONObject("data").getString("carrierId"));
////					billObject.put("carrierName", deiverInfo.getJSONObject("data").getString("carrierName"));
////					billObject.put("driverId", deiverInfo.getJSONObject("data").getString("driverId"));
////					billObject.put("driverName", deiverInfo.getJSONObject("data").getString("driverName"));
////
////				}
////				//获取承运商信息
////				JSONObject plateObject=new JSONObject();
////				plateObject.put("plateno", packingListJson.optString("plateNo"));
////				
////				JSONObject deiverInfo=userService.QueryDriverInfoByPlateNo(plateObject);
////				if(!deiverInfo.getString("error").equals("success")) {
////					responseObject=deiverInfo;
////					return responseObject;
////				}
////				
////				JSONObject requestJsonForUpdate=new JSONObject();
////				
////				requestJsonForUpdate.put("BillOfLadingId", packingListJson.optString("billOfLadingId"));
////				requestJsonForUpdate.put("PlateNo", packingListJson.optString("plateNo"));
////				requestJsonForUpdate.put("DriverId", deiverInfo.getJSONObject("data").optString("driverId"));
////				requestJsonForUpdate.put("DriverName", deiverInfo.getJSONObject("data").optString("driverName"));
////				requestJsonForUpdate.put("CarrierId", deiverInfo.getJSONObject("data").optString("carrierId"));
////				requestJsonForUpdate.put("CarrierName", deiverInfo.getJSONObject("data").optString("carrierName"));
////				
////				String resultJsonForUpdate=null;
//				
////				try {
////					resultJsonForUpdate = new RestUtil().httpPost(path+"/BillOfLading/UpdateBillOfLading", requestJsonForUpdate);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
////				resultJsonForUpdate=billOfLadingService.UpdateBillOfLading(requestJsonForUpdate);
//				
////				JSONObject jsonObjectForUpdate=JSONObject.fromObject(resultJsonForUpdate);
//				if(jsonObjectOfUpdate!=null) {
//				if(jsonObjectOfUpdate.get("error") instanceof JSONNull) {
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
//				}
//
//				logger.info("返回给前端的json数据:"+responseObject.toString());
//				return responseObject;
		
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(packingListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(packingListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+packingListJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					packingListJson.put("packingType", "packingList");
					packingListJson.put("dataType", "PackingList");
					packingListJson.put("id", packingListJson.optString("packingno"));
				
					
       PackingList packList=(PackingList)JSONObject.toBean(packingListJson,PackingList.class);
       
       responseObject=packingListService.CreatePackingList(packList);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreateReceiveList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateReceiveList(@RequestBody JSONObject receiveListJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(receiveListJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(receiveListJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+receiveListJson.toString());
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				//组装json
//				if(receiveListJson.get("weight") instanceof JSONNull||receiveListJson.get("weight").equals("")) {
//					if(!(receiveListJson.get("leavefirstweight") instanceof JSONNull) && !receiveListJson.get("leavefirstweight").equals("") && !(receiveListJson.get("leavesecondweight") instanceof JSONNull) && !receiveListJson.get("leavesecondweight").equals("")) {
////						int leavefirstweight=Integer.parseInt(receiveListJson.getString("leavefirstweight"));
////						int leavesecondweight=Integer.parseInt(receiveListJson.getString("leavesecondweight"));
//						String weight=JsonUtil.operationOfStr(receiveListJson.optString("leavefirstweight"), receiveListJson.optString("leavesecondweight"));
//						receiveListJson.put("weight", weight);
//					}
//				}
//				
//				receiveListJson.put("packingType", "arrivalList");
//				receiveListJson.put("dataType", "PackingList");
//				receiveListJson.put("id", receiveListJson.optString("arriveno"));
//				receiveListJson.put("source", receiveListJson.optString("sellerId"));
//				receiveListJson.put("qualityId", "");
//				requestJson.put("ArrivalList", receiveListJson.toString());
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/PackingList/CreateArrivalList", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				resultJson=packingListService.CreateArrivalList(requestJson);
//				
//				//从区块链获取的jsonObject
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
////		      2.判断返回状态码
//				//获取error信息
//				
//				if(jsonObject.get("error") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
//				}else {
//					
//					JSONArray errorArray=jsonObject.getJSONArray("error");
//					
//					//判断errorArray.size是否为0  2019-1-2 refine
//					if(errorArray.size()==0) {
//						throw new MyException(71,"error数组长度为0");
//					}
//					JSONObject errorMap=errorArray.getJSONObject(0);
//
//					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//					
//				}
//				//获取content信息(contentMap)
////				if(jsonObject.get("data") instanceof JSONNull) {
//					
//					responseObject.put("data", new JSONObject());
//					
//
//
//				logger.info("返回给前端的json数据:"+responseObject.toString());	
//				return responseObject;
		
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(receiveListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(receiveListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+receiveListJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					receiveListJson.put("packingType", "arrivalList");
					receiveListJson.put("dataType", "PackingList");
					receiveListJson.put("id", receiveListJson.optString("arriveno"));
				
					
       PackingList packList=(PackingList)JSONObject.toBean(receiveListJson,PackingList.class);
       
       responseObject=packingListService.CreatePackingList(packList);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryReceiveList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryReceiveList(@RequestBody JSONObject receiveListJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(receiveListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(receiveListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+receiveListJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				
				
				requestJson.put("LeaveDate", receiveListJson.optString("reachedDate"));
				requestJson.put("ArrivalListId", receiveListJson.optString("arriveno"));
				requestJson.put("UserOrgId", receiveListJson.optString("organizationId"));
				requestJson.put("PlateNumber", receiveListJson.optString("plateNo"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/PackingList/QueryArrivalList", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				resultJson=packingListService.QueryArrivalList(requestJson);
				
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("error") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("error");
					
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
					
					JSONArray packlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					if(contentArray.size()==0) {
		            	logger.info("data数组长度为0");
		            	throw new MyException(72,"data数组长度为0");
		            }
					for(int i=0;i<contentArray.size();i++) {
						JSONObject packmap=contentArray.getJSONObject(i);
						
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!packmap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=packmap.getString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						
						JSONObject packObject=JSONObject.fromObject(contentStr);
						packlist.add(packObject);
					}
                    JSONObject packlistObject=new JSONObject();
                    packlistObject.put("arrivallist", packlist);
					responseObject.put("data", packlistObject);

				}

				logger.info("返回给前端的json数据:"+responseObject.toString());	
				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/WeightCompare", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject WeightCompare(@RequestBody JSONObject compareJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(compareJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(compareJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+compareJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				requestJson.put("ArriveDate", compareJson.optString("reachedDate"));
//				requestJson.put("LeaveDate", compareJson.getString("leavedDate"));
				requestJson.put("PlateNumber", compareJson.optString("plateNo"));
				requestJson.put("UserOrgId", compareJson.optString("organizationId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/PackingList/CompareInOutFactory", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				resultJson=packingListService.CompareInOutFactory(requestJson);
				
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
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
				
				
				
				JSONArray compareinfo=new JSONArray();
				JSONObject finalDataObject=new JSONObject();
//				JSONObject compareInfo=new JSONObject();
//				JSONObject leavequalityinfo=new JSONObject();
//				JSONObject arrivequalityinfo=new JSONObject();
//				JSONObject diffinfo=new JSONObject();
				JSONArray dataArray=jsonObject.getJSONArray("data");
				
				if(dataArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
					//异常处理 refine 返回结果为奇数时异常处理
					for(int i=0;i<dataArray.size();i+=2) {
						JSONObject compareObject=new JSONObject();
						JSONObject dataObject=dataArray.getJSONObject(i);
						JSONObject dataObject2=dataArray.getJSONObject(i+1);
						System.out.println("1111"+dataObject2.toString());
						System.out.println("+++++"+dataObject.toString());
						String contentStr=dataObject.getString("content");
						String contentStr2=dataObject2.getString("content");
//						String[] QualityStrArray = contentStr.split("!@#");
//						System.out.println("-------"+QualityStrArray[0]);
//						System.out.println(QualityStrArray.length);
						//leaveObject装箱单
						JSONObject leaveObject=JSONObject.fromObject(contentStr);
						compareObject.put("plateNo", leaveObject.getString("plateNo"));
						compareObject.put("leavedDate", leaveObject.getString("leavedDate"));
						compareObject.put("leavefirstweight", leaveObject.getString("leavefirstweight"));
						compareObject.put("leavesecondweight", leaveObject.getString("leavesecondweight"));
						compareObject.put("leaveweight", leaveObject.getString("weight"));
						JSONObject arriveObject=JSONObject.fromObject(contentStr2);
						compareObject.put("plateNo", arriveObject.getString("plateNo"));
						compareObject.put("reachedDate", arriveObject.getString("reachedDate"));
						compareObject.put("arrivefirstweight", arriveObject.getString("leavefirstweight"));
						compareObject.put("arrivesecondweight", arriveObject.getString("leavesecondweight"));
						compareObject.put("arriveweight", arriveObject.getString("weight"));
						
						compareinfo.add(compareObject);
		
					}
					
					finalDataObject.put("weightcompareinfo", compareinfo);
				responseObject.put("data", finalDataObject);
				
			}

			return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryPackingListByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPackingListByObject(@RequestBody JSONObject packinglistJson) {
		
		if(packinglistJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(packinglistJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+packinglistJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		PackingList packinglistForQuery=(PackingList)JSONObject.toBean(packinglistJson, PackingList.class);
		
		
		List <PackingList> packinglistList = packingListService.SelectPackingListByObject(packinglistForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(packinglistList==null||packinglistList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (PackingList packinglist : packinglistList) {
				
				dataArray.add(JsonUtil.fromObject(packinglist));
				
			}
			dataObject.put("packinglistList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryPackingListListByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPackingListListByObject(@RequestBody JSONObject packinglistJson) {
		
		if(packinglistJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(packinglistJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+packinglistJson.toString());
		
		JSONObject responseObject=new JSONObject();
		Map<String,String> mapForQuery=new HashMap<String,String>();
		
		mapForQuery.put("buyerId", packinglistJson.optString("orgId"));
		mapForQuery.put("sellerId", packinglistJson.optString("orgId"));
		if(packinglistJson.optString("packingType").equals("packingList")) {
			mapForQuery.put("packingType", "packingList");
			if(("").equals(packinglistJson.optString("leavedDateStart"))) {
				mapForQuery.put("leavedDateStart", "0000-00-00");
			}else {
				mapForQuery.put("leavedDateStart", packinglistJson.optString("leavedDateStart"));
			}
			if(("").equals(packinglistJson.optString("leavedDateEnd"))) {
				mapForQuery.put("leavedDateEnd", "9999-12-31");
			}else {
				mapForQuery.put("leavedDateEnd", packinglistJson.optString("leavedDateEnd"));
			}
		}else if(packinglistJson.optString("packingType").equals("arrivalList")) {
			mapForQuery.put("packingType", "arrivalList");
			if(("").equals(packinglistJson.optString("reachedDateStart"))) {
				mapForQuery.put("reachedDateStart", "0000-00-00");
			}else {
				mapForQuery.put("reachedDateStart", packinglistJson.optString("reachedDateStart"));
			}
			if(("").equals(packinglistJson.optString("reachedDateEnd"))) {
				mapForQuery.put("reachedDateEnd", "9999-12-31");
			}else {
				mapForQuery.put("reachedDateEnd", packinglistJson.optString("reachedDateEnd"));
			}
		}
		
		
//		poJson.put("materialdetail", materialdetail);
		mapForQuery.put("productName", packinglistJson.optString("productName"));
//		mapForQuery.put("poType", poJson.optString("poType"));
//		PO poForQuery=(PO)JSONObject.toBean(poJson, PO.class);
//		poForQuery.setOrganizationId("");
//		poForQuery.setOrganizationName("");
		
		List <PackingList> packingListList = packingListService.SelectPackingListByObjectIn(mapForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(packingListList==null||packingListList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (PackingList packingList : packingListList) {
				
				dataArray.add(JsonUtil.fromObject(packingList));
				
			}
			dataObject.put("packingListList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	

}
