package com.commodity.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	private static Properties properties = null;
	private static volatile PropertiesUtil _instance;
	
	private static String serviceConfigPath = "/yuantong.properties";
	
	private static String errorMsgConfigPath = "/errorMsg.properties";
	
	private static String App_Prefix = "APP_";
	private static String Err_Prefix = "ERR_";
	private static String Org_Prefix = "ORG_";
	private static String User_Prefix = "USER_";

	///////////////////////////////////////////////
	///////////////APP SERVICE SETTING/////////////
	///////////////////////////////////////////////
	public static String Mock = "MOCK";
	public static String AttachmentTempMenu = "ATTACHMENT_TEMP_MENU";
	
	///////////////////////////////////////////////
	///////////////ERR MAPPING/////////////////////
	///////////////////////////////////////////////
	public static String IOExceptionId = "IO_EXCEPTION_ID";
	public static String IOExceptionMsg = "IO_EXCEPTION_MSG";
	public static String DBExceptionId = "DB_EXCEPTION_ID";
	public static String DBExceptionMsg = "DB_EXCEPTION_MSG";
	public static String DBInsertFailId = "DB_INSERT_FAIL_ID";
	public static String DBInsertFailMsg = "DB_INSERT_FAIL_MSG";
	public static String DBUpdateFailId = "DB_UPDATE_FAIL_ID";
	public static String DBUpdateFailMsg = "DB_UPDATE_FAIL_MSG";
	public static String DBDeleteFailId = "DB_DELETE_FAIL_ID";
	public static String DBDeleteFailMsg = "DB_DELETE_FAIL_MSG";
	public static String DBDataNotExistId = "DB_DATA_NOT_EXIST_ID";
	public static String DBDataNotExistMsg = "DB_DATA_NOT_EXIST_MSG";
	
	public static String OtherExceptionId = "OTHER_EXCEPTION_ID";
	public static String OtherExceptionMsg = "OTHER_EXCEPTION_MSG";
	public static String ArgMissMappingId = "ARG_MISS_MAPPING_ID";
	public static String ArgMissMappingMsg = "ARG_MISS_MAPPING_MSG";
	public static String ArgMissInvalidId = "ARG_MISS_INVALID_ID";
	public static String ArgMissInvalidMsg = "ARG_MISS_INVALID_MSG";
	public static String ExternalPlatformId = "EXTERNAL_PLATFORM_ID";
	public static String ExternalPlatformMsg = "EXTERNAL_PLATFORM_MSG";
	
	public static String OrgNotExistId = "ORG_NOT_EXIST_ID";
	public static String OrgNotExistMsg = "ORG_NOT_EXIST_MSG";
	public static String ChannelExistId = "CHANNEL_EXIST_ID";
	public static String ChannelExistMsg = "CHANNEL_EXIST_MSG";
	public static String ChannelNotExistId = "CHANNEL_NOT_EXIST_ID";
	public static String ChannelNotExistMsg = "CHANNEL_NOT_EXIST_MSG";
	public static String PeerJoinedId = "PEER_JOINED_ID";
	public static String PeerJoinedMsg = "PEER_JOINED_MSG";
	public static String PeerNotExistId = "PEER_NOT_EXIST_ID";
	public static String PeerNotExistMsg = "PEER_NOT_EXIST_MSG";
	public static String ChaincodeNotExistId = "CHAINCODE_NOT_EXIST_ID";
	public static String ChaincodeNotExistMsg = "CHAINCODE_NOT_EXIST_MSG";
	public static String ChaincodeInstallFailedId = "CHAINCODE_INSTALL_FAILED_ID";
	public static String ChaincodeInstallFailedMsg = "CHAINCODE_INSTALL_FAILED_MSG";
	public static String ProposalInconsistentId = "PROPOSAL_INCONSISTANT_ID";
	public static String ProposalInconsistentMsg = "PROPOSAL_INCONSISTANT_MSG";
	public static String EndorserNotEnoughId = "ENDORSER_NOT_ENOUGH_ID";
	public static String EndorserNotEnoughMsg = "ENDORSER_NOT_ENOUGH_MSG";
	public static String TransEventInvalidId = "TRANS_EVENT_INVALID_ID";
	public static String TransEventInvalidMsg = "TRANS_EVENT_INVALID_MSG";
	public static String TransEventFailedId = "TRANS_EVENT_FAILED_ID";
	public static String TransEventFailedMsg = "TRANS_EVENT_FAILED_MSG";
	public static String ProposalFailedId = "PROPOSAL_FAILED_ID";
	public static String ProposalFailedMsg = "PROPOSAL_FAILED_MSG";
	
	public static String UserNotLoginId = "USER_NOT_LOGIN_ID";
	public static String UserNotLoginMsg = "USER_NOT_LOGIN_MSG";
	public static String UserNotRegisterId = "USER_NOT_REGISTER_ID";
	public static String UserNotRegisterMsg = "USER_NOT_REGISTER_MSG";
	public static String UserExistId = "USER_EXIST_ID";
	public static String UserExistMsg = "USER_EXIST_MSG";
	public static String UserNotExistId = "USER_NOT_EXIST_ID";
	public static String UserNotExistMsg = "USER_NOT_EXIST_MSG";
	public static String UserLockId = "USER_LOCK_ID";
	public static String UserLockMsg = "USER_LOCK_MSG";
	public static String UserDeleteId = "USER_DELETE_ID";
	public static String UserDeleteMsg = "USER_DELETE_MSG";
	public static String UserMaxLoginId = "USER_MAX_LOGIN_ID";
	public static String UserMaxLoginMsg = "USER_MAX_LOGIN_MSG";
	public static String UserWrongPWId = "USER_WRONG_PW_ID";
	public static String UserWrongPWMsg = "USER_WRONG_PW_MSG";
	public static String UserWrongOldPWId = "USER_WRONG_OLDPW_ID";
	public static String UserWrongOldPWMsg = "USER_WRONG_OLDPW_MSG";
	public static String UserWrongOrgId = "USER_WRONG_ORG_ID";
	public static String UserWrongOrgMsg = "USER_WRONG_ORG_MSG";
	public static String UserWrongRoleId = "USER_WRONG_ROLE_ID";
	public static String UserWrongRoleMsg = "USER_WRONG_ROLE_MSG";
	public static String UserNotMatchId = "USER_NOT_MATCH_ID";
	public static String UserNotMatchMsg = "USER_NOT_MATCH_MSG";
	public static String UserListId = "USER_LIST_ID";
	public static String UserListMsg = "USER_LIST_MSG";
	public static String UserExistProductId = "USER_EXIST_PRODUCT_ID";
	public static String UserExistProductMsg = "USER_EXIST_PRODUCT_MSG";
	public static String PSBCOnlyId = "PSBC_ONLY_ID";
	public static String PSBCOnlyMsg = "PSBC_ONLY_MSG";
	public static String UserPasswordWrongId = "USER_PASSWORD_WRONG_ID";
	public static String UserPasswordWrongMsg = "USER_PASSWORD_WRONG_MSG";
	
	public static String PermExistId = "PERM_EXIST_ID";
	public static String PermExistMsg = "PERM_EXIST_MSG";
	public static String PermNotExistId = "PERM_NOT_EXIST_ID";
	public static String PermNotExistMsg = "PERM_NOT_EXIST_MSG";
	public static String PERM_LIST_ID = "PERM_LIST_ID";
	public static String PERM_LIST_MSG = "PERM_LIST_MSG";
	
	
	public static String RoleChangeId = "ROLE_CHANGE_ID";
	public static String RoleChangeMsg = "ROLE_CHANGE_MSG";
	public static String RoleExistId = "ROLE_EXIST_ID";
	public static String RoleExistMsg = "ROLE_EXIST_MSG";
	public static String RoleNotExistId = "ROLE_NOT_EXIST_ID";
	public static String RoleNotExistMsg = "ROLE_NOT_EXIST_MSG";
	public static String RoleExistUserId = "ROLE_EXIST_USER_ID";
	public static String RoleExistUserMsg = "ROLE_EXIST_USER_MSG";
	
	public static String OrgExistId = "ORG_EXIST_ID";
	public static String OrgExistMsg = "ORG_EXIST_MSG";
	public static String OrgNonExistId = "ORG_NON_EXIST_ID";
	public static String OrgNonExistMsg = "ORG_NON_EXIST_MSG";
	public static String OrgExistUserId = "ORG_EXIST_USER_ID";
	public static String OrgExistUserMsg = "ORG_EXIST_USER_MSG";
	public static String OrgEmptyId = "ORG_EMPTY_ID";
	public static String OrgEmptyMsg = "ORG_EMPTY_MSG";
	
	public static String DepartExistId = "DEPART_EXIST_ID";
	public static String DepartExistMsg = "DEPART_EXIST_MSG";
	public static String DepartNotExistId = "DEPART_NOT_EXIST_ID";
	public static String DepartNotExistMsg = "DEPART_NOT_EXIST_MSG";
	public static String DepartExistUserId = "DEPART_EXIST_USER_ID";
	public static String DepartExistUserMsg = "DEPART_EXIST_USER_MSG";
	
	public static String FileUploadExceptionId = "FILE_UPLOAD_EXCEPTION_ID";
	public static String FileUploadExceptionMsg = "FILE_UPLOAD_EXCEPTION_MSG";
	public static String FileUploadFailId = "FILE_UPLOAD_FAIL_ID";
	public static String FileUploadFailMsg = "FILE_UPLOAD_FAIL_MSG";
	public static String FileDownloadId = "FILE_DOWNLOAD_ID";
	public static String FileDownloadMsg = "FILE_DOWNLOAD_MSG";
	public static String FileNotExistId = "FILE_NOT_EXIST_ID";
	public static String FileNotExistMsg = "FILE_NOT_EXIST_MSG";
	public static String FileHashCodeId = "FILE_HASHCODE_ID";
	public static String FileHashCodeMsg = "FILE_HASHCODE_MSG";
	
	public static String LogListId = "LOG_LIST_ID";
	public static String LogListMsg = "LOG_LIST_MSG";
	
	public static String CheckNotPassId = "TG_CHECK_NOT_PASS_ID";
	public static String CheckNotPassMsg = "TG_CHECK_NOT_PASS_MSG";
	
	///////////////////////////////////////////////
	///////////////ORG MAPPING/////////////////////
	///////////////////////////////////////////////
	private static String Default_Org_Name = "org1";
	
	///////////////////////////////////////////////
	///////////////ORG-USER MAPPING////////////////
	///////////////////////////////////////////////
	
	private PropertiesUtil() {
		if (properties == null) {
			properties = new Properties();
			loadConf();
		}
	}
	
	public void loadConf() {
		InputStream fileInputStream = null;
		InputStream errorMsgInputStream = null;
		try {
			
			fileInputStream = this.getClass().getResourceAsStream(serviceConfigPath);
			properties.load(fileInputStream);
			errorMsgInputStream = this.getClass().getResourceAsStream(errorMsgConfigPath);
			Properties errorProp = new Properties();
			errorProp.load(errorMsgInputStream);
			properties.putAll(errorProp);
			errorMsgInputStream.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			logger.error(String.format("Service Config File: %s", e.getMessage().toString()));
		} catch (IOException e) {
			logger.error(String.format("Service Config File: %s", e.getMessage().toString()));
		}finally{
			try{
				fileInputStream.close();
			}catch(Throwable e){
			   	logger.warn(e.toString());
			}
			try{
				errorMsgInputStream.close();
			}catch(Throwable e){
			   	logger.warn(e.toString());
			}
			
		}
	}

	public static PropertiesUtil getInstance() {
		if(_instance == null) {
    		synchronized (PropertiesUtil.class) {
				if (_instance == null) {
					_instance = new PropertiesUtil();
				}
			}
    	}
		return _instance;
	}

	private static String getProperty(String key) {
		try {
			if(properties.getProperty(key) == null)
				return "";
			
			String value = new String(properties.getProperty(key).getBytes(
					"ISO-8859-1"), "UTF-8");// "UTF-8"
			if (value == null || value.trim().equals(""))
				return "";
			else
				return value.trim();
		} catch (Exception e) {
			logger.error(String.format("Error: %s", e.getMessage().toString()));
			return "";
		}
	}
	
	private static String getProperty(String key, String[] args) {
		String prop = getProperty(key);
		return MessageFormat.format(prop, args);
	}
	
	//APP
    public String getAppPropertyStr(String propName) {
		return getProperty(App_Prefix + propName);
	}
    
    public int getAppPropertyInt(String propName) {
        return Integer.parseInt(getProperty(App_Prefix + propName));
    }
    
    public boolean getAppPropertyBoolean(String propName) {
        return getProperty(App_Prefix + propName).equals("true") ? true : false;
    }
    
	//ERR
    public String getErrId(String errName) {
		return getProperty(Err_Prefix + errName);
	}
    
    public String getErrMsg(String errName) {
		return getProperty(Err_Prefix + errName);
	}
    
    public String getErrMsg(String errName, String[] args) {
		return getProperty(Err_Prefix + errName, args);
	}
    
    //ORG
    //ORG-USER
    public String getOrgName(String orgId) {
    	String orgName = getProperty(Org_Prefix + orgId);
    	
    	if(orgName.equals(""))
    		orgName = Default_Org_Name;
    	
    	return orgName;
    }
    
    public String getUserNameByOrgId(String orgId) {
	  	return getUserNameByOrgName(getOrgName(orgId));
	}
    
    public String getUserNameByOrgName(String orgName) {
    	return getProperty(User_Prefix + orgName); //USER_
	}
    
    public String getOrgNameByOrgId(String orgId) {
	  	return getProperty(orgId);
	}
    
}
