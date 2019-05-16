package com.enn.commodity.synergistic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.RoleDao;
import com.enn.commodity.synergistic.dao.UserDao;
import com.enn.commodity.synergistic.entity.Role;
import com.enn.commodity.synergistic.entity.User;
import com.enn.commodity.synergistic.service.RoleService;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	

	

	
//	public JSONObject CreateUser(JSONObject userObject) {
//		
//		JSONObject resultJson=new JSONObject();
//		
//		String userId=userObject.getString("userId");
//		User reqUser=new User();
//		reqUser.setUserId(userId);
//
//		List<User> resUser=new ArrayList<User>();
//		resUser=userDao.SelectUsersByObject(reqUser);
////		User resUser=userDao.SelectUsersByObject(reqUser);
//		if(resUser.size()!=0) {
//			System.out.println("用户已存在");
//			resultJson.put("errno", 23);
//			resultJson.put("error", "用户已存在");
//			resultJson.put("data", new JSONObject());
//		}else {
//			
//			reqUser.setUsername(userObject.getString("username"));
//			reqUser.setOrganizationId(userObject.getString("organizationId"));
//			reqUser.setShowname(userObject.getString("showname"));
//			reqUser.setPassword(userObject.getString("password"));
//			reqUser.setMail(userObject.getString("mail"));
//			reqUser.setContactinfo(userObject.getString("contactinfo"));
//			reqUser.setOrganizationName(userObject.getString("organizationName"));
//			
//			int result=userDao.CreateUser(reqUser);
//			if(result<1) {
//				resultJson.put("errno", 24);
//				resultJson.put("error", "创建用户失败");
//				resultJson.put("data", new JSONObject());
//			}else {
//				resultJson.put("errno", 0);
//				resultJson.put("error", "success");
//				resultJson.put("data", new JSONObject());
//			}
//		}
//		
//
//
//		return resultJson;
//	}

	

	@Override
	public JSONObject QueryRoleDetail(JSONObject roleObject) {
		
		//返回的总json
		JSONObject resultJson=new JSONObject();
		Role reqRole=new Role();
		
		String orgid=roleObject.getString("orgid");
		String organizationId=roleObject.getString("organizationId");
		String rolename=roleObject.getString("rolename");
		
		if(organizationId.equals("admin")) {
			//管理员用户查询所有企业角色
		}else {
			reqRole.setRolename(rolename);
			reqRole.setOrganizationId(orgid);
			
			List<Role> resRole=new ArrayList<Role>();
			
			resRole=roleDao.SelectRolesByObject(reqRole);
			
			if(resRole.size()==0) {
				
				resultJson.put("errno", 25);
				resultJson.put("error", "自定义查询结果为空");
				resultJson.put("data", new JSONObject());
				
			}else {
				JSONObject roleinfoObject=null;
				JSONArray roleinfolist=new JSONArray();
				JSONObject dataObject=new JSONObject();
				for(int i=0;i<resRole.size();i++) {
					roleinfoObject=new JSONObject();
					roleinfoObject.put("roleid", resRole.get(i).getRoleId());
					
					roleinfoObject.put("rolename", resRole.get(i).getRolename());
					roleinfoObject.put("organizationId", resRole.get(i).getOrganizationId());
					roleinfoObject.put("roledetail", resRole.get(i).getOrganizationId());
					roleinfoObject.put("organizationId", resRole.get(i).getOrganizationId());
					roleinfoObject.put("organizationName", resRole.get(i).getOrganizationName());
					
					roleinfolist.add(roleinfoObject);
				}
				
				dataObject.put("userinfolist", roleinfolist);
				resultJson.put("errno", 0);
				resultJson.put("error", "success");
				resultJson.put("data", dataObject);
			}
		}
		
		
		return resultJson;
	}

	@Override
	public JSONObject CreateRole(JSONObject roleObject) {
		
		JSONObject resultJson=new JSONObject();
		
		Role reqRole=new Role();
		
		reqRole.setRoleId(roleObject.getString("roleId"));
		reqRole.setRolename(roleObject.getString("rolename"));
		reqRole.setOrganizationId(roleObject.getString("organizationId"));
		reqRole.setOrganizationName(roleObject.getString("organizationName"));
		reqRole.setRoledetail(roleObject.getString("roledetail"));
		reqRole.setUserId(roleObject.getString("userId"));
		
		int result=roleDao.CreateRole(reqRole);
		if(result<1) {
			resultJson.put("errno", 26);
			resultJson.put("error", "创建角色失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		
		
		return resultJson;
	}

	@Override
	public JSONObject UpdateRole(JSONObject roleObject) {
		
		JSONObject resultJson=new JSONObject();
		
		Role reqRole=new Role();
		
		reqRole.setRoleId(roleObject.optString("roleId"));
		reqRole.setRoledetail(roleObject.optString("roledetail"));
		reqRole.setRolename(roleObject.optString("rolename"));
		reqRole.setOrganizationId(roleObject.optString("roleorgid"));
		reqRole.setUserId(roleObject.optString("userid"));
		
		int result=roleDao.UpdateRole(reqRole);
		
		if(result<1) {
			resultJson.put("errno", 34);
			resultJson.put("error", "修改角色失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		
		return resultJson;
	}

	@Override
	public JSONObject RoleAuthorization(JSONObject authObject) {

		JSONObject resultJson=new JSONObject();
		
		JSONArray rolepermissionlist=authObject.getJSONArray("rolepermissionlist");
		
		List<String> functionList=new ArrayList<String>();
		for(int i=0;i<rolepermissionlist.size();i++) {
			functionList.add(rolepermissionlist.optJSONObject(i).optString("rolepermissionid"));
		}
		
		int result=roleDao.RoleAuthorization(authObject.optString("roleId"), functionList);
		if(result<1) {
			resultJson.put("errno", 35);
			resultJson.put("error", "角色授权失败");
			resultJson.put("data", new JSONObject());
		}else {
			resultJson.put("errno", 0);
			resultJson.put("error", "success");
			resultJson.put("data", new JSONObject());
		}
		return resultJson;
	}

	@Override
	public List<Role> QueryRole(Role role, Integer page, Integer limit) {
		// TODO Auto-generated method stub
		List<Role> roleList=null;
		roleList=roleDao.SelectRolesByObject(role);
		
		return roleList;
	}

}
