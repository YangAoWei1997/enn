package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.OrgAuth;

public interface OrgAuthService {
	
	List<OrgAuth> SelectOrgAuthByObject(OrgAuth orgAuth);

}
