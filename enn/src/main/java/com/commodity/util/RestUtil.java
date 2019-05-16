package com.commodity.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



import net.sf.json.JSONObject;

public class RestUtil {
	
	private static Logger logger = Logger.getLogger(RestUtil.class);
	
	public static String HttpPostWithJson(String url, String json) {
		String returnValue = "这是默认返回值，接口调用失败";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try{
			//第一步：创建HttpClient对象
		 httpClient = HttpClients.createDefault();
		 	
		 	//第二步：创建httpPost对象
	        HttpPost httpPost = new HttpPost(url);
	        
	        //第三步：给httpPost设置JSON格式的参数
	        StringEntity requestEntity = new StringEntity(json,"utf-8");
	        requestEntity.setContentEncoding("UTF-8");    	        
	        httpPost.setHeader("Content-type", "application/json");
	        httpPost.setEntity(requestEntity);
	       
	       //第四步：发送HttpPost请求，获取返回值
	       returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法
	      
		}
		 catch(Exception e)
		{
			 e.printStackTrace();
		}
		
		finally {
	       try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		 //第五步：处理返回值
	     return returnValue;
	}
//
//	  public static void main(String[] args) {
//		  
////		  String str="{\"billOfLadingId\":\"billoftest8\",\"dataType\":\"PackingList\",\"id\":\"packoftest7\",\"leavedDate\":\"2018-12-1\",\"leavefirsttime\":\"2018-11-27\",\"leavefirstweight\":\"100\",\"leavesecondtime\":\"2018-11-28\",\"leavesecondweight\":\"200\",\"organizationId\":\"o1\",\"packingType\":\"packingList\",\"packingno\":\"packoftest7\",\"plateNo\":\"京BS9996\",\"qualityId\":\"repoftest16\",\"reachedDate\":\"2018-12-1\",\"weight\":\"100\"}!@#{\"billOfLadingId\":\"billoftest8\",\"buyerId\":\"b1\",\"buyerName\":\"buyer1\",\"carWeight\":\"\",\"carrierId\":\"c1\",\"carrierName\":\"carr1\",\"comments\":\"\",\"dataType\":\"PackingList\",\"date\":\"\",\"destination\":\"\",\"driverId\":\"qq\",\"driverName\":\"qq\",\"emptyCarWeight\":\"\",\"id\":\"arriveoftest8\",\"leaveFirstTime\":\"\",\"leaveFirstWeight\":\"100\",\"leaveSecondTime\":\"\",\"leaveSecondWeight\":\"200\",\"leavedDate\":\"\",\"name\":\"\",\"organizationId\":\"o1\",\"organizationName\":\"\",\"packingType\":\"arrivalList\",\"plateNo\":\"京BS9996\",\"productId\":\"pro1\",\"productName\":\"product1\",\"qualityId\":\"repoftest17\",\"quantity\":\"\",\"quantityUnit\":\"\",\"reachedDate\":\"2018-12-1\",\"relatedPONumber\":\"\",\"sellerId\":\"s1\",\"sellerName\":\"seller1\",\"source\":\"beijing\",\"userId\":\"1\",\"userName\":\"\",\"version\":\"\",\"weight\":\"\"}";
////		  String[] QualityStrArray = str.split("!@#");
////		  System.out.println(QualityStrArray.length);
////		  System.out.println(QualityStrArray[1]);
//		  ResourceBundle resource = ResourceBundle.getBundle("method");
//			
//			String method=resource.getString("method");
//			
//			System.out.println(method);
//		  
//		  }


}
