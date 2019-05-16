package com.enn.commodity.synergistic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.Location;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.Product;
import com.enn.commodity.synergistic.entity.RequestOfOrg;
import com.enn.commodity.synergistic.entity.ResultMap;
import com.enn.commodity.synergistic.service.AuthService;
import com.enn.commodity.synergistic.service.IPService;
import com.enn.commodity.synergistic.service.OrganizationService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Organization")
@CrossOrigin
public class OrganizationController {
	
	
    private static Logger logger = Logger.getLogger(BillOfLadingController.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private IPService ipService;
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
	@RequestMapping(value = "/QueryOrganizationList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryOrganizationList(@RequestBody JSONObject organizationListJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(organizationListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(organizationListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+organizationListJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		//组装json
//		requestJson.put("UserId", organizationListJson.getString("userId"));
		requestJson.put("UserOrgId", organizationListJson.getString("organizationId"));
		
		//result:区块链接口返回的json字符串
		String resultJson=null;
		try {
			resultJson = new RestUtil().httpPost(path+"/IP/GetIPInformation", requestJson);
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
			
			responseObject.put("errno", 70);
			responseObject.put("error", "查询结果为空");
			responseObject.put("data", new JSONObject());
			
		}else {
			
			JSONArray billlist=new JSONArray();
			JSONObject object=new JSONObject();
			JSONArray contentArray=jsonObject.getJSONArray("data");
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
//				billObject.remove("location");
				object.put("orgid", billObject.optString("id"));
				object.put("orgname", billObject.optString("name"));
				billlist.add(object);
			}
            JSONObject billOfLadingObject=new JSONObject();
            billOfLadingObject.put("orginfolist", billlist);
			responseObject.put("data", billOfLadingObject);
		}

		logger.info("返回给前端的json数据:"+responseObject.toString());	
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryAllOrganization", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryAllOrganization(@RequestBody JSONObject organizationListJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(organizationListJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(organizationListJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+organizationListJson.toString());
		
		//定义访问区块链接口所需要的json
		JSONObject requestJson=new JSONObject();
		//组装json
//		requestJson.put("UserId", organizationListJson.getString("userId"));
		requestJson.put("UserOrgId", organizationListJson.getString("organizationId"));
		
		
		String resultJson=null;
		
		resultJson = organizationService.GetAllIPInformation(requestJson);
		
		
		
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
		
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
			
			responseObject.put("errno", 70);
			responseObject.put("error", "查询结果为空");
			responseObject.put("data", new JSONObject());
			
		}else {
			
			JSONArray billlist=new JSONArray();
			JSONObject object=new JSONObject();
			JSONArray contentArray=jsonObject.getJSONArray("data");
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
//				billObject.remove("location");
				billObject.put("orgid", billObject.optString("id"));
				billObject.put("orgname", billObject.optString("name"));
				billlist.add(billObject);
			}
            JSONObject billOfLadingObject=new JSONObject();
            billOfLadingObject.put("orginfolist", billlist);
			responseObject.put("data", billOfLadingObject);
		}

		logger.info("返回给前端的json数据:"+responseObject.toString());	
		return responseObject;
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/CreateOrganization", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateOrganization(@RequestBody JSONObject organizationJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(organizationJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(organizationJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+organizationJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				
				JSONObject requestJsonOfAuth=new JSONObject();
				
				JSONObject authJson=new JSONObject();
				//组装json
				organizationJson.put("id", organizationJson.optString("orgid"));
				organizationJson.put("name", organizationJson.optString("orgname"));
				organizationJson.put("dataType", "IP");
				organizationJson.put("plan", new JSONArray());
				organizationJson.put("userId", organizationJson.opt("userid"));
				
				authJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
				authJson.put("dataType", "OrgAuth");
				authJson.put("grantor", organizationJson.optString("orgid"));
				authJson.put("grantee", organizationJson.optString("orgid"));
				authJson.put("authDate", "");
				
