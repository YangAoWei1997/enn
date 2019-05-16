package com.enn.commodity.synergistic.service;

import com.enn.commodity.synergistic.entity.Queuing;

public interface QueuingService {
	
	Queuing QueryQueuingInfo(String orgid);
	
	int SaveQueuingInfo(Queuing Queuing);
	
	int ModifyQueuingInfo(Queuing Queuing);

}
