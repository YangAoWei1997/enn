package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.OrgAuth;

public interface OrgAuthDao {
	
	List<OrgAuth> SelectOrgAuthByObject(OrgAuth orgAuth);

}
