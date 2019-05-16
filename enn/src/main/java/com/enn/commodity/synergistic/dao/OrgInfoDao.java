package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.OrgInfo;

public interface OrgInfoDao {
	
    List<OrgInfo> SelectOrgInfoByObject(OrgInfo orgInfo);
	
	int CreateOrgInfo(OrgInfo orgInfo);
	
	int UpdateOrgInfo(OrgInfo orgInfo);
	
	int DeleteOrgInfo(OrgInfo orgInfo);

}
