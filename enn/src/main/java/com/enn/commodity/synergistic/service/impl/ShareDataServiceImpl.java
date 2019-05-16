package com.enn.commodity.synergistic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commodity.service.ShareDataServices;
import com.enn.commodity.synergistic.dao.ShareDataDao;
import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.Group;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.ShareData;
import com.enn.commodity.synergistic.entity.ShareDataTemplate;
import com.enn.commodity.synergistic.service.ShareDataService;
import com.enn.commodity.synergistic.util.JsonUtil;
import com.enn.commodity.synergistic.util.RestUtil;
import com.mchain.apollo.common.entity.base.Arg;
import com.mchain.apollo.common.entity.base.Data;
import com.mchain.apollo.common.entity.base.Message;

import net.sf.json.JSONObject;

@Service
public class ShareDataServiceImpl implements ShareDataService{
	
	public static String path="http://192.168.138.84:8080/commodity/service";
	
	public static Logger logger = Logger.getLogger(ShareDataServiceImpl.class);
	
	@Autowired
	private ShareDataDao shareDataDao;
	
	@Autowired
	private ShareDataServices shareDataServices;
	
	@Autowired
	private JsonUtil jsonUtil;

	@Override
	public String QueryShareDataById(JSONObject requestJson){
		
        String resultJson=null;
		
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/QueryShareDataById", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		return resultJson;
	}

	@Override
	public String CreateShareData(JSONObject requestJson){
		
//		String resultJson=null;
//		
//		try {
//			resultJson = new RestUtil().httpPost(path+"/ShareData/CreateShareData", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		
//		if(resultJson==null) {
//			logger.info("区块链返回null");
//			
//			throw new MyException(81,"区块链返回null");
//		}
//		if(!jsonUtil.isJson(resultJson)) {
//			
//			logger.info("区块链返回格式不是json");
//			
//			throw new MyException(23,"区块链返回格式不符合预期要求");
//			
//		}
//		
//		return resultJson;
		String resultJson=null;
		 
		 Message<ShareData> reqMsg=new Message<ShareData>();
			List<Data> datas = new ArrayList<Data>();
	    	List<Arg> args =  new ArrayList<Arg>();
			reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			reqMsg.setTranCode("CreateShareData");
			reqMsg.setType("0");
			reqMsg.setOrgid("Org1");
			reqMsg.setUserid("Org1Admin");
			reqMsg.setHasGeneric(false);
			
			Arg arg1=new Arg();
			arg1.setName("channelName");
			arg1.setValue("mychannel");
			
			Arg arg2=new Arg();
			arg2.setName("chaincodeName");
			arg2.setValue("example");
			
			Arg arg3=new Arg();
			arg3.setName("functionName");
			arg3.setValue("Invoke");
			
			Arg arg4=new Arg();
			arg4.setName("hasFile");
			arg4.setValue("false");
			
			Arg arg5=new Arg();
			arg5.setName("asyncInvoke");
			arg5.setValue("DISABLE");
			
			Arg arg6=new Arg();
			arg6.setName("otherBusinessArg");
			arg6.setValue("argument");
			
			args.add(arg1);
			args.add(arg2);
			args.add(arg3);
			args.add(arg4);
			args.add(arg5);
			args.add(arg6);
			
			Data data=new Data();
			data.setContent(requestJson.optString("ShareDataJson"));
			data.setDataType("ShareData");
			data.setFormatType("JSONSTRING");
			
			datas.add(data);
			
			reqMsg.setArgs(args);
			reqMsg.setData(datas);
			
			logger.info("before arrangementServices");
//			resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
			resultJson=shareDataServices.createGroup(JSONObject.fromObject(reqMsg).toString());
				if(resultJson==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJson)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
	
				
				return resultJson;
	}


