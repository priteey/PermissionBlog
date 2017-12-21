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
import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.UserDTO;

public class DirectoryDaoImpl implements DirectoryDao {
private Connection conn;
	
	public DirectoryDaoImpl(){
		conn= DBConn.getConnection();
		System.out.print("connection created");
	}
	@Override
	public List<String> getManagerList(String userId) {
		
		//SELECT d.userId, d.directoryId FROM directories d INNER JOIN users u ON d.userId=u.userId WHERE u.`supervision`='AT' ;
//		if()
		int i=0;
		List<String> user= new ArrayList<>();
			String  u = null;
			ResultSet rs;
	        try {
	        	do{
//				PreparedStatement preparedStatement = conn.prepareStatement("SELECT d.userId, d.directoryId, d.directoryName from directories d INNER JOIN users u ON "
//						+ "d.userId=u.userId where u.supervision='" + userId + "'");
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT userId from users u where role='Manager' and u.supervision='" + userId + "'");
	
				 rs= preparedStatement.executeQuery();
				 u =userId;
				while(rs.next()){
//					Directories d = new Directories();
//					d.setDirectoryId(rs.getInt("directoryId"));
//					d.setDirectoryName(rs.getString("directoryName"));
//
//					d.setUserId(rs.getString("userId"));
//					directories.add(d);
					String userID=rs.getString("userId");
					user.add(userID);
					
				 }
				if(user.size()>0){
				userId=user.get(i) ;
				i++;}
	        	}
	        	 while(i<user.size()&&userId!=null && !(u.equals(userId)));
	        }
			 catch (SQLException e) {
				e.printStackTrace();
			}		
			return user;
	}
	@Override
	public List<Directories> getDirectories(List<String> managerList) {
		List<Directories> directories= new ArrayList<Directories>();
		for(int i=0; i<managerList.size();i++){
			try{
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from directories where userId='" + managerList.get(i) + "'");
				ResultSet rs= preparedStatement.executeQuery();
				 while(rs.next()){
						Directories d = new Directories();
						d.setDirectoryId(rs.getInt("directoryId"));
						d.setDirectoryName(rs.getString("directoryName"));
						d.setUserId(rs.getString("userId"));
						directories.add(d);
					 }
		        	}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return directories;
	} 
	@Override
	public List<Directories> getDirectoriesAdded(String userId) {
		List<Directories> directories = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from directories where userId ='" + userId + "'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				Directories d=new Directories();
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setDirectoryName(rs.getString("directoryName"));
				d.setAccessType(rs.getString("accessType"));
				directories.add(d);
			}
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return directories;
	}
	@Override
	public void addDirectory(Directories directories) {
		try{
			PreparedStatement preparedStmt= conn.prepareStatement("insert into directories( directoryName, accessType, userId) values (?,?,?)");
			preparedStmt.setString(1, directories.getDirectoryName());
			preparedStmt.setString(2, directories.getAccessType()); 
			preparedStmt.setString(3, directories.getUserId()); 
			preparedStmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public List<DirectoryFiles> getFiles(String userId) {
		List<DirectoryFiles> files= new ArrayList<DirectoryFiles>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from directoryFiles where  userId='"+userId+"'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				DirectoryFiles d = new DirectoryFiles();
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setFileName(rs.getString("fileName"));
				d.setFileType(rs.getString("fileType"));
				d.setUserId(rs.getString("userId"));
				d.setFileId(rs.getInt("fileId"));
				d.setFile(rs.getBlob("file"));
				d.setExtension(rs.getString("extension"));
				files.add(d);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return files;
}
	
	public Blob getFile(String fileName, int id){
		Blob blob=null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("select * from directoryFiles where fileId= "+id+" and fileName='"+fileName+"'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				blob=rs.getBlob("file");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;	
	}
	@Override
	public void uploadFile(DirectoryFiles files, InputStream ipStream) {
	try{
		PreparedStatement preparedStmt= conn.prepareStatement("insert into directoryFiles(userId, fileName, directoryId, file, fileType, extension) values (?,?,?,?,?,?)");
		preparedStmt.setString(1, files.getUserId());
		preparedStmt.setString(2, files.getFileName());
		preparedStmt.setInt(3, files.getDirectoryId());
		if (ipStream!=null){
		preparedStmt.setBinaryStream(4, ipStream, 900000000);
		}
		preparedStmt.setString(5, files.getFileType());
		preparedStmt.setString(6, files.getExtension());
		preparedStmt.executeUpdate();
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
	@Override
	public List<DirectoryFiles> getAccessedFiles(int directoryId) {
		List<DirectoryFiles> files= new ArrayList<DirectoryFiles>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from directoryFiles where  directoryId="+directoryId);
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				DirectoryFiles d = new DirectoryFiles();
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setFileName(rs.getString("fileName"));
				d.setFileType(rs.getString("fileType"));
				d.setUserId(rs.getString("userId"));
				d.setFileId(rs.getInt("fileId"));
				d.setFile(rs.getBlob("file"));
				files.add(d);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return files;
}
	@Override
	public Directories getDirectoriesById(String directoryId) {
		Directories d = new Directories();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from directories where directoryId=' "+directoryId+"'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setUserId(rs.getString("userId"));
				d.setAccessType(rs.getString("accessType"));
				d.setDirectoryName(rs.getString("directoryName"));
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return d;
}
	@Override
	public void updateDirectory(Directories directory) {

		try{
			PreparedStatement preparedStmt= conn.prepareStatement("update directories set directoryName=?, accessType=?,changedAccessType=?,changedBy=?  where directoryId=?");
			preparedStmt.setString(1, directory.getDirectoryName());
			preparedStmt.setString(2, directory.getAccessType()); 
			preparedStmt.setString(3, directory.getChangedAccessType()); 
			preparedStmt.setString(4, directory.getChangedBy()); 
			preparedStmt.setInt(5, directory.getDirectoryId());
			preparedStmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
	

	@Override
	public List<UserDTO> getAccessedEmployees(String directoryId) {	
		List<UserDTO> users= new ArrayList<>();
	try {
		PreparedStatement preparedStatement = conn.prepareStatement("select * from users u INNER JOIN directorypermission d ON u.userId=d.empId where d.directoryId ='"+directoryId+"'");
		ResultSet rs= preparedStatement.executeQuery();
		while(rs.next()){
			UserDTO u = new UserDTO();
			u.setUserId(rs.getString("userId"));
			u.setFname(rs.getString("fname"));
			u.setLname(rs.getString("lname"));
			u.setDivisionName(rs.getString("divisionName"));
			u.setAccessStatus(rs.getString("accessStatus"));
			users.add(u);
		}
	}
	 catch (SQLException e) {
		e.printStackTrace();
	}		
	return users;
	}
	@Override
	public String getDivisionName(String userId) {	
		String divisionName=null;
	try {
		PreparedStatement preparedStatement = conn.prepareStatement("select divisionName from users where userId ='"+userId+"'");
		ResultSet rs= preparedStatement.executeQuery();
		while(rs.next()){
			 divisionName=rs.getString("divisionName");
		}
	}
	 catch (SQLException e) {
		e.printStackTrace();
	}		
	return divisionName;
	}
	@Override
	public void updateDirectory(List userIds, String directoryId) {
		Directories directory= new Directories();
		for(int i=0; i<userIds.size();i++){
		try{
			PreparedStatement preparedStmt= conn.prepareStatement("insert into directorypermission( empId, directoryId,accessStatus) values(?,?,?)");
			preparedStmt.setString(1, (String) userIds.get(i));
			preparedStmt.setString(2, directoryId);
			preparedStmt.setString(3, "Yes");

			preparedStmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	}
	@Override
	public List<Directories> getAccessedDirectories(String userId) {/*
		List<Directories> directories = new ArrayList<>();
		try {
//			 SELECT DISTINCT d.directoryId FROM directories d INNER JOIN users u INNER JOIN directorypermission p ON u.supervision = d.userId WHERE d.accessType IN ('Protected', 'Default')
//			  AND u.`userId`='PD'
			PreparedStatement preparedStatement = conn.prepareStatement("select d.directoryId, d.directoryName, d.accessType from directories d INNER JOIN users u ON"
					+ " u.supervision = d.userId where d.accessType IN ('Protected', 'Default') and u.userId='" + userId + "'");
		
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				Directories d=new Directories();
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setDirectoryName(rs.getString("directoryName"));
				d.setAccessType(rs.getString("accessType"));
				directories.add(d);
			}
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return directories;
	*/ 

		
		//SELECT d.userId, d.directoryId FROM directories d INNER JOIN users u ON d.userId=u.userId WHERE u.`supervision`='AT' ;
		String currentUserId= userId;
		boolean isPresent=false;
			List<Directories> directories= new ArrayList<Directories>();
			String  u = null;
			ResultSet rs;
	        try {
	        	do{
	        		PreparedStatement preparedStatement = conn.prepareStatement("select * from directories d INNER JOIN users u ON"
	    					+ " u.supervision = d.userId where d.accessType IN ('Protected', 'Default') and u.userId='" + userId + "'");
	    		
				 rs= preparedStatement.executeQuery();
				 u =userId;
				while(rs.next()){
					Directories d = new Directories();
					d.setDirectoryId(rs.getInt("directoryId"));
					d.setDirectoryName(rs.getString("directoryName"));
					d.setChangedAccessType(rs.getString("changedAccessType"));
					d.setChangedBy(rs.getString("changedBy"));
					d.setUserId(rs.getString("userId"));
					if(d.getChangedAccessType()!=null){
						if(currentUserId.equals(d.getChangedBy())){
							d.setAccessType(rs.getString("accessType"));
							d.setChangedAccessType(rs.getString("changedAccessType"));
						}
						else{
							isPresent = isUserPresent(d.getChangedBy(),currentUserId);
							if(isPresent){
								d.setAccessType(rs.getString("accessType"));
							}
							else{
								d.setAccessType(rs.getString("changedAccessType"));
							}
						}
						
					}
					else{
						d.setAccessType(rs.getString("accessType"));
					}
					if(!(d.getAccessType().equals("Private"))){
					directories.add(d);
					}
					userId= d.getUserId();
				 }
	        	}
	        	 while(userId!=null && !(u.equals(userId)));
	        }
			 catch (SQLException e) {
				e.printStackTrace();
			}		
			return directories;
		
	}
	private boolean isUserPresent(String userId,String currentUserId) {
		String  u= null;
		ResultSet rs;
    try {
    	do{
    		PreparedStatement preparedStatement = conn.prepareStatement("select * from directories d INNER JOIN users u ON"
					+ " u.supervision = d.userId where u.userId='" + userId + "'");
		
		 rs= preparedStatement.executeQuery();
		 u =userId;
		while(rs.next()){
			Directories d = new Directories();
			d.setUserId(rs.getString("userId"));
			userId= d.getUserId();
			if(userId.equals(currentUserId)){
				return true;
			}
		}
    	}
    	 while(userId!=null && !(u.equals(userId)));
    }
	 catch (SQLException e) {
		e.printStackTrace();
	}		
	return false;
	}
	@Override
	public String getFileExtension(int id) {	
		String extension=null;
	try {
		PreparedStatement preparedStatement = conn.prepareStatement("select extension from directoryFiles where fileId ="+id);
		ResultSet rs= preparedStatement.executeQuery();
		while(rs.next()){
			extension=rs.getString("extension");
		}
	}
	 catch (SQLException e) {
		e.printStackTrace();
	}		
	return extension;
	}
	@Override
	public List<String> getUserList(String userId) {
		int i=0;
		List<String> user= new ArrayList<>();
			String  u = null;
			ResultSet rs;
	        try {
	        	do{
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT userId from users u where u.supervision='" + userId + "'");
	
				 rs= preparedStatement.executeQuery();
				 u =userId;
				while(rs.next()){
					String userID=rs.getString("userId");
					user.add(userID);
				 }
				userId=user.get(i) ;
				i++;
	        	}
	        	 while(i<user.size()&&userId!=null && !(u.equals(userId)));
	        }
			 catch (SQLException e) {
				e.printStackTrace();
			}		
			return user;
	}
	@Override
	public ArrayList<UserDTO> getEmployees(List<String> managerList, String directoryId) {
//		SELECT * FROM users WHERE userId NOT IN (SELECT empId FROM directorypermission WHERE directoryId='5')
//		AND role='Employee' AND userId NOT IN(SELECT userId FROM users WHERE role='Employee' AND userId IN('ND','NJ','PD'))
		ArrayList<UserDTO> allUsers= new ArrayList<UserDTO>();
		StringBuilder str = new StringBuilder();
		for(int i=0; i<managerList.size();i++){
			str.append("'");
			str.append(managerList.get(i));
			str.append("'");

		      if ( i != managerList.size()-1){
		    	  str.append(", ");
		      }
		    }
		    System.out.println(str.toString());
		 
	try {
		PreparedStatement preparedStatement = conn.prepareStatement("select distinct * from users where userId NOT IN (Select empId from directorypermission where directoryId='"+directoryId+"') "
				+ "and role='Employee' and userId NOT IN (select userId from users where role='Employee' and userId IN(" + str.toString()+"))" );
		ResultSet rs= preparedStatement.executeQuery();
		while(rs.next()){
			UserDTO u = new UserDTO();
			u.setUserId(rs.getString("userId"));
			u.setFname(rs.getString("fname"));
			u.setLname(rs.getString("lname"));
			u.setDivisionName(rs.getString("divisionName"));
			u.setAccessStatus("No");
			allUsers.add(u);
		}
	}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
//	}
	return allUsers;
	}
	@Override
	public ArrayList<UserDTO> getATE(ArrayList<UserDTO> remainingUsers, String directoryId) {
		ArrayList<UserDTO> allUsers= new ArrayList<UserDTO>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where userId NOT IN (Select empId from directorypermission where directoryId='"+directoryId+"') "
					+ "and role='Employee'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				UserDTO u = new UserDTO();
				u.setUserId(rs.getString("userId"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setDivisionName(rs.getString("divisionName"));
				u.setAccessStatus("No");
				allUsers.add(u);
			}
			allUsers.removeAll(remainingUsers);
		}
			 catch (SQLException e) {
				e.printStackTrace();
			}		
		return allUsers;
	}
		@Override
		public List<Directories> getAccessedEmployeeDirectories(String userId) {
			List<Directories> dir= new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from directories dir INNER JOIN directorypermission d ON dir.directoryId=d.directoryId where d.empId ='"+userId+"'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				Directories d = new Directories();
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setDirectoryName(rs.getString("directoryName"));
				d.setAccessType(rs.getString("accessType"));
				d.setChangedAccessType(rs.getString("changedAccessType"));
				d.setChangedBy(rs.getString("changedBy"));
				d.setUserId(rs.getString("userId"));
				dir.add(d);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return dir;
		}
		@Override
		public List<Directories> getPublicDirectories() {	
			List<Directories> dir= new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select * from directories where accessType='Public'");
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				Directories d = new Directories();
				d.setDirectoryId(rs.getInt("directoryId"));
				d.setDirectoryName(rs.getString("directoryName"));
				d.setAccessType(rs.getString("accessType"));
				d.setChangedAccessType(rs.getString("changedAccessType"));
				d.setChangedBy(rs.getString("changedBy"));
				d.setUserId(rs.getString("userId"));
				dir.add(d);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}		
		return dir;}
}
