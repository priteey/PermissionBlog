<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<jsp:include page="empMenu.jsp"/>
<style>

body{
   background-image: url("images/employee-screening.jpg");
   background-repeat: no-repeat;
   background-size: 100% 100%;
       background-attachment: fixed;
   
}
</style>
<body>
<%
if(session == null || session.getAttribute("userId") == null){
	request.setAttribute("err", "User Not Logged In");
	 RequestDispatcher reqdispatcher = request.getRequestDispatcher("index.jsp");
		reqdispatcher.forward(request, response);
} %>
<div style="color:blue; background-color:pink;">${err}</div>
<div class="w3-sidebar w3-bar-block w3-dark-grey w3-animate-left" style="display:block" id="mySidebar">
<a class="w3-bar-item w3-button w3"  href="EmployeeController?action=profile">View Profile</a>
 <a href="EmployeeController?action=leaves"  class="w3-bar-item w3-button">Request Leaves</a>
  <a href="DirectoryController?action=upload" class="w3-bar-item w3-button">Upload Files</a>
  <a href="DirectoryController?action=downloadFiles" style="background-color:#1ec7e0" class="w3-bar-item w3-button">Download Files</a>
  
 <%
 if(session.getAttribute("role").equals("Manager")){%>
  <a class="w3-bar-item w3-button w3"  href="ManagerController?action=manageLeaves">Manage Leaves</a>
  <a href="ManagerController?action=bonus" class="w3-bar-item w3-button">Assign Bonus</a>
  <a href="DirectoryController?action=directories" class="w3-bar-item w3-button">Create Directories</a>
        <a href="DirectoryController?action=manageDirectories"  class="w3-bar-item w3-button">Manage Directory Permission</a>
  
<%} %>
</div>
<div style="margin-left:13%; margin-top:10px">
 <div style="color:white; font-weight:bold;font-size:18px; margin-left:200px">${success}</div>
 <div style="color:red; font-weight:bold;font-size:18px; margin-left:200px">${error}</div>

<form action="DirectoryController" method="post" enctype="multipart/form-data">
<div class="w3-container">

<table  align="center" width=35% style="margin-left:100px" > 
<tr>
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Directories:</td><td><select name="directoryName" style="height:30px; font-weight:bold" >
                  <option>Select anyone from below!</option>
                <c:forEach var="d" items="${directories}">
                  <option value="${d.directoryId}">${d.directoryName}</option> </c:forEach>
                </select></div>
                </td>
               </tr>
  				
                </table>
                <input type="submit" style="height:40px; width:95px; border-radius:8px; font-weight:bold; font-size:15px; margin-left:350px;margin-top:30px" value="Select" name ="action"/>
</div>
</form>
<form>
 <div align="center">
  <table class="w3-table-all w3-card-4">
            <caption><h2 style="color:white; font-weight:bold;">Files Uploaded</h2></caption>
            <tr>
               <th>Directory Id</th>
                 <!-- <th>Directory Name</th> -->
                <th>File Type</th> 
                <th>File Name</th>
               <th>File</th>
                
                
            </tr>
             <c:forEach var="f" items="${accessedFiles}">
                <tr>
                   <td><c:out value="${f.directoryId}" /></td>
<%--                     <td><c:out value="${f.directoryName}"/></td>
 --%> 				      <td><c:out value="${f.fileType}"/></td> 
    				     <td><c:out value="${f.fileName}"/></td>
    				     
                    <td><a href="DirectoryController?action=download&fileName=${f.fileName}&fileId=${f.fileId}&fileType=${f.fileType}">${f.fileName}</a> </td>
                </tr>
       		</c:forEach>
        </table>
    </div>
</form></div>
</body>
</html> 
