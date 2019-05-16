package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.MineralResourcesDao;
import com.enn.commodity.synergistic.entity.MineralResources;
import com.enn.commodity.synergistic.service.MineralResourcesService;
@Service
public class MineralResourcesServiceImpl implements MineralResourcesService{
	
	@Autowired
	private MineralResourcesDao mineralResourcesDao;

	@Override
	public List<MineralResources> SelectMineralResourcesByObject(MineralResources mineralResources) {
		// TODO Auto-generated method stub
		return mineralResourcesDao.SelectMineralResourcesByObject(mineralResources);
	}

	@Override
	public int InsertMineralResources(MineralResources mineralResources) {
		// TODO Auto-generated method stub
		int result=mineralResourcesDao.InsertMineralResources(mineralResources);
		return result;
	}
	
	

}
