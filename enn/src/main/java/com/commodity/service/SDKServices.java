package com.commodity.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.ibm.fabric.sdk.app.Router;
import com.ibm.fabric.sdk.util.Util;

@Path("sdk")
public class SDKServices {
	
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	// get logger
	private static Logger logger = Logger.getLogger(SDKServices.class);
	
	/* Service Check */
	@GET
	@Path("/check")
	@Consumes(MediaType.APPLICATION_JSON)
	public String check() {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		return "OK";
	}
	
	/**
	 * Call router class to route to different methods to handle the request
	 * Mainly for two purposes:
	 * 1. Batch manage peers/orgs in Fabric Network, including EnrollUser/CreateChannel/JoinChannel/InstallChaincode (only 'methodName' parameter required)
	 * 2. Invoke & Query request with 'functionName' & 'args' parameters
	 * Note: chaincodePath is only required by UpgradeChaincode
	 * 
	 * @param methodName Method Name for which method in SDK to be called
	 * @param orgName Organization Name for which the request executed
	 * @param userName User Name of whom execute the request
	 * @param functionName Function Name for which method in chaincode to be called
	 * @param argumentJson Argument String in Json format (for corresponding chaincode method/UpgradeChaincode SDK method)
	 * @param chaincodePath New Chaincode Path for UpgradeChaincode method ONLY!
	 * @return
	 */
	@POST
	@Path("/route")
	@Consumes("application/x-www-form-urlencoded")
	public String route(@FormParam("methodName") String methodName, @FormParam("orgName") String orgName, 
			@FormParam("userName") String userName, @FormParam("functionName") String functionName, 
			@FormParam("argumentJson") String argumentJson, @FormParam("chaincodePath") String chaincodePath) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.addHeader("X-Powered-By", "3.2.1");
		
		logger.info("<<<<<<<<<<<<<<<<< 请求路由 >>>>>>>>>>>>>>>>>");
		logger.info("methodName  : " + methodName);
		
		if (methodName == null || methodName.equals("")) {
			return Util.getErrorMessage("'methodName'");
		}
		
		if(methodName.equals("EnrollUser") || methodName.equals("CreateChannel")
				|| methodName.equals("JoinChannel") || methodName.equals("InstallChaincode"))
			return Router.routeMethods(methodName);
		else
			return Router.routeMethods(methodName, orgName, userName, functionName, argumentJson, chaincodePath);
	}
	
}
