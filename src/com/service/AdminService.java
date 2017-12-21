package com.service;

import java.util.List;

import com.model.User;


public interface AdminService {

	List<User> getInactiveEmp();
	List<User> getActiveEmp();
	User getOrganizationProfile(String userId);
	List<User> getAllEmp();
	void deleteEmp(String userId);
	List<User> getsupervisionList();
	void updateProfile(User user);
	void runPayroll(List<User> allEmpList);
	User getSupervisorNameById(String supervision);

}
