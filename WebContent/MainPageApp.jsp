<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main application page</title>
</head>
<body>

	<a href="./MovieServlet">Show all movies</a>
	<%if (loggedInUser.getRole() == Role.ADMIN){ %>
		<a href="./UsersManagementServlet">Users management</a>
	<%}%>

	
</body>
</html>