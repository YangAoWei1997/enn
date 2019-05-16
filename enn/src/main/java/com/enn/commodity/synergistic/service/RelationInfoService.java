package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.RelationInfo;

public interface RelationInfoService {
	
    int CreateRelationInfo(RelationInfo relationInfo);
	
	int UpdateRelationInfo(RelationInfo relationInfo);
	
	List<RelationInfo> SelectRelationInfoByObject(RelationInfo relationInfo);

}
