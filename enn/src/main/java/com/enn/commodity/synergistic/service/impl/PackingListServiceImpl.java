package com.enn.commodity.synergistic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commodity.service.BillOfLadingServices;
import com.commodity.service.PackingListServices;
import com.enn.commodity.synergistic.controller.PurchaseOrderController;
import com.enn.commodity.synergistic.dao.IPDao;
import com.enn.commodity.synergistic.dao.PackingListDao;
import com.enn.commodity.synergistic.dao.PurchaseOrderDao;
import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.PackingList;
import com.enn.commodity.synergistic.entity.QualityInfo;
import com.enn.commodity.synergistic.service.PackingListService;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONObject;

@Service
public class PackingListServiceImpl implements PackingListService{
	
	public static Logger logger = Logger.getLogger(PurchaseOrderController.class);
	
	public static String path="http://192.168.138.86:8080/commodity/service";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private PackingListServices packingListServices;
	
	@Autowired
	private BillOfLadingServices billOfLadingServices;
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;

	@Autowired
	private PackingListDao packingListDao;
	
	@Autowired
	private IPDao ipDao;
	@Override
	public String CreatePackingList(JSONObject requestJson) {
		//区块链返回的json字符串
		JSONObject resultJsonObject=new JSONObject();
		
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/CreatePackingList", requestJson);
//			
//			resultJsonObject.put("resultJson", resultJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=packingListServices.createPackingList(requestJson.optString("PackingList"));
		
			if(resultJson==null) {
				logger.info("区块链返回null");
				
				throw new MyException(81,"区块链返回null");
			}
			if(!jsonUtil.isJson(resultJson)) {
				
				logger.info("区块链返回格式不是json");
				
				throw new MyException(23,"区块链返回格式不符合预期要求");
				
			}
			
			
			
			//获取承运商信息
			String packingListStr=requestJson.getString("PackingList");
			JSONObject packingListObj=JSONObject.fromObject(packingListStr);
			
			
			JSONObject plateObject=new JSONObject();
			plateObject.put("plateno", packingListObj.optString("plateNo"));
			
			JSONObject deiverInfo=userService.QueryDriverInfoByPlateNo(plateObject);
			if(!deiverInfo.getString("error").equals("success")) {
				throw new MyException(29,"没有找到当前车牌信息");
			}
			
			
			
			JSONObject requestJsonForUpdate=new JSONObject();
			
			requestJsonForUpdate.put("BillOfLadingId", packingListObj.optString("billOfLadingId"));
			requestJsonForUpdate.put("PlateNo", packingListObj.optString("plateNo"));
			requestJsonForUpdate.put("DriverId", deiverInfo.getJSONObject("data").optString("driverId"));
			requestJsonForUpdate.put("DriverName", deiverInfo.getJSONObject("data").optString("driverName"));
			requestJsonForUpdate.put("CarrierId", deiverInfo.getJSONObject("data").optString("carrierId"));
			requestJsonForUpdate.put("CarrierName", deiverInfo.getJSONObject("data").optString("carrierName"));
			
			String resultJsonOfUpdate=null;
//			try {
//				resultJsonOfUpdate = new RestUtil().httpPost(path+"/BillOfLading/UpdateBillOfLading", requestJsonForUpdate);
//				
//				resultJsonObject.put("resultJsonOfUpdate", resultJsonOfUpdate);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				
//			}
			resultJsonOfUpdate=billOfLadingServices.updateBillOfLading(requestJsonForUpdate.optString("BillOfLadingId"), requestJsonForUpdate.optString("PlateNo"), requestJsonForUpdate.optString("DriverId"), requestJsonForUpdate.optString("DriverName"), requestJsonForUpdate.optString("CarrierId"), requestJsonForUpdate.optString("CarrierName"));
			resultJsonObject.put("resultJsonOfUpdate", resultJsonOfUpdate);
				if(resultJsonOfUpdate==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJsonOfUpdate)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
				
				
			//将null转换为""
//			resultJson=jsonUtil.fromObject(resultJson);
			
			return resultJsonObject.toString();
	}

