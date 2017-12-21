package com.dao;

import java.util.List;

import com.model.Directories;
import com.model.User;

public interface ManagerDao {

	void addLeaves(User user);

	User getLeavesInfo(String userId);

	List<User> getEmployees(String userId);

	void addBonus(User u);

	void acceptLeave(String userID, String balanceLeaves);

	void declineLeave(String userID);

	List<User> getLeavesRequest(String userId);

}
