package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.RequestInfo;

public interface RequestInfoDao {
	
	int CreateRequestInfo(RequestInfo requestinfo);
	
	int UpdateRquestInfo(RequestInfo requestinfo);
	
	List<RequestInfo> SelectRequestInfoByObject(RequestInfo requestinfo);

}
