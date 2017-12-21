package com.service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.UserDTO;

public interface DirectoryService {

	List<String> getManagerList(String userId);
	
	void addDirectory(Directories directories);

	List<Directories> getDirectoriesAdded(String userId);

	List<DirectoryFiles> getFiles(String userId);

	Blob getFile(String fileName, int id);

	void uploadFile(DirectoryFiles files, InputStream ipStream);

	List<DirectoryFiles> getAccessedFiles(int directoryId);

	Directories getDirectoriesById(String directoryId);

	void updateDirectory(Directories directory);

	String getDivisionName(String userId);

	void updateDirectory(List userIds, String directoryId);

	List<Directories> getAccessedDirectories(String userId);

	String getFileExtension(int id);

	List<UserDTO> getAccessedEmployees(String directoryId);

	ArrayList<UserDTO> getEmployees(List<String> managerList, String directoryId);

	List<Directories> getDirectories(List<String> managerList);

	List<String> getUserList(String userId);

	ArrayList<UserDTO> getATE(ArrayList<UserDTO> remainingUsers,
			String directoryId);

	List<Directories> getAccessedEmployeeDirectories(String userId);

	List<Directories> getPublicDirectories();


}
