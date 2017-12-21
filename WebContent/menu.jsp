<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #1ec7e0;
}

li {
    float: left;
}

li a, .dropbtn {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: #111;
}

li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

.active {
    background-color: #111;
}
</style>


</head>
<body>
  <ul>
  <li><a class="active" href="AdminController?action=active"><div style="color:white; font-weight:bold;font-size:20px">Home</div></a></li>
  <li style="float:right" class="dropdown">
    <a href="javascript:void(0)" class="dropbtn"><div style="color:white; font-weight:bold;font-size:20px">Welcome ${userName}!</div></a>
    <div class="dropdown-content">
      <a href="UpdateController?action=Profile">Update Profile</a>
      <a href="UpdateController?action=Password">Update Password</a>
      <a href="LoginController?signin=Logout">Logout</a>
    </div>
  </li>
</ul>
</body>
</html>