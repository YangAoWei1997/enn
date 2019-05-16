package com.enn.commodity.synergistic.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.enn.commodity.synergistic.entity.MaterialDetail;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.NodeOrg;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.Payment;
import com.enn.commodity.synergistic.service.BillOfLadingService;
import com.enn.commodity.synergistic.service.PurchaseOrderService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.ExcelUtil;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;
import com.enn.commodity.synergistic.util.UserLoginToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/PurchaseOrder")
@CrossOrigin
public class PurchaseOrderController {
	
	private static Logger logger = Logger.getLogger(PurchaseOrderController.class);
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ExcelUtil excelUtil;
	
	@Autowired
	private BillOfLadingService billOfLadingService;
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * 
	* @Title: CreatePurchaseOrder  
	* @Description: TODO
	* @param @param PurchaseOrderJson
	* @param @return
	* @return String
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/CreateSalesOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateSalesOrder(@RequestBody JSONObject salesOrderJson) {
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(salesOrderJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(salesOrderJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+salesOrderJson.toString());
//		
//		
//		
//					
//					//定义访问区块链接口所需要的json
//					JSONObject requestJson=new JSONObject();
//					//组装json
//					salesOrderJson.put("type", "sales");
//					salesOrderJson.put("dataType", "PO");
//					salesOrderJson.put("purchasePONumber", salesOrderJson.optString("purchasingorderno"));
//					
////					salesOrderJson.put("organizationId", "org1");
//					JSONObject detailJson=new JSONObject();
//					detailJson.put("quantity", salesOrderJson.optString("quantity"));
//					detailJson.put("priceNoTax", salesOrderJson.optString("priceNoTax"));
//					detailJson.put("priceWithTax", salesOrderJson.optString("priceWithTax"));
//					detailJson.put("totalPriceNoTax", salesOrderJson.optString("totalPriceNoTax"));
//					detailJson.put("totalPriceWithTax", salesOrderJson.optString("totalPriceWithTax"));
//					salesOrderJson.put("materialDetail",detailJson);
//					requestJson.put("SalesOrder", salesOrderJson.toString());
//					//result:区块链接口返回的json字符串
//					String resultJson=null;
//					
//					resultJson = purchaseOrderService.CreateSalesOrder(requestJson);
//					
//					
//					//2.定义返回前台的json，用于组装
////			        { { [ { } ] } }
//				
//				//从区块链获取的jsonObject
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				//抓异常
////				System.out.println(resultJson);
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
////		      2.判断返回状态码
//				//获取error信息
//				
//				if(jsonObject.get("error") instanceof JSONNull) {
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
//					JSONObject errorMap=errorArray.getJSONObject(0);
//
//					responseObject=new ErrorHandler().errornoHandler(errorMap.optString("code"),responseObject);
//					responseObject.put("data", new JSONObject());
//					
//				}
//				
//				logger.info("返回给前端的json数据:"+responseObject.toString());
//				
//				return responseObject;
				
				
//		responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(salesOrderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(salesOrderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+salesOrderJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					salesOrderJson.put("poType", "sales");
					salesOrderJson.put("dataType", "PO");
					salesOrderJson.put("purchasePONumber", salesOrderJson.optString("purchasingorderno"));
				
					JSONObject materialdetail=new JSONObject();
					materialdetail.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
					materialdetail.put("totalPriceNoTax", salesOrderJson.optString("totalPriceNoTax"));
					materialdetail.put("totalPriceWithTax", salesOrderJson.optString("totalPriceWithTax"));
					materialdetail.put("priceNoTax", salesOrderJson.optString("priceNoTax"));
					materialdetail.put("priceWithTax", salesOrderJson.optString("priceWithTax"));
					materialdetail.put("quantity", salesOrderJson.optString("quantity"));
					salesOrderJson.put("materialdetail", materialdetail);
       PO po=(PO)JSONObject.toBean(salesOrderJson,PO.class);
       
       responseObject=purchaseOrderService.CreatePO(po);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
				
		
	}
	
	/**
	 * 
	* @Title: QueryPurchaseOrder  
	* @Description: TODO
	* @param @param purchaseOrderJson
	* @param @return
	* @return String
	* @throws
	 */
	
	@RequestMapping(value = "/QuerySalesOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QuerySalesOrder(@RequestBody JSONObject salesOrderJson) {


		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(salesOrderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(salesOrderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+salesOrderJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		
		
		requestJson.put("SalesOrderId", salesOrderJson.optString("id"));
		requestJson.put("UserOrgId", salesOrderJson.optString("organizationId"));
		//result:区块链接口返回的json字符串
//		System.out.println("requestJson:"+requestJson.toString());
		String resultJson=null;
		
		resultJson = purchaseOrderService.QuerySalesOrder(requestJson);
		
		
		
		//从区块链获取的jsonObject
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
		

		
//      2.判断返回状态码
		//获取error信息
		
		//错误处理编写成公共方法 1-2 refine
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
			
			JSONArray contentArray=jsonObject.getJSONArray("data");
            if(contentArray.size()==0) {
            	logger.info("data数组长度为0");
            	throw new MyException(72,"data数组长度为0");
            }
			
			JSONArray salesOrderList=new JSONArray();
			//判断contentArray.size是否为0 1-2 refine
			for(int i=0;i<contentArray.size();i++) {
				JSONObject dataMap=contentArray.getJSONObject(i);
				//判断dataMap.getString是否为空 1-2 refine 为""或 null
				if(!dataMap.has("content")) {
					logger.info("data中缺失content信息");
	            	throw new MyException(73,"data中缺失content信息");
				}
				String contentStr=dataMap.getString("content");
				//将json中的null转换为""
				contentStr=jsonUtil.fromObject(contentStr);
				if(!jsonUtil.isJson(contentStr)) {
					logger.info("格式不是json");
					throw new MyException(74,"格式不是json");
				}
				JSONObject contentObject=JSONObject.fromObject(contentStr);
				if(contentObject.getJSONObject("materialDetail")==null) {
					logger.info("未获取到预期的jsonObject信息");
					throw new MyException(75,"未获取到预期的jsonObject信息");
				}
				contentObject.put("totalPriceNoTax", contentObject.getJSONObject("materialDetail").optString("totalPriceNoTax"));
				contentObject.put("totalPriceWithTax", contentObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
				contentObject.put("priceNoTax", contentObject.getJSONObject("materialDetail").optString("priceNoTax"));
				contentObject.put("priceWithTax", contentObject.getJSONObject("materialDetail").optString("priceWithTax"));
				contentObject.put("quantity", contentObject.getJSONObject("materialDetail").optString("quantity"));
				
				

				
				salesOrderList.add(contentObject);
			}
			JSONObject dataObject=new JSONObject();
			dataObject.put("salesorderlist", salesOrderList);
			responseObject.put("data", dataObject);
		}

		logger.info("返回给前端的json数据:"+responseObject.toString());
		return responseObject;
		
	}
	
	@RequestMapping(value = "/QueryPurchaseOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPurchaseOrder (@RequestBody JSONObject purchaseOrderJson) {
		


		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(purchaseOrderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(purchaseOrderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info(purchaseOrderJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		//组装json
		logger.info("接收前端或外围系统的json数据:"+purchaseOrderJson.toString());
		requestJson.put("PurchaseOrderId", purchaseOrderJson.optString("purchasingorderno"));
		requestJson.put("UserOrgId",  purchaseOrderJson.optString("organizationId"));
		//result:区块链接口返回的json字符串
//		System.out.println("requestJson:"+requestJson.toString());
		String resultJson=null;
		
		resultJson = purchaseOrderService.QueryPurchaseOrder(requestJson);
		
		
		
		//从区块链获取的jsonObject
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		
		
//      2.判断返回状态码
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
			
			JSONArray contentArray=jsonObject.getJSONArray("data");
			 if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }

			//从区块链层获取的content对应的值是string类型，需要反序列化成jsonobject
//			JSONObject dataMap=contentArray.getJSONObject(0);
//			String contentStr=dataMap.getString("content");
//			JSONObject contentObject=JSONObject.fromObject(contentStr);
			//解析contentObject，并组装data
//			JSONObject map=new JSONObject();
//			map=contentObject;
			
			JSONArray purchaseOrderList=new JSONArray();
			for(int i=0;i<contentArray.size();i++) {
				JSONObject dataMap=contentArray.getJSONObject(i);
				if(!dataMap.has("content")) {
					logger.info("data中缺失content信息");
	            	throw new MyException(73,"data中缺失content信息");
				}
				String contentStr=dataMap.getString("content");
				//将json中的null转换为""
				contentStr=jsonUtil.fromObject(contentStr);
				if(!jsonUtil.isJson(contentStr)) {
					logger.info("格式不是json");
					throw new MyException(74,"格式不是json");
				}
				
				JSONObject contentObject=JSONObject.fromObject(contentStr);
				if(contentObject.getJSONObject("materialDetail")==null) {
					logger.info("未获取到预期的jsonObject信息");
					throw new MyException(75,"未获取到预期的jsonObject信息");
				}
//				
				contentObject.put("totalPriceNoTax", contentObject.getJSONObject("materialDetail").optString("totalPriceNoTax"));
				contentObject.put("totalPriceWithTax", contentObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
				contentObject.put("priceNoTax", contentObject.getJSONObject("materialDetail").optString("priceNoTax"));
				contentObject.put("priceWithTax", contentObject.getJSONObject("materialDetail").optString("priceWithTax"));
				contentObject.put("quantity", contentObject.getJSONObject("materialDetail").optString("quantity"));
				
			
			purchaseOrderList.add(contentObject);
		}
			JSONObject dataObject=new JSONObject();
			dataObject.put("purchaseorderlist", purchaseOrderList);
			responseObject.put("data", dataObject);
		}

		logger.info("返回给前端的json数据:"+responseObject.toString());
		return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreatePurchaseOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreatePurchaseOrder(@RequestBody JSONObject purchaseOrderJson) {
		
		
//		//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(purchaseOrderJson==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//		if(!jsonUtil.isJson(purchaseOrderJson.toString())) {
//			throw new MyException(81,"前端传值不是json格式");
//		}
//		logger.info("接收前端或外围系统的json数据:"+purchaseOrderJson.toString());
//		//定义访问区块链接口所需要的json
//		         
//				JSONObject requestJson=new JSONObject();
//				//组装json
//				purchaseOrderJson.put("type", "purchase");
//				purchaseOrderJson.put("dataType", "PO");
//				purchaseOrderJson.put("id", purchaseOrderJson.optString("purchaseorderno"));
//
//				JSONObject detailObject=new JSONObject();
//				detailObject.put("totalPriceNoTax", purchaseOrderJson.optString("totalPriceNoTax"));
//				detailObject.put("totalPriceWithTax", purchaseOrderJson.optString("totalPriceWithTax"));
//				detailObject.put("priceNoTax", purchaseOrderJson.optString("priceNoTax"));
//				detailObject.put("priceWithTax", purchaseOrderJson.optString("priceWithTax"));
//				detailObject.put("quantity", purchaseOrderJson.optString("quantity"));
//				purchaseOrderJson.put("materialDetail", detailObject);
//				requestJson.put("PurchaseOrder", purchaseOrderJson.toString());
//				//result:区块链接口返回的json字符串
//				String resultJson=null;
//				
//				resultJson = purchaseOrderService.CreatePurchaseOrder(requestJson);
//				
//				
//				//从区块链获取的jsonObject
//				JSONObject jsonObject=JSONObject.fromObject(resultJson);
//				
//				logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
//				
//				
////		      2.判断返回状态码
//				//获取error信息
//				
//				if(jsonObject.get("error") instanceof JSONNull) {
//					responseObject.put("errno", 0);
//					responseObject.put("error", "success");
//					responseObject.put("data", new JSONObject());
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
//					responseObject.put("data", new JSONObject());
//					
//				}
//				
//
//				logger.info("返回给前端的json数据:"+responseObject.toString());
//
//				return responseObject;
		
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(purchaseOrderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(purchaseOrderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+purchaseOrderJson.toString());
		
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					purchaseOrderJson.put("poType", "purchase");
					purchaseOrderJson.put("dataType", "PO");
					purchaseOrderJson.put("id", purchaseOrderJson.optString("purchaseorderno"));
					
//					MaterialDetail materialDetail=new MaterialDetail();
//					materialDetail.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
//					materialDetail.setTotalPriceNoTax(purchaseOrderJson.optString("totalPriceNoTax"));
//					materialDetail.setTotalPriceWithTax(purchaseOrderJson.optString("totalPriceWithTax"));
//					materialDetail.setPriceNoTax(purchaseOrderJson.optString("priceNoTax"));
//					materialDetail.setPriceWithTax(purchaseOrderJson.optString("priceWithTax"));
//					materialDetail.setQuantity(purchaseOrderJson.optString("quantity"));
					JSONObject materialdetail=new JSONObject();
					materialdetail.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
					materialdetail.put("totalPriceNoTax", purchaseOrderJson.optString("totalPriceNoTax"));
					materialdetail.put("totalPriceWithTax", purchaseOrderJson.optString("totalPriceWithTax"));
					materialdetail.put("priceNoTax", purchaseOrderJson.optString("priceNoTax"));
					materialdetail.put("priceWithTax", purchaseOrderJson.optString("priceWithTax"));
					materialdetail.put("quantity", purchaseOrderJson.optString("quantity"));
					purchaseOrderJson.put("materialdetail", materialdetail);
				
       PO po=(PO)JSONObject.toBean(purchaseOrderJson,PO.class);
       
       responseObject=purchaseOrderService.CreatePO(po);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
		
	}
	
	
	/**
	 * 
	* @Title: QueryPurchaseOrderList  
	* @Description: TODO
	* @param @param purchaseOrderListJson
	* @param @return
	* @return JSONObject
	* @throws
	 */
	
	
	@ResponseBody
	@RequestMapping(value = "/QueryPurchaseOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPurchaseOrderList(@RequestBody JSONObject purchaseOrderListJson) {
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				requestJson.put("UserId", purchaseOrderListJson.getString("userId"));
				requestJson.put("UserOrgId", purchaseOrderListJson.getString("organizationId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
				try {
					resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/GetPurchaseOrderNoInfo", requestJson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				//responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
				//从区块链获取的jsonObject
				System.out.println("----"+resultJson);
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
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
					JSONObject purchaseOrderObject=null;
					for(int i=0;i<contentArray.size();i++) {
						JSONObject packmap=contentArray.getJSONObject(i);
						JSONObject packObject=JSONObject.fromObject(packmap.getString("content"));
						purchaseOrderObject=new JSONObject();
						purchaseOrderObject.put("purchasingorderid", packObject.getString("organizationId")+"!@#"+ packObject.getString("id"));
						purchaseOrderObject.put("purchasingorderno", packObject.getString("id"));
						packlist.add(purchaseOrderObject);
					}
		            JSONObject packlistObject=new JSONObject();
		            packlistObject.put("productlist", packlist);
					responseObject.put("data", packlistObject);
					
				
					//转换
					
//					
					
					
					
				}

				return responseObject;
	}
	/**
	 * 
	* @Title: QueryPurchaseOrder  
	* @Description: TODO
	* @param @param salesOrderJson
	* @param @return
	* @return JSONObject
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/SalesOrderSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject SalesOrderSelect(@RequestBody JSONObject salesOrderJson) {
		
		        //responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
				//判断前端传的json是否为空
				if(salesOrderJson==null) {
					throw new MyException(80,"前端传值为null");
				}
				if(!jsonUtil.isJson(salesOrderJson.toString())) {
					throw new MyException(81,"前端传值不是json格式");
				}
				logger.info("接收前端或外围系统的json数据:"+salesOrderJson.toString());
		        //定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				
				requestJson.put("UserOrgId", salesOrderJson.optString("organizationId"));
				requestJson.put("BuyerId", salesOrderJson.optString("buyerId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
				
				resultJson=purchaseOrderService.SelectSalesOrder(requestJson);
				
				
				
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
					
//					JSONArray orderlist=new JSONArray();
//					JSONArray contentArray=jsonObject.getJSONArray("data");
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject ordermap=contentArray.getJSONObject(i);
//						JSONObject orderObject=JSONObject.fromObject(ordermap.getString("content"));
//						orderlist.add(orderObject);
//					}
//                    JSONObject salesOrderObject=new JSONObject();
//                    salesOrderObject.put("purchasingorderinfolist", orderlist);
//					responseObject.put("data", salesOrderObject);
					
					JSONArray orderlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
					
					JSONObject purchasingorderinfoobject=new JSONObject();
					for(int i=0;i<contentArray.size();i++) {
						JSONObject ordermap=contentArray.getJSONObject(i);
//						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!ordermap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=ordermap.optString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						JSONObject orderObject=JSONObject.fromObject(contentStr);
						orderObject.put("salesorderno", orderObject.optString("id"));
						
						orderObject.put("purchasingorderno", orderObject.optString("purchasePONumber"));
						
						
//						purchasingorderinfoobject.put("buyerId", orderObject.getString("buyerId"));
//						purchasingorderinfoobject.put("buyerName", orderObject.getString("buyerName"));
//						purchasingorderinfoobject.put("sellerId", orderObject.getString("sellerId"));
//						purchasingorderinfoobject.put("sellerName", orderObject.getString("sellerName"));
//						purchasingorderinfoobject.put("carrierId", orderObject.getString("carrierId"));
//						purchasingorderinfoobject.put("carrierName", orderObject.getString("carrierName"));
//						purchasingorderinfoobject.put("status", orderObject.getString("status"));
//						purchasingorderinfoobject.put("payType", orderObject.getString("payType"));
//						purchasingorderinfoobject.put("payTypeName", orderObject.getString("payTypeName"));
//						purchasingorderinfoobject.put("productId", orderObject.getString("productId"));
//						purchasingorderinfoobject.put("productName", orderObject.getString("productName"));
//						purchasingorderinfoobject.put("issuedDate", orderObject.getString("issuedDate"));
						if(orderObject.getJSONObject("materialDetail")!=null) {
							orderObject.put("quantity", orderObject.getJSONObject("materialDetail").optString("quantity"));
						}else {
							purchasingorderinfoobject.put("quantity", "");
						}
						orderlist.add(orderObject);
					}
                    JSONObject purchaseOrderObject=new JSONObject();
                    purchaseOrderObject.put("purchasingorderinfolist", orderlist);
					responseObject.put("data", purchaseOrderObject);

					
				}

				logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/PurchaseOrderSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject PurchaseOrderSelect(@RequestBody JSONObject purchaseOrderJson) {
		
		 //responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(purchaseOrderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(purchaseOrderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+purchaseOrderJson.toString());
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				
				requestJson.put("UserOrgId", purchaseOrderJson.optString("organizationId"));
				requestJson.put("BuyerId", purchaseOrderJson.optString("buyerId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
				
				resultJson=purchaseOrderService.SelectPurchaseOrderOfSaler(requestJson);
				
				
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
					
					JSONArray orderlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
					JSONObject purchasingorderinfoobject=new JSONObject();
					for(int i=0;i<contentArray.size();i++) {
						JSONObject ordermap=contentArray.getJSONObject(i);
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!ordermap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=ordermap.optString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						JSONObject orderObject=JSONObject.fromObject(contentStr);
						
						
						orderObject.put("purchasingorderno", orderObject.getString("id"));
//						purchasingorderinfoobject.put("buyerId", orderObject.getString("buyerId"));
//						purchasingorderinfoobject.put("buyerName", orderObject.getString("buyerName"));
//						purchasingorderinfoobject.put("sellerId", orderObject.getString("sellerId"));
//						purchasingorderinfoobject.put("sellerName", orderObject.getString("sellerName"));
//						purchasingorderinfoobject.put("payType", orderObject.getString("payType"));
//						purchasingorderinfoobject.put("payTypeName", orderObject.getString("payTypeName"));
//						purchasingorderinfoobject.put("carrierId", orderObject.getString("carrierId"));
//						purchasingorderinfoobject.put("carrierName", orderObject.getString("carrierName"));
//						purchasingorderinfoobject.put("arrangementId", orderObject.getString("arrangementId"));
//						purchasingorderinfoobject.put("status", orderObject.getString("status"));
//						purchasingorderinfoobject.put("productId", orderObject.getString("productId"));
//						purchasingorderinfoobject.put("productName", orderObject.getString("productName"));
						if(orderObject.getJSONObject("materialDetail")!=null) {
							orderObject.put("quantity", orderObject.getJSONObject("materialDetail").optString("quantity"));
							orderObject.put("totalPriceNoTax", orderObject.getJSONObject("materialDetail").optString("totalPriceNoTax"));
							orderObject.put("totalPriceWithTax", orderObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
							orderObject.put("priceNoTax", orderObject.getJSONObject("materialDetail").optString("priceNoTax"));
							orderObject.put("priceWithTax", orderObject.getJSONObject("materialDetail").optString("priceWithTax"));
						}else {
							orderObject.put("quantity", "");
							orderObject.put("totalPriceNoTax", "");
							orderObject.put("totalPriceWithTax", "");
							orderObject.put("priceNoTax", "");
							orderObject.put("priceWithTax", "");
						}
						
//						purchasingorderinfoobject.put("issuedDate", orderObject.getString("issuedDate"));
						orderlist.add(orderObject);
					}
                    JSONObject purchaseOrderObject=new JSONObject();
                    purchaseOrderObject.put("purchasingorderinfolist", orderlist);
					responseObject.put("data", purchaseOrderObject);
					
				
					//转换
					
//					
					
					
					
				}
				
				logger.info("返回给前端的json数据:"+responseObject.toString());

				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/PurchaseOrderSelectForBuyer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject PurchaseOrderSelectForBuyer (@RequestBody JSONObject purchaseOrderJson) {
		//responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
				//判断前端传的json是否为空
				if(purchaseOrderJson==null) {
					throw new MyException(80,"前端传值为null");
				}
				if(!jsonUtil.isJson(purchaseOrderJson.toString())) {
					throw new MyException(81,"前端传值不是json格式");
				}
				logger.info("接收前端或外围系统的json数据:"+purchaseOrderJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//组装json
				
				requestJson.put("UserOrgId", purchaseOrderJson.optString("organizationId"));
				requestJson.put("SellerId", purchaseOrderJson.optString("sellerId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
				
				resultJson=purchaseOrderService.SelectPurchaseOrderOfBuyer(requestJson);
				
				
				
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
					
					JSONArray orderlist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
//					JSONObject purchasingorderinfoobject=new JSONObject();
					for(int i=0;i<contentArray.size();i++) {
						JSONObject ordermap=contentArray.getJSONObject(i);
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!ordermap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						String contentStr=ordermap.optString("content");
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						JSONObject orderObject=JSONObject.fromObject(contentStr);
						orderObject.put("purchasingorderno", orderObject.optString("id"));
//						purchasingorderinfoobject.put("buyerId", orderObject.getString("buyerId"));
//						purchasingorderinfoobject.put("buyerName", orderObject.getString("buyerName"));
//						purchasingorderinfoobject.put("sellerId", orderObject.getString("sellerId"));
//						purchasingorderinfoobject.put("sellerName", orderObject.getString("sellerName"));
//						purchasingorderinfoobject.put("carrierId", orderObject.getString("carrierId"));
//						purchasingorderinfoobject.put("carrierName", orderObject.getString("carrierName"));
//						purchasingorderinfoobject.put("payType", orderObject.getString("payType"));
//						purchasingorderinfoobject.put("payTypeName", orderObject.getString("payTypeName"));
//						purchasingorderinfoobject.put("arrangementId", orderObject.getString("arrangementId"));
//						purchasingorderinfoobject.put("status", orderObject.getString("status"));
//						purchasingorderinfoobject.put("productId", orderObject.getString("productId"));
//						purchasingorderinfoobject.put("productName", orderObject.getString("productName"));
						if(orderObject.getJSONObject("materialDetail")!=null) {
							orderObject.put("quantity", orderObject.getJSONObject("materialDetail").optString("quantity"));
							orderObject.put("totalPriceNoTax", orderObject.getJSONObject("materialDetail").optString("totalPriceNoTax"));
							orderObject.put("totalPriceWithTax", orderObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
							orderObject.put("priceNoTax", orderObject.getJSONObject("materialDetail").optString("priceNoTax"));
							orderObject.put("priceWithTax", orderObject.getJSONObject("materialDetail").optString("priceWithTax"));
						}else {
							orderObject.put("quantity", "");
							orderObject.put("totalPriceNoTax", "");
							orderObject.put("totalPriceWithTax", "");
							orderObject.put("priceNoTax", "");
							orderObject.put("priceWithTax", "");
						}
//						purchasingorderinfoobject.put("issuedDate", orderObject.getString("issuedDate"));
						orderlist.add(orderObject);
					}
                    JSONObject purchaseOrderObject=new JSONObject();
                    purchaseOrderObject.put("purchasingorderinfolist", orderlist);
					responseObject.put("data", purchaseOrderObject);
					
				
					//转换
					
//					
					
					
					
				}

				logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryOrderState", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryOrderState (@RequestBody JSONObject orderJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(orderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+orderJson.toString());
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				
				if(orderJson.optString("purchasingorderno").equals("")&&orderJson.optString("salesorderno").equals("")) {
					responseObject.put("errno", 30);
					responseObject.put("error", "传入参数不能都为空");
					responseObject.put("data", new JSONObject());
					return responseObject;
				}
				//组装json
				
				requestJson.put("PurchaseOrderId", orderJson.optString("purchasingorderno"));
				requestJson.put("SalesOrderId", orderJson.optString("salesorderno"));
				requestJson.put("UserOrgId",  orderJson.optString("organizationId"));
				//result:区块链接口返回的json字符串
				
				String resultJson=null;


				
				resultJson=purchaseOrderService.QueryStatusOfPO(requestJson);
				
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
					
//					JSONObject stateinfo=new JSONObject();
//					JSONArray queuinglist=new JSONArray();
					JSONArray contentArray=jsonObject.getJSONArray("data");
					 if(contentArray.size()==0) {
			            	logger.info("data数组长度为0");
			            	throw new MyException(72,"data数组长度为0");
			            }
					JSONObject dataObject=new JSONObject();
					
					for(int i=0;i<contentArray.size();i++) {
						
						JSONObject ordermap=contentArray.getJSONObject(i);
						//判断dataMap.getString是否为空 1-2 refine 为""或 null
						if(!ordermap.has("content")) {
							logger.info("data中缺失content信息");
			            	throw new MyException(73,"data中缺失content信息");
						}
						
						String contentStr=ordermap.optString("content");
						
						if(ordermap.optString("dataType").equals("billcount")) {
							
							dataObject.put("billcount", contentStr);
							continue;
							
						}else if(ordermap.optString("dataType").equals("billweight")) {
							
							dataObject.put("billweight", contentStr);
							continue;
						}else if(ordermap.optString("dataType").equals("packingcount")) {
							
							dataObject.put("packingcount", contentStr);
							continue;
						}else if(ordermap.optString("dataType").equals("packingweight")) {
							
							dataObject.put("packingweight", contentStr);
							continue;
						}else if(ordermap.optString("dataType").equals("receivingcount")) {
							
							dataObject.put("receivingcount", contentStr);
							continue;
						}else if(ordermap.optString("dataType").equals("receivingweight")) {
							
							dataObject.put("receivingweight", contentStr);
							continue;
						}
						
						
						
						
						//将json中的null转换为""
						contentStr=jsonUtil.fromObject(contentStr);
						if(!jsonUtil.isJson(contentStr)) {
							logger.info("格式不是json");
							throw new MyException(74,"格式不是json");
						}
						JSONObject orderObject=JSONObject.fromObject(contentStr);
						
						if(orderObject.optString("dataType").equals("PO")) {
							
							if(orderObject.optString("type").equals("purchase")) {
								
								dataObject.put("purchasingorderproductname", orderObject.optString("productName"));
								if(orderObject.getJSONObject("materialDetail")!=null) {
									dataObject.put("purchasingorderweight", orderObject.getJSONObject("materialDetail").optString("quantity"));
								}else {
									dataObject.put("purchasingorderweight", "");
								}
								
								dataObject.put("purchasingorderdate", orderObject.optString("issuedDate"));
								dataObject.put("purchasingorderstatus", orderObject.optString("status"));
								
							}else if(orderObject.optString("type").equals("sales")) {
								
								dataObject.put("salesorderproductname", orderObject.optString("productName"));
								if(orderObject.getJSONObject("materialDetail")!=null) {
									dataObject.put("salesorderweight", orderObject.getJSONObject("materialDetail").optString("quantity"));
								}else {
									dataObject.put("salesorderweight", "");
								}
							
								dataObject.put("salesorderdate", orderObject.optString("issuedDate"));
								dataObject.put("salesorderstatus", orderObject.optString("status"));
								
							}
							
						}
//						else if(ordermap.optString("dataType").equals("billcount")) {
//							
//							dataObject.put("billcount", contentStr);
//							
//						}else if(ordermap.optString("dataType").equals("billweight")) {
//							
//							dataObject.put("billweight", contentStr);
//							
//						}else if(ordermap.optString("dataType").equals("packingcount")) {
//							
//							dataObject.put("packingcount", contentStr);
//							
//						}else if(ordermap.optString("dataType").equals("packingweight")) {
//							
//							dataObject.put("packingweight", contentStr);
//							
//						}else if(ordermap.optString("dataType").equals("receivingcount")) {
//							
//							dataObject.put("receivingcount", contentStr);
//							
//						}else if(ordermap.optString("dataType").equals("receivingweight")) {
//							
//							dataObject.put("receivingweight", contentStr);
//							
//						}
						
					}
					
//					JSONObject purchaseObj=contentArray.getJSONObject(0);
//					
//					
//					
//					JSONObject purchaseContent=JSONObject.fromObject(purchaseObj.getString("content"));
//					dataObject.put("purchasingorderproductname", purchaseContent.getString("productName"));
//					dataObject.put("purchasingorderweight", purchaseContent.getJSONObject("materialDetail").getString("quantity"));
//					dataObject.put("purchasingorderdate", purchaseContent.getString("issuedDate"));
//					dataObject.put("purchasingorderstatus", purchaseContent.getString("status"));
//					
//					JSONObject salesObj=contentArray.getJSONObject(1);
//					
//					JSONObject salesContent=JSONObject.fromObject(salesObj.getString("content"));
//					dataObject.put("salesorderproductname", salesContent.getString("productName"));
//					dataObject.put("salesorderweight", salesContent.getJSONObject("materialDetail").getString("quantity"));
//					dataObject.put("salesorderdate", salesContent.getString("issuedDate"));
//					dataObject.put("salesorderstatus", salesContent.getString("status"));
//					
//					JSONObject billObj=contentArray.getJSONObject(2);
//					
//					String billcount=billObj.getString("content");
//					dataObject.put("billcount", billcount);
//					
//                    JSONObject billObj2=contentArray.getJSONObject(3);
//					
//					String billweight=billObj2.getString("content");
//					dataObject.put("billweight", billweight);
//					
//                    JSONObject packObj=contentArray.getJSONObject(4);
//					
//					String packingcount=packObj.getString("content");
//					dataObject.put("packingcount", packingcount);
//					
//					
//                    JSONObject packObj2=contentArray.getJSONObject(5);
//					
//					String packingweight=packObj2.getString("content");
//					dataObject.put("packingweight", packingweight);
//					
//                    JSONObject arriveObj=contentArray.getJSONObject(6);
//					
//					String receivingcount=arriveObj.getString("content");
//					dataObject.put("receivingcount", receivingcount);
//					
//                    JSONObject arriveObj2=contentArray.getJSONObject(7);
//					
//					String receivingweight=arriveObj2.getString("content");
//					dataObject.put("receivingweight", receivingweight);
					
					
					
//					int billcount=0;
//					int packingcount=0;
//					int receivingcount=0;
//					int billweight=0;
//					int packingweight=0;
//					int receivingweight=0;
//					int addWeight=0;
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject orderObject=contentArray.getJSONObject(i);
//						JSONObject stateObject=new JSONObject();
//						if(jsonUtil.isJson(resultJson)) {
//						stateObject=JSONObject.fromObject(orderObject.getString("content"));
//						}else {
//							responseObject.put("data", new JSONObject());
//						}
//						if(stateObject.getString("dataType").equals("PO")) {
//							if(stateObject.getString("type").equals("purchase")) {
//								
//								dataObject.put("purchasingorderproductname", stateObject.getString("productName"));
//								dataObject.put("purchasingorderdate", stateObject.getString("issuedDate"));
//								dataObject.put("purchasingorderweight", stateObject.getJSONObject("MaterialDetail").getString("quantity"));
//								dataObject.put("purchasingorderstatus", stateObject.getString("status"));
//								
//							}else if(stateObject.getString("type").equals("sales")) {
//								
//								dataObject.put("salesorderproductname", stateObject.getString("productName"));
//								dataObject.put("salesorderdate", stateObject.getString("issuedDate"));
//								dataObject.put("salesorderweight", stateObject.getJSONObject("MaterialDetail").getString("quantity"));
//								dataObject.put("salesorderstatus", stateObject.getString("status"));
//								
//							}
//						}else if(stateObject.getString("dataType").equals("BillOfLading")) {
//							billcount+=1;
//							if(stateObject.getString("weight").equals("")) {
//								addWeight=0;
//							}else {
//								addWeight=Integer.parseInt(stateObject.getString("weight"));
//							}
//							billweight+=addWeight;
//							dataObject.put("billcount", billcount);
//							dataObject.put("billweight", billweight);
//							
//						}else if(stateObject.getString("dataType").equals("PackingList")) {
//							if(stateObject.getString("packingType").equals("packingList")) {
//								
//								packingcount+=1;
//								if(stateObject.getString("weight").equals("")) {
//									addWeight=0;
//								}else {
//									addWeight=Integer.parseInt(stateObject.getString("weight"));
//								}
//								packingweight+=addWeight;
//								dataObject.put("packingcount", packingcount);
//								dataObject.put("packingweight", packingweight);
//								
//							}else if(stateObject.getString("packingType").equals("arrivalList")) {
//								
//								receivingcount+=1;
//								if(stateObject.getString("weight").equals("")) {
//									addWeight=0;
//								}else {
//									addWeight=Integer.parseInt(stateObject.getString("weight"));
//								}
//								receivingweight+=addWeight;
//								dataObject.put("receivingcount", receivingcount);
//								dataObject.put("receivingweight", receivingweight);
//								
//							}
//						}
//					}
						responseObject.put("data", dataObject);
					}
					
					
				

				logger.info("返回给前端的json数据:"+responseObject.toString());
				return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryCarInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryCarInfo (@RequestBody JSONObject infoJson) {
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				//responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
//				if(infoJson.getString("plateNo").equals("")&&infoJson.getString("purchasingorderno").equals("")) {
//					responseObject.put("errno", 30);
//					responseObject.put("error", "传入参数不能都为空");
//					return responseObject;
//				}
				//组装json
				
				requestJson.put("PlateNumber", infoJson.getString("plateNo"));
				requestJson.put("PurchaseOrderId", infoJson.getString("purchasingorderno"));
				requestJson.put("UserOrgId",  infoJson.getString("organizationId"));
				//result:区块链接口返回的json字符串
				System.out.println("requestJson:"+requestJson.toString());
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectCar", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				resultJson=billOfLadingService.SelectCar(requestJson);
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				
				//从区块链获取的jsonObject
				JSONObject jsonObject=new JSONObject();
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
					return responseObject;
				}
				//获取content信息(contentMap)
				if(jsonObject.get("data") instanceof JSONNull) {
					responseObject.put("errno", 12);
					responseObject.put("error", "查询结果为空");
					responseObject.put("data", new JSONObject());
					
				}else {
					
//					JSONObject carinfo=new JSONObject();
//					JSONArray carinfolist=new JSONArray();
//					JSONArray contentArray=jsonObject.getJSONArray("data");
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject carinfomap=contentArray.getJSONObject(i);
//						JSONObject carinfoObject=JSONObject.fromObject(carinfomap.getString("content"));
//						carinfolist.add(carinfoObject);
//					}
//					carinfo.put("carinfolist", carinfolist);
//					responseObject.put("data", carinfo);
					
					
					JSONArray contentArray=jsonObject.getJSONArray("data");
					//carinfolist[]
					JSONArray carinfolist=new JSONArray();
					
					JSONObject carinfoObject=null;
					for(int i=0;i<contentArray.size();i++) {
						
						JSONObject dataObject=contentArray.getJSONObject(i);
						String contentStr=dataObject.getString("content");
						JSONObject contentObject=new JSONObject();
						if(jsonUtil.isJson(contentStr)) {
							contentObject=JSONObject.fromObject(contentStr);
						}
						
						if(contentObject.getString("dataType").equals("BillOfLading")) {
							carinfoObject=new JSONObject();
							
							carinfoObject.put("billno", contentObject.getString("id"));
							carinfoObject.put("billtime", contentObject.getString("issuedDate"));
							carinfoObject.put("billproductname", contentObject.getString("productName"));
							carinfoObject.put("billweight", contentObject.getString("weight"));
							
							carinfoObject.put("purchasingorderno", "");
							carinfoObject.put("purchasingordertime", "");
							carinfoObject.put("purchasingorderproductname", "");
							carinfoObject.put("purchasingorderweight", "");
							
							carinfoObject.put("salesorderno", "");
							carinfoObject.put("salesordertime", "");
							carinfoObject.put("salesorderproductname", "");
							carinfoObject.put("salesorderweight", "");
							
							carinfoObject.put("packingno", "");
							carinfoObject.put("packingtime", "");
							carinfoObject.put("packingproductname", "");
							carinfoObject.put("packingweight", "");
							carinfoObject.put("receivingno", "");
							carinfoObject.put("receivingtime", "");
							carinfoObject.put("receivingproductname", "");
							carinfoObject.put("receivingweight", "");
							//判断下一条数据的dataType，如果是BillOfLading，则说明是本组最后一条数据，add进数组里
							if(i+1==contentArray.size()) {
								carinfolist.add(carinfoObject);
								break;
							}
							JSONObject dataObjectnext=contentArray.getJSONObject(i+1);
							String contentStrnext=dataObjectnext.getString("content");
							JSONObject contentObjectnext=new JSONObject();
							if(jsonUtil.isJson(contentStrnext)) {
								contentObjectnext=JSONObject.fromObject(contentStrnext);
							}
							if(contentObjectnext.getString("dataType").equals("BillOfLading")) {
								carinfolist.add(carinfoObject);
							}
							
						}
						if(contentObject.getString("dataType").equals("PackingList")) {
//							JSONObject carinfoObject=new JSONObject();
							if(contentObject.getString("packingType").equals("packingList")) {
							carinfoObject.put("packingno", contentObject.getString("id"));
							if(contentObject.has("date")) {
								carinfoObject.put("packingtime", contentObject.getString("date"));
							}else {
								carinfoObject.put("packingtime", "");
							}
							if(contentObject.has("productName")) {
								carinfoObject.put("packingproductname", contentObject.getString("productName"));
							}else {
								carinfoObject.put("packingproductname", "");
							}
							
							carinfoObject.put("packingweight", contentObject.getString("weight"));
							//判断下一条数据的dataType，如果是BillOfLading，则说明是本组最后一条数据，add进数组里
							if(i+1==contentArray.size()) {
								carinfolist.add(carinfoObject);
								break;
							}
							JSONObject dataObjectnext=contentArray.getJSONObject(i+1);
							String contentStrnext=dataObjectnext.getString("content");
							JSONObject contentObjectnext=new JSONObject();
							if(jsonUtil.isJson(contentStrnext)) {
								contentObjectnext=JSONObject.fromObject(contentStrnext);
							}
							if(contentObjectnext.getString("dataType").equals("BillOfLading")) {
								carinfolist.add(carinfoObject);
							}
							}else {
								
								carinfoObject.put("receivingno", contentObject.getString("id"));
								carinfoObject.put("receivingtime", contentObject.getString("reachedDate"));
								carinfoObject.put("receivingproductname", contentObject.getString("productName"));
								carinfoObject.put("receivingweight", contentObject.getString("weight"));
								//判断下一条数据的dataType，如果是BillOfLading，则说明是本组最后一条数据，add进数组里
								if(contentArray.size()-1==i) {
									carinfolist.add(carinfoObject);
									break;
								}
								JSONObject dataObjectnext=contentArray.getJSONObject(i+1);
								String contentStrnext=dataObjectnext.getString("content");
								JSONObject contentObjectnext=new JSONObject();
								if(jsonUtil.isJson(contentStrnext)) {
									contentObjectnext=JSONObject.fromObject(contentStrnext);
								}
								if(contentObjectnext.getString("dataType").equals("BillOfLading")) {
									carinfolist.add(carinfoObject);
								}
								
							}
							
						}
						
						if(contentObject.getString("dataType").equals("PO")) {
							if(contentObject.getString("type").equals("purchase")) {
								
								carinfoObject.put("purchasingorderno", contentObject.getString("id"));
								carinfoObject.put("purchasingordertime", contentObject.getString("issuedDate"));
								carinfoObject.put("purchasingorderproductname", contentObject.getString("productName"));
								carinfoObject.put("purchasingorderweight", contentObject.getJSONObject("materialDetail").getString("quantity"));
								//判断下一条数据的dataType，如果是BillOfLading，则说明是本组最后一条数据，add进数组里
								JSONObject dataObjectnext=contentArray.getJSONObject(i+1);
								String contentStrnext=dataObjectnext.getString("content");
								JSONObject contentObjectnext=new JSONObject();
								if(jsonUtil.isJson(contentStrnext)) {
									contentObjectnext=JSONObject.fromObject(contentStrnext);
								}
								if(contentObjectnext.getString("dataType").equals("BillOfLading")) {
									carinfolist.add(carinfoObject);
								}
						
							}else {
								
								carinfoObject.put("salesorderno", contentObject.getString("id"));
								carinfoObject.put("salesordertime", contentObject.getString("issuedDate"));
								carinfoObject.put("salesorderproductname", contentObject.getString("productName"));
								carinfoObject.put("salesorderweight", contentObject.getJSONObject("materialDetail").getString("quantity"));
								//判断下一条数据的dataType，如果是BillOfLading，则说明是本组最后一条数据，add进数组里
								if(contentArray.size()-1==i) {
								carinfolist.add(carinfoObject);
								break;
							}
							JSONObject dataObjectnext=contentArray.getJSONObject(i+1);
							String contentStrnext=dataObjectnext.getString("content");
							JSONObject contentObjectnext=new JSONObject();
							if(jsonUtil.isJson(contentStrnext)) {
								contentObjectnext=JSONObject.fromObject(contentStrnext);
							}
							if(contentObjectnext.getString("dataType").equals("BillOfLading")) {
								carinfolist.add(carinfoObject);
							}
								
							}
						}
//						if(contentObject.getString("dataType").equals("PO")) {
//							if(contentObject.getString("type").equals("purchase")) {
//								JSONObject carinfoObject=new JSONObject();
//								carinfoObject.put("purchasingorderno", contentObject.getString("id"));
//								carinfoObject.put("purchasingordertime", contentObject.getString("issuedDate"));
//								carinfoObject.put("purchasingorderproductname", contentObject.getString("productName"));
//								carinfoObject.put("purchasingorderweight", contentObject.getJSONObject("materialDetail").getString("quantity"));
//							}else if(contentObject.getString("type").equals("sales")) {
//								
//								carinfoObject.put("salesorderno", "");
//								carinfoObject.put("salesordertime", "");
//								carinfoObject.put("salesorderproductname", "");
//								carinfoObject.put("salesorderweight", "");
//								
//							}
//						}
						
					}
					
					
					
					
					
					
					
//					for(int i=0;i<contentArray.size();i++) {
//						JSONObject dataObject=contentArray.getJSONObject(i);
//						String contentStr=dataObject.getString("content");
//						JSONObject contentObject=new JSONObject();
//						if(jsonUtil.isJson(contentStr)) {
//							contentObject=JSONObject.fromObject(contentStr);
//						}
//						if(contentObject.getString("dataType").equals("PO")) {
//							if(contentObject.getString("type").equals("purchase")) {
//							//carinfolist[]中的object
//							JSONObject carinfoObject=new JSONObject();
//							carinfoObject.put("purchasingorderno", contentObject.getString("id"));
//							carinfoObject.put("purchasingordertime", contentObject.getString("issuedDate"));
//							carinfoObject.put("purchasingorderproductname", contentObject.getString("productName"));
//							carinfoObject.put("purchasingorderweight", contentObject.getJSONObject("materialDetail").getString("quantity"));
//							
//							carinfoObject.put("salesorderno", "");
//							carinfoObject.put("salesordertime", "");
//							carinfoObject.put("salesorderproductname", "");
//							carinfoObject.put("salesorderweight", "");
//							
//							carinfoObject.put("billno", "");
//							carinfoObject.put("billtime", "");
//							carinfoObject.put("billproductname", "");
//							carinfoObject.put("billweight", "");
//							
//							carinfoObject.put("packingno", "");
//							carinfoObject.put("packingtime", "");
//							carinfoObject.put("packingproductname", "");
//							carinfoObject.put("packingweight", "");
//							
//							carinfoObject.put("receivingno", "");
//							carinfoObject.put("receivingtime", "");
//							carinfoObject.put("receivingproductname", "");
//							carinfoObject.put("receivingweight", "");
//							for(int j=i+1;j<contentArray.size();j++) {
//								JSONObject dataObject2=contentArray.getJSONObject(j);
//								String contentStr2=dataObject2.getString("content");
//								JSONObject contentObject2=new JSONObject();
//								if(jsonUtil.isJson(contentStr2)) {
//									contentObject2=JSONObject.fromObject(contentStr2);
//								}
//								if(contentObject2.getString("dataType").equals("PO")) {
//								if(contentObject2.getString("type").equals("sales")) {
//									carinfoObject.put("salesorderno", contentObject2.getString("id"));
//									carinfoObject.put("salesordertime", contentObject2.getString("issuedDate"));
//									carinfoObject.put("salesorderproductname", contentObject2.getString("productName"));
//									carinfoObject.put("salesorderweight", contentObject2.getJSONObject("materialDetail").getString("quantity"));
//									
//									
//								}else if(contentObject2.getString("type").equals("purchase")) {
//									carinfolist.add(carinfoObject);
//									i=j-1;
//									break;
//								}
//								}
//								if(contentObject2.getString("dataType").equals("BillOfLading")) {
//									carinfoObject.put("billno", contentObject2.getString("id"));
//									carinfoObject.put("billtime", contentObject2.getString("issuedDate"));
//									carinfoObject.put("billproductname", contentObject2.getString("productName"));
//									carinfoObject.put("billweight", contentObject2.getString("weight"));
//									
//									JSONObject dataObject3=contentArray.getJSONObject(j+1);
//									String contentStr3=dataObject3.getString("content");
//									JSONObject contentObject3=new JSONObject();
//									if(jsonUtil.isJson(contentStr2)) {
//										contentObject3=JSONObject.fromObject(contentStr3);
//									}
//									if(!contentObject3.getString("dataType").equals("PackingList"))
//									carinfolist.add(carinfoObject);
//								}
//								if(contentObject2.getString("dataType").equals("PackingList")) {
//									if(contentObject2.getString("packingType").equals("packingList")) {
//									carinfoObject.put("packingno", contentObject2.getString("id"));
//									carinfoObject.put("packingtime", contentObject2.getString("date"));
//									carinfoObject.put("packingproductname", contentObject2.getString("productName"));
//									carinfoObject.put("packingweight", contentObject2.getString("weight"));
//									
//									carinfolist.add(carinfoObject);
//									}else if(contentObject2.getString("packingType").equals("arrivalList")) {
//										carinfoObject.put("receivingno", contentObject2.getString("id"));
////										carinfoObject.put("receivingtime", contentObject2.getString("date"));
//										carinfoObject.put("receivingproductname", contentObject2.getString("productName"));
//										carinfoObject.put("receivingweight", contentObject2.getString("weight"));
////										carinfolist.add(carinfoObject);
//									}
//								}
//								
//								
//							}
////							carinfolist.add(carinfoObject);
////							JSONObject dataObject2=contentArray.getJSONObject(i+1);
////							String contentStr2=dataObject2.getString("content");
////							JSONObject contentObject2=new JSONObject();
////							if(jsonUtil.isJson(contentStr2)) {
////								contentObject2=JSONObject.fromObject(contentStr2);
////							}
////							if(contentObject2.getString("dataType").equals("PO")) {
////								if(contentObject2.getString("type").equals("sale")) {
////									carinfoObject.put("salesorderno", contentObject.getString("id"));
////									carinfoObject.put("salesordertime", contentObject.getString("issuedDate"));
////									carinfoObject.put("salesorderproductname", contentObject.getString("productName"));
////									carinfoObject.put("salesorderweight", contentObject.getJSONObject("MaterialDetail").getString("quantity"));
////									
////								}else {
////									continue;
////								}
////							}
//							
////							carinfolist.add(carinfoObject);
//						}
//							
//						}
//					}
					JSONObject carinfo=new JSONObject();
					carinfo.put("carinfolist", carinfolist);
					responseObject.put("data", carinfo);
					}
					
					
				

				return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/OrderCompare", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject OrderCompare(@RequestBody JSONObject compareJson) {
		
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
				requestJson.put("PurchaseOrderId", compareJson.optString("purchasingorderno"));
				requestJson.put("SalesOrderId", compareJson.optString("salesorderno"));
				requestJson.put("ContractNumber", compareJson.optString("arrangementId"));
				requestJson.put("UserOrgId", compareJson.optString("organizationId"));
				//result:区块链接口返回的json字符串
				String resultJson=null;
				
				resultJson=purchaseOrderService.ComparePO(requestJson);
				
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

					
				String leaveweight="";
				String arriveweight="";
				JSONObject compareObject=null;
				JSONObject diffinfo=null;
				for(int i=0;i<dataArray.size();i++) {
					JSONObject dataObject=dataArray.getJSONObject(i);
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!dataObject.has("dataType")) {
						logger.info("data中缺失dataType信息");
		            	throw new MyException(75,"data中缺失dataType信息");
					}
					if(!dataObject.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					
					if(dataObject.optString("dataType").equals("leaveweight")) {
						leaveweight=dataObject.optString("content");
						continue;
					}else if(dataObject.optString("dataType").equals("arriveweight")) {
						arriveweight=dataObject.optString("content");
						continue;
					}
					
					String contentStr=dataObject.optString("content");
					
					//将json中的null转换为""
					contentStr=JsonUtil.fromObject(contentStr);
					System.out.println(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info(contentStr+"格式不是json");
						throw new MyException(74,"格式不是json");
					}
					JSONObject contentObject=JSONObject.fromObject(contentStr);
//					if(dataObject.optString("dataType").equals(leaveweight)) {
//						leaveweight=dataObject.optString("content");
//					}else if(dataObject.optString("dataType").equals(arriveweight)) {
//						arriveweight=dataObject.optString("content");
//					}
					
					if(contentObject.has("type")) {
						if(contentObject.optString("type").equals("sales")) {
							
							compareObject=new JSONObject();
							diffinfo=new JSONObject();
							contentObject.put("salesorderno", contentObject.getString("id"));
							if(contentObject.getJSONObject("materialDetail")!=null) {
								contentObject.put("quantity", contentObject.getJSONObject("materialDetail").optString("quantity"));
								contentObject.put("totalPriceWithTax", contentObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
								contentObject.put("priceWithTax", contentObject.getJSONObject("materialDetail").optString("priceWithTax"));
							}else {
								contentObject.put("quantity","");
								contentObject.put("totalPriceWithTax", "");
								contentObject.put("priceWithTax", "");
							}
							
							contentObject.put("realtotal", "");
							contentObject.put("realunitprice", "");
							contentObject.put("realweight", leaveweight/*装箱单净重*/);
							
							compareObject.put("salesorderinfo", contentObject);
				
						}else if(contentObject.optString("type").equals("purchase")) {
							
							contentObject.put("purchasingorderno", contentObject.optString("id"));
							if(contentObject.getJSONObject("materialDetail")!=null) {
								contentObject.put("quantity", contentObject.getJSONObject("materialDetail").optString("quantity"));
								contentObject.put("totalPriceWithTax", contentObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
								contentObject.put("priceWithTax", contentObject.getJSONObject("materialDetail").optString("priceWithTax"));
							}else {
								contentObject.put("quantity","");
								contentObject.put("totalPriceWithTax", "");
								contentObject.put("priceWithTax", "");
							}
							
							contentObject.put("realtotal", "");
							contentObject.put("realunitprice", "");
							contentObject.put("realweight", arriveweight/*到货单净重*/);
							compareObject.put("purchasingorderinfo", contentObject);
							
							
							
							
//							diffinfo.put("realweight", (Integer.parseInt(compareObject.getJSONObject("purchasingorderinfo").optString("realweight"))-Integer.parseInt(compareObject.getJSONObject("salesorderinfo").optString("realweight"))));
//							diffinfo.put("totalPriceWithTax", (Integer.parseInt(compareObject.getJSONObject("purchasingorderinfo").optString("totalPriceWithTax"))-Integer.parseInt(compareObject.getJSONObject("salesorderinfo").optString("totalPriceWithTax"))));
//							diffinfo.put("priceWithTax", (Integer.parseInt(compareObject.getJSONObject("purchasingorderinfo").optString("priceWithTax"))-Integer.parseInt(compareObject.getJSONObject("salesorderinfo").optString("priceWithTax"))));
							
							
							diffinfo.put("realweight", JsonUtil.operationOfStr(compareObject.getJSONObject("purchasingorderinfo").optString("realweight"), compareObject.getJSONObject("salesorderinfo").optString("realweight")));
							diffinfo.put("totalPriceWithTax", JsonUtil.operationOfStr(compareObject.getJSONObject("purchasingorderinfo").optString("totalPriceWithTax"), compareObject.getJSONObject("salesorderinfo").optString("totalPriceWithTax")));
							diffinfo.put("priceWithTax", JsonUtil.operationOfStr(compareObject.getJSONObject("purchasingorderinfo").optString("priceWithTax"), compareObject.getJSONObject("salesorderinfo").optString("priceWithTax")));
							compareObject.put("diffinfo", diffinfo);
							
							compareinfo.add(compareObject);
						}
						
					}
					
				}
				
//				for(int i=0;i<dataArray.size();i++) {
//					JSONObject dataObject=dataArray.getJSONObject(i);
//					//判断dataMap.getString是否为空 1-2 refine 为""或 null
//					if(!dataObject.has("dataType")) {
//						logger.info("data中缺失dataType信息");
//		            	throw new MyException(75,"data中缺失dataType信息");
//					}
//					if(!dataObject.has("content")) {
//						logger.info("data中缺失content信息");
//		            	throw new MyException(73,"data中缺失content信息");
//					}
//					
//					String contentStr=dataObject.optString("content");
//					//将json中的null转换为""
//					contentStr=jsonUtil.fromObject(contentStr);
//					if(!jsonUtil.isJson(contentStr)) {
//						logger.info("格式不是json");
//						throw new MyException(74,"格式不是json");
//					}
//					JSONObject contentObject=JSONObject.fromObject(contentStr);
//					if(contentObject.has("type")) {
//						if(contentObject.optString("type").equals("purchase")) {
//							JSONObject compareObject=new JSONObject();
////							JSONObject purchasingorderinfo=new JSONObject();
//							JSONObject diffinfo=new JSONObject();
//							contentObject.put("purchasingorderno", contentObject.optString("id"));
//							if(contentObject.getJSONObject("materialDetail")!=null) {
//								contentObject.put("quantity", contentObject.getJSONObject("materialDetail").optString("quantity"));
//								contentObject.put("totalPriceWithTax", contentObject.getJSONObject("materialDetail").optString("totalPriceWithTax"));
//								contentObject.put("priceWithTax", contentObject.getJSONObject("materialDetail").optString("priceWithTax"));
//							}else {
//								contentObject.put("quantity","");
//								contentObject.put("totalPriceWithTax", "");
//								contentObject.put("priceWithTax", "");
//							}
//							
//							contentObject.put("realtotal", "");
//							contentObject.put("realunitprice", "");
//							contentObject.put("realweight", arriveweight/*到货单净重*/);
//							compareObject.put("purchasingorderinfo", contentObject);
//						}else {
//							
//						}
//					}
//					
//					
//				}
				
				
//				leaveweight=dataArray.getJSONObject(0).getString("content");
//				arriveweight=dataArray.getJSONObject(1).getString("content");
//					for(int i=2;i<dataArray.size();i++) {
//				        
//						JSONObject compareObject=new JSONObject();
//						JSONObject purchasingorderinfo=new JSONObject();
//						JSONObject purchasingorderobj=new JSONObject();
//						JSONObject salesorderinfo=new JSONObject();
//						JSONObject salesorderobj=new JSONObject();
//						
////						
////						
//							JSONObject dataObject=dataArray.getJSONObject(i);
//							
////							
//						JSONObject salesorderinfoObject=dataArray.getJSONObject(i);
//						JSONObject purchasingorderinfoObject=dataArray.getJSONObject(i+1);
//						
////						
//						String purchasingorderinfoStr=purchasingorderinfoObject.getString("content");
//						String salesorderinfoStr=salesorderinfoObject.getString("content");
////						
//						if(jsonUtil.isJson(purchasingorderinfoStr)) {
//							purchasingorderobj=JSONObject.fromObject(purchasingorderinfoStr);
//							System.out.println("222"+purchasingorderinfo.toString());
//						}
//						purchasingorderinfo.put("purchasingorderno", purchasingorderobj.getString("id"));
//						purchasingorderinfo.put("buyerName", purchasingorderobj.getString("buyerName"));
//						purchasingorderinfo.put("sellerName", purchasingorderobj.getString("sellerName"));
//						purchasingorderinfo.put("arrangementId", purchasingorderobj.getString("arrangementId"));
//						purchasingorderinfo.put("status", purchasingorderobj.getString("status"));
//						purchasingorderinfo.put("productName", purchasingorderobj.getString("productName"));
//						purchasingorderinfo.put("issuedDate", purchasingorderobj.getString("issuedDate"));
//				
//						purchasingorderinfo.put("quantity", purchasingorderobj.getJSONObject("materialDetail").getString("quantity"));
//						purchasingorderinfo.put("totalPriceWithTax", purchasingorderobj.getJSONObject("materialDetail").getString("totalPriceWithTax"));
//						purchasingorderinfo.put("priceWithTax", purchasingorderobj.getJSONObject("materialDetail").getString("priceWithTax"));
//						purchasingorderinfo.put("realtotal", "");
//						purchasingorderinfo.put("realunitprice", "");
//						purchasingorderinfo.put("realweight", arriveweight/*到货单净重*/);
//						System.out.println("arriveweight:"+arriveweight);
//						compareObject.put("purchasingorderinfo", purchasingorderinfo);
//						
////						JSONObject salesorderinfo=new JSONObject();
//						if(jsonUtil.isJson(salesorderinfoStr)) {
//							salesorderobj=JSONObject.fromObject(salesorderinfoStr);
//						}
//						salesorderinfo.put("salesorderno", salesorderobj.getString("id"));
//						salesorderinfo.put("buyerName", purchasingorderobj.getString("buyerName"));
//						salesorderinfo.put("sellerName", purchasingorderobj.getString("sellerName"));
//						salesorderinfo.put("arrangementId", purchasingorderobj.getString("arrangementId"));
//						salesorderinfo.put("status", purchasingorderobj.getString("status"));
//						salesorderinfo.put("productName", purchasingorderobj.getString("productName"));
//						salesorderinfo.put("issuedDate", purchasingorderobj.getString("issuedDate"));
//						
//						salesorderinfo.put("quantity", salesorderobj.getJSONObject("materialDetail").getString("quantity"));
//						salesorderinfo.put("totalPriceWithTax", purchasingorderobj.getJSONObject("materialDetail").getString("totalPriceWithTax"));
//						salesorderinfo.put("priceWithTax", purchasingorderobj.getJSONObject("materialDetail").getString("priceWithTax"));
//						salesorderinfo.put("realtotal", "");
//						salesorderinfo.put("realunitprice", "");
//						salesorderinfo.put("realweight", leaveweight/*装箱单净重*/);
//						
//						compareObject.put("salesorderinfo", salesorderinfo);
//						i+=1;
////						}
//						
//						JSONObject diffinfo=new JSONObject();
//						//错误，对""进行数据类型转换
////						diffinfo.put("quantity", (Integer.parseInt(purchasingorderobj.getJSONObject("materialDetail").getString("quantity"))-Integer.parseInt(salesorderobj.getJSONObject("materialDetail").getString("quantity"))));
////						diffinfo.put("totalPriceNoTax", (Integer.parseInt(purchasingorderobj.getJSONObject("materialDetail").getString("totalPriceNoTax"))-Integer.parseInt(salesorderobj.getJSONObject("materialDetail").getString("totalPriceNoTax"))));
////						diffinfo.put("totalPriceWithTax", (Integer.parseInt(purchasingorderobj.getJSONObject("materialDetail").getString("totalPriceWithTax"))-Integer.parseInt(salesorderobj.getJSONObject("materialDetail").getString("totalPriceWithTax"))));
////						diffinfo.put("priceNoTax", (Integer.parseInt(purchasingorderobj.getJSONObject("materialDetail").getString("priceNoTax"))-Integer.parseInt(salesorderobj.getJSONObject("materialDetail").getString("priceNoTax"))));
////						diffinfo.put("priceWithTax", (Integer.parseInt(purchasingorderobj.getJSONObject("materialDetail").getString("priceWithTax"))-Integer.parseInt(salesorderobj.getJSONObject("materialDetail").getString("priceWithTax"))));
//						diffinfo.put("realweight", (Integer.parseInt(purchasingorderinfo.getString("realweight"))-Integer.parseInt(salesorderinfo.getString("realweight"))));
//						diffinfo.put("totalPriceWithTax", (Integer.parseInt(purchasingorderinfo.getString("totalPriceWithTax"))-Integer.parseInt(salesorderinfo.getString("totalPriceWithTax"))));
//						diffinfo.put("priceWithTax", (Integer.parseInt(purchasingorderinfo.getString("priceWithTax"))-Integer.parseInt(salesorderinfo.getString("priceWithTax"))));
//						
//						compareObject.put("diffinfo", diffinfo);
//						
//						compareinfo.add(compareObject);
//		
//					}
					
					finalDataObject.put("ordercompareinfo", compareinfo);
				responseObject.put("data", finalDataObject);
				
			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryPayment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPayment(@RequestBody JSONObject paymentJson) {
		
		if(paymentJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(paymentJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+paymentJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		Payment paymentForQuery=(Payment)JSONObject.toBean(paymentJson, Payment.class);
		
		
		List <Payment> paymentList = purchaseOrderService.SelectPaymentByObject(paymentForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(paymentList==null||paymentList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (Payment payment : paymentList) {
				
				dataArray.add(JSONObject.fromObject(payment));
				
			}
			dataObject.put("paymentList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryPO", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPO(@RequestBody JSONObject poJson) {
		
		if(poJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(poJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+poJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		PO poForQuery=(PO)JSONObject.toBean(poJson, PO.class);
		poForQuery.setOrganizationId("");
		poForQuery.setOrganizationName("");
		
		List <PO> poList = purchaseOrderService.SelectPOByObject(poForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(poList==null||poList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (PO po : poList) {
				
				dataArray.add(JsonUtil.fromObject(po));
				
			}
			dataObject.put("poList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryPOByObject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPOByObject(@RequestBody JSONObject poJson) {
		
		if(poJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(poJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+poJson.toString());
		
		JSONObject responseObject=new JSONObject();
		Map<String,String> mapForQuery=new HashMap<String,String>();
		
		mapForQuery.put("orgId", poJson.optString("orgId"));
		mapForQuery.put("organizationId", poJson.optString("organizationId"));
		if(("").equals(poJson.optString("totalPriceWithTaxStart"))) {
			mapForQuery.put("totalPriceWithTaxStart", "0");
		}else {
			mapForQuery.put("totalPriceWithTaxStart", poJson.optString("totalPriceWithTaxStart"));
		}
			
		
		if(("").equals(poJson.optString("totalPriceWithTaxEnd"))) {
			mapForQuery.put("totalPriceWithTaxEnd", "99999999999999999");
		}else {
			mapForQuery.put("totalPriceWithTaxEnd", poJson.optString("totalPriceWithTaxEnd"));
		}
		
		
		
		if(("").equals(poJson.optString("issuedDateStart"))) {
			mapForQuery.put("issuedDateStart", "0000-00-00");
		}else {
			mapForQuery.put("issuedDateStart", poJson.optString("issuedDateStart"));
		}
		if(("").equals(poJson.optString("issuedDateEnd"))) {
			mapForQuery.put("issuedDateEnd", "9999-12-31");
		}else {
			mapForQuery.put("issuedDateEnd", poJson.optString("issuedDateEnd"));
		}
		
//		poJson.put("materialdetail", materialdetail);
		mapForQuery.put("productName", poJson.optString("productName"));
		mapForQuery.put("poType", poJson.optString("poType"));
		mapForQuery.put("dataSourceId", poJson.optString("orgId"));
//		PO poForQuery=(PO)JSONObject.toBean(poJson, PO.class);
//		poForQuery.setOrganizationId("");
//		poForQuery.setOrganizationName("");
		
		List <PO> poList = purchaseOrderService.SelectPOByObjectIn(mapForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(poList==null||poList.size()==0) {
			responseObject.put("errno", 12);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (PO po : poList) {
				
				dataArray.add(JsonUtil.fromObject(po));
				
			}
			dataObject.put("poList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdatePO", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdatePO(@RequestBody JSONObject orderJson) {
		

				
//		responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(orderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+orderJson.toString());
		
		if(orderJson.optString("id").equals("")) {
			throw new MyException(102,"ID为必传项");
		}
		
		
					
					//定义访问区块链接口所需要的json
					JSONObject requestJson=new JSONObject();
					//组装json
					PO po=new PO();
					po.setId(orderJson.optString("id"));
					
					String materialDetailId=purchaseOrderService.SelectPOByObject(po).get(0).getMaterialdetail().getId();
					if(!("").equals(orderJson.optString("status"))) {
						po.setStatus(orderJson.optString("status"));
					}
					
					if(!("").equals(orderJson.optString("quantity"))||!("").equals(orderJson.optString("quantityUnit"))||!("").equals(orderJson.optString("priceNoTax"))||!("").equals(orderJson.optString("priceWithTax"))||!("").equals(orderJson.optString("totalPriceNoTax"))||!("").equals(orderJson.optString("totalPriceWithTax"))||!("").equals(orderJson.optString("priceQualityUnit"))||!("").equals(orderJson.optString("realTotalPrice"))||!("").equals(orderJson.optString("realPrice"))) {
						MaterialDetail materialDetail=(MaterialDetail)JSONObject.toBean(orderJson,MaterialDetail.class);
						materialDetail.setId(materialDetailId);
						po.setMaterialdetail(materialDetail);
					}
				
					
       
       responseObject=purchaseOrderService.UpdatePO(po);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
				
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeletePO", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject DeletePO(@RequestBody JSONObject orderJson) {
		

				
//		responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(orderJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orderJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+orderJson.toString());
		
		if(orderJson.optString("id").equals("")) {
			throw new MyException(102,"ID为必传项");
		}
		
		
					
					
					
					//组装json
					PO po=new PO();
					po.setId(orderJson.optString("id"));
					
				
					
       
       responseObject=purchaseOrderService.DeletePO(po);
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       return responseObject;
				
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/TestExcelExport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	private void TestExcelExport(HttpServletResponse response) {
		

				
//		responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		
	
					
		try {
			excelUtil.export(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		responseObject.put("errno", 0);
		responseObject.put("error", "success");
		
       
       logger.info("返回给前端的json数据:"+responseObject.toString());
       
       
				
		
	}
	
}
