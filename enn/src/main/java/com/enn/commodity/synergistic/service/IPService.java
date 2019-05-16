package com.enn.commodity.synergistic.service;


import java.util.List;

import com.enn.commodity.synergistic.entity.Ability;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.Location;
import com.enn.commodity.synergistic.entity.Product;
import com.enn.commodity.synergistic.entity.ResultMap;

import net.sf.json.JSONObject;

public interface IPService {
	
	List<IP>SelectIPByObject(IP ip);
	
    JSONObject CreateIP(IP ip);
	
	int CreateProduct(Product product);
	
	int CreateLocation(Location location);
	
	JSONObject SelectInfoByObject(IP ip);
	
	int CreateAbility(Ability ability);

}
