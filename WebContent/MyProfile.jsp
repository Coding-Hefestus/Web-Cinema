<%@page import="model.Ticket"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="utility.Utility"%>
<%@page import="model.Role"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%ArrayList<Ticket> ticketsForUser = (ArrayList<Ticket>) request.getAttribute("tickets");%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile page</title>
</head>
<body>
	<form action="MyProfileServlet" method="get">
	<table border="1" style=width:100%>
		
		<tr>
		
			<th>User name</th>
			<th>Registration date</th>
			<th>Role</th>
			<th>New password</th>
			<th>Save profile</th>
					
					
		</tr>
		
		<tr>
		 <td><%= loggedInUser.getUsername() %></td>
		 <td><%=Utility.convertDateWithTimeToString(loggedInUser.getRegistrationDate()) %></td>
		 <td>
		 
		 
		 	<select name="role" >
		 		<%if(loggedInUser.getRole() == Role.USER){ %>
		 		
		 			<option <%if(loggedInUser.getRole() == Role.USER){ %>selected <%} %> value="USER">User</option>
		 		
		 		<%} %>
		 		
		 		<%if(loggedInUser.getRole() == Role.ADMIN){ %>
		 		
		 			<option <%if(loggedInUser.getRole() == Role.ADMIN){ %>selected <%} %> value="ADMIN">ADMIN</option>
		 		
		 		<%} %>
		 	</select>	
		 	
		 	  
		
		 </td>
		 <td>
		 
		 	<input type="text"  name="newPassword">
		 
		 </td>
		 
		 

		 <td align="center">
		 	
		 	<input type="submit" value="Edit/Save">
		 
		 </td>
		 
		 
		</tr>
		

	</table>
	</form>
	
	
	<h3>Tickets for user</h3>
	
	
	<table border="1" style=width:100%>
		
		<tr>
			<th>Purchasing date</th>
		</tr>
		
		<%for(Ticket t : ticketsForUser){ %>
			
			<tr> 
				<td> <a href="TicketServlet?id=<%= t.getId()%>"> <%=Utility.convertDateWithTimeToString(t.getPurchasingDate()) %> </a></td>
			</tr>
			
		
		<%} %>
	
	</table>
	
	<form action="MainPageAppServlet" method="get" >
			<input type=submit value="Back to Main Page" style=width:100%>
	</form>
	
	
	
	
	
</body>
</html>