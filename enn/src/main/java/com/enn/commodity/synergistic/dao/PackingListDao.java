package com.enn.commodity.synergistic.dao;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.PackingList;
import com.enn.commodity.synergistic.entity.QualityInfo;

public interface PackingListDao {
	
	List<PackingList>SelectPackingListByObject(PackingList packingList);
	
	List<QualityInfo>SelectQualityInfoByObject(QualityInfo qualityInfo);
	
	int CreatePackingList(PackingList packingList);
	
	int CreateQualityInfo(QualityInfo qualityInfo);
	
	List<PackingList> SelectPackingListByObjectIn(Map<String,String> map);

}
