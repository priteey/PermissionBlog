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
 <a class="w3-bar-item w3-button w3"  href="ManagerController?action=profile">View Profile</a>
 <a href="ManagerController?action=leaves"  class="w3-bar-item w3-button">Request Leaves</a>
  <a href="DirectoryController?action=upload" class="w3-bar-item w3-button">Upload Files</a>
  <a href="DirectoryController?action=downloadFiles" class="w3-bar-item w3-button">Download Files</a>
  <a class="w3-bar-item w3-button w3" style="background-color:#1ec7e0" href="ManagerController?action=manageLeaves">Manage Leaves</a>
  <a href="ManagerController?action=bonus" class="w3-bar-item w3-button">Assign Bonus</a>
  <a href="DirectoryController?action=directories" class="w3-bar-item w3-button">Create Directories</a>
        <a href="DirectoryController?action=manageDirectories"  class="w3-bar-item w3-button">Manage Directory Permission</a>
  
</div>
<div style="margin-left:13%; margin-top:10px">
 <div style="color:white; font-weight:bold;font-size:18px; margin-left:200px">${success}</div>
 <div style="color:red; font-weight:bold;font-size:18px; margin-left:200px">${error}</div>

<form action="ManagerController" method="post">
<div class="w3-container">

<!--  <div align="center"> -->
  <table class="w3-table-all w3-card-4">
            <h2 style="color:white; font-weight:bold;">Leaves Requested</h2>
            <tr>
            <th>Employee Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>EmailId</th>
               <th>From Date</th>
                <th>To Date</th>
                <th>Number Of Leaves</th>
 					<th>Leave Type</th>
 					<th>Leave Status</th>
 					<th>Remaining Leaves</th>
 					 <th>Action</th>
 					
            </tr>
                        <c:forEach var="leaves" items="${leavesList}">
            
                <tr>
                   <td><c:out value="${leaves.userId}" /></td>
                   <td><c:out value="${leaves.fname}" /></td>
                   <td><c:out value="${leaves.lname}" /></td>
                   <td><c:out value="${leaves.emailId}" /></td>
                   <td><c:out value="${leaves.fromDate}" /></td>
                    <td><c:out value="${leaves.toDate}"/></td>
 				     <td><c:out value="${leaves.leaves}"/></td>
 				     <td><c:out value="${leaves.leaveType}"/></td>
 					 <td><c:out value="${leaves.leaveStatus}" /></td>
 					   <td><c:out value="${leaves.remainingLeaves}"/></td>
 					<td><a href="<%=request.getContextPath() %>/ManagerController?action=acceptLeaves&userId=${leaves.userId}">Accept</a><a href="<%=request.getContextPath() %>/ManagerController?action=declineLeaves&userId=${leaves.userId}">Decline</a></td>
 					   
                </tr>
                </c:forEach>
        </table>
    </div>
</form></div>
</body>
</html> 
