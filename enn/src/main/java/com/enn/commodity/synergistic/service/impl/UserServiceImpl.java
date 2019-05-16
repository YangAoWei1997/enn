package com.enn.commodity.synergistic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.controller.PurchaseOrderController;
import com.enn.commodity.synergistic.dao.FunctionDao;
import com.enn.commodity.synergistic.dao.UserDao;
import com.enn.commodity.synergistic.entity.Function;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.User;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class UserServiceImpl implements UserService{
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FunctionDao functionDao;
	
	
	
	public List<User> queryUser(){
		
		return userDao.selectAllUser();
		
	}

	@Override
	public JSONObject login(JSONObject userObject) {
		
		//返回的总json
		JSONObject resultJson=new JSONObject();
		
		String userId=userObject.getString("userId");
		String password=userObject.getString("password");
		User reqUser=new User();
		reqUser.setUserId(userId);
		
		List<User> resUser=new ArrayList<User>();
		resUser=userDao.SelectUsersByObject(reqUser);
//		User resUser=userDao.SelectUsersByObject(reqUser);
		if(resUser.size()==0) {
			System.out.println("用户不存在");
			resultJson.put("errno", 21);
			resultJson.put("error", "用户不存在");
			resultJson.put("data", new JSONObject());
		}else if(!resUser.get(0).getPassword().equals(password)) {
			System.out.println("密码错误");
			resultJson.put("errno", 22);
			resultJson.put("error", "密码错误");
			resultJson.put("data", new JSONObject());
		}else {
			
			logger.info("登陆成功");
			resultJson.put("errno", 0);
			resultJson.put("error", "successs");
			JSONObject dataJson=new JSONObject();
			dataJson.put("userId", resUser.get(0).getUserId());
			dataJson.put("username", resUser.get(0).getUsername());
			dataJson.put("organizationId", resUser.get(0).getOrganizationId());
			dataJson.put("organizationName", resUser.get(0).getOrganizationName());
			dataJson.put("token", TokenUtil.getToken(resUser.get(0)));
			
			//获取当前用户所有role的function List
			List<Function>functionList=functionDao.SelectFunctionsByUserId(reqUser);
			
			
			JSONArray permissionlist=new JSONArray();
			//权限数组
			for(int i=0;i<functionList.size();i++) {
				permissionlist.add(functionList.get(i).getPoweridfy());
			}
			
			
//			if(resUser.get(0).getUserId().equals("u1")) {
//			permissionlist.add("QualityListCompare");
//			permissionlist.add("WeightCompare");
//			permissionlist.add("QueryPackingListForSell");
//			permissionlist.add("QueryBillOfLadingForSell");
//			permissionlist.add("QueryQueuingInfo");
//			permissionlist.add("QueryOrderStatus");
//			permissionlist.add("QuerysingleCarSatus");
//			permissionlist.add("QueryArriveInfoForSell");
//			permissionlist.add("QueryMineralResourcesInfo");
//			permissionlist.add("Querypurchasingorder");
//			permissionlist.add("QuerySalesOrder");
//			permissionlist.add("OrderCompare");
//			permissionlist.add("CreateMineralResourcesInfo");
//			permissionlist.add("CreateQualityListOfOut");
//			permissionlist.add("CreateSalesOrder");
//			permissionlist.add("SellOrderForBill");
//			
//			
//			}else if(resUser.get(0).getUserId().equals("u2")) {
//				permissionlist.add("QualityListCompare");
//				permissionlist.add("WeightCompare");
//				permissionlist.add("QueryPackingListForBuy");
//				permissionlist.add("QueryBillOfLadingForBuy");
//				permissionlist.add("QueryWarningInfo");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QueryOrderStatus");
//				permissionlist.add("QuerysingleCarSatus");
//				permissionlist.add("QueryArriveInfoForBuy");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("Querypurchasingorder");
//				permissionlist.add("QuerySalesOrder");
//				permissionlist.add("OrderCompare");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("CreateQualityListOfEnter");
//				permissionlist.add("CreatePurchaseOrder");
//				permissionlist.add("PurchaseOrderForBill");
//				permissionlist.add("BillOfLadingForBuyer");
//				permissionlist.add("CreateReceiveList");
//				
//			}else if(resUser.get(0).getUserId().equals("u3")) {
//				permissionlist.add("QueryPackingListForDrive");
//				permissionlist.add("QueryBillOfLadingForCarrier");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QuerysingleCarSatusForDrive");
//				permissionlist.add("QueryArriveInfoForCarrier");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("BillOfLadingForDriver");
//				permissionlist.add("BillOfLadingForDriver");
//				
//			}else if(resUser.get(0).getUserId().equals("u4")) {
//				permissionlist.add("QueryPackingListForDriver");
//				permissionlist.add("QueryBillOfLadingForDriver");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("CreatePacking");
//				permissionlist.add("CreatePacking");
//				
//				
//			}else if(resUser.get(0).getUserId().equals("user1")||resUser.get(0).getUserId().equals("user4")||resUser.get(0).getUserId().equals("user9")) {
//				permissionlist.add("QualityListCompare");
//				permissionlist.add("WeightCompare");
//				permissionlist.add("QueryPackingListForSell");
//				permissionlist.add("QueryBillOfLadingForSell");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QueryOrderStatus");
//				permissionlist.add("QuerysingleCarSatus");
//				permissionlist.add("QueryArriveInfoForSell");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("Querypurchasingorder");
//				permissionlist.add("QuerySalesOrder");
//				permissionlist.add("OrderCompare");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("CreateQualityListOfOut");
//				permissionlist.add("CreateSalesOrder");
//				permissionlist.add("SellOrderForBill");
//			}else if(resUser.get(0).getUserId().equals("user2")||resUser.get(0).getUserId().equals("user5")||resUser.get(0).getUserId().equals("user10")) {
//				
//				permissionlist.add("QualityListCompare");
//				permissionlist.add("WeightCompare");
//				permissionlist.add("QueryPackingListForBuy");
//				permissionlist.add("QueryBillOfLadingForBuy");
//				permissionlist.add("QueryWarningInfo");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QueryOrderStatus");
//				permissionlist.add("QuerysingleCarSatus");
//				permissionlist.add("QueryArriveInfoForBuy");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("Querypurchasingorder");
//				permissionlist.add("QuerySalesOrder");
//				permissionlist.add("OrderCompare");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("CreateQualityListOfEnter");
//				permissionlist.add("CreatePurchaseOrder");
//				permissionlist.add("PurchaseOrderForBill");
//				permissionlist.add("BillOfLadingForBuyer");
//				permissionlist.add("CreateReceiveList");
//			}else if(resUser.get(0).getUserId().equals("user3")||resUser.get(0).getUserId().equals("user6")||resUser.get(0).getUserId().equals("user11")) {
//				
//				permissionlist.add("QueryPackingListForDrive");
//				permissionlist.add("QueryBillOfLadingForCarrier");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QuerysingleCarSatusForDrive");
//				permissionlist.add("QueryArriveInfoForCarrier");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("BillOfLadingForDriver");
//				permissionlist.add("BillOfLadingForDriver");
//			}else if(resUser.get(0).getUserId().equals("user7")||resUser.get(0).getUserId().equals("user8")||resUser.get(0).getUserId().equals("user12")||resUser.get(0).getUserId().equals("user13")) {
//				
//				permissionlist.add("QueryPackingListForDriver");
//				permissionlist.add("QueryBillOfLadingForDriver");
//				permissionlist.add("QueryQueuingInfo");
//				permissionlist.add("QueryMineralResourcesInfo");
//				permissionlist.add("CreateMineralResourcesInfo");
//				permissionlist.add("CreatePacking");
//				permissionlist.add("CreatePacking");
//				
//			}
			dataJson.put("permissionlist", permissionlist);
			resultJson.put("data", dataJson);
		}
		return resultJson;
	}

	@Override
	public JSONObject CreateUser(JSONObject userObject) {
		
		JSONObject resultJson=new JSONObject();
		
		String adminUserId=userObject.getString("userid");
		//判断当前用户是否有权限创建用户，没有权限返回错误
		String userId=userObject.getJSONObject("newuserinfo").getString("userId");
		User reqUser=new User();
		reqUser.setUserId(userId);

		List<User> resUser=new ArrayList<User>();
		resUser=userDao.SelectUsersByObject(reqUser);
//		User resUser=userDao.SelectUsersByObject(reqUser);
		if(resUser.size()!=0) {
			//数据库在创建时能否自己判断用户是否已存在
			//userid和OrganizationId联合主键
			System.out.println("用户已存在");
			resultJson.put("errno", 23);
			resultJson.put("error", "用户已存在");
			resultJson.put("data", new JSONObject());
		}else {
			
			reqUser.setUsername(userObject.getJSONObject("newuserinfo").getString("username"));
			reqUser.setOrganizationId(userObject.getJSONObject("newuserinfo").getString("organizationId"));
			reqUser.setShowname(userObject.getJSONObject("newuserinfo").getString("showname"));
			//password哈希加密
			reqUser.setPassword(userObject.getJSONObject("newuserinfo").getString("password"));
			reqUser.setMail(userObject.getJSONObject("newuserinfo").getString("mail"));
			reqUser.setContactinfo(userObject.getJSONObject("newuserinfo").getString("contactinfo"));
			reqUser.setOrganizationName(userObject.getJSONObject("newuserinfo").getString("organizationName"));
			
			int result=userDao.CreateUser(reqUser);
			if(result<1) {
				resultJson.put("errno", 24);
				resultJson.put("error", "创建用户失败");
				resultJson.put("data", new JSONObject());
			}else {
				resultJson.put("errno", 0);
				resultJson.put("error", "success");
				resultJson.put("data", new JSONObject());
			}
		}
		


		return resultJson;
	}

	@Override
	public JSONObject QueryUserDetail(JSONObject userObject) {
		
		//返回的总json
		JSONObject resultJson=new JSONObject();
		User reqUser=new User();
		reqUser.setUserId(userObject.optString("userId"));
//		reqUser.setUserno(userObject.getString("userno"));
		reqUser.setUsername(userObject.optString("username"));
		reqUser.setUserstatus(userObject.optString("userstatus"));
		reqUser.setOrganizationId(userObject.optString("organizationId"));
		
		List<User> resUser=new ArrayList<User>();
		resUser=userDao.SelectUsersByObject(reqUser);
		if(resUser.size()==0) {
			
			resultJson.put("errno", 25);
			resultJson.put("error", "自定义查询结果为空");
			resultJson.put("data", new JSONObject());
			
		}else {
			
			JSONObject userinfoObject=null;
			JSONArray userinfolist=new JSONArray();
			JSONObject dataObject=new JSONObject();
			for(int i=0;i<resUser.size();i++) {
				userinfoObject=new JSONObject();
				userinfoObject.put("userno", resUser.get(i).getUserId());
				userinfoObject.put("userId", resUser.get(i).getUserindex());
				userinfoObject.put("username", resUser.get(i).getUsername());
				userinfoObject.put("organizationId", resUser.get(i).getOrganizationId());
				userinfoObject.put("organizationName", resUser.get(i).getOrganizationName());
				userinfoObject.put("userstatus", resUser.get(i).getUserstatus());
				userinfoObject.put("usersconnect", resUser.get(i).getUsersconnect());
				
				userinfolist.add(userinfoObject);
			}
			
			dataObject.put("userinfolist", userinfolist);
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", dataObject);
		}
		
		return resultJson;
	}

	@Override
	public JSONObject QueryDriverInfoByPlateNo(JSONObject userObject) {
		
		//返回的总json
				JSONObject resultJson=new JSONObject();
				User reqUser=new User();
				reqUser.setPlateno(userObject.optString("plateno"));
				
				
				List<User> resUser=new ArrayList<User>();
				resUser=userDao.SelectUsersByObject(reqUser);
				if(resUser.size()==0) {
					
					resultJson.put("errno", 29);
					resultJson.put("error", "没有找到当前车牌信息");
					resultJson.put("data", new JSONObject());
					logger.info("没有找到当前车牌信息");
					
				}else {
					
					JSONObject driverInfo=new JSONObject();
					
					driverInfo.put("carrierId", resUser.get(0).getOrganizationId());
					driverInfo.put("carrierName", resUser.get(0).getOrganizationName());
					driverInfo.put("driverId", resUser.get(0).getUserId());
					driverInfo.put("driverName", resUser.get(0).getUsername());
					
					resultJson.put("errno", 0);
					resultJson.put("error", "success");
					resultJson.put("data", driverInfo);
					
					
				}
		
		return resultJson;
	}

	@Override
	public JSONObject UpdatePassword(JSONObject userObject) {
		
		//返回的总json
		JSONObject resultJson=new JSONObject();
		
		
		//判断旧密码是否正确
		
		User reqUserForConfirm=new User();
		reqUserForConfirm.setUserId(userObject.optString("userId"));
		reqUserForConfirm.setOrganizationId(userObject.optString("organizationId"));
		
        List<User> resUser=new ArrayList<User>();
        
		resUser=userDao.SelectUsersByObject(reqUserForConfirm);
		
		if(resUser.size()!=1) {
			throw new MyException(32,"数据库返回不是预期结果");
		}
		
		String oldpassword=resUser.get(0).getPassword();
		
		if(!userObject.optString("oldpassword").equals(oldpassword)) {
			throw new MyException(22,"密码错误");
		}
		//修改密码
		User reqUser=new User();
		
		reqUser.setUserId(userObject.optString("userId"));
		reqUser.setPassword(userObject.optString("newpassword"));
		reqUser.setOrganizationId(userObject.optString("organizationId"));
		
		int result=userDao.UpdatePassword(reqUser);
		
		if(result<1) {
			resultJson.put("errno", 30);
			resultJson.put("error", "修改密码失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		
		
		
		return resultJson;
	}

	@Override
	public JSONObject UpdateUser(JSONObject userObject) {
		
		//返回的总json
		JSONObject resultJson=new JSONObject();

		JSONObject updateuserinfo=userObject.optJSONObject("updateuserinfo");
        User reqUser=new User();
		reqUser.setUserId(updateuserinfo.optString("userId"));
		reqUser.setPassword(updateuserinfo.optString("username"));
		reqUser.setShowname(updateuserinfo.optString("showname"));
		reqUser.setContactinfo(updateuserinfo.optString("contactinfo"));
		reqUser.setMail(updateuserinfo.optString("mail"));
		reqUser.setOrganizationId(updateuserinfo.optString("userorgid"));
		reqUser.setUserstatus(updateuserinfo.optString("userstatus"));
		
		int result=userDao.UpdateUser(reqUser);
		
		if(result<1) {
			resultJson.put("errno", 33);
			resultJson.put("error", "修改用户失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		
		return resultJson;
	}

	
	


	@Override
	public JSONObject BatchUpdateUser(JSONObject userObject) {
		
		//返回的总json
		JSONObject resultJson=new JSONObject();

		Map<String,Object> map=new HashMap<>();
		List<User> updateList=new ArrayList<User>();
		JSONArray upuserstatuslist=userObject.getJSONArray("upuserstatuslist");
		for(int i=0;i<upuserstatuslist.size();i++) {
			User user=new User();
			user.setUserId(upuserstatuslist.getJSONObject(i).optString("upuserstatusid"));
			user.setOrganizationId(upuserstatuslist.getJSONObject(i).optString("upuserstatusorgid"));
//			user.setUserstatus(userObject.optString("updatestatus"));
			updateList.add(user);
			logger.info(updateList.get(i).getUserId());
		}
		map.put("userstatus", userObject.optString("updatestatus"));
		map.put("updateIdList", updateList);
		
		
		logger.info("传递给dao层的数据:"+JSONObject.fromObject(map));
		int result=userDao.BatchUpdateUser(map);
		
		if(result<1) {
			resultJson.put("errno", 33);
			resultJson.put("error", "修改用户失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		
		return resultJson;
	}

	@Override
	public JSONObject BatchDeleteUser(JSONObject userObject) {

		//返回的总json
		JSONObject resultJson=new JSONObject();
		
		List<User> deleteList=new ArrayList<User>();
		JSONArray upuserstatuslist=userObject.getJSONArray("upuserstatuslist");
		
		for(int i=0;i<upuserstatuslist.size();i++) {
			User user=new User();
			user.setUserId(upuserstatuslist.getJSONObject(i).optString("upuserstatusid"));
			user.setOrganizationId(upuserstatuslist.getJSONObject(i).optString("upuserstatusorgid"));
//			user.setUserstatus(userObject.optString("updatestatus"));
			deleteList.add(user);
			logger.info(deleteList.get(i).getUserId());
		}
		
		logger.info("传递给dao层的数据:"+JSONObject.fromObject(deleteList));
		int result=userDao.BatchDelete(deleteList);
		
		if(result<1) {
			resultJson.put("errno", 37);
			resultJson.put("error", "删除用户失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		return resultJson;
	}
	
	

}
