<%@page import="model.User"%>
<%@page import="utility.Utility"%>
<%@page import="model.Role"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile page</title>
</head>
<body>
	<form action="MainPageApp.jsp">
	<table border="1" style=width:100%>
		
		<tr>
		
			<th>User name</th>
			<th>Registration date</th>
			<th>Role</th>
					
					
		</tr>
		
		<tr>
		 <td><%= loggedInUser.getUsername() %></td>
		 <td><%=Utility.convertDateWithTimeToString(loggedInUser.getRegistrationDate()) %></td>
		 <td>
		 	
		 	  	<select name="role" >
		 	  		
		 	  		<option <%if(loggedInUser.getRole() == Role.ADMIN){ %>selected <%} %> value="ADMIN">Administrator</option>
		 	  		<option <%if(loggedInUser.getRole() == Role.USER){ %>selected <%} %> value="USER">User</option>
		 	  	</select>	
		
		 </td>
		 
		 

		 <td align="center">
		 	
		 	<input type="submit" value="Back to main page">
		 
		 </td>
		 
		 
		</tr>
		

	</table>
	</form>
</body>
</html>