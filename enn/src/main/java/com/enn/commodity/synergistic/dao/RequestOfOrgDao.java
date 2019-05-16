package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.RequestInfo;
import com.enn.commodity.synergistic.entity.RequestOfOrg;

public interface RequestOfOrgDao {
	
	int CreateRequestOfOrg(RequestOfOrg requestOfOrg);
	
	int UpdateRequestOfOrg(RequestOfOrg requestOfOrg);
	
	List<RequestOfOrg> SelectRequestOfOrgByObject(RequestOfOrg requestOfOrg);

}
