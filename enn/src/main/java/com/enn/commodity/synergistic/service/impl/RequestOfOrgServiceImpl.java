package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.enn.commodity.synergistic.dao.RequestInfoDao;
import com.enn.commodity.synergistic.dao.RequestOfOrgDao;
import com.enn.commodity.synergistic.entity.RequestInfo;
import com.enn.commodity.synergistic.entity.RequestOfOrg;
import com.enn.commodity.synergistic.service.RequestInfoService;
import com.enn.commodity.synergistic.service.RequestOfOrgService;

@Service
public class RequestOfOrgServiceImpl implements RequestOfOrgService{
	
	@Autowired
	private RequestOfOrgDao requestOfOrgDao;

	@Override
	public int CreateRequestOfOrg(RequestOfOrg requestOfOrg) {
		// TODO Auto-generated method stub
		return requestOfOrgDao.CreateRequestOfOrg(requestOfOrg);
	}

	@Override
	public int UpdateRequestOfOrg(RequestOfOrg requestOfOrg) {
		// TODO Auto-generated method stub
		return requestOfOrgDao.UpdateRequestOfOrg(requestOfOrg);
	}

	@Override
	public List<RequestOfOrg> SelectRequestOfOrgByObject(RequestOfOrg requestOfOrg) {
		// TODO Auto-generated method stub
		return requestOfOrgDao.SelectRequestOfOrgByObject(requestOfOrg);
	}
	
	

}
