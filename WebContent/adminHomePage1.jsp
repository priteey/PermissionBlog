<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="menu.jsp"/>
<body>
<form action="AdminController" method="get">
<table border="1px"  style = "color:black" cellspacing="0" width="75%" top="20%">
                                            
                                                <tr>
                                                    <th>Employee Id</th>
                                                    <th>First Name</th>
                                                    <th>Last Name</th>
                                                    <th>Email Id</th>
                                                    <th>Address</th>
                                                    <th>Phone Number</th>
                                                    <th>Role</th>
													<th>Action</th>
                                                </tr>

                                            <c:forEach var="e" items="${inactiveEmpList}">
                                                <tr>
                                                    <td><c:out value="${e.userId}"/></td>
                                                    <td><c:out value="${e.fname}" /></td>
                                                    <td><c:out value="${e.lname}" /></td>
                                                    <td><c:out value="${e.emailId}" /></td>
                                                    <td><c:out value="${e.address}" /></td>
                                                    <td><c:out value="${e.phoneNo}" /></td>
                                                    <td><c:out value="${e.role}" /></td>
                                          			<td><a href="<%=request.getContextPath() %>/AdminController?action=Delete&id=${e.userId}">Delete</a></td>
                                           </tr>
                                                </c:forEach>
                                        </table>

</form>
</body>
</html>