package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.DBConn;

public class RegisterDaoImpl implements RegisterDao {
	private Connection conn;
	
	public RegisterDaoImpl(){
		conn= DBConn.getConnection();
	}

	@Override
	public boolean checkUserIdExist(String userId) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("select * from users where userId='"+userId+"'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				return true;
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	
	}

}
