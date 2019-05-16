package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.ShareData;

public interface ShareDataDao {
	
	List<ShareData> SelectShareDataByObject(ShareData shareData);
	
	int CreateShareData(ShareData shareData);
	
	int UpdateShareData(ShareData shareData);
	
	int DeleteShareDataOrg(ShareData shareData);

}
