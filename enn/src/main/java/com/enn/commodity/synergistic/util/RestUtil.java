package com.enn.commodity.synergistic.util;

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

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.enn.commodity.synergistic.controller.PurchaseOrderController;

import net.sf.json.JSONObject;

public class RestUtil {
	
	private static Logger logger = Logger.getLogger(RestUtil.class);
	
	//get
	  public void getMethod(String url) throws IOException {
	    URL restURL = new URL(url);
	 
	    HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
	 
	    conn.setRequestMethod("GET"); // POST GET PUT DELETE
	    conn.setRequestProperty("Accept", "application/json");
	 
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while((line = br.readLine()) != null ){
	      System.out.println(line);
	    }
	 
	    br.close();
	  }
	 
	  //post 
	  public void postMethod(String url, String query) throws IOException {
	    URL restURL = new URL(url);
	 
	    HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setDoOutput(true);
	 
	    PrintStream ps = new PrintStream(conn.getOutputStream());
	    ps.print(query);
	    ps.close();
	 
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while((line = br.readLine()) != null ){
	      System.out.println(line);
	    }
	 
	    br.close();
	  }
	  
	  public  String httpPost(String url,JSONObject requestJson) throws Exception {
	        // 返回body
	        //String body = "";
	        String result=null;
	        
	       
	        
	        // 1、创建一个htt客户端
	        HttpClient httpClient = HttpClients.createDefault();
	        // 2、创建一个HttpPost请求
	        HttpPost post = new HttpPost(url);
	        post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//	        post.setHeader("charset", "UTF-8");
	        List params=new ArrayList();
	        Iterator iterator = requestJson.keys();
	        
	        while (iterator.hasNext())
	        {
	            Object key =  iterator.next();
//	            System.out.println(key+":"+requestJson.get(key));
	            logger.info(key+":"+requestJson.get(key));
	            params.add(new BasicNameValuePair(key.toString(), requestJson.get(key).toString()));
	           // keys.add(key);
//	            post.setEntity(new StringEntity(key+"="+requestJson.get(key),Charset.forName("UTF-8")));
	           
	        }
	        post.setEntity(new UrlEncodedFormEntity(params,Charset.forName("UTF-8")));
	        //post.setEntity(new StringEntity(paramkey+"="+paramval,Charset.forName("UTF-8")));
	        
	        // 5、设置header信息
//	        post.setHeader("X-Appid", APPID);
//	        post.setHeader("X-CurTime", getUTCTimeStr());
//	        post.setHeader("X-Param", Base64String(param));
//	        post.setHeader("X-CheckSum", MD5String(APIKey + getUTCTimeStr() + Base64String(param)));
	        // post.setHeader("Connection", "close");
	        // 设置参数
//	        if (map != null) {
//	            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//	            for (Map.Entry<String, String> entry : map.entrySet()) {
//	                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//	            }
//	            post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//	        }
	        // 7、执行post请求操作，并拿到结果
	        HttpResponse httpResponse = httpClient.execute(post);
	        
	        // 获取结果实体
	        //HttpEntity entity = httpResponse.getEntity();
//	        if (entity != null) {
//	            // 按指定编码转换结果实体为String类型
//	            body = EntityUtils.toString(entity, HTTP.UTF_8);
//	        }
//	        // EntityUtils.consume(entity);
//	        httpClient.getConnectionManager().shutdown();
//	        System.out.println(body.length());
	        if (httpResponse != null) {
              HttpEntity resEntity = httpResponse.getEntity();
              if (resEntity != null) {
                  result = EntityUtils.toString(resEntity, "utf-8");
              }
          }else {
        	  //httpResponse为空组jsonObject
        	  logger.info("httpResponse为null");
          }
//	        System.out.println(result);
	        logger.info("区块链返回数据:"+result);
	        return result;
	    }
	  
	  public static void main(String[] args) {
		  
//		  String str="{\"billOfLadingId\":\"billoftest8\",\"dataType\":\"PackingList\",\"id\":\"packoftest7\",\"leavedDate\":\"2018-12-1\",\"leavefirsttime\":\"2018-11-27\",\"leavefirstweight\":\"100\",\"leavesecondtime\":\"2018-11-28\",\"leavesecondweight\":\"200\",\"organizationId\":\"o1\",\"packingType\":\"packingList\",\"packingno\":\"packoftest7\",\"plateNo\":\"京BS9996\",\"qualityId\":\"repoftest16\",\"reachedDate\":\"2018-12-1\",\"weight\":\"100\"}!@#{\"billOfLadingId\":\"billoftest8\",\"buyerId\":\"b1\",\"buyerName\":\"buyer1\",\"carWeight\":\"\",\"carrierId\":\"c1\",\"carrierName\":\"carr1\",\"comments\":\"\",\"dataType\":\"PackingList\",\"date\":\"\",\"destination\":\"\",\"driverId\":\"qq\",\"driverName\":\"qq\",\"emptyCarWeight\":\"\",\"id\":\"arriveoftest8\",\"leaveFirstTime\":\"\",\"leaveFirstWeight\":\"100\",\"leaveSecondTime\":\"\",\"leaveSecondWeight\":\"200\",\"leavedDate\":\"\",\"name\":\"\",\"organizationId\":\"o1\",\"organizationName\":\"\",\"packingType\":\"arrivalList\",\"plateNo\":\"京BS9996\",\"productId\":\"pro1\",\"productName\":\"product1\",\"qualityId\":\"repoftest17\",\"quantity\":\"\",\"quantityUnit\":\"\",\"reachedDate\":\"2018-12-1\",\"relatedPONumber\":\"\",\"sellerId\":\"s1\",\"sellerName\":\"seller1\",\"source\":\"beijing\",\"userId\":\"1\",\"userName\":\"\",\"version\":\"\",\"weight\":\"\"}";
//		  String[] QualityStrArray = str.split("!@#");
//		  System.out.println(QualityStrArray.length);
//		  System.out.println(QualityStrArray[1]);
		  }


}
