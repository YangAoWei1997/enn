package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.NodeOrg;

public interface NodeOrgDao {
	
	List<NodeOrg> SelectNodeOrgByObject(NodeOrg nodeOrg);
	
	int CreateNodeOrg(NodeOrg nodeOrg);
	
	int UpdateNodeOrg(NodeOrg nodeOrg);
	
	int DeleteNodeOrg(NodeOrg nodeOrg);

}
