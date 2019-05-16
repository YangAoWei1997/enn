package com.enn.commodity.synergistic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commodity.service.BillOfLadingServices;
import com.commodity.service.PackingListServices;
import com.enn.commodity.synergistic.controller.PurchaseOrderController;
import com.enn.commodity.synergistic.dao.BillOfLadingDao;
import com.enn.commodity.synergistic.dao.IPDao;
import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.BillOfLading;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.service.BillOfLadingService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@Service
public class BillOfLadingServiceImpl implements BillOfLadingService{
	
    public static Logger logger = Logger.getLogger(PurchaseOrderController.class);
	
	public static String path="http://192.168.138.86:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private BillOfLadingServices billOfLadingServices;
	
	@Autowired
	private PackingListServices packingListServices;

	@Autowired
	private BillOfLadingDao billOfLadingDao;
	
	@Autowired
	private IPDao ipDao;
	
	@Override
	public String CreateBillOfLading(JSONObject requestJsonOfBill,JSONObject requestJsonOfPacking) {


		//区块链返回的json字符串
		String resultJsonOfBill=null;
		String resultJsonOfPack=null;
		
		JSONObject resultJsonObject=new JSONObject();
//		try {
//			resultJsonOfBill = new RestUtil().httpPost(path+"/BillOfLading/CreateBillOfLading", requestJsonOfBill);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJsonOfBill=billOfLadingServices.createBillOfLading(requestJsonOfBill.optString("BillOfLading"));
		
			if(resultJsonOfBill==null) {
				logger.info("创建提货单时区块链返回null");
				
				throw new MyException(81,"区块链返回null");
			}
			if(!jsonUtil.isJson(resultJsonOfBill)) {
				
				logger.info("创建提货单时区块链返回格式不是json");
				
				throw new MyException(23,"区块链返回格式不符合预期要求");
				
			}
			resultJsonObject.put("resultJsonOfBill", resultJsonOfBill);
			
			
			String packingListStr=requestJsonOfPacking.getString("PackingList");
			JSONObject packingListObj=JSONObject.fromObject(packingListStr);
			
			if(!packingListObj.optString("id").equals("")) {
				
//				try {
//					resultJsonOfPack = new RestUtil().httpPost(path+"/PackingList/CreatePackingList", requestJsonOfPacking);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					
//				}
				resultJsonOfPack=packingListServices.createPackingList(requestJsonOfPacking.optString("PackingList"));
				
					if(resultJsonOfPack==null) {
						logger.info("创建装箱单时区块链返回null");
						
						throw new MyException(81,"区块链返回null");
					}
					if(!jsonUtil.isJson(resultJsonOfPack)) {
						
						logger.info("创建装箱单时区块链返回格式不是json");
						
						throw new MyException(23,"区块链返回格式不符合预期要求");
						
					}
					resultJsonObject.put("resultJsonOfPack", resultJsonOfPack);
			}
			
			
			//将null转换为""
//			resultJson=jsonUtil.fromObject(resultJson);
			
			return resultJsonObject.toString();
	}

