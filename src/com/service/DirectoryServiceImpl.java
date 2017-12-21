package com.service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.dao.DirectoryDao;
import com.dao.DirectoryDaoImpl;
import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.UserDTO;

public class DirectoryServiceImpl implements DirectoryService{
	DirectoryDao directoryDao= new DirectoryDaoImpl();
	@Override
	public List<String> getManagerList(String userId) {
		List<String> d= directoryDao.getManagerList(userId);
		return d;
	}
	@Override
	public void addDirectory(Directories directories) {
		directoryDao.addDirectory(directories);		
	}
	@Override
	public List<Directories> getDirectoriesAdded(String userId) {
		List<Directories> d= directoryDao.getDirectoriesAdded(userId);
		return d;
	}
	@Override
	public List<DirectoryFiles> getFiles(String userId) {
		List<DirectoryFiles> files= directoryDao.getFiles(userId);
		return files;
	}
	
	@Override
	public Blob getFile(String fileName, int id) {
		Blob files=directoryDao.getFile(fileName,id);
		return files;
	}
	@Override
	public void uploadFile(DirectoryFiles files, InputStream ipStream) {
		directoryDao.uploadFile(files, ipStream);
	}
	@Override
	public List<DirectoryFiles> getAccessedFiles(int directoryId) {
		List<DirectoryFiles> files= directoryDao.getAccessedFiles(directoryId);
		return files;
	}
	@Override
	public Directories getDirectoriesById(String directoryId) {
		Directories d= directoryDao.getDirectoriesById(directoryId);
		return d;
	}
	@Override
	public void updateDirectory(Directories directory) {
	  directoryDao.updateDirectory(directory);
		
	}
	@Override
	public List<UserDTO> getAccessedEmployees(String directoryId) {
		List<UserDTO> user= directoryDao.getAccessedEmployees(directoryId);
		return user;
	}
	@Override
	public String getDivisionName(String userId) {
		String divisionName= directoryDao.getDivisionName(userId);
		return divisionName;
	}
	@Override
	public void updateDirectory(List userIds, String directoryId) {
		  directoryDao.updateDirectory(userIds, directoryId);
		
	}
	@Override
	public List<Directories> getAccessedDirectories(String userId) {
		List<Directories> d= directoryDao.getAccessedDirectories(userId);
		return d;
	}
	@Override
	public String getFileExtension(int id) {
		String extension= directoryDao.getFileExtension(id);
		return extension;
	}
	
	@Override
	public ArrayList<UserDTO> getEmployees(List<String> managerList, String directoryId) {
		ArrayList<UserDTO> user= directoryDao.getEmployees(managerList, directoryId);
		return user;
	}
	
	@Override
	public List<Directories> getDirectories(List<String> managerList) {
		List<Directories> d= directoryDao.getDirectories(managerList);
		return d;
	}
	@Override
	public ArrayList<UserDTO> getATE(ArrayList<UserDTO> remainingUsers, String directoryId) {
		ArrayList<UserDTO> d= directoryDao.getATE(remainingUsers, directoryId);
		return d;
	}
	@Override
	public List<String> getUserList(String userId) {
		List<String> d= directoryDao.getUserList(userId);
		return d;
	}
	@Override
	public List<Directories> getAccessedEmployeeDirectories(String userId) {
		List<Directories> d= directoryDao.getAccessedEmployeeDirectories(userId);
		return d;	}
	@Override
	public List<Directories> getPublicDirectories() {
		List<Directories> d= directoryDao.getPublicDirectories();
		return d;
	}
}
