package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.SystemOrg;

public interface SystemOrgService {
	
    List<SystemOrg> SelectSystemOrgByObject(SystemOrg systemOrg);
	
	int CreateSystemOrg(SystemOrg systemOrg);
	
	int UpdateSystemOrg(SystemOrg systemOrg);
	
	int DeleteSystemOrg(SystemOrg systemOrg);

}
