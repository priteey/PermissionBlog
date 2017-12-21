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
  <a href="DirectoryController?action=directories" class="w3-bar-item w3-button">Create Directories</a>
    <a href="DirectoryController?action=manageDirectories" style="background-color:#1ec7e0" class="w3-bar-item w3-button">Manage Directory Permission</a>
  
<%} %>
</div>
<div style="margin-left:13%; margin-top:10px">
 <div style="color:white; font-weight:bold;font-size:18px; margin-left:200px">${success}</div>
 <div style="color:red; font-weight:bold;font-size:18px; margin-left:200px">${error}</div>
<%
if(request.getAttribute("access").equals("acessPermission")){%>
<form action="DirectoryController" method="post">
<div class="w3-container">

<table  align="center" width=25% style="margin-left:100px" > 
              <%--  <%
               if(request.getAttribute("changedAccessType").equals("Private")){%>
               <div style="color:white; font-weight:bold"><h1>Cannot change the access permission</h1></div>
               <%}
               else if(request.getAttribute("changedAccessType").equals("Default")){%>
               <td height=60px>
               <div style="color:white; font-weight:bold"><font color="red">*</font>Access Type:</div></td><td><select name="accessType" style="height:30px; font-weight:bold">
              <option disabled="${changedAccessType}" selected="${changedAccessType}" value="${changedAccessType}">${changedAccessType}</option>
                 <option value="Private">Private</option>
               </select></td>
              <tr> <td> <input type="submit" style="height:38px; width:145px; border-radius:8px; font-weight:bold; font-size:16px;margin-left:100px;  margin-top:30px"; value="Update Access" name ="action"/><td>
               </tr><%}
               else --%> <%if(request.getAttribute("accessType").equals("Protected")){%>
				 <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Access Type:</div></td><td><select name="accessType" style="height:30px; font-weight:bold">
                <option disabled="${accessType}" selected="${accessType}" value="${accessType}">${accessType}</option>
                  <option value="Private">Private</option>
                  <option value="Default">Default</option>
                </select></td>
                <tr><td><input type="submit" style="height:38px; width:145px; border-radius:8px; font-weight:bold; font-size:16px; margin-left:100px;margin-top:30px"; value="Update Access" name ="action"/>
                </td></tr>
                <%}
                else if(request.getAttribute("accessType").equals("Default")){%>
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Access Type:</div></td><td><select name="accessType" style="height:30px; font-weight:bold">
               <option disabled="${accessType}" selected="${accessType}" value="${accessType}">${accessType}</option>
                  <option value="Private">Private</option>
                </select></td>
                  <tr><td> <input type="submit" style="height:38px; width:145px; border-radius:8px; font-weight:bold; font-size:16px; margin-left:100px;margin-top:30px"; value="Update Access" name ="action"/>
                </td></tr>
                <%}%>
 					
                </table>
</div>
</form>
    <%}%>    
<form action="DirectoryController" method="post">

 <div align="center">
  <table class="w3-table-all w3-card-4">
            <caption><h2 style="color:white; font-weight:bold;">Directories</h2></caption>
            <tr>
               <th>Directory Id</th>
                <th>Directory Name</th>
                <th>Access Type</th>
                 <th>Changed Access Type</th>
                <th>Action</th>
            </tr>
             <c:forEach var="d" items="${directories}">
                <tr>
                   <td><c:out value="${d.directoryId}" /></td>
                    <td><c:out value="${d.directoryName}"/></td>
 				     <td><c:out value="${d.accessType}"/></td>
 				     <td><c:out value="${d.changedAccessType}"/></td>
 				      <td><a href="<%=request.getContextPath() %>/DirectoryController?action=updateAccess&directoryId=${d.directoryId}&currentAccessType=${d.accessType}&directoryName=${d.directoryName}&changedAccessType=${d.changedAccessType}">Change Access</a></td>
                </tr>
       		</c:forEach>
        </table>
    </div>
</form></div>
</body>
</html> 
