package com.service;

import java.util.List;

import com.model.User;

public interface UserService {

	void addUser(User user);

	boolean authenticateUsers(String userId, String password);

	List<User> getUserNameById(String userId);

	User getUserDetails(String userId);

	void updateUser(User user);

}
