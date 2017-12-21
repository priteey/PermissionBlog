<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<jsp:include page="menu.jsp"/>

<body>
<%
if(session == null || session.getAttribute("userId") == null){
	request.setAttribute("err", "User Not Logged In");
	 RequestDispatcher reqdispatcher = request.getRequestDispatcher("index.jsp");
		reqdispatcher.forward(request, response);
} %>
<div style="color:blue; background-color:pink;">${err}</div>
<div class="w3-sidebar w3-bar-block w3-dark-grey w3-animate-left" style="display:block" id="mySidebar">
  <a class="w3-bar-item w3-button w3" href="AdminController?action=active">View Active Employees</a>
<!--   <a href="AdminController?action=active" class="w3-bar-item w3-button">View Active Employees</a>
 -->  <a href="AdminController?action=inactive" class="w3-bar-item w3-button" style="background-color:#1ec7e0">View Inactive Employees</a>
  <a href="AdminController?action=update" class="w3-bar-item w3-button">Update Organization Profile</a>
  <a href="AdminController?action=payroll" class="w3-bar-item w3-button">Run Payroll</a>
  
</div>


<div style="margin-left:13%; margin-top:10px">

<form action="AdminController" method="post">
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
													<th>Action</th>
                                                </tr>
                                               </thead>

                                            <c:forEach var="e" items="${inactiveEmpList}">
                                                <tr>
                                                    <td><c:out value="${e.userId}"/></td>
                                                    <td><c:out value="${e.fname}" /></td>
                                                    <td><c:out value="${e.lname}" /></td>
                                                    <td><c:out value="${e.emailId}" /></td>
                                                    <td><c:out value="${e.address}" /></td>
                                                    <td><c:out value="${e.phoneNo}" /></td>
                                          			<td><a href="<%=request.getContextPath() %>/AdminController?action=deleteInactive&userId=${e.userId}">Delete</a></td>
                                           </tr>
                                                </c:forEach>
                                        </table>
                                        </div></div>

</form>
     
</body>
</html> 
