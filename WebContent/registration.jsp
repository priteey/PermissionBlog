<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<style>

body{
   background-image: url("images/employee-screening.jpg");
   background-size: 100% 100%;
      background-repeat: no-repeat;
         background-attachment: fixed;
      
   
}
</style>
<jsp:include page="menu.jsp"/>

<body>
<%
if(session != null && session.getAttribute("userId") != null){
	session.setAttribute("err", "User Already Registered");
	if(session.getAttribute("role").equals("Admin")){
	response.sendRedirect("adminHomePage.jsp");
	}
	else {
		 RequestDispatcher reqdispatcher = request.getRequestDispatcher("EmployeeController?action=profile");
			reqdispatcher.forward(request, response);
	}
} %>

<div style="color:red">${userExistErr}</div>
<form action="RegistrationController" method="post">
<table  width=50% style="margin-left:100px;"> 
<tr>
			   <td height=60px>
               <div style="color:white; font-weight:bold"><font color="red">*</font>First Name:</td><td><input type="text"  style="height:30px; font-weight:bold;" name="fname"  placeholder="firstname" required/><div style="color:red">${firstNameErr}</div></div>
                </td></tr>
                <tr>
                <td height=60px>
               	<div style="color:white; font-weight:bold"><font color="red">*</font>Last Name:</td><td><input type="text" style="height:30px; font-weight:bold" name="lname"  placeholder="lastname" required/><div style="color:red">${lastNameErr}</div></div>
                </td></tr>
               <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>User Name:</td><td><input type="text" style="height:30px; font-weight:bold" name="userId"  placeholder="username" required/><div style="color:red">${userIdErr}</div></div>
                </td></tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Gender:</td><td><select name="gender" style="height:30px; font-weight:bold;width:175px">
                 <option>Select anyone from below!</option>
                 <option disabled="${user.gender}" selected="${user.gender}" value="${user.gender}">${user.gender}</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                </select></div>
                </td></tr>
                <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Email Id:</td><td><input type="text" style="height:30px; font-weight:bold" name="emailId"  placeholder="username@gmail.com"  /></div>
                </td></tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold"><font color="red">*</font>Password:</td><td><input type="password" style="height:30px;margin-top:20px; font-weight:bold" name="password"  placeholder="password" required/><div style="color:red">${passErr}</div><div style="color:white;width:40%; font-size:80%">(Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character)</div></div>
                </td></tr>
                <tr>
                <td height=60px>
                  <div style="color:white; font-weight:bold"><font color="red">*</font>Confirm Password:</td><td><input type="password" style="height:30px; margin-top:20px;font-weight:bold" name="confirmPassword" id ="confirmPassword"  placeholder="confirmpassword" required/><div style="color:red"> ${cnpassErr}</div></div>
                </td></tr>
                <tr>
                <td height=60px>
                  <div style="color:white; font-weight:bold">Address:</td><td><input type="text" style="height:30px; font-weight:bold" name="address" id ="address"  placeholder="address"/></div>
                </td></tr>
                <tr>
                <td height=60px>
                  <div style="color:white; font-weight:bold">Phone No.:</td><td><input type="text" style="height:30px; font-weight:bold" name="phoneNo" id ="phoneNo"  placeholder="phoneNo"/> </div>
                </td></tr>
                <input type ="hidden" name="role" value="Employee">
                <input type ="hidden" name="status" value="inactive"></tr>
                </tr>
                </table>
                <input type="submit" style="height:40px; width:85px; border-radius:10px; font-weight:bold; font-size:18px; margin-left:200px;margin-top:30px" value="Register" name ="action"/>
                
	</form>
</body>
</html>