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
td{
color:white;
font-size:18px

}
</style>
<body>
<%
System.out.println(session == null || session.getAttribute("userId") == null);
if(session == null || session.getAttribute("userId") == null){
	request.setAttribute("err", "User Not Logged In");
	 RequestDispatcher reqdispatcher = request.getRequestDispatcher("index.jsp");
		reqdispatcher.forward(request, response);
} 
else{%>
<div style="color:blue; background-color:pink;">${err}</div>
<div class="w3-sidebar w3-bar-block w3-dark-grey w3-animate-left" style="display:block" id="mySidebar">
  <a class="w3-bar-item w3-button w3" style="background-color:#1ec7e0" href="EmployeeController?action=profile">View Profile</a>
  <a href="EmployeeController?action=leaves" class="w3-bar-item w3-button">Request Leaves</a>
  <a href="DirectoryController?action=upload" class="w3-bar-item w3-button">Upload Files</a>
  <a href="DirectoryController?action=downloadFiles" class="w3-bar-item w3-button">Download Files</a>
   <%
 if(session.getAttribute("role").equals("Manager")){%>
  <a class="w3-bar-item w3-button w3"  href="ManagerController?action=manageLeaves">Manage Leaves</a>
  <a href="ManagerController?action=bonus" class="w3-bar-item w3-button">Assign Bonus</a>
  <a href="DirectoryController?action=directories" class="w3-bar-item w3-button">Create Directories</a>
        <a href="DirectoryController?action=manageDirectories" class="w3-bar-item w3-button">Manage Directory Permission</a>
<%} %>
</div>
<% } %>
<%-- <div style="color:blue; background-color:pink;">${err}</div>
 --%><div style="margin-left:13%; margin-top:10px">

<form action="EmployeeController" method="post">
<table  style="margin-left:100px;"> 
<tr>
			   <td height=60px>
              <div style="font-weight:bold"> First Name:</div></td><td>${userDetails.fname}</td>
                </tr>
                <tr>
                <td height=60px>
               <div style="font-weight:bold">Last Name:</div></td><td>${userDetails.lname}</td>
                </tr>
                 <tr>
               <tr>
                <td height=60px>
               <div style="font-weight:bold">Email Id:</div></td><td>${userDetails.emailId}</td>
                </tr>
                <tr>
                <td height=60px>
               <div style="font-weight:bold">Gender:</div></td><td>${userDetails.gender}</td>
                </tr>
                 <tr>
                <td height=60px>
                <div style="font-weight:bold">Designation:</div></td><td>${userDetails.role}</td></tr>
                <tr>
                <td height=60px>
                <div style="font-weight:bold">Address:</div></td><td>${userDetails.address}</td></tr>
                <tr>
                <td height=60px>
                 <div style="font-weight:bold">Phone No.:</div></td><td>${userDetails.phoneNo}</td></tr>
                  <tr>
                <td height=60px>
                 <div style="font-weight:bold">Bonus:</div></td><td>${userDetails.bonus}</td></tr>
                  <tr>
                <td height=60px>
                  <div style="font-weight:bold">Division Name:</div></td><td>${userDetails.divisionName}</td></tr>
                  <tr>
                <td height=60px>
                <div style="font-weight:bold">Salary:</div></td><td>${userDetails.salary}</td></tr>
                  <tr>
                <td height=60px>
                 <div style="font-weight:bold">Under the Supervision Of:</div></td><td>${userDetails.supervision}</td></tr>
                </table>
	</form>
</div>
</body>
</html> 
