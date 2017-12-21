package com.service;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.util.List;

import com.dao.EmployeeDao;
import com.dao.EmployeeDaoImpl;
import com.model.DirectoryFiles;
import com.model.User;

public class EmployeeServiceImpl implements EmployeeService{
EmployeeDao empDao= new EmployeeDaoImpl();
	
	@Override
	public void addLeaves(User user) {
		empDao.addLeaves(user);
	}

	@Override
	public User getLeavesInfo(String userId) {
		User u=empDao.getLeavesInfo(userId);
		return u;
	}

}
