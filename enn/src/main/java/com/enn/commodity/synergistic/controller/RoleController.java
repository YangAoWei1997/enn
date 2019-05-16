package com.enn.commodity.synergistic.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.Role;
import com.enn.commodity.synergistic.entity.User;
import com.enn.commodity.synergistic.service.RoleService;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@CrossOrigin
@RestController
@RequestMapping("/Role")
public class RoleController {
	
    public static Logger logger = Logger.getLogger(PurchaseOrderController.class);
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private RoleService roleService;
	


	
	@ResponseBody
	@RequestMapping(value="/CreateRole",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject CreateRole(@RequestBody JSONObject rloeJson) {
		
		JSONObject resultJson=roleService.CreateRole(rloeJson);
		
		return resultJson;
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryRoleDetail",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject QueryRoleDetail(@RequestBody JSONObject roleJson) {
		
//		JSONObject resultJson=roleService.QueryRoleDetail(roleJson);
//		
//		return resultJson;
		
		JSONObject responseObject=new JSONObject();
		
		        Integer page=roleJson.optInt("page");
		        Integer limit=roleJson.optInt("limit");
		        Role roleForQuery=(Role)JSONObject.toBean(roleJson, Role.class);
		        JSONObject map = new JSONObject();//创建JSONmap来存放JSON数据传到前台
		        PageHelper.startPage(page -1, limit);//设置数据库分页查询的范围
		        List<Role> roleList = roleService.QueryRole(roleForQuery, page, limit);//执行service层的方法
		        PageInfo<Role> pageInfo=new PageInfo<>(roleList);
		        responseObject.put("count",pageInfo.getTotal());//获取查询总条数，这里我会在后面解释
//		        map.put("data",roleList);
		        
		        JSONObject dataObject=new JSONObject();
				JSONArray dataArray=new JSONArray();
				if(roleList==null||roleList.size()==0) {
					responseObject.put("errno", 12);
					responseObject.put("error", "自定义查询结果为空");
					responseObject.put("data", new JSONObject());
				}else {
					
					for (Role role : roleList) {
						
						dataArray.add(JsonUtil.fromObject(role));
						
					}
					dataObject.put("roleList", dataArray);
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
					responseObject.put("data", dataObject);
				}
		        return responseObject;
		    
		    
		

		
	}
	
	@ResponseBody
	@RequestMapping(value="/UpdateRole",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject UpdateRole(@RequestBody JSONObject rloeJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(rloeJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(rloeJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+rloeJson.toString());
		
		responseObject=roleService.UpdateRole(rloeJson);
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}

	@ResponseBody
	@RequestMapping(value="/RoleAuthorization",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject RoleAuthorization(@RequestBody JSONObject authJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(authJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(authJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+authJson.toString());
		
		responseObject=roleService.RoleAuthorization(authJson);
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
}
