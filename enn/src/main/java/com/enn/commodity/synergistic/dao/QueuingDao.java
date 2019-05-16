package com.enn.commodity.synergistic.dao;

import com.enn.commodity.synergistic.entity.Queuing;

public interface QueuingDao {
	
	Queuing QueryQueuingInfo(String orgid);
	
	int SaveQueuingInfo(Queuing Queuing);
	
	int ModifyQueuingInfo(Queuing Queuing);

}
