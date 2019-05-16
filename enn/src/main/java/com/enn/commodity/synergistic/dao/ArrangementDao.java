package com.enn.commodity.synergistic.dao;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.MaterialDetail;

public interface ArrangementDao {
	
	List<Arrangement>SelectArrangementByObject(Arrangement arrangement);
	
	int CreateArrangement(Arrangement arrangement);
	
	int CreateMaterialDetail(MaterialDetail materialDtail);
	
	List<Arrangement> SelectArrangementByObjectIn(Map<String,String> map);

}
