package com.enn.commodity.synergistic.dao;

import java.util.List;


import com.enn.commodity.synergistic.entity.SystemOrg;

public interface SystemOrgDao {
	
    List<SystemOrg> SelectSystemOrgByObject(SystemOrg systemOrg);
	
	int CreateSystemOrg(SystemOrg systemOrg);
	
	int UpdateSystemOrg(SystemOrg systemOrg);
	
	int DeleteSystemOrg(SystemOrg systemOrg);

}
