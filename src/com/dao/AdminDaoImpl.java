package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controller.DBConn;
import com.model.User;

public class AdminDaoImpl implements AdminDao{
	private Connection conn;
	
	public AdminDaoImpl(){
		conn= DBConn.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getInactiveEmp() {
		@SuppressWarnings("rawtypes")
		List userList= new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where role NOT IN ('admin') and status = 'inactive'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setAddress(rs.getString("address"));
				user.setEmailId(rs.getString("emailId"));
				user.setPhoneNo(rs.getString("phoneNo"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	@Override
	public List<User> getActiveEmp() {
		@SuppressWarnings("rawtypes")
		List userList= new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where role NOT IN ('admin') and status = 'active'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setAddress(rs.getString("address"));
				user.setEmailId(rs.getString("emailId"));
				user.setPhoneNo(rs.getString("phoneNo"));
				user.setRole(rs.getString("role"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getOrganizationProfile(String userId) {
		@SuppressWarnings("rawtypes")
		User user = new User();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where role NOT IN ('admin') and userId='"+ userId +"'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				user.setDivisionName(rs.getString("divisionName"));
				user.setRole(rs.getString("role"));
				user.setSalary(rs.getString("salary"));
				user.setSupervision(rs.getString("supervision"));
				user.setBonus(rs.getString("bonus"));
				user.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllEmp() {
		@SuppressWarnings("rawtypes")
		List userList= new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where role NOT IN ('admin')");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setAddress(rs.getString("address"));
				user.setEmailId(rs.getString("emailId"));
				user.setPhoneNo(rs.getString("phoneNo"));
				user.setDivisionName(rs.getString("divisionName"));
				user.setRole(rs.getString("role"));
				user.setSalary(rs.getString("salary"));
				user.setSupervision(rs.getString("supervision"));
				user.setBonus(rs.getString("bonus"));
				user.setStatus(rs.getString("status"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public void deleteEmp(String userId) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users where userId='"+ userId +"'");
			preparedStatement.executeUpdate();
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
	}

	@Override
	public List<User> getsupervisionList() {

		@SuppressWarnings("rawtypes")
		List userList= new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where role IN ('Manager')");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	
	}

	@Override
	public void updateProfile(User user) {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("update users set divisionName=?, supervision=?, role=?, status=?, salary=? where userId=?");
            preparedStatement.setString(1, user.getDivisionName());
            preparedStatement.setString(2, user.getSupervision());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.setString(5, user.getSalary());
            preparedStatement.setString(6, user.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    		
	}

	@Override
	public void runPayroll(User user) {
		 try {
	            PreparedStatement preparedStatement = conn.prepareStatement("update users set salary=?, bonus=? where userId=?");
	            preparedStatement.setString(1, user.getSalary());
	            preparedStatement.setString(2, user.getBonus());

	            preparedStatement.setString(3, user.getUserId());
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public User getSupervisorNameById(String supervision) {
		@SuppressWarnings("rawtypes")
		User user = new User();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where userId='"+ supervision +"'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
