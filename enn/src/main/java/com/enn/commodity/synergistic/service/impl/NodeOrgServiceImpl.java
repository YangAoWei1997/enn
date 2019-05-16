package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.NodeOrgDao;
import com.enn.commodity.synergistic.entity.NodeOrg;
import com.enn.commodity.synergistic.service.NodeOrgService;

@Service
public class NodeOrgServiceImpl implements NodeOrgService{
	
	@Autowired
	private NodeOrgDao nodeOrgDao;

	@Override
	public List<NodeOrg> SelectNodeOrgByObject(NodeOrg nodeOrg) {
		
		return nodeOrgDao.SelectNodeOrgByObject(nodeOrg);
	}

	@Override
	public int CreateNodeOrg(NodeOrg nodeOrg) {
		
		return nodeOrgDao.CreateNodeOrg(nodeOrg);
	}

	@Override
	public int UpdateNodeOrg(NodeOrg nodeOrg) {
		
		return nodeOrgDao.UpdateNodeOrg(nodeOrg);
	}

	@Override
	public int DeleteNodeOrg(NodeOrg nodeOrg) {
		
		return nodeOrgDao.DeleteNodeOrg(nodeOrg);
	}

}
