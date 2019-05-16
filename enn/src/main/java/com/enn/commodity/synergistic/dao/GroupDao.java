package com.enn.commodity.synergistic.dao;

import com.enn.commodity.synergistic.entity.Group;

public interface GroupDao {
	
	int CreateGroup(Group group);
	
	int UpdateGroup(Group group);
	
	int DeleteGroup(Group group);

}
