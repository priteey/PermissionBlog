<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<jsp:include page="menu.jsp"/>
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
  <a class="w3-bar-item w3-button w3"  href="AdminController?action=active">View Active Employees</a>
 <a href="AdminController?action=inactive" class="w3-bar-item w3-button">View Inactive Employees</a>
  <a href="AdminController?action=update" class="w3-bar-item w3-button" style="background-color:#1ec7e0">Update Organization Profile</a>
  <a href="AdminController?action=payroll" class="w3-bar-item w3-button">Run Payroll</a>
  
</div>
<div style="margin-left:10%; margin-top:10px">

<form action="AdminController?userId=${userId}" method="post">

<div class="w3-container">

<table  align="center" width=35% style="margin-left:100px" > 
<tr>
			   <td height=60px>
               <div style="color:white; font-weight:bold"><font color="red">*</font>Division Name:</td><td><input type="text"  style="height:30px; font-weight:bold;" name="divisionName"  placeholder="division name" value="${user.divisionName}"/></div>
                </td></tr>
                <%-- <tr>
                <td height=60px>
               	<div style="color:white; font-weight:bold"><font color="red">*</font>Supervision:</td><td><input type="text" style="height:30px; font-weight:bold" name="supervision"  placeholder="supervision"/><div style="color:red">${supervision}</div></div>
                </td></tr> --%>
                <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Supervision:</td><td><select name="supervision" style="height:30px; font-weight:bold" >
                  <option>Select anyone from below!</option>
              <option disabled="${supervisor.fname}" selected="${supervisor.fname}" value="${user.supervision}">${supervisor.fname} ${supervisor.lname}</option>
                <c:forEach var="i" items="${supervisionList}">
                  <option value="${i.userId}">${i.fname} ${i.lname}</option></c:forEach>
                </select></div>
                </td>
                </tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Role:</td><td><select name="role" style="height:30px; font-weight:bold">
                 <option>Select anyone from below!</option>
                 <option disabled="${user.role}" selected="${user.role}" value="${user.role}">${user.role}</option>
                  <option value="Employee">Employee</option>
                  <option value="Manager">Manager</option>
                </select></div>
                </td></tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Salary:</td><td><input type="text" style="height:30px; font-weight:bold" name="salary"  placeholder="salary" value="${user.salary }"/></div>
                </td></tr>
                <tr>
               <!-- <td height=60px>
                <div style="color:white; font-weight:bold">Bonus:</td><td><input type="text" style="height:30px; font-weight:bold" name="bonus"  placeholder="bonus"  /></div>
                </td></tr> -->
                </table>
                <input type="submit" style="height:40px; width:135px; border-radius:8px; font-weight:bold; font-size:15px; margin-left:250px;margin-top:30px" value="Update Profile" name ="action"/>
</div>
</form>
</div>
</body >
</html>