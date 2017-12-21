package com.dao;

import java.util.List;

import com.model.User;

public interface AdminDao {

	List<User> getInactiveEmp();
	List<User> getActiveEmp();
	User getOrganizationProfile(String userId);
	List<User> getAllEmp();
	void deleteEmp(String userId);
	List<User> getsupervisionList();
	void updateProfile(User user);
	void runPayroll(User user);
	User getSupervisorNameById(String supervision);
}
