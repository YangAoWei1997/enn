package com.enn.commodity.synergistic.dao;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.User;



public interface UserDao {
	
	List<User> selectAllUser();
	
	List<User> SelectUsersByObject(User user);
	
	int CreateUser(User user);
	
	int UpdatePassword(User user);
	
	int UpdateUser(User user);

	int BatchUpdateUser(Map<String,Object> map);
	
	int BatchDelete(List<User> list);
}
