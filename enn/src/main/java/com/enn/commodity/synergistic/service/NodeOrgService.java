package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.NodeOrg;

public interface NodeOrgService {
	
    List<NodeOrg> SelectNodeOrgByObject(NodeOrg nodeOrg);
	
	int CreateNodeOrg(NodeOrg nodeOrg);
	
	int UpdateNodeOrg(NodeOrg nodeOrg);
	
	int DeleteNodeOrg(NodeOrg nodeOrg);

}
