package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.OrgAuthDao;
import com.enn.commodity.synergistic.entity.OrgAuth;
import com.enn.commodity.synergistic.service.OrgAuthService;

@Service
public class OrgAuthServiceImpl implements OrgAuthService{

	
	@Autowired
	private OrgAuthDao orgAuthDao;
	@Override
	public List<OrgAuth> SelectOrgAuthByObject(OrgAuth orgAuth) {
		
		return orgAuthDao.SelectOrgAuthByObject(orgAuth);
	}

}
