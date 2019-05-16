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
import com.enn.commodity.synergistic.entity.Queuing;
import com.enn.commodity.synergistic.service.QueuingService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/PeripheralSystem")
@CrossOrigin
public class PeripheralSystemController {
	
	public static Logger logger = Logger.getLogger(ArrangementController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	@Autowired
	private JsonUtil jsonUtil;
	@Autowired
	private QueuingService queuingService;
	
	@ResponseBody
	@RequestMapping(value = "/CreateQueuingInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateQueuingInfo(@RequestBody JSONObject QueuingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(QueuingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(QueuingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+QueuingJson.toString());
		

		Queuing reqQueuing=new Queuing();
		reqQueuing.setListcount(QueuingJson.optInt("listcount"));
		reqQueuing.setOrgid(QueuingJson.optString("orgid"));
		reqQueuing.setDataSourceId(QueuingJson.optString("dataSourceId"));
		reqQueuing.setDataSourceName(QueuingJson.optString("dataSourceName"));
		reqQueuing.setGoodsType(QueuingJson.optString("goodsType"));
		
//		queuingService.SaveQueuingInfo(reqQueuing);
		//查询数据库中是否有当前企业的排队信息，有则修改操作
		
		Queuing resQueuing=queuingService.QueryQueuingInfo(QueuingJson.optString("orgid"));
//		System.out.println(resQueuing.getListcount());
		if(resQueuing!=null) {
			int result=queuingService.ModifyQueuingInfo(reqQueuing);
			if(result==0) {
				responseObject.put("errno", 27);
				responseObject.put("error", "更新排队信息失败");
				responseObject.put("data", new JSONObject());
			}else {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}
		}else {
		int result = queuingService.SaveQueuingInfo(reqQueuing);
		
		if(result==0) {
			responseObject.put("errno", 26);
			responseObject.put("error", "录入排队信息失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		}
		
		return responseObject;
		//定义访问区块链接口所需要的json
//				JSONObject requestJson=new JSONObject();
//				//组装上链json
//				requestJson.put("plateNo", QueuingJson.getString("plateNo"));
//				requestJson.put("organizationId", QueuingJson.getString("organizationId"));
//				
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost("http://10.39.28.20:8080/commodity/service/BillOfLading/SelectBillOfLading", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				//2.定义返回前台的json，用于组装
////			        { { [ { } ] } }
//				//responseObject:方法最终返回的返回
//				JSONObject responseObject=new JSONObject();
//				//从区块链获取的jsonObject
////				System.out.println(resultJson);
////				String resultJson="{\"listcount\":\"17\"}";
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				System.out.println(resultJson);
//				
////		      2.判断返回状态码
//				//获取error信息
//				
////				if(jsonObject.get("error") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
////				}else {
////					
////					JSONArray errorArray=jsonObject.getJSONArray("error");
////					JSONObject errorMap=errorArray.getJSONObject(0);
////
////					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//					
////				}
//				//获取content信息(contentMap)
////				if(jsonObject.get("data") instanceof JSONNull) {
//					
////					responseObject.put("data", new HashMap());
////					
////				}else {
////					
////					JSONArray billlist=new JSONArray();
////					JSONArray contentArray=jsonObject.getJSONArray("data");
////					for(int i=0;i<contentArray.size();i++) {
////						JSONObject billmap=contentArray.getJSONObject(i);
////						JSONObject billObject=JSONObject.fromObject(billmap.getString("content"));
////						billlist.add(billObject);
////					}
////                    JSONObject billOfLadingObject=new JSONObject();
////                    billOfLadingObject.put("billinfolist", billlist);
//					responseObject.put("data", jsonObject);
					
				
					//转换
					
//					
					
					
					
//				}

//				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryQueuingInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryQueuingInfo(@RequestBody JSONObject QueuingJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(QueuingJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(QueuingJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+QueuingJson.toString());
		
		
		JSONObject countObject=new JSONObject();
		Queuing resQueuing=queuingService.QueryQueuingInfo(QueuingJson.optString("orgid"));
		
		if(resQueuing!=null) {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			countObject.put("listcount", String.valueOf(resQueuing.getListcount()));
			countObject.put("orgid", resQueuing.getOrgid());
			countObject.put("dataSourceId", resQueuing.getDataSourceId());
			countObject.put("dataSourceName", resQueuing.getDataSourceName());
			countObject.put("goodsType", resQueuing.getGoodsType());
			responseObject.put("data", countObject);
		}else {
			responseObject.put("errno", 27);
			responseObject.put("error", "查询排队信息失败");
			responseObject.put("data", new JSONObject());
		}
		//定义访问区块链接口所需要的json
//		JSONObject requestJson=new JSONObject();
//		//组装上链json
//		requestJson.put("", QueuingJson.getString("plateNo"));
//		requestJson.put("", QueuingJson.getString("organizationId"));
//		
//		//result:区块链接口返回的json字符串
//		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost("http://10.39.28.20:8080/commodity/service/BillOfLading/SelectBillOfLading", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//2.定义返回前台的json，用于组装
////	        { { [ { } ] } }
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		JSONObject jsonObject=new JSONObject();
//		//从区块链获取的jsonObject
////		System.out.println(resultJson);
////		String resultJson="{\"listcount\":\"17\"}";
//		if(jsonUtil.isJson(resultJson)) {
//		    jsonObject=JSONObject.fromObject(resultJson);
//		}else {
//			responseObject.put("errno",23);
//			responseObject.put("error", "区块链返回数据格式不符合预期要求");
//			responseObject.put("data", new JSONObject());
//			return responseObject;
//		}
//		System.out.println(resultJson);
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
//			
//		}
////		//获取content信息(contentMap)
//		if(jsonObject.get("data") instanceof JSONNull) {
//			
//			responseObject.put("data", new JSONObject());
////			
//		}else {
//			JSONObject queuinginfo=new JSONObject();
//			JSONArray queuinglist=new JSONArray();
//			JSONArray contentArray=jsonObject.getJSONArray("data");
//			for(int i=0;i<contentArray.size();i++) {
//				JSONObject queuingmap=contentArray.getJSONObject(i);
//				JSONObject queuingObject=JSONObject.fromObject(queuingmap.getString("content"));
//				queuinglist.add(queuingObject);
//			}
//			queuinginfo.put("queuinginfo", queuinglist);
//			responseObject.put("data", queuinginfo);
//			
//		
//			//转换
//			
////			
//			
//			
//			
//		}

		return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreateWarningInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateWarningInfo(@RequestBody JSONObject warningJson) {
		
			//responseObject:方法最终返回的返回
			JSONObject responseObject=new JSONObject();
			//判断前端传的json是否为空
			if(warningJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(warningJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+warningJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装上链json
				warningJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
				warningJson.put("dataType", "Monitor");
				requestJson.put("Monitor", warningJson.toString());
				requestJson.put("PlateNumber", warningJson.optString("plateNo"));
				requestJson.put("Source", warningJson.optString("Source"));
//				requestJson.put("", warningJson.getString("organizationId"));
				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				try {
					resultJson = new RestUtil().httpPost(path+"/Monitor/CreateMonitor", requestJson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				
				JSONObject jsonObject=new JSONObject();
				//从区块链获取的jsonObject
//				System.out.println(resultJson);
//				String resultJson="{\"listcount\":\"17\"}";
				if(jsonUtil.isJson(resultJson)) {
				    jsonObject=JSONObject.fromObject(resultJson);
				}else {
					responseObject.put("errno",23);
					responseObject.put("error", "区块链返回数据格式不符合预期要求");
					responseObject.put("data", new JSONObject());
					return responseObject;
				}
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("error") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("error");
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					
				}
//				//获取content信息(contentMap)
//				if(jsonObject.get("data") instanceof JSONNull) {
					
					responseObject.put("data", new JSONObject());
//					
//				}else {
//					JSONObject warnninginfo=new JSONObject();
//					JSONArray warnlist=new JSONArray();
//					JSONArray contentArray=jsonObject.getJSONArray("data");
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject warnmap=contentArray.getJSONObject(i);
//						JSONObject warnObject=JSONObject.fromObject(warnmap.getString("content"));
//						warnlist.add(warnObject);
//					}
//					warnninginfo.put("warnninginfo", warnlist);
//					responseObject.put("data", warnninginfo);
					
				
					//转换
					
//					
					
					
					
//				}

				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryWarningInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryWarningInfo(@RequestBody JSONObject warningJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(warningJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(warningJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+warningJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装上链json
				
				requestJson.put("Source", warningJson.optString("Source"));
				requestJson.put("UserOrgId", warningJson.optString("organizationId"));
				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				try {
					resultJson = new RestUtil().httpPost(path+"/Monitor/QueryMonitor", requestJson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				JSONObject jsonObject=new JSONObject();
				//从区块链获取的jsonObject
//				System.out.println(resultJson);
//				String resultJson="{\"listcount\":\"17\"}";
				if(jsonUtil.isJson(resultJson)) {
				    jsonObject=JSONObject.fromObject(resultJson);
				}else {
					responseObject.put("errno",23);
					responseObject.put("error", "区块链返回数据格式不符合预期要求");
					responseObject.put("data", new JSONObject());
					return responseObject;
				}
				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("error") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("error");
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					
				}
//				//获取content信息(contentMap)
				if(jsonObject.get("data") instanceof JSONNull) {
					
					responseObject.put("data", new JSONObject());
//					
				}else {
					JSONObject warninginfo=new JSONObject();
					JSONArray warnlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					for(int i=0;i<contentArray.size();i++) {
						JSONObject warnmap=contentArray.getJSONObject(i);
						JSONObject warnObject=JSONObject.fromObject(warnmap.optString("content"));
						warnObject.put("warnningtime", warnObject.optString("issueDate"));
						warnObject.put("warnningtype", warnObject.optString("type"));
						warnObject.put("warnningcontent", warnObject.optString("content"));
						warnlist.add(warnObject);
					}
					warninginfo.put("warnninginfo", warnlist);
					responseObject.put("data", warninginfo);
					
				
					//转换
					
//					
					
					
					
				}

				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryArriveInfo ", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryArriveInfo (@RequestBody JSONObject arriveJson) {
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装上链json
				
				
				requestJson.put("UserOrgId", arriveJson.getString("organizationId"));
				
				//判断当前用户所属组织，上游or下游or运输企业
				
				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				try {
					resultJson = new RestUtil().httpPost(path+"/Monitor/QueryMonitor", requestJson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				//responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
				JSONObject jsonObject=new JSONObject();
				//从区块链获取的jsonObject
//				System.out.println(resultJson);
//				String resultJson="{\"listcount\":\"17\"}";
				if(jsonUtil.isJson(resultJson)) {
				    jsonObject=JSONObject.fromObject(resultJson);
				}else {
					responseObject.put("errno",23);
					responseObject.put("error", "区块链返回数据格式不符合预期要求");
					responseObject.put("data", new JSONObject());
					return responseObject;
				}
				System.out.println(resultJson);
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("error") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("error");
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					
				}
//				//获取content信息(contentMap)
				if(jsonObject.get("data") instanceof JSONNull) {
					
					responseObject.put("data", new JSONObject());
//					
				}else {
					JSONObject arriveinfo=new JSONObject();
//					JSONArray warnlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					JSONObject arriveObject=contentArray.getJSONObject(0);
					//在途车辆数
					String arriveCount=arriveObject.getString("content");
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject warnmap=contentArray.getJSONObject(i);
//						JSONObject warnObject=JSONObject.fromObject(warnmap.getString("content"));
//						warnObject.put("warnningtime", warnObject.getString("issueDate"));
//						warnObject.put("warnningtype", warnObject.getString("type"));
//						warnObject.put("warnningcontent", warnObject.getString("content"));
//						warnlist.add(warnObject);
//					}
					arriveinfo.put("arrivenumber", arriveCount);
					responseObject.put("data", arriveinfo);
					
				
					//转换
					
//					
					
					
					
				}

				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/GetInfoFromHDD", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject GetInfoFromHDD(@RequestBody JSONObject infoJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(infoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(infoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+infoJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装上链json
				infoJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
				infoJson.put("dataType", "");
				
				requestJson.put("info", infoJson.toString());

				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				try {
					resultJson = new RestUtil().httpPost(path+"/Monitor/CreateMonitor", requestJson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				
				JSONObject jsonObject=new JSONObject();
				//从区块链获取的jsonObject
//				System.out.println(resultJson);
//				String resultJson="{\"listcount\":\"17\"}";
				if(jsonUtil.isJson(resultJson)) {
				    jsonObject=JSONObject.fromObject(resultJson);
				    logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
				}else {
					responseObject.put("errno",23);
					responseObject.put("error", "区块链返回数据格式不符合预期要求");
					responseObject.put("data", new JSONObject());
					return responseObject;
				}
				
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("error") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("error");
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					
				}
//				//获取content信息(contentMap)
//				if(jsonObject.get("data") instanceof JSONNull) {
					
				responseObject.put("data", new JSONObject());

				return responseObject;
		
	}

}
