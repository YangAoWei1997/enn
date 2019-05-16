package com.enn.commodity.synergistic.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.MineralResources;
import com.enn.commodity.synergistic.service.MineralResourcesService;
import com.enn.commodity.synergistic.util.UserLoginToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/MineralResources")
@CrossOrigin
public class MineralResourcesController {
	
	@Autowired
	private MineralResourcesService mineralResourcesService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/CreateMineralResourcesInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateMineralResourcesInfo(@RequestBody JSONObject mineralJson) {
		
		JSONObject responseObject=new JSONObject();
		MineralResources mineralResources=new MineralResources();
		mineralResources.setLocality(mineralJson.optString("locality"));
		mineralResources.setProductName(mineralJson.optString("productName"));
		mineralResources.setCommitdate(mineralJson.optString("commitdate"));
		mineralResources.setUnitprice(mineralJson.optString("unitprice"));
		mineralResources.setUserId(mineralJson.optString("userId"));
		mineralResources.setShowname(mineralJson.optString("showname"));
		mineralResources.setImg(mineralJson.optString("img"));
		mineralResources.setPhonenumber(mineralJson.optString("phonenumber"));
		mineralResources.setCombustionvalue(mineralJson.optString("combustionvalue"));
		mineralResources.setLocation(mineralJson.optString("location"));
		mineralResources.setIsCompany(Integer.parseInt(mineralJson.optString("isCompany")));
		mineralResources.setDataSourceId(mineralJson.optString("dataSourceId"));
		mineralResources.setDataSourceName(mineralJson.optString("dataSourceName"));
		mineralResources.setGoodsType(mineralJson.optString("goodsType"));
		
		int result =mineralResourcesService.InsertMineralResources(mineralResources);
		if(result==0) {
			responseObject.put("errno", 41);
			responseObject.put("error", "录入矿源信息失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/QueryMineralResourcesInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryMineralResourcesInfo(@RequestBody JSONObject mineralJson) {
		
		JSONObject responseObject=new JSONObject();
		MineralResources mineralResources=new MineralResources();
		mineralResources.setProductName(mineralJson.optString("productName"));
		mineralResources.setCommitdate(mineralJson.optString("commitdate"));
		mineralResources.setLocation(mineralJson.optString("location"));
//		mineralResources.setCombustionvalue(mineralJson.getString("combustionvalue"));
		List<MineralResources> infoList=mineralResourcesService.SelectMineralResourcesByObject(mineralResources);
		System.out.println(infoList.size());
		if(infoList!=null&&infoList.size()!=0) {
			JSONObject dataObject=new JSONObject();
			JSONArray productinfolist =new JSONArray();
			JSONObject productinfoObject=null;
			for(int i=0;i<infoList.size();i++) {
				productinfoObject=new JSONObject();
				productinfoObject.put("locality", infoList.get(i).getLocality());
				productinfoObject.put("productName", infoList.get(i).getProductName());
				productinfoObject.put("unitprice", infoList.get(i).getUnitprice());
				productinfoObject.put("commitdate", infoList.get(i).getCommitdate());
				productinfoObject.put("phonenumber", infoList.get(i).getPhonenumber());
				productinfoObject.put("showname", infoList.get(i).getShowname());
				productinfoObject.put("location", infoList.get(i).getLocation());
				productinfoObject.put("combustionvalue", infoList.get(i).getCombustionvalue());
				productinfoObject.put("isCompany", infoList.get(i).getIsCompany());
				productinfoObject.put("dataSourceId", infoList.get(i).getDataSourceId());
				productinfoObject.put("dataSourceName", infoList.get(i).getDataSourceName());
				productinfoObject.put("goodsType", infoList.get(i).getGoodsType());
				productinfolist.add(productinfoObject);
			}
			dataObject.put("productinfolist", productinfolist);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}else {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	

}
