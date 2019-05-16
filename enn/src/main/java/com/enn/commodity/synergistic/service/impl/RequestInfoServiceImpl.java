package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.enn.commodity.synergistic.dao.RequestInfoDao;
import com.enn.commodity.synergistic.entity.RequestInfo;
import com.enn.commodity.synergistic.service.RequestInfoService;

@Service
public class RequestInfoServiceImpl implements RequestInfoService{
	
	@Autowired
	private RequestInfoDao requestInfoDao;
	
	@Transactional
	@Override
	public int CreateRequestInfo(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		
		
		    Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
			int result=requestInfoDao.CreateRequestInfo(requestinfo);
			if(!true) {
				TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
				return 0;
			}
			
			return result;
		
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			return 0;
		
		
		
		
		
	}

	@Override
	public int UpdateRquestInfo(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		return requestInfoDao.UpdateRquestInfo(requestinfo);
	}

	@Override
	public List<RequestInfo> SelectRequestInfoByObject(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		return requestInfoDao.SelectRequestInfoByObject(requestinfo);
	}

}
