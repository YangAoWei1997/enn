package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.BusinessArea;

public interface BusinessAreaDao {
	
	List<BusinessArea> SelectBusinessAreaByObject(BusinessArea businessArea);
	
	int CreateBusinessArea(BusinessArea businessArea);
	
	int UpdateBusinessArea(BusinessArea businessArea);
	
	int DeleteBusinessArea(BusinessArea businessArea);

}
