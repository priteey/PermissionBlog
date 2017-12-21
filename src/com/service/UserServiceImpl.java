package com.service;

import java.util.List;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.model.User;

public class UserServiceImpl implements UserService {
	 UserDao userDao = new UserDaoImpl(); 

	
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
		
	}
   public boolean authenticateUsers(String userId, String password) {
		boolean users= userDao.authenticateUsers(userId,password);		
        return users;
	}
	public List<User> getUserNameById(String userId) {
		List<User> user = userDao.getUserNameById(userId); 	
		return user;
	}
	@Override
	public User getUserDetails(String userId) {
		User user = userDao.getUserDetails(userId); 	
		return user;
	}
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);		
	}
}
