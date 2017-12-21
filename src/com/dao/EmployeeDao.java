package com.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import com.model.DirectoryFiles;
import com.model.User;

public interface EmployeeDao {

	void addLeaves(User user);

	User getLeavesInfo(String userId);



}
