package com.enn.commodity.synergistic.controller;

import java.util.HashMap;
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
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.QualityInfo;
import com.enn.commodity.synergistic.service.PackingListService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/QualityList")
@CrossOrigin
public class QualityListController {
	
    private static Logger logger = Logger.getLogger(BillOfLadingController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private PackingListService packingListService;
	
	@ResponseBody
	@RequestMapping(value = "/CreateQualityListOfOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreatePackingList(@RequestBody JSONObject qualityListJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(qualityListJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(qualityListJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+qualityListJson.toString());
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				//组装json
//				qualityListJson.put("qualityType", "outQuality");
//				qualityListJson.put("dataType", "Quality");
//				qualityListJson.put("id", qualityListJson.optString("reportno"));
//				String quality="mi:"+qualityListJson.optString("mi")+";"
//						       +"aad:"+qualityListJson.optString("aad")+";"
//						       +"vad:"+qualityListJson.optString("vad")+";"
//						       +"fcad:"+qualityListJson.optString("fcad")+";"
//						       +"si:"+qualityListJson.optString("si")+";"
//						       +"had:"+qualityListJson.optString("had")+";"
//						       +"coalt1:"+qualityListJson.optString("coalt1")+";"
//						       +"coalt2:"+qualityListJson.optString("coalt2")+";"
//						       +"coalt3:"+qualityListJson.optString("coalt3")+";"
//						       +"coalt4:"+qualityListJson.optString("coalt4")+";"
//						       +"calorificqbad:"+qualityListJson.optString("calorificqbad")+";"
//						       +"calorificvalue:"+qualityListJson.optString("calorificvalue")+";";
//				qualityListJson.put("quality", quality);
//				requestJson.put("QualityInfo", qualityListJson.toString());
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/PackingList/CreateQualityInfo", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				resultJson=packingListService.CreateQualityInfo(requestJson);
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
		if(qualityListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(qualityListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+qualityListJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					qualityListJson.put("qualityType", "outQuality");
					qualityListJson.put("dataType", "Quality");
					qualityListJson.put("id", qualityListJson.optString("reportno"));
					String quality="mi:"+qualityListJson.optString("mi")+";"
						       +"aad:"+qualityListJson.optString("aad")+";"
						       +"vad:"+qualityListJson.optString("vad")+";"
						       +"fcad:"+qualityListJson.optString("fcad")+";"
						       +"si:"+qualityListJson.optString("si")+";"
						       +"had:"+qualityListJson.optString("had")+";"
						       +"coalt1:"+qualityListJson.optString("coalt1")+";"
						       +"coalt2:"+qualityListJson.optString("coalt2")+";"
						       +"coalt3:"+qualityListJson.optString("coalt3")+";"
						       +"coalt4:"+qualityListJson.optString("coalt4")+";"
						       +"calorificqbad:"+qualityListJson.optString("calorificqbad")+";"
						       +"calorificvalue:"+qualityListJson.optString("calorificvalue")+";";
				qualityListJson.put("quality", quality);
				
					
       QualityInfo qualityInfo=(QualityInfo)JSONObject.toBean(qualityListJson,QualityInfo.class);
       
       responseObject=packingListService.CreateQualityInfo(qualityInfo);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreateQualityListOfEnter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateQualityListOfEnter(@RequestBody JSONObject qualityListJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(qualityListJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(qualityListJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+qualityListJson.toString());
//		
//		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				//组装json
//				qualityListJson.put("qualityType", "enterQuality");
//				qualityListJson.put("dataType", "Quality");
//				qualityListJson.put("id", qualityListJson.optString("reportno"));
//				String quality="mi:"+qualityListJson.optString("mi")+";"
//						       +"aad:"+qualityListJson.optString("aad")+";"
//						       +"vad:"+qualityListJson.optString("vad")+";"
//						       +"fcad:"+qualityListJson.optString("fcad")+";"
//						       +"si:"+qualityListJson.optString("si")+";"
//						       +"had:"+qualityListJson.optString("had")+";"
//						       +"coalt1:"+qualityListJson.optString("coalt1")+";"
//						       +"coalt2:"+qualityListJson.optString("coalt2")+";"
//						       +"coalt3:"+qualityListJson.optString("coalt3")+";"
//						       +"coalt4:"+qualityListJson.optString("coalt4")+";"
//						       +"calorificqbad:"+qualityListJson.optString("calorificqbad")+";"
//						       +"calorificvalue:"+qualityListJson.optString("calorificvalue")+";";
//				qualityListJson.put("quality", quality);
//				qualityListJson.put("source", qualityListJson.optString("orgid"));
//				qualityListJson.put("reportQualityDate", qualityListJson.optString("qualityDate"));
//				requestJson.put("QualityInfo", qualityListJson.toString());
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/PackingList/CreateQualityInfo", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//				
//				resultJson=packingListService.CreateQualityInfo(requestJson);
//				
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
		if(qualityListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(qualityListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+qualityListJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					qualityListJson.put("qualityType", "enterQuality");
					qualityListJson.put("dataType", "Quality");
					qualityListJson.put("id", qualityListJson.optString("reportno"));
					String quality="mi:"+qualityListJson.optString("mi")+";"
						       +"aad:"+qualityListJson.optString("aad")+";"
						       +"vad:"+qualityListJson.optString("vad")+";"
						       +"fcad:"+qualityListJson.optString("fcad")+";"
						       +"si:"+qualityListJson.optString("si")+";"
						       +"had:"+qualityListJson.optString("had")+";"
						       +"coalt1:"+qualityListJson.optString("coalt1")+";"
						       +"coalt2:"+qualityListJson.optString("coalt2")+";"
						       +"coalt3:"+qualityListJson.optString("coalt3")+";"
						       +"coalt4:"+qualityListJson.optString("coalt4")+";"
						       +"calorificqbad:"+qualityListJson.optString("calorificqbad")+";"
						       +"calorificvalue:"+qualityListJson.optString("calorificvalue")+";";
				qualityListJson.put("quality", quality);
				
					
       QualityInfo qualityInfo=(QualityInfo)JSONObject.toBean(qualityListJson,QualityInfo.class);
       
       responseObject=packingListService.CreateQualityInfo(qualityInfo);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QualityListCompare", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QualityListCompare(@RequestBody JSONObject qualityListJson) {
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				requestJson.put("PlateNumber", qualityListJson.getString("plateNo"));
				requestJson.put("ArriveDate", qualityListJson.getString("reachedDate"));
				requestJson.put("PurchasingOrderNo", qualityListJson.getString("purchasingorderno"));
				requestJson.put("UserOrgId", qualityListJson.getString("organizationId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/PackingList/CompareQualityInfo", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				resultJson=packingListService.CompareQualityInfo(requestJson);
				
				//2.定义返回前台的json，用于组装
//		        { { [ { } ] } }
			//responseObject:方法最终返回的返回
			JSONObject responseObject=new JSONObject();
			//从区块链获取的jsonObject
			System.out.println(resultJson);
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			System.out.println(resultJson);
			
//	      2.判断返回状态码
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
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				
				
				JSONArray qualitycompareinfo=new JSONArray();
				JSONObject finalDataObject=new JSONObject();
				JSONObject compareInfo=new JSONObject();
				JSONObject leavequalityinfo=new JSONObject();
				JSONObject arrivequalityinfo=new JSONObject();
				JSONObject diffinfo=new JSONObject();
				JSONArray dataArray=jsonObject.getJSONArray("data");
				System.out.println("size:"+dataArray.size());
				System.out.println(dataArray.get(0).toString());
				System.out.println(dataArray.get(1).toString());
				System.out.println(dataArray.get(2).toString());
				String purchaseOrderStr=dataArray.getJSONObject(0).getString("content");
				
				compareInfo.put("plateNo", qualityListJson.getString("plateNo"));
				compareInfo.put("purchasingorderno", purchaseOrderStr);
				for(int h=1;h<dataArray.size();h+=2) {
					
					
					JSONObject leaveObject=JSONObject.fromObject(dataArray.getJSONObject(h+1).getString("content"));
					//出厂质检单quality字段
					String leaveQuality=leaveObject.getString("quality");
					System.out.println("leaveQuality"+leaveQuality);
					//入场
					JSONObject arriveObject=JSONObject.fromObject(dataArray.getJSONObject(h).getString("content"));
					String arriveQuality=arriveObject.getString("quality");
					leavequalityinfo=leaveObject;
					
					 String[] leaveQualityStrArray = leaveQuality.split(";");
					 
					 for (int i = 0; i < leaveQualityStrArray.length; i++) {
						 String[] leaveQualityArray=leaveQualityStrArray[i].split(":");
						 System.out.println("leaveQualityArray:"+leaveQualityArray);
						 leavequalityinfo.put(leaveQualityArray[0], leaveQualityArray[1]);
				        }
					 compareInfo.put("leavequalityinfo", leavequalityinfo);
					 //
					 arrivequalityinfo=arriveObject;
					 String[] arriveQualityStrArray = arriveQuality.split(";");
					 for (int i = 0; i < arriveQualityStrArray.length; i++) {
						 String[] arriveQualityArray=arriveQualityStrArray[i].split(":");
						 arrivequalityinfo.put(arriveQualityArray[0], arriveQualityArray[1]);
				        }
					 compareInfo.put("arrivequalityinfo", arrivequalityinfo);
					 
					 diffinfo=leaveObject;
					 for (int i = 0; i < leaveQualityStrArray.length; i++) {
						 String[] leaveQualityArray=leaveQualityStrArray[i].split(":");
						 diffinfo.put(leaveQualityArray[0],(Integer.parseInt(arrivequalityinfo.getString(leaveQualityArray[0]))-Integer.parseInt(leavequalityinfo.getString(leaveQualityArray[0]))));
				        }
					 compareInfo.put("diffinfo", diffinfo);
					 qualitycompareinfo.add(compareInfo);
					 finalDataObject.put("qualitycompareinfo", qualitycompareinfo);
					
				}
//				if(dataArray.getJSONObject(1).getString("content").contains("!@#")) {
//					//只有两条对应质检单
//		            //获取采购订单号
//					String purchaseOrderStr=dataArray.getJSONObject(0).getString("content");
//					
//					compareInfo.put("plateNo", qualityListJson.getString("plateNo"));
//					compareInfo.put("purchasingorderno", purchaseOrderStr);
//					//出厂
//					JSONObject leaveObject=JSONObject.fromObject(dataArray.getJSONObject(2).getString("content"));
//					//出厂质检单quality字段
//					String leaveQuality=leaveObject.getString("quality");
//					//入场
//					JSONObject arriveObject=JSONObject.fromObject(dataArray.getJSONObject(1).getString("content"));
//					String arriveQuality=arriveObject.getString("quality");
//					leavequalityinfo=leaveObject;
//					
//					 String[] leaveQualityStrArray = leaveQuality.split(";");
//					 
//					 for (int i = 0; i < leaveQualityStrArray.length; i++) {
//						 String[] leaveQualityArray=leaveQualityStrArray[i].split(":");
//						 leavequalityinfo.put(leaveQualityArray[0], leaveQualityArray[1]);
//				        }
//					 compareInfo.put("leavequalityinfo", leavequalityinfo);
//					 //
//					 arrivequalityinfo=arriveObject;
//					 String[] arriveQualityStrArray = arriveQuality.split(";");
//					 for (int i = 0; i < arriveQualityStrArray.length; i++) {
//						 String[] arriveQualityArray=arriveQualityStrArray[i].split(":");
//						 arrivequalityinfo.put(arriveQualityArray[0], arriveQualityArray[1]);
//				        }
//					 compareInfo.put("arrivequalityinfo", arrivequalityinfo);
//					 
//					 diffinfo=leaveObject;
//					 for (int i = 0; i < leaveQualityStrArray.length; i++) {
//						 String[] leaveQualityArray=leaveQualityStrArray[i].split(":");
//						 diffinfo.put(leaveQualityArray[0],(Integer.parseInt(leavequalityinfo.getString(leaveQualityArray[0]))-Integer.parseInt(arrivequalityinfo.getString(leaveQualityArray[0]))));
//				        }
//					 compareInfo.put("diffinfo", diffinfo);
//					 qualitycompareinfo.add(compareInfo);
//					 finalDataObject.put("qualitycompareinfo", qualitycompareinfo);
//				}else {
//					
//					for(int i=1;i<dataArray.size();i++) {
//						JSONObject dataObject=dataArray.getJSONObject(i);
//						String contentStr=dataObject.getString("content");
//						String[] QualityStrArray = contentStr.split("!@#");
//						JSONObject leaveObject=JSONObject.fromObject(QualityStrArray[0]);
//						String qualityStr=leaveObject.getString("quality");
//						 String[] leaveQualityStrArray = qualityStr.split(";");
//						 for (int j = 0; j < leaveQualityStrArray.length; i++) {
//							 String[] leaveQualityArray=leaveQualityStrArray[i].split(":");
//							 leaveObject.put(leaveQualityArray[0], leaveQualityArray[1]);
//					        }
//						 compareInfo.put("leavequalityinfo", leaveObject);
//						 
//						 JSONObject arriveObject=JSONObject.fromObject(QualityStrArray[1]);
//						 arrivequalityinfo=arriveObject;
//							String qualityStrOfArrive=arriveObject.getString("quality");
//							 String[] arriveQualityStrArray = qualityStrOfArrive.split(";");
//							 for (int j = 0; j < leaveQualityStrArray.length; i++) {
//								 String[] arriveQualityArray=arriveQualityStrArray[i].split(":");
//								 leaveObject.put(arriveQualityArray[0], arriveQualityArray[1]);
//						        }
//							 compareInfo.put("arrivequalityinfo", arriveObject);
//							 
//							 
//							 diffinfo=arrivequalityinfo;
//							 for (int h = 0; h < leaveQualityStrArray.length; h++) {
//								 String[] leaveQualityArray=leaveQualityStrArray[h].split(":");
//								 diffinfo.put(leaveQualityArray[0],(Integer.parseInt(leaveObject.getString(leaveQualityArray[0]))-Integer.parseInt(arriveObject.getString(leaveQualityArray[0]))));
//						        }
//							 compareInfo.put("diffinfo", diffinfo);
//							 qualitycompareinfo.add(compareInfo);
//					}
//					finalDataObject.put("qualitycompareinfo", qualitycompareinfo);
//					
//				}
				responseObject.put("data", finalDataObject);
				
			}

			return responseObject;
		
	}
	

}
