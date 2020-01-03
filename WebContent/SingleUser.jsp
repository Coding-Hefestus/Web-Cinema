<%@page import="model.Ticket"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="utility.Utility"%>
<%@page import="model.Role"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%User user = (User) request.getAttribute("user"); %>
<%ArrayList<Ticket> ticketsForUser = (ArrayList<Ticket>) request.getAttribute("tickets");%>
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
			<th>Save changes</th>
			
					
		</tr>
		
		<tr>
		 <td><%= user.getUsername() %></td>
		 <td><%=Utility.convertDateWithTimeToString(user.getRegistrationDate()) %></td>
		 <td>
		 	
		 	  	<select name="role" >
		 	  		
		 	  		
		 	  	 
		 	  	 <%if(loggedInUser.getRole() == Role.ADMIN){ %>
		 	  	 	 <option <%if(user.getRole() == Role.ADMIN){ %>selected <%} %> value="ADMIN">Administrator</option>
		 	  		<option <%if(user.getRole() == Role.USER){ %>selected <%} %> value="USER">User</option>
		 	  	 
		 	  	 <% }%>
		 	  	 
		 	  	
		 	  	 
		 	  	</select>	
		
		 </td>
		 
		 <td>
		 	<input type="text"  name="newPassword">
		 	
		 </td>

		 <td align="center">
		 	<input type="hidden" name="id" value="<%=user.getId() %>" />
		 	<input type="submit" value="Edit/Save">
		 
		 </td>
		 
		 
		 
		 
		 
		 
		
		</tr>
		
		
		
		
		
		
		
	</table>
	
	
	</form>
	
	<form action="DeleteUserServlet" action="get">
			<input type="hidden" name="id" value="<%=user.getId()%>">
			<input style=width:100% type="submit" value="Delete user">
	</form>
	
	<h3>Tickets for user</h3>
	
	
	<table border="1" style=width:100%>
		
		<tr>
			<th>Purchasing date</th>
		</tr>
		
		<%for(Ticket t : ticketsForUser){ %>
			
			<tr> 
				<td> <a href="ServletStranicaKarteNIJE__IMPLEMENTIRANOOOO?id=<%= t.getId()%>"> <%=Utility.convertDateWithTimeToString(t.getPurchasingDate()) %> </a></td>
			</tr>
			
		
		<%} %>
	
	</table>
	

</body>
</html>