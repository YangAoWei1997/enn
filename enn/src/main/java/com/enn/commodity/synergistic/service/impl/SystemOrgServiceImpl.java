package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.SystemOrgDao;
import com.enn.commodity.synergistic.entity.SystemOrg;
import com.enn.commodity.synergistic.service.SystemOrgService;

@Service
public class SystemOrgServiceImpl implements SystemOrgService{
	
	@Autowired
	private SystemOrgDao systemOrgDao;

	@Override
	public List<SystemOrg> SelectSystemOrgByObject(SystemOrg systemOrg) {
		
		return systemOrgDao.SelectSystemOrgByObject(systemOrg);
	}

	@Override
	public int CreateSystemOrg(SystemOrg systemOrg) {
		
		return systemOrgDao.CreateSystemOrg(systemOrg);
	}

	@Override
	public int UpdateSystemOrg(SystemOrg systemOrg) {
		
		return systemOrgDao.UpdateSystemOrg(systemOrg);
	}

	@Override
	public int DeleteSystemOrg(SystemOrg systemOrg) {
		
		return systemOrgDao.DeleteSystemOrg(systemOrg);
	}

}
