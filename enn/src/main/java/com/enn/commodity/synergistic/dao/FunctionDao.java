package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.Function;
import com.enn.commodity.synergistic.entity.Role;
import com.enn.commodity.synergistic.entity.User;

public interface FunctionDao {
	
	List<Function>SelectFunctionsByUserId(User user);
	
	List<Function>SelectFunctionsByRoleId(Role role);
	
	List<Function>SelectFunctionsByFunname(Function function);
	
	int insertFunction(Function function);
	
	int updateFunction(Function function);

}
