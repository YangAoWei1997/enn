package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.MineralResources;


public interface MineralResourcesService {
	
	List<MineralResources> SelectMineralResourcesByObject(MineralResources mineralResources);
	
	int InsertMineralResources(MineralResources mineralResources);

}