	@Override
	public String CreateShareDataTemplate(JSONObject requestJson) {
//        String resultJson=null;
//		
//		try {
//			resultJson = new RestUtil().httpPost(path+"/ShareData/CreateShareDataTemplate", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		
//		if(resultJson==null) {
//			logger.info("区块链返回null");
//			
//			throw new MyException(81,"区块链返回null");
//		}
//		if(!jsonUtil.isJson(resultJson)) {
//			
//			logger.info("区块链返回格式不是json");
//			
//			throw new MyException(23,"区块链返回格式不符合预期要求");
//			
//		}
//		
//		return resultJson; 
		String resultJson=null;
		 
		 Message<ShareDataTemplate> reqMsg=new Message<ShareDataTemplate>();
			List<Data> datas = new ArrayList<Data>();
	    	List<Arg> args =  new ArrayList<Arg>();
			reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			reqMsg.setTranCode("CreateShareDataTemplate");
			reqMsg.setType("0");
			reqMsg.setOrgid("Org1");
			reqMsg.setUserid("Org1Admin");
			reqMsg.setHasGeneric(false);
			
			Arg arg1=new Arg();
			arg1.setName("channelName");
			arg1.setValue("mychannel");
			
			Arg arg2=new Arg();
			arg2.setName("chaincodeName");
			arg2.setValue("example");
			
			Arg arg3=new Arg();
			arg3.setName("functionName");
			arg3.setValue("Invoke");
			
			Arg arg4=new Arg();
			arg4.setName("hasFile");
			arg4.setValue("false");
			
			Arg arg5=new Arg();
			arg5.setName("asyncInvoke");
			arg5.setValue("DISABLE");
			
			Arg arg6=new Arg();
			arg6.setName("otherBusinessArg");
			arg6.setValue("argument");
			
			
			args.add(arg1);
			args.add(arg2);
			args.add(arg3);
			args.add(arg4);
			args.add(arg5);
			args.add(arg6);
			
			Data data=new Data();
			data.setContent(requestJson.optString("ShareDataTemplateJson"));
			data.setDataType("ShareDataTemplate");
			data.setFormatType("JSONSTRING");
			
			datas.add(data);
			
			reqMsg.setArgs(args);
			reqMsg.setData(datas);
			
			logger.info("before arrangementServices");
//			resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
			resultJson=shareDataServices.createShareDataTemplate(JSONObject.fromObject(reqMsg).toString());
				if(resultJson==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJson)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
	
				
				return resultJson;
	}

	@Override
	public String DeleteShareDataTemplate(JSONObject requestJson) {
        String resultJson=null;
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/DeleteShareDataTemplate", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		
		return resultJson; 
	}

	@Override
	public String UpdateShareDataTemplate(JSONObject requestJson) {
        String resultJson=null;
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/UpdateShareDataTemplate", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		
		return resultJson; 
	}

	@Override
	public String DeleteShareData(JSONObject requestJson) {
        String resultJson=null;
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/DeleteShareData", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		
		return resultJson; 
	}

	@Override
	public String UpdateShareData(JSONObject requestJson) {
//        String resultJson=null;
//		
//		try {
//			resultJson = new RestUtil().httpPost(path+"/ShareData/UpdateShareData", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		
//		if(resultJson==null) {
//			logger.info("区块链返回null");
//			
//			throw new MyException(81,"区块链返回null");
//		}
//		if(!jsonUtil.isJson(resultJson)) {
//			
//			logger.info("区块链返回格式不是json");
//			
//			throw new MyException(23,"区块链返回格式不符合预期要求");
//			
//		}
//		
//		return resultJson; 
		String resultJson=null;
		 
		 Message<ShareData> reqMsg=new Message<ShareData>();
			List<Data> datas = new ArrayList<Data>();
	    	List<Arg> args =  new ArrayList<Arg>();
			reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			reqMsg.setTranCode("UpdateShareData");
			reqMsg.setType("0");
			reqMsg.setOrgid("Org1");
			reqMsg.setUserid("Org1Admin");
			reqMsg.setHasGeneric(false);
			
			Arg arg1=new Arg();
			arg1.setName("channelName");
			arg1.setValue("mychannel");
			
			Arg arg2=new Arg();
			arg2.setName("chaincodeName");
			arg2.setValue("example");
			
			Arg arg3=new Arg();
			arg3.setName("functionName");
			arg3.setValue("Invoke");
			
			Arg arg4=new Arg();
			arg4.setName("hasFile");
			arg4.setValue("false");
			
			Arg arg5=new Arg();
			arg5.setName("asyncInvoke");
			arg5.setValue("DISABLE");
			
			Arg arg6=new Arg();
			arg6.setName("otherBusinessArg");
			arg6.setValue("argument");
			
			args.add(arg1);
			args.add(arg2);
			args.add(arg3);
			args.add(arg4);
			args.add(arg5);
			args.add(arg6);
			
			Data data=new Data();
			data.setContent(requestJson.optString("ShareDataJson"));
			data.setDataType("ShareData");
			data.setFormatType("JSONSTRING");
			
			datas.add(data);
			
			reqMsg.setArgs(args);
			reqMsg.setData(datas);
			
			logger.info("before arrangementServices");
//			resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
			resultJson=shareDataServices.updateShareData(JSONObject.fromObject(reqMsg).toString());
				if(resultJson==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJson)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
	
				
				return resultJson;
	}

