<%@page import="model.User"%>
<%@page import="utility.Utility"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%ArrayList<User> filteredUsers = (ArrayList<User>) request.getAttribute("filteredUsers"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All users page</title>
</head>
<body>
	<h3>All users</h3>
	
	<table border="1" style=width:100%>
		<tr>
			<th>User name</th>
			<th>Role</th>
			<th>Registration date</th>
		</tr>
		
		<form action="UsersManagementServlet" method="get">
			<tr>
				<td align="center">
					<fieldset> 
						<legend>Filter</legend>
						<input type="text" name="usernameFilter" value="<%=request.getAttribute("usernameFilter") %>">
				 	</fieldset> 
    					<fieldset> 
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="nameAsc" name="byUsername" value="asc">
    							<label for="nameAsc">Ascending</label><br>

    							<input type="radio" id="nameDsc" name="byUsername" value="dsc">
    							<label for="nameDsc">Descending</label>

    					</fieldset> 
				</td>
				
				
				<td align="center">
				 	
    					 <fieldset>
        						<legend>Filter</legend>
        						
        						<input type="radio" id="nameAdmin" name="roleFilter" value="ADMIN">
    							<label for="nameAdmin">Administrators</label><br>

    							<input type="radio" id="nameUser" name="roleFilter" value="USER">
    							<label for="nameUser">Users</label>

    					</fieldset> 
    					
    					<fieldset>
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="roleAsc" name="byRole" value="asc">
    							<label for="roleAsc">Ascending</label><br>

    							<input type="radio" id="roleDsc" name="byRole" value="dsc">
    							<label for="roleDsc">Descending</label>

    					</fieldset> 
				</td>
				
				
				
				<td align="center">
					<fieldset>
						<legend>Filter</legend>
							from:&nbsp;<input name="fromDate" type="date" value="<%=request.getAttribute("fromDate")%>" > 
							<input name="fromTime" type="time" value="<%=request.getAttribute("fromTime")%>" ></br>
							
							
							to:&nbsp;<input name="toDate" type="date" value="<%=request.getAttribute("toDate")%>" > 
							<input name="toTime" type="time" value="<%=request.getAttribute("toTime")%>" ></br>
					
						
						
					</fieldset>
					
					<fieldset>
						<legend>Sorting</legend>
						
        						
        						<input type="radio" id="dateAsc" name="byDate" value="asc">
    							<label for="dateAsc">Ascending</label><br>

    							<input type="radio" id="dateDsc" name="byDate" value="dsc">
    							<label for="dateDsc">Descending</label>
					</fieldset>
					

				</td>
					
					
			
				<td align="center"><input type="submit" value="Filter"></td>
				
				<td align="center"><input type="submit" value="Sort"></td>
			
				
			</tr>
		
		
		</form>
		
		<%for (User u : filteredUsers){ %>
			<tr>
					<td><a href="SingleUserServlet?id=<%= u.getId() %>"> <%= u.getUsername() %></a></td>
					<td><%=u.getRole() %></td>
					<td><%=Utility.convertDateWithTimeToString(u.getRegistrationDate()) %></td>	
			</tr>
		
		<%} %>
		
	</table>
	
	<form action="LogoutServlet" method="get">
					 	<input type=submit value="Logout" style=width:100%>
	</form>
	
	<form action="MainPageApp.jsp" >
			<input type=submit value="Back to Main Page" style=width:100%>
	</form>
	
	
</body>
</html>