package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.User;
import com.model.UserDTO;
import com.service.DirectoryService;
import com.service.DirectoryServiceImpl;

/**
 * Servlet implementation class DirectoryController
 */
@WebServlet("/DirectoryController")
@MultipartConfig
public class DirectoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DirectoryService directoryService= new DirectoryServiceImpl();
     	private static final int BUFFER_SIZE = 4096; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String button= request.getParameter("action");
		 if(button.equals("upload")){
			 List<Directories> allDirectories = new ArrayList<>();
				List<String> managerList= directoryService.getManagerList(userId);
				List<Directories> directories= directoryService.getDirectories(managerList);
				allDirectories.addAll(directories);
				directories= directoryService.getAccessedDirectories(userId);
				allDirectories.addAll(directories);
			 directories= directoryService.getDirectoriesAdded(userId);
			 allDirectories.addAll(directories);
				directories= directoryService.getAccessedEmployeeDirectories(userId);
				allDirectories.addAll(directories);
				directories= directoryService.getPublicDirectories();
				allDirectories.addAll(directories);
			request.setAttribute("directoriesList", allDirectories);
			List<DirectoryFiles> allFiles= directoryService.getFiles(userId);
			 request.setAttribute("files", allFiles);
			 RequestDispatcher req = request.getRequestDispatcher("uploadFiles.jsp");
	            req.forward(request, response);
		}
		
		else if(button.equals("download")){	
			String fileName= request.getParameter("fileName");
			int id= Integer.parseInt(request.getParameter("fileId"));
			String fileType = request.getParameter("fileType");
			System.out.print("fileType"+fileType);
			try{
				 if(fileName!=null){
					 	Blob file=directoryService.getFile(fileName,id);
					 	String extension=directoryService.getFileExtension(id);

		                InputStream inputStream = file.getBinaryStream();
		                int fileLength = inputStream.available();

		                response.setContentLength(fileLength);
		           
				        String headerKey = "Content-Disposition";
				        String headerValue = String.format("attachment; filename=\"%s\"",fileName+"."+extension);
				        response.setHeader(headerKey, headerValue);
				        OutputStream opStream = response.getOutputStream();
			             
			            byte[] buffer = new byte[BUFFER_SIZE];
			            int bytesRead = -1;
			             
				            while ((bytesRead = inputStream.read(buffer)) != -1) {
				                opStream.write(buffer, 0, bytesRead);
				            }
				             
				            inputStream.close();
				            opStream.close();  

				 } else{
					 response.getWriter().print("File not found for the id: " + userId);  
			 		}
			}catch (SQLException e) {
	            e.printStackTrace();
	            response.getWriter().print("SQL Error: " + e.getMessage());
	        }catch (IOException e) {
	            e.printStackTrace();
	            response.getWriter().print("IO Error: " + e.getMessage());
	        } 
		}
		else if(button.equals("directories")){
			List<Directories> directories= directoryService.getDirectoriesAdded(userId);
			request.setAttribute("directories", directories);
			 RequestDispatcher req = request.getRequestDispatcher("createDirectories.jsp");
	            req.forward(request, response);
		}        
		else if(button.equals("downloadFiles")){
			List<Directories> allDirectories = new ArrayList<>();
			List<String> managerList= directoryService.getManagerList(userId);
			List<Directories> directories= directoryService.getDirectories(managerList);
			allDirectories.addAll(directories);
			 directories= directoryService.getDirectoriesAdded(userId);
			 allDirectories.addAll(directories);
			directories= directoryService.getAccessedDirectories(userId);
			allDirectories.addAll(directories);
			directories= directoryService.getAccessedEmployeeDirectories(userId);
			allDirectories.addAll(directories);
			directories= directoryService.getPublicDirectories();
			allDirectories.addAll(directories);
			
			request.setAttribute("directories", allDirectories);
			 RequestDispatcher req = request.getRequestDispatcher("downloadFiles.jsp");
	            req.forward(request, response);
		}
		else if(button.equals("updateDirectory")){
			String divisionName=directoryService.getDivisionName(userId);
			String directoryId= request.getParameter("directoryId");
			String accessType=request.getParameter("accessType");
			Directories directories= directoryService.getDirectoriesById(directoryId);
			request.setAttribute("directories", directories);
			System.out.println(directories.getAccessType());
			request.setAttribute("accessType", directories.getAccessType());
			session.setAttribute("access", accessType);
			session.setAttribute("directoryId", directoryId);
			List<UserDTO> allusers= new ArrayList<>();
			List<UserDTO> users= directoryService.getAccessedEmployees(directoryId);
			allusers.addAll(users);
			List<String> userList= directoryService.getUserList(userId);
			ArrayList<UserDTO> remainingUsers= directoryService.getEmployees(userList,directoryId);
//			List<UserDTO> ate= directoryService.getATE(remainingUsers,directoryId);

			allusers.addAll(remainingUsers);
			session.setAttribute("employees", allusers);

			 RequestDispatcher req = request.getRequestDispatcher("updateDirectory.jsp");
	            req.forward(request, response);
		}
		else if(button.equals("manageDirectories")){
			List<Directories> directories= directoryService.getAccessedDirectories(userId);
			session.setAttribute("directories", directories);
        	request.setAttribute("access", "acessNoPermission");
			 RequestDispatcher req = request.getRequestDispatcher("manageDirectories.jsp");
	            req.forward(request, response);
		}
		else if(button.equals("updateAccess")){
			Directories directory = new Directories();
            String directoryName =  request.getParameter("directoryName");
            String directoryId =  request.getParameter("directoryId");
            String changedAccessType =  request.getParameter("changedAccessType");
            String currentAccessType =  request.getParameter("currentAccessType");
            int directoryID= Integer.parseInt(directoryId);
            directory.setDirectoryName(directoryName);
            directory.setDirectoryId(directoryID);
            session.setAttribute("directoryId", directoryId);
        	request.setAttribute("access", "acessPermission");
			request.setAttribute("accessType", currentAccessType);
			session.setAttribute("previousAccessType", currentAccessType);
			request.setAttribute("changedAccessType", changedAccessType);
			session.setAttribute("directoryName", directoryName);
			 RequestDispatcher req = request.getRequestDispatcher("manageDirectories.jsp");
	            req.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String button= request.getParameter("action");
		 if(button.equals("Upload")){
			int directoryId =Integer.parseInt(request.getParameter("directoryName"));
			String fileName =request.getParameter("fileName");
			Part filePart = request.getPart("file");
			String content=filePart.getHeader("content-disposition");
			String fullFileName = content.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1");
			System.out.print(fullFileName);
			String extension="";
			int i = fullFileName.lastIndexOf('.');
			if (i >= 0) {
			     extension = fullFileName.substring(i+1);
			}
			String fileType =filePart.getContentType();
			DirectoryFiles files= new DirectoryFiles();
			files.setDirectoryId(directoryId);
			files.setFileName(fileName);
			files.setUserId(userId);
			files.setFileType(fileType);
			files.setExtension(extension);
			InputStream ipStream= null;
			if(filePart!=null){
				ipStream = filePart.getInputStream();
			}
			if(files.getFileName().isEmpty()||directoryId==0||ipStream==null){
				
				if(files.getFileName().isEmpty()){

					request.setAttribute("fileNameErr", "Enter fileName");
				}
				if(directoryId==0){

					request.setAttribute("fileDescErr", "Select Directory Name");
				}
				if(ipStream==null){
		
					request.setAttribute("fileErr", "No file added");
				}
				RequestDispatcher reqdispatcher = request.getRequestDispatcher("uploadFiles.jsp");
				reqdispatcher.forward(request, response);
			}
		 
			else{
				directoryService.uploadFile(files, ipStream);
				request.setAttribute("fileMsg", "File added successfully");
					List<DirectoryFiles> allFiles= directoryService.getFiles(userId);
					 request.setAttribute("files", allFiles);
					 List<Directories> allDirectories = new ArrayList<>();
						List<String> managerList= directoryService.getManagerList(userId);
						List<Directories> directories= directoryService.getDirectories(managerList);

						allDirectories.addAll(directories);
						directories= directoryService.getAccessedDirectories(userId);
						allDirectories.addAll(directories);
					 directories= directoryService.getDirectoriesAdded(userId);
					 allDirectories.addAll(directories);
						directories= directoryService.getAccessedEmployeeDirectories(userId);
						allDirectories.addAll(directories);
						directories= directoryService.getPublicDirectories();
						allDirectories.addAll(directories);
					request.setAttribute("directoriesList", allDirectories);
					 RequestDispatcher reqdispatcher = request.getRequestDispatcher("uploadFiles.jsp");
					 reqdispatcher.forward(request, response); 
			}
		 }
			else if(button.equals("Create")){
	            String directoryName =  request.getParameter("directoryName");
	            String accessType = request.getParameter("accessType");
	            Directories directory = new Directories();
	            directory.setAccessType(accessType);
	            directory.setDirectoryName(directoryName);
	            directory.setUserId(userId);
            	directoryService.addDirectory(directory);
            	List<Directories> directories= directoryService.getDirectoriesAdded(userId);
    			request.setAttribute("directories", directories);
    			 RequestDispatcher req = request.getRequestDispatcher("createDirectories.jsp");
    	            req.forward(request, response);
			}
			else if(button.equals("Select")){
				int directoryId =Integer.parseInt(request.getParameter("directoryName"));
	            String accessType = request.getParameter("accessType");
	            List<DirectoryFiles> allAccessedFiles= directoryService.getAccessedFiles(directoryId);
				 request.setAttribute("accessedFiles", allAccessedFiles);
				 List<Directories> allDirectories = new ArrayList<>();
				 List<String> managerList= directoryService.getManagerList(userId);
				 List<Directories> directories= directoryService.getDirectories(managerList);
				 allDirectories.addAll(directories);
				 directories= directoryService.getAccessedDirectories(userId);
				 allDirectories.addAll(directories);
				 directories= directoryService.getDirectoriesAdded(userId);
				 allDirectories.addAll(directories);
					directories= directoryService.getAccessedEmployeeDirectories(userId);
					allDirectories.addAll(directories);
					directories= directoryService.getPublicDirectories();
					allDirectories.addAll(directories);
				request.setAttribute("directories", allDirectories);
				 RequestDispatcher reqdispatcher = request.getRequestDispatcher("downloadFiles.jsp");
				 reqdispatcher.forward(request, response); 
			}
			else if(button.equals("Submit")){
				Directories directory = new Directories();
	            String directoryName =  request.getParameter("directoryName");
	            String directoryId = (String) session.getAttribute("directoryId");
	            int directoryID= Integer.parseInt(directoryId);
	            String accessType = request.getParameter("accessType");
	            if(accessType==null){
	            	 accessType= (String) session.getAttribute("access");
	            	directory.setAccessType(accessType);
	            }
	            else{
	            	
	            	directory.setAccessType(accessType);
	            }
	            directory.setUserId(userId);
	            directory.setDirectoryName(directoryName);
	            directory.setDirectoryId(directoryID);
            	directoryService.updateDirectory(directory);
            	List<Directories> directories= directoryService.getDirectoriesAdded(userId);
    			request.setAttribute("directories", directories);
    			 RequestDispatcher req = request.getRequestDispatcher("createDirectories.jsp");
    	            req.forward(request, response);
			}
			else if(button.equals("Give Access")){
				String directoryId=(String) session.getAttribute("directoryId");
				List<User> u = (List<User>) session.getAttribute("employees");
				List userIds=new ArrayList<>();
				String[] check =  request.getParameterValues("emp");
				for(int i=0; i<check.length; i++){
					String user=check[i];
					userIds.add(user);
				}
				directoryService.updateDirectory(userIds,directoryId);

				String divisionName=directoryService.getDivisionName(userId);
				String accessType=request.getParameter("accessType");
				Directories directories= directoryService.getDirectoriesById(directoryId);
				request.setAttribute("directories", directories);
				System.out.println(directories.getAccessType());
				request.setAttribute("accessType", directories.getAccessType());
				session.setAttribute("access", accessType);
				session.setAttribute("directoryId", directoryId);
				List<UserDTO> allusers= new ArrayList<>();
				List<UserDTO> users= directoryService.getAccessedEmployees(directoryId);
				allusers.addAll(users);
				
				List<String> userList= directoryService.getUserList(userId);
				ArrayList<UserDTO> remainingUsers= directoryService.getEmployees(userList,directoryId);
//				List<UserDTO> ate= directoryService.getATE(remainingUsers,directoryId);
				allusers.addAll(remainingUsers);
				session.setAttribute("employees", allusers);

				 RequestDispatcher req = request.getRequestDispatcher("updateDirectory.jsp");
		            req.forward(request, response);
			}
			else if(button.equals("Update Access")){
				Directories directory = new Directories();
		            String directoryId=(String) session.getAttribute("directoryId");
		            directory.setDirectoryId(Integer.parseInt(directoryId));
		            String directoryName=(String) session.getAttribute("directoryName");
		            String previousAccessType=(String) session.getAttribute("previousAccessType");
				String accessType = request.getParameter("accessType");
				directory.setChangedAccessType(accessType);
 				directory.setChangedBy(userId);
 				directory.setDirectoryName(directoryName);
 				directory.setAccessType(previousAccessType);
				directoryService.updateDirectory(directory);
				request.setAttribute("success", "Access changed");
				List<Directories> directories= directoryService.getAccessedDirectories(userId);
				session.setAttribute("directories", directories);
	        	request.setAttribute("access", "acessNoPermission");
			      RequestDispatcher req = request.getRequestDispatcher("manageDirectories.jsp");
		            req.forward(request, response);
			}
		
		 }
}