//				salesOrderJson.put("organizationId", "org1");
//				JSONObject detailJson=new JSONObject();
//				detailJson.put("quantity", salesOrderJson.getString("quantity"));
//				detailJson.put("priceNoTax", salesOrderJson.getString("priceNoTax"));
//				detailJson.put("priceWithTax", salesOrderJson.getString("priceWithTax"));
//				detailJson.put("totalPriceNoTax", salesOrderJson.getString("totalPriceNoTax"));
//				detailJson.put("totalPriceWithTax", salesOrderJson.getString("totalPriceWithTax"));
//				salesOrderJson.put("materialDetail",detailJson);
				requestJson.put("IPInformation", organizationJson.toString());
				
				requestJsonOfAuth.put("Permission", authJson.toString());
				//result:区块链接口返回的json字符串
				String resultJsonOfIP=null;
				String resultJsonOfAuth=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/IP/CreateIPInformation", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				resultJsonOfIP=organizationService.CreateIPInformation(requestJson);
				
				resultJsonOfAuth=authService.CreateAuth(requestJsonOfAuth);
				//从区块链获取的jsonObject
				JSONObject jsonObjectOfIP=JSONObject.fromObject(resultJsonOfIP);
				
				JSONObject jsonObjectOfAuth=JSONObject.fromObject(resultJsonOfAuth);
				
				logger.info("区块链层反返回创建IP数据反序列化结果:"+jsonObjectOfIP.toString());
				
				logger.info("区块链层反返回创建Auth数据反序列化结果:"+jsonObjectOfAuth.toString());
