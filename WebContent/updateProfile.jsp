<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
if(session == null || session.getAttribute("userId") == null){
	request.setAttribute("err", "User Not Logged In");
	 RequestDispatcher reqdispatcher = request.getRequestDispatcher("index.jsp");
		reqdispatcher.forward(request, response);
} %>
<div style="color:blue; background-color:pink;">${err}</div>
 <div style="color:white; font-weight:bold;font-size:18px; margin-left:200px;margin-top:50px">${success}</div>
 <div style="color:red; font-weight:bold;font-size:18px; margin-left:200px;margin-top:50px">${error}</div>
<form method="post" name="update" action="UpdateController">
<table  width=30% style="margin-left:100px;margin-top:90px"> 
<tr>
<td height=60px>
               <div style="color:white; font -weight:bold">First Name:</td><td><input type="text"  style="height:30px; font-weight:bold" name="fname"  placeholder="firstname" value= "${ user.fname}"  /></div>
                </td></tr>
                <tr>
                <td height=60px>
               	<div style="color:white; font-weight:bold">Last Name:</td><td><input type="text" style="height:30px; font-weight:bold" name="lname"  placeholder="lastname" value= "${ user.lname}" /></div>
                </td></tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Gender:</td><td><select name="gender" style="height:30px; font-weight:bold">
               <option disabled="${user.gender}" selected="${user.gender}" value="${user.gender}">${user.gender}</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                </select></div>
                </td></tr>
                <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Email Id:</td><td><input type="text" style="height:30px; font-weight:bold" name="emailId"  placeholder="myusername@gmail.com"  value= "${ user.emailId}"/></div>
                </td></tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Address:</td><td><textarea rows="4" cols="22"  style=" font-weight:bold"  name="address"  placeholder="address"  value= "${ user.address}"></textarea></div>
                </td></tr>
                 <tr>
                <td height=60px>
                <div style="color:white; font-weight:bold">Phone No.:</td><td><input type="text" style="height:30px; font-weight:bold" name="phoneNo"  placeholder="phoneNo"  value= "${ user.phoneNo}"/></div>
                </td></tr>
                <input type ="hidden" name="role" value="customer"></tr>
               
                </table>
               
                <div class="btn" >
                  <input type="submit" style="height:40px; width:80px; border-radius:10px; font-weight:bold; font-size:18px; margin-left:200px;margin-top:30px" value="Update" name ="action"/>
                   <input type="submit" style="height:40px; width:180px; border-radius:10px; font-weight:bold; font-size:18px; margin-left:50px;margin-top:30px" value="Back To Homepage" name ="action"/>

                </div>
              </form>
</body>
</html>
</body>
</html>