	@Override
	public String QueryBillOfLading(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/QueryBillOfLading", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=billOfLadingServices.queryBillOfLading(requestJson.optString("BillOfLadingId"), requestJson.optString("UserOrgId"));
		
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
	public String SelectBillOfLadingByPlateNoAndIssDate(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingByPlateNoAndIssDate", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		
		resultJson=billOfLadingServices.selectBillOfLadingByPlateNoAndIssDate(requestJson.optString("PlateNumber"), requestJson.optString("UserOrgId"), requestJson.optString("PickUpDate"));
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
	public String SelectBillOfLadingBySalesCompanyAndBillId(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingBySalesCompanyAndBillId", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=billOfLadingServices.selectBillOfLadingBySalesCompanyAndBillId(requestJson.optString("SalesCompanyId"), requestJson.optString("UserOrgId"), requestJson.optString("BillOfLadingId"));
		
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
	public String SelectBillOfLadingByPlateNoAndIssDateOfBuyerId(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingByPlateNoAndIssDateOfBuyerId", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=billOfLadingServices.selectBillOfLadingByPlateNoAndIssDateOfBuyerId(requestJson.optString("PlateNumber"), requestJson.optString("UserOrgId"), requestJson.optString("PickUpDate"));
		
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
	public String SelectBillOfLadingBySalesPurchaseAndId(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/SelectBillOfLadingBySalesPurchaseAndId", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=billOfLadingServices.selectBillOfLadingBySalesPurchaseAndId(requestJson.optString("SalesCompanyId"), requestJson.optString("UserOrgId"), requestJson.optString("BillOfLadingId"), requestJson.optString("PurchaseCompanyId"));
		
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
	public String UpdateBillOfLading(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/BillOfLading/UpdateBillOfLading", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=billOfLadingServices.updateBillOfLading(requestJson.optString("BillOfLadingId"), requestJson.optString("PlateNo"), requestJson.optString("DriverId"), requestJson.optString("DriverName"), requestJson.optString("CarrierId"), requestJson.optString("CarrierName"));
		
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
	public String SelectCar(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
		
		resultJson=billOfLadingServices.selectCar(requestJson.optString("PlateNumber"), requestJson.optString("PurchaseOrderId"), requestJson.optString("UserOrgId"));
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		//将null转换为""
//		resultJson=jsonUtil.fromObject(resultJson);
		
		return resultJson;
	}

	@Override
	public List<BillOfLading> SelectBillOfLadingByObject(BillOfLading billOfLading) {
		
		return billOfLadingDao.SelectBillOfLadingByObject(billOfLading);
	}

	@Override
	public JSONObject CreateBillOfLading(BillOfLading billOfLading) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		int resultOfBillOfLading=0;
		int resultOfAbility=0;
		
		resultOfBillOfLading=billOfLadingDao.CreateBillOfLading(billOfLading);
		if(resultOfBillOfLading<1) {
			throw new MyException(224,"创建BillOfLading失败");
		}
		
		
		
//		IP ipForQuery=new IP();
//		ipForQuery.setId(billOfLading.getBuyerId());
//		
//		List <IP> ipList = ipDao.SelectIPByObject(ipForQuery);
//		if(ipList==null||ipList.size()==0) {
//			throw new MyException(81,"buyer不存在");
//		}
//		
//		List<Ability> abilityList=null;
//		abilityList=ipList.get(0).getAbility();
//		
//		for (Ability ability : abilityList) {
//			if(ability.getName().equals("BillOfLading")) {
//				if(ability.getValue().equals("false")) {
//					//修改为true
//					ability.setValue("true");
//					resultOfAbility=ipDao.UpdateAbility(ability);
//					if(resultOfAbility<1) {
//						throw new MyException(224,"修改Ability失败");
//					}
//				}
//			}
//			
//		}
		
		//判断ability
				IP ipForQueryBuyer=new IP();
				ipForQueryBuyer.setId(billOfLading.getBuyerId());
				
				List <IP> ipListBuyer = ipDao.SelectIPByObject(ipForQueryBuyer);
				
				IP ipForQuerySeller=new IP();
				ipForQuerySeller.setId(billOfLading.getSellerId());
				
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
//						if(abilityBuyer.getName().equals("BillOfLading")) {
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
//						if(abilitySeller.getName().equals("BillOfLading")) {
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
						if(abilityBuyer.getName().equals("BillOfLading")) {
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
						if(abilitySeller.getName().equals("BillOfLading")) {
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
	public List<BillOfLading> SelectBillOfLadingByObjectIn(Map<String, String> map) {
		// TODO Auto-generated method stub
		return billOfLadingDao.SelectBillOfLadingByObjectIn(map);
	}

}
