package com.enn.commodity.synergistic.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commodity.service.PurchaseOrderServices;
import com.enn.commodity.synergistic.controller.MyControllerAdvice;
import com.enn.commodity.synergistic.controller.PurchaseOrderController;
import com.enn.commodity.synergistic.dao.BillOfLadingDao;
import com.enn.commodity.synergistic.dao.IPDao;
import com.enn.commodity.synergistic.dao.PurchaseOrderDao;
import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.BillOfLading;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.MaterialDetail;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.Payment;
import com.enn.commodity.synergistic.entity.PaymentDetail;
import com.enn.commodity.synergistic.exception.HttpException;
import com.enn.commodity.synergistic.service.PurchaseOrderService;
import com.enn.commodity.synergistic.util.ExcelUtil;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	
    public static Logger logger = Logger.getLogger(PurchaseOrderController.class);
	
	public static String path="http://192.168.138.86:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private PurchaseOrderServices purchaseOrderServices;
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	private IPDao ipDao;

	@Autowired
	private BillOfLadingDao billOfLadingDao;
	
	@Override
	public String CreateSalesOrder(JSONObject requestJson)  {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/CreateSalesOrder", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=purchaseOrderServices.createSalesOrder(requestJson.optString("SalesOrder"));
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
	public String QuerySalesOrder(JSONObject requestJson) {


		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/QuerySalesOrder", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=purchaseOrderServices.queryBillOfLading(requestJson.optString("SalesOrderId"), requestJson.optString("UserOrgId"));
		
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
	public String QueryPurchaseOrder(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/QueryPurchaseOrder", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=purchaseOrderServices.queryPurchaseOrder(requestJson.optString("PurchaseOrderId"), requestJson.optString("UserOrgId"));
		
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
	public String CreatePurchaseOrder(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/CreatePurchaseOrder", requestJson);
//		} catch (Exception e) {
//					
//			e.printStackTrace();
//					
//		}
		resultJson=purchaseOrderServices.createPurchaseOrder(requestJson.optString("PurchaseOrder"));
		
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
	public String SelectSalesOrder(JSONObject requestJson) {


		//区块链返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/SelectSalesOrder", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					
//				}
				resultJson=purchaseOrderServices.selectSalesOrder(requestJson.optString("BuyerId"), requestJson.optString("UserOrgId"));
				
					if(resultJson==null) {
						logger.info("区块链返回null");
						
						throw new MyException(81,"区块链返回null");
					}
					if(!jsonUtil.isJson(resultJson)) {
						
						logger.info("区块链返回格式不是json");
						
						throw new MyException(23,"区块链返回格式不符合预期要求");
						
					}
					//将null转换为""
//					resultJson=jsonUtil.fromObject(resultJson);
					
					return resultJson;
	}

	@Override
	public String SelectPurchaseOrderOfSaler(JSONObject requestJson) {
		
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/SelectPurchaseOrderOfSaler", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=purchaseOrderServices.selectPurchaseOrderOfSaler(requestJson.optString("BuyerId"), requestJson.optString("UserOrgId"));
		
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
	public String SelectPurchaseOrderOfBuyer(JSONObject requestJson) {
		//区块链返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/SelectPurchaseOrderOfBuyer", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					
//				}
				resultJson=purchaseOrderServices.selectPurchaseOrder(requestJson.optString("SellerId"), requestJson.optString("UserOrgId"));
				
					if(resultJson==null) {
						logger.info("区块链返回null");
						
						throw new MyException(81,"区块链返回null");
					}
					if(!jsonUtil.isJson(resultJson)) {
						
						logger.info("区块链返回格式不是json");
						
						throw new MyException(23,"区块链返回格式不符合预期要求");
						
					}
					//将null转换为""
//					resultJson=jsonUtil.fromObject(resultJson);
					
					return resultJson;
	}

	@Override
	public String QueryStatusOfPO(JSONObject requestJson) {


//		//区块链返回的json字符串
//				String resultJson=null;
////				try {
////					resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/QueryStatusOfPO", requestJson);
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////					
////				}
//				resultJson=purchaseOrderServices.queryStatusOfPO(requestJson.optString("PurchaseOrderId"), requestJson.optString("SalesOrderId"), requestJson.optString("UserOrgId"));
//				
//					if(resultJson==null) {
//						logger.info("区块链返回null");
//						
//						throw new MyException(81,"区块链返回null");
//					}
//					if(!jsonUtil.isJson(resultJson)) {
//						
//						logger.info("区块链返回格式不是json");
//						
//						throw new MyException(23,"区块链返回格式不符合预期要求");
//						
//					}
//					//将null转换为""
////					resultJson=jsonUtil.fromObject(resultJson);
//					
//					return resultJson;
		JSONObject resultJson=new JSONObject();
		
		int billOfLadingNum=0;
		int billOfLadingWeight=0;
		int packingListNum=0;
		int packingListWeight=0;
		int receiveListNum=0;
		int receiveListWeight=0;
		
		String salesorderproductname=null;
		String salesorderweight=null;
		String salesorderdate=null;
		String salesorderstatus=null;
		
		if(!requestJson.optString("purchasingorderno").equals("")&&requestJson.optString("salesorderno").equals("")) {
			//有采购订单号
			
		}else if(!requestJson.optString("salesorderno").equals("")&&requestJson.optString("purchasingorderno").equals("")) {
			//有销售订单号
			
			//查询提货单
			BillOfLading billOfLadingForQuery=new BillOfLading();
			billOfLadingForQuery.setSalesPONumber(requestJson.optString("salesorderno"));
			List<BillOfLading> billOfLadingList=billOfLadingDao.SelectBillOfLadingByObject(billOfLadingForQuery);
			billOfLadingNum=billOfLadingList.size();
			
			for (BillOfLading billOfLading : billOfLadingList) {
				int billWeight=0;
				if(!("").equals(billOfLading.getWeight())&&billOfLading.getWeight()!=null) {
					billWeight=Integer.parseInt(billOfLading.getWeight());
				}
				
				
				billOfLadingWeight+=billWeight;
			}
			
			//查询销售订单
			PO poForQuery=new PO();
			poForQuery.setId(requestJson.optString("salesorderno"));
			List<PO> poList=purchaseOrderDao.SelectPOByObject(poForQuery);
			salesorderproductname=poList.get(0).getProductName();
			salesorderweight=poList.get(0).getCompleteWeight();
			salesorderdate=poList.get(0).getIssuedDate();
			salesorderstatus=poList.get(0).getStatus();
			
			
		}
		
		
		return null;
	}

	@Override
	public String ComparePO(JSONObject requestJson) {
		
		//区块链返回的json字符串
				String resultJson=null;
//				try {
//					resultJson = new RestUtil().httpPost(path+"/PurchaseOrder/ComparePO", requestJson);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					
//				}
				resultJson=purchaseOrderServices.compareOrder(requestJson.optString("SalesOrderId"), requestJson.optString("PurchaseOrderId"), requestJson.optString("UserOrgId"), requestJson.optString("ContractNumber"));
				
				
					if(resultJson==null) {
						logger.info("区块链返回null");
						
						throw new MyException(81,"区块链返回null");
					}
					if(!jsonUtil.isJson(resultJson)) {
						
						logger.info("区块链返回格式不是json");
						
						throw new MyException(23,"区块链返回格式不符合预期要求");
						
					}
					//将null转换为""
//					resultJson=jsonUtil.fromObject(resultJson);
					
					return resultJson;
	}

	@Override
	public List<Payment> SelectPaymentByObject(Payment payment) {
		
		return purchaseOrderDao.SelectPaymentByObject(payment);
	}

	@Override
	public List<PO> SelectPOByObject(PO po) {
		
		return purchaseOrderDao.SelectPOByObject(po);
	}

	@Override
	public JSONObject CreatePO(PO po) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		
		int resultOfPO=0;
		int resultOfPayment=0;
		int resultOfPaymentDetail=0;
		int resultOfMaterialDetail=0;
		int resultOfAbility=0;
		
		
		
//		if(("OverRide").equals(po.getStatus())) {
//			PO poForQuery=new PO();
//			poForQuery.setId(po.getId());
//			
//			String materialDetailId=purchaseOrderDao.SelectPOByObject(poForQuery).get(0).getMaterialdetail().getId();
//			int resultOfUpdate=purchaseOrderDao.UpdatePO(po);
//			
//			if(resultOfUpdate<1) {
//				throw new MyException(342,"修改PO失败");
//			}
//			
//			if(po.getMaterialdetail()!=null) {
//				resultOfMaterialDetail=purchaseOrderDao.UpdateMaterialDetail(po.getMaterialdetail());
//				
//				if(resultOfMaterialDetail<1) {
//					throw new MyException(362,"修改MaterialDetail失败");
//				}
//			}
//			
//			
//		}
		
		
		
		
		if(po.getMaterialdetail()==null) {
			MaterialDetail materialDetail=new MaterialDetail();
			String materialDetailId=UUID.randomUUID().toString().replace("-", "").toLowerCase();
			materialDetail.setId(materialDetailId);
			po.setMaterialdetail(materialDetail);
		}
		
		
		
		resultOfPO=purchaseOrderDao.CreatePO(po);
		
		if(resultOfPO<1) {
			throw new MyException(224,"创建PO失败");
		}
		//判断ability
		IP ipForQueryBuyer=new IP();
		ipForQueryBuyer.setId(po.getBuyerId());
		
		List <IP> ipListBuyer = ipDao.SelectIPByObject(ipForQueryBuyer);
		
		IP ipForQuerySeller=new IP();
		ipForQuerySeller.setId(po.getSellerId());
		
		List <IP> ipListSeller = ipDao.SelectIPByObject(ipForQuerySeller);
		
	
		
			
			List<Ability> abilityListBuyer=null;
			List<Ability> abilityListSeller=null;
			
			if(ipListBuyer.size()!=0) {
				abilityListBuyer=ipListBuyer.get(0).getAbility();
				for (Ability abilityBuyer : abilityListBuyer) {
					if(abilityBuyer.getName().equals("PO")) {
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
					if(abilitySeller.getName().equals("PO")) {
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
			
//			abilityListBuyer=ipListBuyer.get(0).getAbility();
//			abilityListSeller=ipListSeller.get(0).getAbility();
			
//			if(ipListBuyer==null||ipListBuyer.size()==0) {
//				logger.info("buyer不是化plus");
//			}else {
//				for (Ability abilityBuyer : abilityListBuyer) {
//					if(abilityBuyer.getName().equals("PO")) {
//						if(abilityBuyer.getValue().equals("false")) {
//							//修改为true
//							abilityBuyer.setValue("true");
//							resultOfAbility=ipDao.UpdateAbility(abilityBuyer);
//							if(resultOfAbility<1) {
//								throw new MyException(224,"修改Ability失败");
//							}
//						}
//					}
//					
//				}
//			}
//			
//			if(ipListSeller==null||ipListSeller.size()==0) {
//				logger.info("seller不是化plus");
//			}else {
//				for (Ability abilitySeller : abilityListSeller) {
//					if(abilitySeller.getName().equals("PO")) {
//						if(abilitySeller.getValue().equals("false")) {
//							//修改为true
//							abilitySeller.setValue("true");
//							resultOfAbility=ipDao.UpdateAbility(abilitySeller);
//							if(resultOfAbility<1) {
//								throw new MyException(224,"修改Ability失败");
//							}
//						}
//					}
//					
//				}
//			}
			
		
		
		
		
		
		
		resultOfMaterialDetail=purchaseOrderDao.CreateMaterialDetail(po.getMaterialdetail());
		
		if(resultOfMaterialDetail<1) {
			throw new MyException(224,"创建MaterialDetail失败");
		}
		if(po.getPayment()==null) {
			
			Payment payment=new Payment();
			payment.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			payment.setPoId(po.getId());
			resultOfPayment=purchaseOrderDao.CreatePayment(payment);
			if(resultOfPayment<1) {
				throw new MyException(224,"创建Payment失败");
			}
			
			PaymentDetail paymentDetail=payment.getPaymentdetail();
			
            if(paymentDetail==null) {
            	paymentDetail=new PaymentDetail();
			}
            paymentDetail.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			paymentDetail.setPaymentId(payment.getId());
			payment.setPaymentdetail(paymentDetail);
			resultOfPaymentDetail=purchaseOrderDao.CreatePaymentDetail(paymentDetail);
			if(resultOfPaymentDetail<1) {
				throw new MyException(224,"创建PaymentDetail失败");
			}
			
		}else {
			
			for (Payment payment : po.getPayment()) {
				
				
				payment.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
				payment.setPoId(po.getId());
				resultOfPayment=purchaseOrderDao.CreatePayment(payment);
				if(resultOfPayment<1) {
					throw new MyException(224,"创建Payment失败");
				}
				
				PaymentDetail paymentDetail=payment.getPaymentdetail();
				
	            if(paymentDetail==null) {
	            	paymentDetail=new PaymentDetail();
				}
	            paymentDetail.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
				paymentDetail.setPaymentId(payment.getId());
				payment.setPaymentdetail(paymentDetail);
				resultOfPaymentDetail=purchaseOrderDao.CreatePaymentDetail(paymentDetail);
				if(resultOfPaymentDetail<1) {
					throw new MyException(224,"创建PaymentDetail失败");
				}
			}
			
		}
		
		
		resultJson.put("errno", 0);
		resultJson.put("error", "success");
		resultJson.put("data", new JSONObject());
		return resultJson;
	}

	@Override
	public List<PO> SelectPOByObjectIn(Map<String,String>map) {
		// TODO Auto-generated method stub
		return purchaseOrderDao.SelectPOByObjectIn(map);
	}

	@Override
	public JSONObject UpdatePO(PO po) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		
		int resultOfPO=0;
		int resultOfMaterialDetail=0;
		
		if(po.getStatus()!=null&&!("").equals(po.getStatus())) {
			resultOfPO=purchaseOrderDao.UpdatePO(po);
			
			if(resultOfPO<1) {
				throw new MyException(342,"修改PO失败");
			}
		}
		
		if(po.getMaterialdetail()!=null) {
			resultOfMaterialDetail=purchaseOrderDao.UpdateMaterialDetail(po.getMaterialdetail());
			
			if(resultOfMaterialDetail<1) {
				throw new MyException(362,"修改MaterialDetail失败");
			}
		}
		
		resultJson.put("errno", 0);
		resultJson.put("error", "success");
		resultJson.put("data", new JSONObject());
		return resultJson;
		
		
	}

	@Override
	public JSONObject DeletePO(PO po) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		
		int resultOfPO=0;
		int resultOfMaterialDetail=0;
		int resultOfPayment=0;
		int resultOfPaymemtDetail=0;
		
		List<PO> poList=purchaseOrderDao.SelectPOByObject(po);
		
		if(poList==null||poList.size()==0) {
			throw new MyException(362,"订单不存在");
		}
		
		String materialDetailId=poList.get(0).getMaterialdetail().getId();
		
		MaterialDetail materialDetail=new MaterialDetail();
		materialDetail.setId(materialDetailId);
		po.setMaterialdetail(materialDetail);
		
		resultOfMaterialDetail=purchaseOrderDao.DeleteMaterialDetail(po);
		if(resultOfMaterialDetail<1) {
			throw new MyException(362,"删除MaterialDetail失败");
		}
		
		
		
		List<Payment> paymentList=poList.get(0).getPayment();
		
		for (Payment payment : paymentList) {
			resultOfPaymemtDetail=purchaseOrderDao.DeletePaymentDetail(payment);
			if(resultOfPaymemtDetail<1) {
				throw new MyException(362,"删除PaymentDetail失败");
			}
		}
		
		resultOfPayment=purchaseOrderDao.DeletePayment(po);
		if(resultOfPayment<1) {
			throw new MyException(362,"删除Payment失败");
		}
		
		resultOfPO=purchaseOrderDao.DeletePO(po);
		if(resultOfPO<1) {
			throw new MyException(362,"删除PO失败");
		}
		
		resultJson.put("errno", 0);
		resultJson.put("error", "success");
		resultJson.put("data", new JSONObject());
		return resultJson;
	}

	

	
	
	

}
