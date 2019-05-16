package com.enn.commodity.synergistic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enn.commodity.synergistic.entity.MyException;

import net.sf.json.JSONObject;

@RestControllerAdvice
public class MyControllerAdvice {
	
	 /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("errno", "100");
        map.put("error", ex.getMessage());
        return map;
    }
    
    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public JSONObject myErrorHandler(MyException ex) {
    	JSONObject responseJson=new JSONObject();
    	responseJson.put("errno", ex.getErrno());
    	responseJson.put("error", ex.getError());
    	responseJson.put("data", new JSONObject());

        return responseJson;
    }

}
