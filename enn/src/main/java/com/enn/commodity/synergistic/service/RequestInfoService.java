package com.enn.commodity.synergistic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.enn.commodity.synergistic.entity.RequestInfo;

public interface RequestInfoService {
	@Transactional
    int CreateRequestInfo(RequestInfo requestinfo);
	
	int UpdateRquestInfo(RequestInfo requestinfo);
	
	List<RequestInfo> SelectRequestInfoByObject(RequestInfo requestinfo);

}
