package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {

	void addUser(User user);

    public boolean authenticateUsers(String userId, String password);
	public List<User> getUserNameById(String userId);
	User getUserDetails(String userId);
	public void updateUser(User user);

}
