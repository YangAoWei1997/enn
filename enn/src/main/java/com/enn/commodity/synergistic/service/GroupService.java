package com.enn.commodity.synergistic.service;

import com.enn.commodity.synergistic.entity.Group;

public interface GroupService {
	
    int CreateGroup(Group group);
	
	int UpdateGroup(Group group);
	
	int DeleteGroup(Group group);

}
