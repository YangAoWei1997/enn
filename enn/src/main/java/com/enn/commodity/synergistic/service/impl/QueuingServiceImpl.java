package com.enn.commodity.synergistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.QueuingDao;
import com.enn.commodity.synergistic.entity.Queuing;
import com.enn.commodity.synergistic.service.QueuingService;
@Service
public class QueuingServiceImpl implements QueuingService{
	
	@Autowired
	private QueuingDao queuingDao;

	@Override
	public Queuing QueryQueuingInfo(String orgid) {
		
		return queuingDao.QueryQueuingInfo(orgid);
	}

	@Override
	public int SaveQueuingInfo(Queuing Queuing) {
		
		return queuingDao.SaveQueuingInfo(Queuing);
	}

	@Override
	public int ModifyQueuingInfo(Queuing Queuing) {
		// TODO Auto-generated method stub
		return queuingDao.ModifyQueuingInfo(Queuing);
	}

}
