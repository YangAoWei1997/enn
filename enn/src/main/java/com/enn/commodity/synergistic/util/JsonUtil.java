package com.enn.commodity.synergistic.util;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
@Component
public class JsonUtil {
	
	public boolean isJson(String content){

	    try {
	        JSONObject jsonStr= JSONObject.fromObject(content);
	        return  true;
	   } catch (Exception e) {
	        return false;
	  }
	}
	
	public static String fromObject(Object object) {
		String str = JSONObject.fromObject(object).toString();
		if (str != null) {
			// 去除null
			str = str.replaceAll(":null", ":\"\"");
//			str = str.replaceAll("null", "\"\"");
		}
		return str;
	}
	public static String fromJSONObject(JSONObject object) {
		String str = object.toString();
		if (str != null) {
			// 去除null
			str = str.replaceAll(":null", ":\"\"");
//			str = str.replaceAll("null", "\"\"");
		}
		return str;
	}
	
	public static String operationOfStr(String arg1,String arg2) {
		
		String resultStr=null;
		int resultNum=0;
		
		if(!NumberUtils.isNumber(arg1)) {
			arg1="0";
		}
		
		if(!NumberUtils.isNumber(arg2)) {
			arg2="0";
		}
	
		resultNum=Integer.parseInt(arg1)-Integer.parseInt(arg2);	
			
		resultStr=String.valueOf(resultNum);
		
		
		
		
		return resultStr;
	}
}
