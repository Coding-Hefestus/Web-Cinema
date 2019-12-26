<%@page import="model.User"%>
<%@page import="utility.Utility"%>
<%@page import="model.Role"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%User user = (User) request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Single user page</title>
</head>
<body>
	
	<form action="EditUserServlet" method="get">
	<table border="1" style=width:100%>
		
		<tr>
		
			<th>User name</th>
			<th>Registration date</th>
			<th>Role</th>
			<th>New password</th>		
					
		</tr>
		
		<tr>
		 <td><%= user.getUsername() %></td>
		 <td><%=Utility.convertDateWithTimeToString(user.getRegistrationDate()) %></td>
		 <td>
		 	
		 	  	<select name="role" >
		 	  		
		 	  		<option <%if(user.getRole() == Role.ADMIN){ %>selected <%} %> value="ADMIN">Administrator</option>
		 	  		<option <%if(user.getRole() == Role.USER){ %>selected <%} %> value="USER">User</option>
		 	  	</select>	
		
		 </td>
		 
		 <td>
		 	<input type="text"  name="newPassword">
		 	
		 </td>

		 <td align="center">
		 	<input type="hidden" name="id" value="<%=user.getId() %>" />
		 	<input type="submit" value="Edit user">
		 
		 </td>
		 
		 
		 
		 
		
		</tr>
		
		
		
		
		
		
		
	</table>
	
	
	</form>
	

</body>
</html>