//		      2.判断返回状态码
				//获取error信息
				
				
				if(jsonObjectOfIP!=null) {
					if(jsonObjectOfIP.get("error") instanceof JSONNull) {
						responseObject.put("errno", 0);
						responseObject.put("error", "success");
						responseObject.put("data", new JSONObject());
					}else {
						
						JSONArray errorArray=jsonObjectOfIP.getJSONArray("error");
						//判断errorArray.size是否为0  2019-1-2 refine
						if(errorArray.size()==0) {
							throw new MyException(71,"error数组长度为0");
						}
						JSONObject errorMap=errorArray.getJSONObject(0);

						responseObject=new ErrorHandler().errornoHandler(errorMap.optString("code"),responseObject);
						responseObject.put("data", new JSONObject());
						
					}
					}
					
					if(jsonObjectOfAuth!=null) {
						
						if(jsonObjectOfAuth.get("error") instanceof JSONNull) {
							responseObject.put("errno", 0);
							responseObject.put("error", "success");
							responseObject.put("data", new JSONObject());
						}else {
							
							JSONArray errorArray=jsonObjectOfAuth.getJSONArray("error");
							//判断errorArray.size是否为0  2019-1-2 refine
							if(errorArray.size()==0) {
								throw new MyException(71,"error数组长度为0");
							}
							JSONObject errorMap=errorArray.getJSONObject(0);

							responseObject=new ErrorHandler().errornoHandler(errorMap.optString("code"),responseObject);
							responseObject.put("data", new JSONObject());
							
						}
						
					}
				
				
				
				
				
				
				
				
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
//					
//					JSONObject errorMap=errorArray.getJSONObject(0);
//
//					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
//					responseObject.put("data", new JSONObject());
//					
//				}
				
				logger.info("返回给前端的json数据:"+responseObject.toString());	

				return responseObject;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/OrganizationAuth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject OrganizationAuth(@RequestBody JSONObject AuthJson) {
		
		
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		
		JSONArray orgArray=AuthJson.getJSONArray("info");
		for(int i=0;i<orgArray.size();i++) {
			//定义访问区块链接口所需要的json
			JSONObject requestJson=new JSONObject();
			JSONObject permissionObject=new JSONObject();
			JSONObject orgObject=orgArray.getJSONObject(i);
			JSONArray authArray=orgObject.getJSONArray("auth");
			
			String authStr="";
			permissionObject.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
			permissionObject.put("dataType", "OrgAuth");
			permissionObject.put("organizationId", AuthJson.getString("organizationId"));
			permissionObject.put("grantor", AuthJson.getString("organizationId"));
			permissionObject.put("grantee", orgObject.getString("orgid"));
			for(int j=0;j<authArray.size();j++) {
				authStr+=authArray.getString(j)+";";
			}
			permissionObject.put("content", authStr);
			requestJson.put("Permission", permissionObject.toString());
			
			String resultJson=null;
			try {
				resultJson = new RestUtil().httpPost(path+"/IP/CreatePermissionInformation", requestJson);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//从区块链获取的jsonObject
			System.out.println(resultJson);
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			System.out.println(resultJson);
			

			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				
				responseObject.put("data", new JSONObject());
				
				return responseObject;
			}
		}
		
		
		
//      2.判断返回状态码
		//获取error信息
		
		
		//获取content信息(contentMap)
//		if(jsonObject.get("data") instanceof JSONNull) {
//			
//			responseObject.put("data", new HashMap());
//			
//		}else {
//			
//			JSONArray billlist=new JSONArray();
//			JSONObject object=new JSONObject();
//			JSONArray contentArray=jsonObject.getJSONArray("data");
//			for(int i=0;i<contentArray.size();i++) {
//				JSONObject billmap=contentArray.getJSONObject(i);
//				JSONObject billObject=JSONObject.fromObject(billmap.getString("content"));
//				billObject.remove("location");
//				object.put("orgid", billObject.getString("id"));
//				object.put("orgname", billObject.getString("name"));
//				billlist.add(object);
//			}
//            JSONObject billOfLadingObject=new JSONObject();
//            billOfLadingObject.put("orginfolist", billlist);
//			responseObject.put("data", billOfLadingObject);
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
	@RequestMapping(value = "/QueryIP", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryIP(@RequestBody JSONObject ipJson) {
		
		if(ipJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(ipJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+ipJson.toString());
		
		JSONObject responseObject=new JSONObject();
		if(ipJson.optString("id").equals("")) {
			throw new MyException(102,"ID为必传项");
		}
//		IP ipForQuery=(IP)JSONObject.toBean(ipJson, IP.class);
		IP ipForQuery=new IP();
		ipForQuery.setId(ipJson.optString("id"));
		
		List <IP> ipList = ipService.SelectIPByObject(ipForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(ipList==null||ipList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (IP ip : ipList) {
				
				dataArray.add(JsonUtil.fromObject(ip));
				
			}
			dataObject.put("ipList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryIPOfHDD", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryIPOfHDD(@RequestBody JSONObject ipJson) {
		
		if(ipJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(ipJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+ipJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		IP ipForQuery=(IP)JSONObject.toBean(ipJson, IP.class);
		
		
		List <IP> ipList = ipService.SelectIPByObject(ipForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		String id="";
		String name="";
		JSONObject ipObject=new JSONObject();
		if(ipList==null||ipList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (IP ip : ipList) {
				
				id=ip.getId();
				name=ip.getName();
				ipObject.put("id", id);
				ipObject.put("name", name);
				dataArray.add(ipObject);
				
			}
			dataObject.put("ipList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryIPOfHPlus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryIPOfHPlus(@RequestBody JSONObject ipJson) {
		
		if(ipJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(ipJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+ipJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
//		IP ipForQuery=(IP)JSONObject.toBean(ipJson, IP.class);
		IP ipForQuery=new IP();
		ipForQuery.setId(ipJson.optString("id"));
		
		List <IP> ipList = ipService.SelectIPByObject(ipForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		String id="";
		String name="";
		
		JSONObject ipObject=new JSONObject();
		if(ipList==null||ipList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (IP ip : ipList) {
				
				JSONArray ability=new JSONArray();
				ability=JSONArray.fromObject(ip.getAbility());
				id=ip.getId();
				name=ip.getName();
				ipObject.put("id", id);
				ipObject.put("name", name);
				ipObject.put("img", "");
				ipObject.put("ability", ability);
				dataArray.add(ipObject);
				
			}
			dataObject.put("ipList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreateIP", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateIP(@RequestBody JSONObject ipJson) {
		
		if(ipJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(ipJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+ipJson.toString());
		
//		int resultOfIP=0;
//		int resultOfLocation=0;
//		int resultOfProduct=0;
//		int resultOfAbility=0;
		
		if(ipJson.optString("id").equals("")) {
			throw new MyException(230,"id为空");
		}
		
		JSONObject responseObject=new JSONObject();
		ipJson.put("dataType", "IP");
		
		
		
		IP ip=(IP)JSONObject.toBean(ipJson, IP.class);
		
		
//		resultOfIP =ipService.CreateIP(ip);
//		if(resultOfIP<1) {
//			throw new MyException(221,"创建IP失败");
//		}
		
		
//		Ability ability=new Ability();
//		ability.setIpId(ipJson.optString("id"));
//		resultOfAbility=ipService.CreateAbility(ability);
//		if(resultOfAbility<1) {
//			throw new MyException(224,"创建Ability失败");
//		}
		
//		if(ipJson.optJSONArray("location")!=null&&ipJson.optJSONArray("location").size()!=0) {
//			JSONArray locationList=ipJson.optJSONArray("location");
//			for(int i=0;i<locationList.size();i++) {
//				JSONObject locationObject=locationList.getJSONObject(i);
//				Location location=(Location)JSONObject.toBean(locationObject, Location.class);
//				location.setIpId(ipJson.optString("id"));
//				resultOfLocation =ipService.CreateLocation(location);
//				if(resultOfLocation<1) {
//					throw new MyException(223,"创建Location失败");
//				}
//			}
//		}
//		
//		if(ipJson.optJSONArray("product")!=null&&ipJson.optJSONArray("product").size()!=0) {
//			JSONArray productList=ipJson.optJSONArray("product");
//			for(int i=0;i<productList.size();i++) {
//				JSONObject productObject=productList.getJSONObject(i);
//				Product product=(Product)JSONObject.toBean(productObject, Product.class);
//				product.setIpId(ipJson.optString("id"));
//				resultOfProduct =ipService.CreateProduct(product);
//				if(resultOfProduct<1) {
//					throw new MyException(222,"创建Product失败");
//				}
//			}
//		}
		
//		if(resultOfIP==0) {
//			responseObject.put("errno", 221);
//			responseObject.put("error", "创建IP失败");
//			responseObject.put("data", new JSONObject());
//		}else {
//			responseObject.put("errno", 0);
//			responseObject.put("error", "success");
//			responseObject.put("data", new JSONObject());
//		}
		
		responseObject=ipService.CreateIP(ip);
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryInfo(@RequestBody JSONObject ipJson) {
		
		if(ipJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(ipJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+ipJson.toString());
		
		if(ipJson.optString("organizationId").equals("")) {
			throw new MyException(230,"id为空");
		}
		JSONObject responseObject=new JSONObject();
		
//		IP ipForQuery=(IP)JSONObject.toBean(ipJson, IP.class);
		IP ipForQuery=new IP();
		ipForQuery.setId(ipJson.optString("organizationId"));
		
		responseObject = ipService.SelectInfoByObject(ipForQuery);
		
//		JSONObject dataObject=new JSONObject();
//		JSONArray dataArray=new JSONArray();
//		if(ipList==null||ipList.size()==0) {
//			responseObject.put("errno", 25);
//			responseObject.put("error", "自定义查询结果为空");
//			responseObject.put("data", new JSONObject());
//		}else {
//			
//			
//			for (HashMap resultMap : ipList) {
//				
//				
//				dataArray.add(JsonUtil.fromObject(resultMap));
//				
//			}
//			dataObject.put("ipList", dataArray);
//			responseObject.put("errno", 0);
//			responseObject.put("error", "success");
//			responseObject.put("data", dataObject);
//		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}

}
