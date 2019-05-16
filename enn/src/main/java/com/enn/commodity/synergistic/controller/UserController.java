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
import com.enn.commodity.synergistic.entity.User;
import com.enn.commodity.synergistic.service.UserService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@CrossOrigin
@RestController
@RequestMapping("/User")
public class UserController {
	
	public static Logger logger = Logger.getLogger(PurchaseOrderController.class);
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/queryUser",method=RequestMethod.GET)
	public String queryUser() {
		List<User> userList=userService.queryUser();
		String jsonStr=JSONArray.fromObject(userList).toString();
		return jsonStr;
	}
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject login(@RequestBody JSONObject loginJson) {
		
		JSONObject resultJson=userService.login(loginJson);
		
		return resultJson;
	}
	
	@ResponseBody
	@RequestMapping(value="/CreateUser",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject CreateUser(@RequestBody JSONObject userJson) {
		
		JSONObject resultJson=userService.CreateUser(userJson);
		
		return resultJson;
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryUserDetail",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject QueryUserDetail(@RequestBody JSONObject userJson) {
		
		JSONObject resultJson=userService.QueryUserDetail(userJson);
		
		return resultJson;
	}
	
	@ResponseBody
	@RequestMapping(value="/UpdatePassword",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject UpdatePassword(@RequestBody JSONObject userJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(userJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(userJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+userJson.toString());
		
		if(!userJson.optString("newpassword").equals(userJson.optString("confirmpassword"))) {
			throw new MyException(31,"两次输入密码不一致");
			
		}
		
		
		responseObject=userService.UpdatePassword(userJson);
		
		
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value="/UpdateUser",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject UpdateUser(@RequestBody JSONObject userJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(userJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(userJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+userJson.toString());
		
		
		responseObject=userService.UpdateUser(userJson);
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value="/UpdateUserStatus",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public JSONObject UpdateUserStatus(@RequestBody JSONObject userJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(userJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(userJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+userJson.toString());
		
		if(userJson.optString("locktype").equals("lock")) {
			//锁定用户
			userJson.put("updatestatus", "lock");
			responseObject=userService.BatchUpdateUser(userJson);
			
		}else if(userJson.optString("locktype").equals("unlock")) {
			//解锁用户
			userJson.put("updatestatus", "正常");
			responseObject=userService.BatchUpdateUser(userJson);
		}else if(userJson.optString("locktype").equals("delete")) {
			//删除用户
			responseObject=userService.BatchDeleteUser(userJson);
		}else {
			//未识别的操作种类
			throw new MyException(36,"未识别的操作种类");
		}

		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}

}
