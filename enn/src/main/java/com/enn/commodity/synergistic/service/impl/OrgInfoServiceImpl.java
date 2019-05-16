package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.OrgInfoDao;
import com.enn.commodity.synergistic.entity.OrgInfo;
import com.enn.commodity.synergistic.service.OrgInfoService;

@Service
public class OrgInfoServiceImpl implements OrgInfoService{
	
	@Autowired
	private OrgInfoDao orgInfoDao;

	@Override
	public List<OrgInfo> SelectOrgInfoByObject(OrgInfo orgInfo) {
		// TODO Auto-generated method stub
		return orgInfoDao.SelectOrgInfoByObject(orgInfo);
	}

	@Override
	public int CreateOrgInfo(OrgInfo orgInfo) {
		// TODO Auto-generated method stub
		return orgInfoDao.CreateOrgInfo(orgInfo);
	}

	@Override
	public int UpdateOrgInfo(OrgInfo orgInfo) {
		// TODO Auto-generated method stub
		return orgInfoDao.UpdateOrgInfo(orgInfo);
	}

	@Override
	public int DeleteOrgInfo(OrgInfo orgInfo) {
		// TODO Auto-generated method stub
		return orgInfoDao.DeleteOrgInfo(orgInfo);
	}

}
