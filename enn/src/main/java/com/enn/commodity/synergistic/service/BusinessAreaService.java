package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.BusinessArea;

public interface BusinessAreaService {
	
    List<BusinessArea> SelectBusinessAreaByObject(BusinessArea businessArea);
	
	int CreateBusinessArea(BusinessArea businessArea);
	
	int UpdateBusinessArea(BusinessArea businessArea);
	
	int DeleteBusinessArea(BusinessArea businessArea);

}