	@Override
	public String CreateGroup(JSONObject requestJson) {
		 String resultJson=null;
		 
		 Message<Group> reqMsg=new Message<Group>();
			List<Data> datas = new ArrayList<Data>();
	    	List<Arg> args =  new ArrayList<Arg>();
			reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			reqMsg.setTranCode("CreateGroup");
			reqMsg.setType("0");
			reqMsg.setOrgid("Org1");
			reqMsg.setUserid("Org1Admin");
			reqMsg.setHasGeneric(false);
			
			Arg arg1=new Arg();
			arg1.setName("channelName");
			arg1.setValue("mychannel");
			
			Arg arg2=new Arg();
			arg2.setName("chaincodeName");
			arg2.setValue("example");
			
			Arg arg3=new Arg();
			arg3.setName("functionName");
			arg3.setValue("Invoke");
			
			Arg arg4=new Arg();
			arg4.setName("hasFile");
			arg4.setValue("false");
			
			Arg arg5=new Arg();
			arg5.setName("asyncInvoke");
			arg5.setValue("DISABLE");
			
			Arg arg6=new Arg();
			arg6.setName("otherBusinessArg");
			arg6.setValue("argument");
			
			
			args.add(arg1);
			args.add(arg2);
			args.add(arg3);
			args.add(arg4);
			args.add(arg5);
			args.add(arg6);
			
			Data data=new Data();
			data.setContent(requestJson.optString("GroupJson"));
			data.setDataType("Group");
			data.setFormatType("JSONSTRING");
			
			datas.add(data);
			
			reqMsg.setArgs(args);
			reqMsg.setData(datas);
			
			logger.info("before arrangementServices");
//			resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
			resultJson=shareDataServices.createGroup(JSONObject.fromObject(reqMsg).toString());
				if(resultJson==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJson)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
	
				
				return resultJson;
			
//			try {
//				resultJson = new RestUtil().httpPost(path+"/ShareData/CreateGroup", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				
//			}
//			
//			if(resultJson==null) {
//				logger.info("区块链返回null");
//				
//				throw new MyException(81,"区块链返回null");
//			}
//			if(!jsonUtil.isJson(resultJson)) {
//				
//				logger.info("区块链返回格式不是json");
//				
//				throw new MyException(23,"区块链返回格式不符合预期要求");
//				
//			}
//			
//			return resultJson; 
	}

	@Override
	public String DeleteGroup(JSONObject requestJson) {
        String resultJson=null;
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/DeleteGroup", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		
		return resultJson; 
	}

