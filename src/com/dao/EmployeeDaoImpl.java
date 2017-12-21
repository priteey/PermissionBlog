package com.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.controller.DBConn;
import com.model.DirectoryFiles;
import com.model.User;

public class EmployeeDaoImpl implements EmployeeDao{
private Connection conn;
	
	public EmployeeDaoImpl(){
		conn= DBConn.getConnection();
	}
	@Override
	public void addLeaves(User user) {
		try{
			PreparedStatement preparedStmt= conn.prepareStatement("update users set noOfLeaves=?, leaveType=?, fromDate=?, toDate=?, leaveStatus=? where userId=?");
			preparedStmt.setString(1, user.getLeaves());
			preparedStmt.setString(2, user.getLeaveType()); 
			preparedStmt.setString(3, user.getFromDate());
			preparedStmt.setString(4, user.getToDate());
			preparedStmt.setString(5, user.getLeaveStatus());
			preparedStmt.setString(6, user.getUserId());
			preparedStmt.executeUpdate();
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Override
	public User getLeavesInfo(String userId) {
		User u = new User();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where userId='"+userId+"'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				u.setLeaves(rs.getString("noOfLeaves"));
				u.setLeaveType(rs.getString("leaveType"));
				u.setFromDate(rs.getString("fromDate"));
				u.setToDate(rs.getString("toDate"));	 
				u.setLeaveStatus(rs.getString("leaveStatus"));
				u.setRemainingLeaves(rs.getString("remainingLeaves"));
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return u;
	}
		
}
