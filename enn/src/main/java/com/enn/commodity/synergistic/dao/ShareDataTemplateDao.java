package com.enn.commodity.synergistic.dao;

import com.enn.commodity.synergistic.entity.ShareDataTemplate;

public interface ShareDataTemplateDao {
	
	int CreateShareDataTemplate(ShareDataTemplate shareDataTemplate);
	
	int UpdateShareDataTemplate(ShareDataTemplate shareDataTemplate);
	
	int DeleteShareDsataTemplate(ShareDataTemplate shareDataTemplate);

}
