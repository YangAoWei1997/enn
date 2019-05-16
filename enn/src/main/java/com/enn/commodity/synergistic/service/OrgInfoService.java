package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.OrgInfo;

public interface OrgInfoService {
	
	List<OrgInfo> SelectOrgInfoByObject(OrgInfo orgInfo);
	
	int CreateOrgInfo(OrgInfo orgInfo);
	
	int UpdateOrgInfo(OrgInfo orgInfo);
	
	int DeleteOrgInfo(OrgInfo orgInfo);

}
