package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.RelationInfoDao;
import com.enn.commodity.synergistic.entity.RelationInfo;
import com.enn.commodity.synergistic.service.RelationInfoService;

@Service
public class RelationInfoServiceImpl implements RelationInfoService{
	
	@Autowired
	private RelationInfoDao relationInfoDao;

	@Override
	public int CreateRelationInfo(RelationInfo relationInfo) {
		// TODO Auto-generated method stub
		return relationInfoDao.CreateRelationInfo(relationInfo);
	}

	@Override
	public int UpdateRelationInfo(RelationInfo relationInfo) {
		// TODO Auto-generated method stub
		return relationInfoDao.UpdateRelationInfo(relationInfo);
	}

	@Override
	public List<RelationInfo> SelectRelationInfoByObject(RelationInfo relationInfo) {
		// TODO Auto-generated method stub
		return relationInfoDao.SelectRelationInfoByObject(relationInfo);
	}

}
