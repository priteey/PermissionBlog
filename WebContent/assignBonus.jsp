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
  <a class="w3-bar-item w3-button w3"  href="ManagerController?action=manageLeaves">Manage Leaves</a>
  <a href="ManagerController?action=bonus" style="background-color:#1ec7e0" class="w3-bar-item w3-button">Assign Bonus</a>
  <a href="DirectoryController?action=directories" class="w3-bar-item w3-button">Create Directories</a>
      <a href="DirectoryController?action=manageDirectories"  class="w3-bar-item w3-button">Manage Directory Permission</a>
  
  
</div>
<div style="margin-left:13%; margin-top:10px">
 <div style="color:white; font-weight:bold;font-size:18px; margin-left:200px">${success}</div>
 <div style="color:red; font-weight:bold;font-size:18px; margin-left:200px">${error}</div>
<%
System.out.print(request.getAttribute("bonus"));
System.out.print(request.getAttribute("bonus").equals("bonusAssigned"));
if(request.getAttribute("bonus").equals("bonusAssigned")){%>
<form action="ManagerController" method="post">
<div class="w3-container">

<table  align="center" width=35% style="margin-left:100px" > 

                <tr>
                <td height=60px>
               	<div style="color:white; font-weight:bold"><font color="red">*</font>Assign Bonus :</td><td><input type="text" style="height:30px; font-weight:bold" name="bonus" placeholder="enter bonus"/></div>
                </td></tr> 
                </table>
                <input type="submit" style="height:40px; width:84px; border-radius:10px; font-weight:bold; font-size:18px; margin-left:200px;margin-top:30px" value="Assign" name ="action"/>
                </div>
                </form><br>
    <%}%>            
<form action="ManagerController" method="post">
<div class="w3-container">

  <table class="w3-table-all w3-card-4">
    <thead>
      <tr>
                                                    <th>Employee Id</th>
                                                    <th>First Name</th>
                                                    <th>Last Name</th>
                                                    <th>Email Id</th>
                                                    <th>Address</th>
                                                    <th>Phone Number</th>
                                                    <th>Division Name</th>
                                                    <th>Salary</th>
                                                    <th>Bonus</th>
													<th>Action</th>
                                                </tr>
                                               </thead>
 
                                            <c:forEach var="e" items="${empList}">
                                                <tr>
                                                    <td><c:out value="${e.userId}"/></td>
                                                    <td><c:out value="${e.fname}" /></td>
                                                    <td><c:out value="${e.lname}" /></td>
                                                    <td><c:out value="${e.emailId}" /></td>
                                                    <td><c:out value="${e.address}" /></td>
                                                    <td><c:out value="${e.phoneNo}" /></td>
                                                    <td><c:out value="${e.divisionName}" /></td>
                                                    <td><c:out value="${e.salary}" /></td>
                                                    <td><c:out value="${e.bonus}" /></td>
                                                    
                                          			<td><a href="<%=request.getContextPath() %>/ManagerController?action=assignBonus&userId=${e.userId}">Assign Bonus</a></td>
                                           </tr>
                                                </c:forEach>
                                        </table>
                                        </div>
                                      

</form></div>
</body>
</html> 
