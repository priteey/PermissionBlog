package com.service;

import java.util.List;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.model.User;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao= new AdminDaoImpl();
	
	@Override
	public List<User> getInactiveEmp() {
		List<User> empList = adminDao.getInactiveEmp();
		return empList;
	}

	@Override
	public List<User> getActiveEmp() {
		List<User> activeEmpList = adminDao.getActiveEmp();
		return activeEmpList;
	}

	@Override
	public User getOrganizationProfile(String userId) {
		User profileList = adminDao.getOrganizationProfile(userId);
		return profileList;
	}

	@Override
	public List<User> getAllEmp() {
		List<User> empList = adminDao.getAllEmp();
		return empList;
	}

	@Override
	public void deleteEmp(String userId) {
		adminDao.deleteEmp(userId);
	}

	@Override
	public List<User> getsupervisionList() {
		List<User> supervisionList = adminDao.getsupervisionList();
		return supervisionList;
	}

	@Override
	public void updateProfile(User user) {
		adminDao.updateProfile(user);		
	}

	@Override
	public void runPayroll(List<User> allEmpList) {
		String salary, bonus, userId;
		String newSalary=null;
		for(int i=0; i<allEmpList.size(); i++){
			User user= new User();
			salary = allEmpList.get(i).getSalary();
			bonus = allEmpList.get(i).getBonus();
			userId = allEmpList.get(i).getUserId();
			if(bonus==null){
				user.setSalary(salary);
			}
			else if(bonus.isEmpty()){
				user.setSalary(salary);
			}
			else{
			    newSalary= (Integer.parseInt(salary)+Integer.parseInt(bonus))+"";
			    user.setSalary(newSalary);
			}
			user.setBonus("0");
			user.setUserId(userId);
			adminDao.runPayroll(user);	
		}
	}

	@Override
	public User getSupervisorNameById(String supervision) {
		User supervisor = adminDao.getSupervisorNameById(supervision);
		return supervisor;
	}
	
}
