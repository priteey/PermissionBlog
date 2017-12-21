<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<style>

body{
   background-image: url("images/employee-screening.jpg");
   background-size: 100% 100%;
      background-repeat: no-repeat;
          background-attachment: fixed;
}
table {border:3px solid #fff}
td,th {border:none}
</style>
<body>
<%
System.out.print(session.getAttribute("userId"));
if(session != null && session.getAttribute("userId") != null){
	request.setAttribute("err", "User Already Logged In");
	System.out.print(session.getAttribute("role").equals("Admin"));
	if(session.getAttribute("role").equals("Admin")){
		 RequestDispatcher reqdispatcher = request.getRequestDispatcher("adminHomePage.jsp");
			reqdispatcher.forward(request, response);
		}
		else {
			 RequestDispatcher reqdispatcher = request.getRequestDispatcher("EmployeeController?action=profile");
				reqdispatcher.forward(request, response);
		}
		
} %>
<%-- <div style="color:white">${UserRegisteredMsg}</div>
 --%>
<div style="color:red; font-weight:bold; font-size:25px; "align="center">${err}</div>
<form action="LoginController" method="post">
<table  align="center"  style="width:10%; margin-top:200px">
 <tr>
 <td height=60px>
 <div style="color:white; font-size:20px; font-weight:bold"><font color="red">*</font>User Name:</td><td><input type="text" style="height:35px; width:200px; font-weight:bold" name="userId"  placeholder="username" required/>
 </td></tr>
<tr>
 <td height=60px>
 <div style="color:white; font-size:20px; font-weight:bold"><font color="red">*</font>Password:</td><td><input type="password" style="height:35px; width:200px; font-weight:bold" name="password"  placeholder="password" required/>
</tr>

<tr>
<td> <input type="submit" style="height:40px; width:95px; border-radius:8px; font-weight:bold; font-size:18px; margin-left:98px;margin-top:30px" value="Sign In" name ="action"/>

</td>
</tr>
</table>
</form>
</body>
</html>