package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.MineralResources;


public interface MineralResourcesDao {
	
	List<MineralResources> SelectMineralResourcesByObject(MineralResources mineralResources);
	
	int InsertMineralResources(MineralResources mineralResources);

}
