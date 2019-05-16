package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.RelationInfo;

public interface RelationInfoDao {
	
	int CreateRelationInfo(RelationInfo relationInfo);
	
	int UpdateRelationInfo(RelationInfo relationInfo);
	
	List<RelationInfo> SelectRelationInfoByObject(RelationInfo relationInfo);

}
