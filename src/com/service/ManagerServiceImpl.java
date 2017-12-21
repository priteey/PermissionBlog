package com.service;

import java.util.List;

import com.dao.ManagerDao;
import com.dao.ManagerDaoImpl;
import com.model.Directories;
import com.model.User;

public class ManagerServiceImpl implements ManagerService{
ManagerDao manageDao= new ManagerDaoImpl();
	
	@Override
	public void addLeaves(User user) {
		manageDao.addLeaves(user);
	}

	@Override
	public User getLeavesInfo(String userId) {
		User u=manageDao.getLeavesInfo(userId);
		return u;
	}

	@Override
	public List<User> getEmployees(String userId) {
		List<User> emp= manageDao.getEmployees(userId);
		return emp;
	}

	@Override
	public void addBonus(User u) {
		manageDao.addBonus(u);
		
	}

	@Override
	public void acceptLeave(String userID) {
		User u=manageDao.getLeavesInfo(userID);
		String leavesRequested=u.getLeaves();
		String remainingLeaves= u.getRemainingLeaves();
		String balanceLeaves= Integer.parseInt(remainingLeaves)-Integer.parseInt(leavesRequested)+"";
		System.out.print(balanceLeaves);
		manageDao.acceptLeave(userID,balanceLeaves);
	}

	@Override
	public void declineLeave(String userID) {
		manageDao.declineLeave(userID);	
	}

	@Override
	public List<User> getLeavesRequest(String userId) {
		List<User> leaveList= manageDao.getLeavesRequest(userId);
		return leaveList;
	}



	

}
