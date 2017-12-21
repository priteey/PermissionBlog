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
  <a href="DirectoryController?action=upload"  class="w3-bar-item w3-button">Upload Files</a>
  <a href="DirectoryController?action=downloadFiles" class="w3-bar-item w3-button">Download Files</a>
 <%
 if(session.getAttribute("role").equals("Manager")){%>
  <a class="w3-bar-item w3-button w3"  href="ManagerController?action=manageLeaves">Manage Leaves</a>
  <a href="ManagerController?action=bonus" class="w3-bar-item w3-button">Assign Bonus</a>
  <a href="DirectoryController?action=directories" style="background-color:#1ec7e0" class="w3-bar-item w3-button">Create Directories</a>
<%} %>
</div>
<div style="margin-left:13%; margin-top:10px">
 <div style="color:white; font-weight:bold;font-size:18px; margin-left:200px">${success}</div>
 <div style="color:red; font-weight:bold;font-size:18px; margin-left:200px">${error}</div>

<form action="DirectoryController" method="post">
<div class="w3-container">

<table  align="center" width=35% style="margin-left:100px" > 

                <tr>
                <td height=60px>
               	<div style="color:white; font-weight:bold"><font color="red">*</font>Directory Name :</td><td><input type="text" readonly="readonly" style="height:30px; font-weight:bold" name="directoryName" placeholder="directory name" value="${directories.directoryName}"/></div>
                </td></tr> 
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Access Type:</td><td><select name="accessType" style="height:30px; font-weight:bold">
               <option disabled="${directories.accessType}" selected="${directories.accessType}" value="${directories.accessType}">${directories.accessType}</option>
                  <option value="Public">Public</option>
                  <option value="Private">Private</option>
                  <option value="Protected">Protected</option>
                  <option value="Default">Default</option>
                </select></div>
                </td></tr>
 					
                </table>
                <input type="submit" style="height:40px; width:95px; border-radius:8px; font-weight:bold; font-size:15px; margin-left:350px;margin-top:30px" value=Submit name ="action"/>
</div>

</div>
</body>
</html> 