	@Override
	public String QueryPackingList(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/QueryPackingList", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		
		resultJson=packingListServices.queryBillOfLading(requestJson.optString("PackingListId"), requestJson.optString("PlateNumber"), requestJson.optString("UserOrgId"), requestJson.optString("LeaveDate"));
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

	@Override
	public String CreateArrivalList(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/CreateArrivalList", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=packingListServices.createArrivalList(requestJson.optString("ArrivalList"));
		
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

	@Override
	public String QueryArrivalList(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/QueryArrivalList", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=packingListServices.queryArrivalList(requestJson.optString("ArrivalListId"), requestJson.optString("PlateNumber"), requestJson.optString("UserOrgId"),  requestJson.optString("LeaveDate"));
		
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

	@Override
	public String CompareInOutFactory(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/CompareInOutFactory", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=packingListServices.compareInOutFactory(requestJson.optString("ArriveDate"), requestJson.optString("LeaveDate"), requestJson.optString("PlateNumber"), requestJson.optString("UserOrgId"));
		
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

	@Override
	public String CreateQualityInfo(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/CreateQualityInfo", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=packingListServices.createQualityInfo(requestJson.optString("QualityInfo"));
		
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

	@Override
	public String CompareQualityInfo(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PackingList/CompareQualityInfo", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=packingListServices.compareQualityInfo(requestJson.optString("PlateNumber"), requestJson.optString("ArriveDate"), requestJson.optString("PurchasingOrderNo"), requestJson.optString("UserOrgId"));
		
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

	@Override
	public List<PackingList> SelectPackingListByObject(PackingList packingList) {
		
		return packingListDao.SelectPackingListByObject(packingList);
	}

	@Override
	public List<QualityInfo> SelectQualityInfoByObject(QualityInfo qualityInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject CreatePackingList(PackingList packingList) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		int resultOfPackingList=0;
		int resultOfAbility=0;
		int resultOfUpdate=0;
		
		resultOfPackingList=packingListDao.CreatePackingList(packingList);
		
		if(resultOfPackingList<1) {
			throw new MyException(281,"创建PackingList失败");
		}
		
		//获取完成重量
		String packWeight=packingList.getWeight();
		PO poForQuery=new PO();
		if(packingList.getPackingType().equals("packingList")) {
			if(packingList.getSalesPONumber()==null||("").equals(packingList.getSalesPONumber())) {
				throw new MyException(281,"装箱单缺失销售订单号");
			}
			poForQuery.setId(packingList.getSalesPONumber());
			
		}else if(packingList.getPackingType().equals("arrivalList")) {
			if(packingList.getPurchasePONumber()==null||("").equals(packingList.getPurchasePONumber())) {
				throw new MyException(281,"到货单缺失采购订单号");
			}
			poForQuery.setId(packingList.getPurchasePONumber());
		}else {
			throw new MyException(281,"缺失单据类型");
		}
		
		List<PO> poList=purchaseOrderDao.SelectPOByObject(poForQuery);
		if(poList.size()<1) {
			//订单不存在
			throw new MyException(281,"订单不存在");
		}
		PO poOfPacking=poList.get(0);
		String completeWeight=poOfPacking.getCompleteWeight();
		String carNumber=poOfPacking.getCarNumber();
		if(completeWeight==null||("").equals(completeWeight)) {
			completeWeight="0";
		}
		if(carNumber==null||("").equals(carNumber)) {
			carNumber="0";
		}
		float weight=Float.parseFloat(completeWeight);
		weight=weight+Float.parseFloat(packWeight);
		completeWeight=String.valueOf(weight);
		poForQuery.setCompleteWeight(completeWeight);
		
		int number=Integer.parseInt(carNumber);
		number+=1;
		carNumber=String.valueOf(number);
		poForQuery.setCarNumber(carNumber);
		
		resultOfUpdate=purchaseOrderDao.UpdatePO(poForQuery);
		
		if(resultOfUpdate<1) {
			throw new MyException(281,"更新订单信息失败");
		}
		
		//判断ability
				IP ipForQueryBuyer=new IP();
				ipForQueryBuyer.setId(packingList.getBuyerId());
				
				List <IP> ipListBuyer = ipDao.SelectIPByObject(ipForQueryBuyer);
				
				IP ipForQuerySeller=new IP();
				ipForQuerySeller.setId(packingList.getSellerId());
				
				List <IP> ipListSeller = ipDao.SelectIPByObject(ipForQuerySeller);
				
			
				
				List<Ability> abilityListBuyer=null;
				List<Ability> abilityListSeller=null;
//				abilityListBuyer=ipListBuyer.get(0).getAbility();
//				abilityListSeller=ipListSeller.get(0).getAbility();
//				
//				if(ipListBuyer==null||ipListBuyer.size()==0) {
//					logger.info("buyer不是化plus");
//				}else {
//					for (Ability abilityBuyer : abilityListBuyer) {
//						if(abilityBuyer.getName().equals("PackingList")) {
//							if(abilityBuyer.getValue().equals("false")) {
//								//修改为true
//								abilityBuyer.setValue("true");
//								resultOfAbility=ipDao.UpdateAbility(abilityBuyer);
//								if(resultOfAbility<1) {
//									throw new MyException(224,"修改Ability失败");
//								}
//							}
//						}
//						
//					}
//				}
//				
//				if(ipListSeller==null||ipListSeller.size()==0) {
//					logger.info("seller不是化plus");
//				}else {
//					for (Ability abilitySeller : abilityListSeller) {
//						if(abilitySeller.getName().equals("PackingList")) {
//							if(abilitySeller.getValue().equals("false")) {
//								//修改为true
//								abilitySeller.setValue("true");
//								resultOfAbility=ipDao.UpdateAbility(abilitySeller);
//								if(resultOfAbility<1) {
//									throw new MyException(224,"修改Ability失败");
//								}
//							}
//						}
//						
//					}
//				}
				if(ipListBuyer.size()!=0) {
					abilityListBuyer=ipListBuyer.get(0).getAbility();
					for (Ability abilityBuyer : abilityListBuyer) {
						if(abilityBuyer.getName().equals("PackingList")) {
							if(abilityBuyer.getValue().equals("false")) {
								//修改为true
								abilityBuyer.setValue("true");
								resultOfAbility=ipDao.UpdateAbility(abilityBuyer);
								if(resultOfAbility<1) {
									throw new MyException(224,"修改Ability失败");
								}
							}
						}
						
					}
				}else {
					logger.info("buyer不是化plus");
				}
				
				if(ipListSeller.size()!=0) {
					abilityListSeller=ipListSeller.get(0).getAbility();
					for (Ability abilitySeller : abilityListSeller) {
						if(abilitySeller.getName().equals("PackingList")) {
							if(abilitySeller.getValue().equals("false")) {
								//修改为true
								abilitySeller.setValue("true");
								resultOfAbility=ipDao.UpdateAbility(abilitySeller);
								if(resultOfAbility<1) {
									throw new MyException(224,"修改Ability失败");
								}
							}
						}
						
					}
				}else {
					logger.info("seller不是化plus");
				}
				
				resultJson.put("errno", 0);
				resultJson.put("error", "success");
				resultJson.put("data", new JSONObject());
		
		return resultJson;
	}

	@Override
	public JSONObject CreateQualityInfo(QualityInfo qualityInfo) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		int resultOfQualityInfo=0;
		int resultOfAbility=0;
		
		resultOfQualityInfo=packingListDao.CreateQualityInfo(qualityInfo);
		
		if(resultOfQualityInfo<1) {
			throw new MyException(301,"创建QualityInfo失败");
		}
		
		//判断ability
//				IP ipForQueryBuyer=new IP();
//				ipForQueryBuyer.setId(qualityInfo.getBuyerId());
//				
//				List <IP> ipListBuyer = ipDao.SelectIPByObject(ipForQueryBuyer);
//				
//				IP ipForQuerySeller=new IP();
//				ipForQuerySeller.setId(qualityInfo.getSellerId());
//				
//				List <IP> ipListSeller = ipDao.SelectIPByObject(ipForQuerySeller);
//				
//			
//				
//				List<Ability> abilityListBuyer=null;
//				List<Ability> abilityListSeller=null;
//				abilityListBuyer=ipListBuyer.get(0).getAbility();
//				abilityListSeller=ipListSeller.get(0).getAbility();
//				
//				if(ipListBuyer==null||ipListBuyer.size()==0) {
//					logger.info("buyer不是化plus");
//				}else {
//					for (Ability abilityBuyer : abilityListBuyer) {
//						if(abilityBuyer.getName().equals("PackingList")) {
//							if(abilityBuyer.getValue().equals("false")) {
//								//修改为true
//								abilityBuyer.setValue("true");
//								resultOfAbility=ipDao.UpdateAbility(abilityBuyer);
//								if(resultOfAbility<1) {
//									throw new MyException(224,"修改Ability失败");
//								}
//							}
//						}
//						
//					}
//				}
//				
//				if(ipListSeller==null||ipListSeller.size()==0) {
//					logger.info("seller不是化plus");
//				}else {
//					for (Ability abilitySeller : abilityListSeller) {
//						if(abilitySeller.getName().equals("PackingList")) {
//							if(abilitySeller.getValue().equals("false")) {
//								//修改为true
//								abilitySeller.setValue("true");
//								resultOfAbility=ipDao.UpdateAbility(abilitySeller);
//								if(resultOfAbility<1) {
//									throw new MyException(224,"修改Ability失败");
//								}
//							}
//						}
//						
//					}
//				}
				
				resultJson.put("errno", 0);
				resultJson.put("error", "success");
				resultJson.put("data", new JSONObject());
		
		return resultJson;
	}

	@Override
	public List<PackingList> SelectPackingListByObjectIn(Map<String, String> map) {
		// TODO Auto-generated method stub
		return packingListDao.SelectPackingListByObjectIn(map);
	}

}