	@Override
	public String UpdateGroup(JSONObject requestJson) {
		    String resultJson=null;
			
			try {
				resultJson = new RestUtil().httpPost(path+"/ShareData/UpdateGroup", requestJson);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			if(resultJson==null) {
				logger.info("区块链返回null");
				
				throw new MyException(81,"区块链返回null");
			}
			if(!jsonUtil.isJson(resultJson)) {
				
				logger.info("区块链返回格式不是json");
				
				throw new MyException(23,"区块链返回格式不符合预期要求");
				
			}
			
			return resultJson; 
	}

	@Override
	public String AddOrgUserToGroup(JSONObject requestJson) {
//		String resultJson=null;
//		
//		try {
//			resultJson = new RestUtil().httpPost(path+"/ShareData/AddOrgUserToGroup", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		
//		if(resultJson==null) {
//			logger.info("区块链返回null");
//			
//			throw new MyException(81,"区块链返回null");
//		}
//		if(!jsonUtil.isJson(resultJson)) {
//			
//			logger.info("区块链返回格式不是json");
//			
//			throw new MyException(23,"区块链返回格式不符合预期要求");
//			
//		}
//		
//		return resultJson; 
		String resultJson=null;
		 
		 Message<Group> reqMsg=new Message<Group>();
			List<Data> datas = new ArrayList<Data>();
	    	List<Arg> args =  new ArrayList<Arg>();
			reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			reqMsg.setTranCode("AddOrgUserToGroup");
			reqMsg.setType("0");
			reqMsg.setOrgid("Org1");
			reqMsg.setUserid("Org1Admin");
			reqMsg.setHasGeneric(false);
			
			Arg arg1=new Arg();
			arg1.setName("channelName");
			arg1.setValue("mychannel");
			
			Arg arg2=new Arg();
			arg2.setName("chaincodeName");
			arg2.setValue("example");
			
			Arg arg3=new Arg();
			arg3.setName("functionName");
			arg3.setValue("Invoke");
			
			Arg arg4=new Arg();
			arg4.setName("hasFile");
			arg4.setValue("false");
			
			Arg arg5=new Arg();
			arg5.setName("asyncInvoke");
			arg5.setValue("DISABLE");
			
			Arg arg6=new Arg();
			arg6.setName("otherBusinessArg");
			arg6.setValue("argument");
			
			Arg arg7=new Arg();
			arg7.setName("OrgId");
			arg7.setValue(requestJson.optString("GroupId"));
			
			Arg arg8=new Arg();
			arg8.setName("argsOfGroupId");
			arg8.setValue(requestJson.optString("OrgId"));
			
			args.add(arg1);
			args.add(arg2);
			args.add(arg3);
			args.add(arg4);
			args.add(arg5);
			args.add(arg6);
			args.add(arg7);
			args.add(arg8);
			
			Data data=new Data();
//			data.setContent(requestJson.optString("GroupJson"));
			data.setDataType("Group");
			data.setFormatType("JSONSTRING");
			
			datas.add(data);
			
			reqMsg.setArgs(args);
			reqMsg.setData(datas);
			
			logger.info("before arrangementServices");
//			resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
			resultJson=shareDataServices.addOrgUserToGroup(JSONObject.fromObject(reqMsg).toString());
				if(resultJson==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJson)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
	
				
				return resultJson;
	}

	@Override
	public String QueryShareDataTemplate(JSONObject requestJson) {
        String resultJson=null;
		
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/QueryShareDataTemplate", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		return resultJson;
	}

	

	@Override
	public String QueryGroup(JSONObject requestJson) {
		
//       String resultJson=null;
//		
//		
//		try {
//			resultJson = new RestUtil().httpPost(path+"/ShareData/QueryGroup", requestJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		
//		if(resultJson==null) {
//			logger.info("区块链返回null");
//			
//			throw new MyException(81,"区块链返回null");
//		}
//		if(!jsonUtil.isJson(resultJson)) {
//			
//			logger.info("区块链返回格式不是json");
//			
//			throw new MyException(23,"区块链返回格式不符合预期要求");
//			
//		}
//		return resultJson;
		String resultJson=null;
		 
		 Message<Group> reqMsg=new Message<Group>();
//			List<Data> datas = new ArrayList<Data>();
	    	List<Arg> args =  new ArrayList<Arg>();
			reqMsg.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			reqMsg.setTranCode("QueryGroup");
			reqMsg.setType("0");
			reqMsg.setOrgid("Org1");
			reqMsg.setUserid("Org1Admin");
			reqMsg.setHasGeneric(false);
			
			Arg arg1=new Arg();
			arg1.setName("channelName");
			arg1.setValue("mychannel");
			
			Arg arg2=new Arg();
			arg2.setName("chaincodeName");
			arg2.setValue("example");
			
			Arg arg3=new Arg();
			arg3.setName("functionName");
			arg3.setValue("Query");
			
			Arg arg4=new Arg();
			arg4.setName("hasFile");
			arg4.setValue("false");
			
			Arg arg5=new Arg();
			arg5.setName("asyncInvoke");
			arg5.setValue("DISABLE");
			
			Arg arg6=new Arg();
			arg6.setName("otherBusinessArg");
			arg6.setValue("argument");
			
			Arg arg7=new Arg();
			arg7.setName("GroupId");
			arg7.setValue(requestJson.optString("GroupId"));
			
			
			args.add(arg1);
			args.add(arg2);
			args.add(arg3);
			args.add(arg4);
			args.add(arg5);
			args.add(arg6);
			args.add(arg7);
			
//			Data data=new Data();
//			data.setContent(requestJson.optString("ShareDataJson"));
//			data.setDataType("ShareData");
//			data.setFormatType("JSONSTRING");
			
//			datas.add(data);
			
			reqMsg.setArgs(args);
//			reqMsg.setData(datas);
			
			logger.info("before arrangementServices");
//			resultJson=arrangementServices.createArrangement(requestJson.optString("Arrangement"));
			resultJson=shareDataServices.queryGroup(JSONObject.fromObject(reqMsg).toString());
				if(resultJson==null) {
					logger.info("区块链返回null");
					
					throw new MyException(81,"区块链返回null");
				}
				if(!jsonUtil.isJson(resultJson)) {
					
					logger.info("区块链返回格式不是json");
					
					throw new MyException(23,"区块链返回格式不符合预期要求");
					
				}
	
				
				return resultJson;
	}

	@Override
	public String QueryGroupByOrgId(JSONObject requestJson) {
        String resultJson=null;
		
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/QueryGroupByOrgId", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		return resultJson;
	}

	@Override
	public String QueryShareDataTemplateByOrgId(JSONObject requestJson) {
        String resultJson=null;
		
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/QueryShareDataTemplateByOrgId", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		return resultJson;
	}

	@Override
	public String QueryShareDataByOrgId(JSONObject requestJson) {
        String resultJson=null;
		
		
		try {
			resultJson = new RestUtil().httpPost(path+"/ShareData/QueryShareDataByOrgId", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(resultJson==null) {
			logger.info("区块链返回null");
			
			throw new MyException(81,"区块链返回null");
		}
		if(!jsonUtil.isJson(resultJson)) {
			
			logger.info("区块链返回格式不是json");
			
			throw new MyException(23,"区块链返回格式不符合预期要求");
			
		}
		return resultJson;
	}

	@Override
	public String SelectShareDataByObject(JSONObject requestJson) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
