<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<style>
body{
   background-image: url("images/people_with_globa_background.jpg");
   background-size: 100%,100%;s
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
<div style="color:red">${err}</div>
<center><h1>Login</h1></center>
<form action="LoginController" method="get">
<table align="center" cellpadding="20">
<tr>
<td> <input type="submit" style="height:40px; width:95px; border-radius:8px; font-weight:bold; font-size:18px;" value="Sign In" name ="signin"/>
</td>
</tr>
<tr><td>
<input type="submit" style="height:40px; width:205px; border-radius:8px; font-weight:bold; font-size:18px;"  value="Create a new account" name ="signin"/>
</td>
</tr>
</table>
</form>
</body>
</html>