package com.service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.User;

public interface EmployeeService {

	void addLeaves(User user);

	User getLeavesInfo(String userId);

}
