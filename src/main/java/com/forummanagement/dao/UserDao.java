package com.forummanagement.dao;

import java.util.List;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.model.User;

public interface UserDao {
	
	User login(String username, String password);
	
	StatusMessage register(User user);
	
	User getUserByUsername(String username);
	
	List<User> getAllUsers();
	
}
