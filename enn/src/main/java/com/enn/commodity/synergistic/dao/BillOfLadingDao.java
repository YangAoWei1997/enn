package com.enn.commodity.synergistic.dao;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.BillOfLading;

public interface BillOfLadingDao {
	
	List<BillOfLading>SelectBillOfLadingByObject(BillOfLading billOfLading);
	
	int CreateBillOfLading(BillOfLading billOfLading);
	
	List<BillOfLading>SelectBillOfLadingByObjectIn(Map<String,String> map);

}
