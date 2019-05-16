package com.enn.commodity.synergistic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.enn.commodity.synergistic.entity.Role;

import net.sf.json.JSONArray;

public interface RoleDao {
	
	List<Role> SelectRolesByObject(Role role);
	
	int CreateRole(Role role);
	
	int UpdateRole(Role role);
	
	int RoleAuthorization(@Param("roleId")String roleId,@Param("functionList")List<String> functionList);
	
	

}
