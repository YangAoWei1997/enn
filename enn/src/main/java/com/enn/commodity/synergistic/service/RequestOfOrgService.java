package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.RequestOfOrg;

public interface RequestOfOrgService {
	
    int CreateRequestOfOrg(RequestOfOrg requestOfOrg);
	
	int UpdateRequestOfOrg(RequestOfOrg requestOfOrg);
	
	List<RequestOfOrg> SelectRequestOfOrgByObject(RequestOfOrg requestOfOrg);

}
