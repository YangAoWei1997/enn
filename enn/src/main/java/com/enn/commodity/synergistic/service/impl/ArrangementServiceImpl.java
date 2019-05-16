package com.enn.commodity.synergistic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commodity.service.ArrangementServices;

import com.enn.commodity.synergistic.dao.ArrangementDao;
import com.enn.commodity.synergistic.dao.IPDao;
import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.MaterialDetail;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.Payment;
import com.enn.commodity.synergistic.entity.PaymentDetail;
import com.enn.commodity.synergistic.service.ArrangementService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;
import com.mchain.apollo.common.entity.base.Arg;
import com.mchain.apollo.common.entity.base.Data;
import com.mchain.apollo.common.entity.base.Message;

import net.sf.json.JSONObject;

@Service
public class ArrangementServiceImpl implements ArrangementService{
	
    public static Logger logger = Logger.getLogger(ArrangementServiceImpl.class);
	
	public static String path="http://192.168.138.101:8080/commodity/service";
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private ArrangementServices arrangementServices;
	
	@Autowired
	private ArrangementDao arrangementDao;
	
	@Autowired
	private IPDao ipDao;

	@Override
	public String CreateArrangement(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/Arrangement/CreateArrangement", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		
		Message<Arrangement> reqMsg=new Message<Arrangement>();
		List<Data> datas = new ArrayList<Data>();
    	List<Arg> args =  new ArrayList<Arg>();
		reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		reqMsg.setTranCode("CreateArrangement");
		reqMsg.setType("0");
		reqMsg.setOrgid("Org1");
		reqMsg.setUserid("Org1Admin");
		reqMsg.setHasGeneric(false);
		
		Arg arg1=new Arg();
		arg1.setName("channelName");
		arg1.setValue("mychannel");
		
		Arg arg2=new Arg();
		arg2.setName("chaincodeName");
		arg2.setValue("example");
		
		Arg arg3=new Arg();
		arg3.setName("functionName");
		arg3.setValue("Invoke");
		
		Arg arg4=new Arg();
		arg4.setName("hasFile");
		arg4.setValue("false");
		
		Arg arg5=new Arg();
		arg5.setName("asyncInvoke");
		arg5.setValue("DISABLE");
		
		Arg arg6=new Arg();
		arg6.setName("otherBusinessArg");
		arg6.setValue("argument");
		
		args.add(arg1);
		args.add(arg2);
		args.add(arg3);
		args.add(arg4);
		args.add(arg5);
		args.add(arg6);
		
		Data data=new Data();
		data.setContent(requestJson.optString("Arrangement"));
		data.setDataType("Arrangement");
		data.setFormatType("JSONSTRING");
		
		datas.add(data);
		
		reqMsg.setArgs(args);
		reqMsg.setData(datas);
		
		logger.info("before arrangementServices");
//		resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
		resultJson=arrangementServices.createArrangement(JSONObject.fromObject(reqMsg).toString());
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
	public String QueryArrangement(JSONObject requestJson) {
		//区块链返回的json字符串
		String resultJson=null;
//		try {
//			resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		resultJson=arrangementServices.queryArrangement(requestJson.optString("OrgId"), requestJson.optString("ArrangementId"), requestJson.optString("UserOrgId"));
		
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
	public List<Arrangement> SelectArrangementByObject(Arrangement arrangement) {
		
		return arrangementDao.SelectArrangementByObject(arrangement);
	}

	@Override
	public JSONObject CreateArrangement(Arrangement arrangement) {
		// TODO Auto-generated method stub
				JSONObject resultJson=new JSONObject();
				
				int resultOfArrangement=0;
				
				int resultOfMaterialDetail=0;
				int resultOfAbility=0;
				
				
				
				
				if(arrangement.getArrangementdetail()==null) {
					MaterialDetail materialDetail=new MaterialDetail();
					String materialDetailId=UUID.randomUUID().toString().replace("-", "").toLowerCase();
					materialDetail.setId(materialDetailId);
					arrangement.setArrangementdetail(materialDetail);
				}
				
				
				
				resultOfArrangement=arrangementDao.CreateArrangement(arrangement);
				
				if(resultOfArrangement<1) {
					throw new MyException(321,"创建Arrangement失败");
				}
				//判断ability
				IP ipForQueryBuyer=new IP();
				ipForQueryBuyer.setId(arrangement.getBuyerId());
				
				List <IP> ipListBuyer = ipDao.SelectIPByObject(ipForQueryBuyer);
				
				IP ipForQuerySeller=new IP();
				ipForQuerySeller.setId(arrangement.getSellerId());
				
				List <IP> ipListSeller = ipDao.SelectIPByObject(ipForQuerySeller);
				
			
				
					
					List<Ability> abilityListBuyer=null;
					List<Ability> abilityListSeller=null;
					
					if(ipListBuyer.size()!=0) {
						abilityListBuyer=ipListBuyer.get(0).getAbility();
						for (Ability abilityBuyer : abilityListBuyer) {
							if(abilityBuyer.getName().equals("Arrangement")) {
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
							if(abilitySeller.getName().equals("Arrangement")) {
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
					
//					abilityListBuyer=ipListBuyer.get(0).getAbility();
//					abilityListSeller=ipListSeller.get(0).getAbility();
					
//					if(ipListBuyer==null||ipListBuyer.size()==0) {
//						logger.info("buyer不是化plus");
//					}else {
//						for (Ability abilityBuyer : abilityListBuyer) {
//							if(abilityBuyer.getName().equals("PO")) {
//								if(abilityBuyer.getValue().equals("false")) {
//									//修改为true
//									abilityBuyer.setValue("true");
//									resultOfAbility=ipDao.UpdateAbility(abilityBuyer);
//									if(resultOfAbility<1) {
//										throw new MyException(224,"修改Ability失败");
//									}
//								}
//							}
//							
//						}
//					}
//					
//					if(ipListSeller==null||ipListSeller.size()==0) {
//						logger.info("seller不是化plus");
//					}else {
//						for (Ability abilitySeller : abilityListSeller) {
//							if(abilitySeller.getName().equals("PO")) {
//								if(abilitySeller.getValue().equals("false")) {
//									//修改为true
//									abilitySeller.setValue("true");
//									resultOfAbility=ipDao.UpdateAbility(abilitySeller);
//									if(resultOfAbility<1) {
//										throw new MyException(224,"修改Ability失败");
//									}
//								}
//							}
//							
//						}
//					}
					
				
				
				
				
				
				
				resultOfMaterialDetail=arrangementDao.CreateMaterialDetail(arrangement.getArrangementdetail());
				
				if(resultOfMaterialDetail<1) {
					throw new MyException(224,"创建MaterialDetail失败");
				}
				
				
				
				resultJson.put("errno", 0);
				resultJson.put("error", "success");
				resultJson.put("data", new JSONObject());
				return resultJson;
	}

	@Override
	public List<Arrangement> SelectArrangementByObjectIn(Map<String,String> map) {
		// TODO Auto-generated method stub
		return arrangementDao.SelectArrangementByObjectIn(map);
	}

}
