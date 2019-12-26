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
		
		<form action="MovieServlet" method="get">
			<tr>
				<td align="center">
					<fieldset> 
						<legend>Filter</legend>
						<input type="text" name="usernameFilter" value="<% request.getAttribute("usernameFilter"); %>">
				 	</fieldset> 
    					<fieldset> 
        						<legend>Sorting order</legend>
        						
        						<input type="radio" id="nameAsc" name="byUsername" value="asc">
    							<label for="nameAsc">Ascending</label><br>

    							<input type="radio" id="nameDsc" name="byUsername" value="dsc">
    							<label for="nameDsc">Descending</label>

    					</fieldset> 
				</td>
				
				
				<td align="center">
				 	
    					 <fieldset>
        						<legend>Filter</legend>
        						
        						<input type="radio" id="nameAsc" name="roleFilter" value="ADMIN">
    							<label for="nameAsc">Administrators</label><br>

    							<input type="radio" id="nameDsc" name="roleFilter" value="USER">
    							<label for="nameDsc">Users</label>

    					</fieldset> 
    					
    					<fieldset>
        						<legend>Sorting order</legend>
        						
        						<input type="radio" id="nameAsc" name="byRole" value="asc">
    							<label for="nameAsc">Ascending</label><br>

    							<input type="radio" id="nameDsc" name="byRole" value="dsc">
    							<label for="nameDsc">Descending</label>

    					</fieldset> 
				</td>
				
				
				
				<td align="center">
						from:&nbsp; <select name="fromDay"> <%for (int i = 1; i <= 31; i++ ){ %> <option value="<%=i %>"> <%=i %> </option>   <%} %>  </select> 
									 <select name="fromMonth"> <%for (int i = 1; i <= 12; i++ ){ %> <option value="<%=i %>"> <%=i %> </option>   <%} %>  </select>	
									 <select name="fromYear"> <%for (int i = 1950; i <= 2020; i++ ){ %> <option value="<%=i %>"> <%=i %> </option>   <%}%>  </select></br>	
						to:&nbsp; <select name="toDay"> <%for (int i = 1; i <= 31; i++ ){ %> <option value="<%=i %>"> <%=i %> </option>   <%} %>  </select> 
									 <select name="toMonth"> <%for (int i = 1; i <= 12; i++ ){ %> <option value="<%=i %>"> <%=i %> </option>   <%} %>  </select>	
									 <select name="toYear"> <%for (int i = 1950; i <= 2020; i++ ){ %> <option value="<%=i %>"> <%=i %> </option>   <%}%>  </select></br>		
						

				</td>
					
					
			
				
			
				
			</tr>
		
		
		</form>
		
		<%for (User u : filteredUsers){ %>
			<tr>
					<td><a href="#?id=<%= u.getId() %>"> <%= u.getUsername() %></a></td>
					<td><%=u.getRole() %></td>
					<td><%=Utility.convertDateWithTimeToString(u.getRegistrationDate()) %></td>
					
			</tr>
		
		<%} %>
		
	</table>
</body>
</html>