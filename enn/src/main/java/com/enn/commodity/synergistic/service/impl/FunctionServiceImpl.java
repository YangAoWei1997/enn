package com.enn.commodity.synergistic.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.FunctionDao;
import com.enn.commodity.synergistic.entity.Function;
import com.enn.commodity.synergistic.entity.Role;
import com.enn.commodity.synergistic.entity.User;
import com.enn.commodity.synergistic.service.FunctionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class FunctionServiceImpl implements FunctionService{
	
	@Autowired
	private FunctionDao functionDao;

	@Override
	public JSONObject SelectFunctionsByFunname(JSONObject functionJson) {
		
		JSONObject responseJson=new JSONObject();
		Function reqFunction=new Function();
		reqFunction.setFunname(functionJson.optString("funname"));		

        List<Function> funList=functionDao.SelectFunctionsByFunname(reqFunction);
        
        JSONArray functionList=new JSONArray();
        if(funList.size()<1) {
        	responseJson.put("errno", 25);
        	responseJson.put("error", "自定义查询结果为空");
        	responseJson.put("data", new JSONObject());
        	return responseJson;
        }else {
        	
        	for(int i=0;i<funList.size();i++) {
        		JSONObject functionObject=new JSONObject();
        		
        		functionObject.put("funid", funList.get(i).getFunid());
        		functionObject.put("funname", funList.get(i).getFunname());
        		functionObject.put("funurl", funList.get(i).getFunurl());
        		functionObject.put("poweridfy", funList.get(i).getPoweridfy());
        		functionObject.put("fundetail", funList.get(i).getFundetail());
        		functionList.add(functionObject);
        	}
        }
        
        JSONObject dataObject=new JSONObject();
        dataObject.put("functionlist", functionList);
        
        responseJson.put("errno", 0);
    	responseJson.put("error", "success");
    	responseJson.put("data", dataObject);
		
		return responseJson;
	}

	@Override
	public JSONObject insertFunction(JSONObject functionJson) {
		
		JSONObject responseJson=new JSONObject();
		Function reqFunction=new Function();
		reqFunction.setFunid(functionJson.getString("funid"));
		reqFunction.setFunname(functionJson.getString("funname"));
		reqFunction.setFunurl(functionJson.getString("funurl"));
		reqFunction.setPoweridfy(functionJson.getString("poweridfy"));
		reqFunction.setFundetail(functionJson.getString("fundetail"));
		
		int result=functionDao.insertFunction(reqFunction);
		if(result<1) {
			responseJson.put("errno", 27);
			responseJson.put("error", "创建功能失败");
			responseJson.put("data", new JSONObject());
		}else {
			responseJson.put("errno", 0);
			responseJson.put("error", "success");
			responseJson.put("data", new JSONObject());
		} 
		return responseJson;
	}

	@Override
	public JSONObject updateFunction(JSONObject functionJson) {
		
		JSONObject responseJson=new JSONObject();
		Function reqFunction=new Function();
		reqFunction.setFunid(functionJson.getString("funid"));
		reqFunction.setFunname(functionJson.getString("funname"));
		reqFunction.setFunurl(functionJson.getString("funurl"));
		reqFunction.setPoweridfy(functionJson.getString("poweridfy"));
		reqFunction.setFundetail(functionJson.getString("fundetail"));
		
		int result=functionDao.updateFunction(reqFunction);
		if(result<1) {
			responseJson.put("errno", 27);
			responseJson.put("error", "修改功能失败");
			responseJson.put("data", new JSONObject());
		}else {
			responseJson.put("errno", 0);
			responseJson.put("error", "success");
			responseJson.put("data", new JSONObject());
		} 
		return responseJson;
	}

	@Override
	public JSONObject SelectFunctionsByRoleId(JSONObject roleJson) {


		JSONObject responseJson=new JSONObject();
		Role reqRole=new Role();
		reqRole.setRoleId(roleJson.optString("roleId"));
		List<Function> functionList=functionDao.SelectFunctionsByRoleId(reqRole);
		
		JSONArray jsonArray=new JSONArray();
		if(functionList.size()<1||functionList==null) {
			responseJson.put("errno", 25);
        	responseJson.put("error", "自定义查询结果为空");
        	responseJson.put("data", new JSONObject());
        	return responseJson;
		}else {
			
			for(int i=0;i<functionList.size();i++) {
        		JSONObject functionObject=new JSONObject();
        		
        		functionObject.put("funid", functionList.get(i).getFunid());
        		functionObject.put("funname", functionList.get(i).getFunname());
        		functionObject.put("funurl", functionList.get(i).getFunurl());
        		functionObject.put("poweridfy", functionList.get(i).getPoweridfy());
        		functionObject.put("fundetail", functionList.get(i).getFundetail());
        		jsonArray.add(functionObject);
        		
//        		JSONObject dataObject=new JSONObject();
        		
//        		dataObject.put("rolefunctionlist", jsonArray);
        		
//        		responseJson.put("errno", 0);
//            	responseJson.put("error", "success");
            	responseJson.put("rolefunctionlist", jsonArray);
        		
        	}
		}
		return responseJson;
	}

	@Override
	public JSONObject SelectFunctionsByUserId(JSONObject userJson) {
		
        JSONObject responseJson=new JSONObject();
		User reqUser=new User();
		reqUser.setUserId(userJson.optString("userId"));
		List<Function> functionList=functionDao.SelectFunctionsByUserId(reqUser);
		
		JSONArray jsonArray=new JSONArray();
		if(functionList.size()<1||functionList==null) {
			responseJson.put("errno", 25);
        	responseJson.put("error", "自定义查询结果为空");
        	responseJson.put("data", new JSONObject());
        	return responseJson;
		}else {
			
			for(int i=0;i<functionList.size();i++) {
        		JSONObject functionObject=new JSONObject();
        		
        		functionObject.put("funid", functionList.get(i).getFunid());
        		functionObject.put("funname", functionList.get(i).getFunname());
        		functionObject.put("funurl", functionList.get(i).getFunurl());
        		functionObject.put("poweridfy", functionList.get(i).getPoweridfy());
        		functionObject.put("fundetail", functionList.get(i).getFundetail());
        		jsonArray.add(functionObject);
        		
//        		JSONObject dataObject=new JSONObject();
        		
//        		dataObject.put("rolefunctionlist", jsonArray);
        		
//        		responseJson.put("errno", 0);
//            	responseJson.put("error", "success");
            	responseJson.put("userfunctionlist", jsonArray);
        		
        	}
		}
		return responseJson;
	}


	
	

}
