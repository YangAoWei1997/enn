package com.enn.commodity.synergistic.util;

import net.sf.json.JSONObject;

public class ErrorHandler {
	//改成配置文件 refine 1-2
	//编写公共方法直接从errormessage中提取错误信息
	public JSONObject errornoHandler(String code,JSONObject responseObject) {
		
		if(code!=null&&!code.equals("")) {
			String[] codeArray= code.split(":");
			String codeStr=codeArray[0].substring(codeArray[0].indexOf("ERRNMCC")+1);
//			int errno=Integer.parseInt(codeStr);
			String error=codeArray[1];
			responseObject.put("errno",codeStr);
			responseObject.put("error", error);
		}else {
			responseObject.put("errno",0);
			responseObject.put("error", "success");
		}
		
//		if(code!=null&&!code.equals("")) {
//			if(code.equals("ERRNMCC0000:交易码匹配错误")) {
//				responseObject.put("errno",21);
//				responseObject.put("error", "交易码匹配错误");
//			}else if(code.equals("ERRNMCC0001:json反序列化错误")) {
//				responseObject.put("errno",1);
//				responseObject.put("error", "json反序列化错误");
//			}else if(code.equals("ERRNMCC0002:对象序列化错误")) {
//				responseObject.put("errno",2);
//				responseObject.put("error", "对象序列化错误");
//			}else if(code.equals("ERRNMCC0003:解析请求报文错误")) {
//				responseObject.put("errno",3);
//				responseObject.put("error", "解析请求报文错误");
//			}else if(code.equals("ERRNMCC0004:DelState错误")) {
//				responseObject.put("errno",4);
//				responseObject.put("error", "DelState错误");
//			}else if(code.equals("ERRNMCC0005:PutState错误")) {
//				responseObject.put("errno",5);
//				responseObject.put("error", "PutState错误");
//			}else if(code.equals("ERRNMCC0006:GetState错误")) {
//				responseObject.put("errno",6);
//				responseObject.put("error", "GetState错误");
//			}else if(code.equals("ERRNMCC0007:couchDB富查询错误")) {
//				responseObject.put("errno",7);
//				responseObject.put("error", "couchDB富查询错误");
//			}else if(code.equals("ERRNMCC0008:报文数据错误")) {
//				responseObject.put("errno",8);
//				responseObject.put("error", "报文数据错误");
//			}else if(code.equals("ERRNMCC0009:数据类型转换错误")) {
//				responseObject.put("errno",9);
//				responseObject.put("error", "数据类型转换错误");
//			}else if(code.equals("ERRNMCC0010:插入数据重复错误")) {
//				responseObject.put("errno",10);
//				responseObject.put("error", "插入数据重复错误");
//			}else if(code.equals("ERRNMCC0011:UpState错误")) {
//				responseObject.put("errno",11);
//				responseObject.put("error", "UpState错误");
//			}else if(code.equals("ERRNMCC0012:没有查找到对应的数据信息")) {
//				responseObject.put("errno",12);
//				responseObject.put("error", "没有查找到对应的数据信息");
//			}else if(code.equals("ERRNMCC0013:请求报文data属性为空")) {
//				responseObject.put("errno",13);
//				responseObject.put("error", "请求报文data属性为空");
//			}else if(code.equals("ERRNMCC0014:请求报文args属性为空")) {
//				responseObject.put("errno",14);
//				responseObject.put("error", "请求报文args属性为空");
//			}else if(code.equals("ERRNMCC0015:提货单对应的销售订单数据不存在")) {
//				responseObject.put("errno",15);
//				responseObject.put("error", "提货单对应的销售订单数据不存在");
//			}else if(code.equals("ERRNMCC0016:数据获取不是预期结果")) {
//				responseObject.put("errno",16);
//				responseObject.put("error", "数据获取不是预期结果");
//			}else if(code.equals("ERRNMCC0017:数据取到条目数量大于一，不是期望值")) {
//				responseObject.put("errno",17);
//				responseObject.put("error", "数据取到条目数量大于一，不是期望值");
//			}else if(code.equals("ERRNMCC0018:所需查询参数没有取到")) {
//				responseObject.put("errno",18);
//				responseObject.put("error", "所需查询参数没有取到");
//			}else if(code.equals("ERRNMCC0019:更新工单所属的计划信息不存在")) {
//				responseObject.put("errno",19);
//				responseObject.put("error", "更新工单所属的计划信息不存在");
//			}else if(code.equals("ERRNMCC0020:不满足删除条件")) {
//				responseObject.put("errno",20);
//				responseObject.put("error", "不满足删除条件");
//			}else {
//				responseObject.put("errno",9999);
//				responseObject.put("error", "未知错误");
//			}
//		}else {
//			responseObject.put("errno",0);
//			responseObject.put("error", "success");
//			
//		}
		responseObject.put("data", new JSONObject());
		return responseObject;
	}
	
//	public JSONObject apperrornoHandler(String code,JSONObject responseObject) {
//		
//		if(code!=null&&!code.equals("")) {
//			if(code.equals("400")) {
//				responseObject.put("errno",400);
//				responseObject.put("error", "请求出现语法错误");
//			}else if(code.equals("401")) {
//				responseObject.put("errno",401);
//				responseObject.put("error", "无法访问未经授权受密码保护的页面");
//			}else {
//				responseObject.put("errno",9998);
//				responseObject.put("error", "application未知错误");
//			}
//			
//		
//	}else {
//		responseObject.put("errno",0);
//		responseObject.put("error", "success");
//	}
//		return responseObject;
//	}
	
	

}
