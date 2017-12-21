package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controller.DBConn;
import com.model.Directories;
import com.model.User;

public class ManagerDaoImpl implements ManagerDao {
private Connection conn;
	
	public ManagerDaoImpl(){
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
	@Override
	public List<User> getEmployees(String userId) {
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where supervision='"+userId+"'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setUserId(rs.getString("userId"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setAddress(rs.getString("address"));
				u.setPhoneNo(rs.getString("phoneNo"));	 
				u.setEmailId(rs.getString("emailId"));
				u.setDivisionName(rs.getString("divisionName"));
				u.setSalary(rs.getString("salary"));
				u.setBonus(rs.getString("bonus"));
				u.setLeaves(rs.getString("noOfLeaves"));
				u.setLeaveStatus(rs.getString("leaveStatus"));
				u.setLeaveType(rs.getString("leaveType"));
				u.setFromDate(rs.getString("fromDate"));
				u.setToDate(rs.getString("toDate"));
				u.setRemainingLeaves(rs.getString("remainingLeaves"));
				userList.add(u);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return userList;
	}
	@Override
	public void addBonus(User u) {
		try{
			PreparedStatement preparedStmt= conn.prepareStatement("update users set bonus=? where userId=?");
			preparedStmt.setString(1, u.getBonus());
			preparedStmt.setString(2, u.getUserId());
			preparedStmt.executeUpdate();
		}
		catch (SQLException e) {
            e.printStackTrace();
        }		
	}
	@Override
	public void acceptLeave(String userID, String balanceLeaves) {
		try{
		PreparedStatement preparedStmt= conn.prepareStatement("update users set leaveStatus='Approved', remainingLeaves='"+ balanceLeaves+"' where userId='"+userID+"'");
		preparedStmt.executeUpdate();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }		
	}
	@Override
	public void declineLeave(String userID) {
		try{
		PreparedStatement preparedStmt= conn.prepareStatement("update users set leaveStatus='Disapproved' where userId='"+userID+"'");
		preparedStmt.executeUpdate();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }		
	}
	@Override
	public List<User> getLeavesRequest(String userId) {
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where leaveStatus='Pending' and supervision='"+userId+"'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setUserId(rs.getString("userId"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setAddress(rs.getString("address"));
				u.setPhoneNo(rs.getString("phoneNo"));	 
				u.setEmailId(rs.getString("emailId"));
				u.setDivisionName(rs.getString("divisionName"));
				u.setSalary(rs.getString("salary"));
				u.setBonus(rs.getString("bonus"));
				u.setLeaves(rs.getString("noOfLeaves"));
				u.setLeaveStatus(rs.getString("leaveStatus"));
				u.setLeaveType(rs.getString("leaveType"));
				u.setFromDate(rs.getString("fromDate"));
				u.setToDate(rs.getString("toDate"));
				u.setRemainingLeaves(rs.getString("remainingLeaves"));
				userList.add(u);
			}
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return userList;
	}
	}
