package com.enn.commodity.synergistic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.ArrangementDao;
import com.enn.commodity.synergistic.dao.BillOfLadingDao;
import com.enn.commodity.synergistic.dao.IPDao;
import com.enn.commodity.synergistic.dao.PackingListDao;
import com.enn.commodity.synergistic.dao.PurchaseOrderDao;
import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.BillOfLading;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.Location;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.PackingList;
import com.enn.commodity.synergistic.entity.Product;
import com.enn.commodity.synergistic.entity.QualityInfo;
import com.enn.commodity.synergistic.entity.RequestOfOrg;
import com.enn.commodity.synergistic.entity.ResultMap;
import com.enn.commodity.synergistic.service.IPService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class IPServiceImpl implements IPService{

	@Autowired
	private IPDao ipDao;
	
	@Autowired
	private ArrangementDao arrangementDao;
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	private BillOfLadingDao billOfLadingDao;
	
	@Autowired
	private PackingListDao packingListDao;
	
	
	
	@Override
	public List<IP> SelectIPByObject(IP ip) {
		
		return ipDao.SelectIPByObject(ip);
	}

	@Override
	public JSONObject CreateIP(IP ip) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		
		int resultOfIP=0;
		int resultOfLocation=0;
		int resultOfProduct=0;
		int resultOfAbility=0;
		
		resultOfIP =ipDao.CreateIP(ip);
		
		if(resultOfIP<1) {
			throw new MyException(221,"创建IP失败");
		}
		
		
		Ability ability=new Ability();
		ability.setIpId(ip.getId());
		
		resultOfAbility=ipDao.CreateAbility(ability);
		
		if(resultOfAbility<1) {
			throw new MyException(224,"创建Ability失败");
		}
		
		if(ip.getLocation()!=null&&ip.getLocation().size()!=0) {
			List<Location> locationList=ip.getLocation();
			for (Location location : locationList) {
				location.setIpId(ip.getId());
				resultOfLocation =ipDao.CreateLocation(location);
				if(resultOfLocation<1) {
					throw new MyException(223,"创建Location失败");
				}
			}
			
		}
		
		if(ip.getProduct()!=null&&ip.getProduct().size()!=0) {
			List<Product> productList=ip.getProduct();
			for (Product product : productList) {
				product.setIpId(ip.getId());
				resultOfProduct =ipDao.CreateProduct(product);
				if(resultOfProduct<1) {
					throw new MyException(222,"创建Product失败");
				}
			}
		
		}
		resultJson.put("errno", 0);
		resultJson.put("error", "success");
		resultJson.put("data", new JSONObject());
		return resultJson;
		
	}

	@Override
	public int CreateProduct(Product product) {
		// TODO Auto-generated method stub
		return ipDao.CreateProduct(product);
	}

	@Override
	public int CreateLocation(Location location) {
		// TODO Auto-generated method stub
		return ipDao.CreateLocation(location);
	}

	@Override
	public JSONObject SelectInfoByObject(IP ip) {
		// TODO Auto-generated method stub
		JSONObject resultJson=new JSONObject();
		
		JSONObject dataObject=new JSONObject();
		JSONArray arrangementArray=new JSONArray();
		JSONArray poArray=new JSONArray();
		JSONArray billOfLadingArray=new JSONArray();
		JSONArray packingListArray=new JSONArray();
		JSONArray qualityInfoArray=new JSONArray();
		
		Arrangement arrangementForQuery=new Arrangement();
		arrangementForQuery.setBuyerId(ip.getId());
		arrangementForQuery.setSellerId(ip.getId());
		List<Arrangement> arrangemetList=arrangementDao.SelectArrangementByObject(arrangementForQuery);
		
		for (Arrangement arrangement : arrangemetList) {
			
			arrangementArray.add(JsonUtil.fromObject(arrangement));
			
		}
		
		PO poForQuery=new PO();
		poForQuery.setBuyerId(ip.getId());
		poForQuery.setSellerId(ip.getId());
		List<PO> poList=purchaseOrderDao.SelectPOByObject(poForQuery);
		
		if(poList!=null&&poList.size()!=0) {
			for (PO po : poList) {
				
				poArray.add(JsonUtil.fromObject(po));
				
			}
		}
		
		
		BillOfLading billForQuery=new BillOfLading();
		billForQuery.setBuyerId(ip.getId());
		billForQuery.setSellerId(ip.getId());
		List<BillOfLading> billList=billOfLadingDao.SelectBillOfLadingByObject(billForQuery);
		
		if(billList!=null&&billList.size()!=0) {
			for (BillOfLading billOfLading : billList) {
				
				billOfLadingArray.add(JsonUtil.fromObject(billOfLading));
				
			}
		}
		
		
		
		PackingList packForQuery=new PackingList();
		packForQuery.setBuyerId(ip.getId());
		packForQuery.setSellerId(ip.getId());
		List<PackingList> packList=packingListDao.SelectPackingListByObject(packForQuery);
		
		if(packList!=null&&packList.size()!=0) {
			for (PackingList packingList : packList) {
				
				packingListArray.add(JsonUtil.fromObject(packingList));
				
			}
		}
		
		
		
		QualityInfo qualityInfoForQuery=new QualityInfo();
		qualityInfoForQuery.setOrgId(ip.getId());
		
		List<QualityInfo> qualityInfoList=packingListDao.SelectQualityInfoByObject(qualityInfoForQuery);
		if(qualityInfoList!=null&&qualityInfoList.size()!=0) {
            for (QualityInfo qualityInfo : qualityInfoList) {
				
            	JSONObject qualityObject=JSONObject.fromObject(qualityInfo);
            	String quality=qualityInfo.getQuality();
            	String[] qualityArray=quality.split(";");
            	for (String str : qualityArray) {
            		String qualityKey=str.split(":")[0];
            		String qualityValue=str.split(":")[1];
            		qualityObject.put(qualityKey, qualityValue);
				}
            	qualityInfoArray.add(JsonUtil.fromJSONObject(qualityObject));
				
			}
		}
		
		dataObject.put("Arrangement", arrangementArray);
		dataObject.put("PO", poArray);
		dataObject.put("BillOfLading", billOfLadingArray);
		dataObject.put("PackingList", packingListArray);
		dataObject.put("QualityInfoList", qualityInfoArray);
		
		resultJson.put("errno", 0);
		resultJson.put("error", "success");
		resultJson.put("data", dataObject);
		return resultJson;
	}

	@Override
	public int CreateAbility(Ability ability) {
		// TODO Auto-generated method stub
		return ipDao.CreateAbility(ability);
	}

	

	

}
