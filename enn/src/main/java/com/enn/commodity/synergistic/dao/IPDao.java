package com.enn.commodity.synergistic.dao;

import java.util.HashMap;
import java.util.List;

import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.Location;
import com.enn.commodity.synergistic.entity.Product;
import com.enn.commodity.synergistic.entity.ResultMap;

public interface IPDao {
	
	List<IP>SelectIPByObject(IP ip);
	
	int CreateIP(IP ip);
	
	int CreateProduct(Product product);
	
	int CreateLocation(Location location);
	
	List<HashMap> SelectInfoByObject(IP ip);
	
	int CreateAbility(Ability ability);
	
	int UpdateAbility(Ability ability);

}
