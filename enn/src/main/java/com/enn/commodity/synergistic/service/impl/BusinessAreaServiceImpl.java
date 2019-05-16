package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.BusinessAreaDao;
import com.enn.commodity.synergistic.entity.BusinessArea;
import com.enn.commodity.synergistic.service.BusinessAreaService;

@Service
public class BusinessAreaServiceImpl implements BusinessAreaService{
	
	@Autowired
	BusinessAreaDao businessAreaDao;

	@Override
	public List<BusinessArea> SelectBusinessAreaByObject(BusinessArea businessArea) {

		return businessAreaDao.SelectBusinessAreaByObject(businessArea);
	}

	@Override
	public int CreateBusinessArea(BusinessArea businessArea) {
		
		return businessAreaDao.CreateBusinessArea(businessArea);
	}

	@Override
	public int UpdateBusinessArea(BusinessArea businessArea) {
		
		return businessAreaDao.UpdateBusinessArea(businessArea);
	}

	@Override
	public int DeleteBusinessArea(BusinessArea businessArea) {
		
		return businessAreaDao.DeleteBusinessArea(businessArea);
	}

}
