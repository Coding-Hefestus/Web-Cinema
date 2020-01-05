<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Projection"%>
<%@page import="utility.Utility"%>
<%@page import="model.Role"%>
<%@page import="model.Ticket"%>
<%Projection projection = (Projection) request.getAttribute("projection");%>
 <%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
 <%ArrayList<Ticket> ticketsForProjection = (ArrayList<Ticket>) request.getAttribute("ticketsForProjection"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Single Projection page</title>
</head>
<body>
	<h3>Projection</h3>

	<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Period</th>
			<th>Projection type</th>
			<th>Hall</th>
			<th>Ticket price</th>
			<th>Seats available</th>
		
		<tr>
	
		 <td><%= projection.getMovie().getName() %></td>
		 <td><%= Utility.convertDateWithTimeToString(projection.getPeriod().getStart()) + " " +  Utility.convertDateWithTimeToString(projection.getPeriod().getEnd())%></td>
		 <td><%= projection.getProjectionType().getName() %></td>
		 <td><%= projection.getHall().getName() %></td>
		 <td><%= projection.getTicketPrice() %></td>
		 <td><%= projection.getHall().getCapacity() - projection.getTicketsSold() %></td>

		 
		</tr>

	</table>
	
	<%if(loggedInUser.getRole() == Role.USER && (projection.getHall().getCapacity() - projection.getTicketsSold()) != 0){ %>
		
		
		<form action="FindAvailableSeatsServlet" method="get">
			<input type="hidden" name="projection" value="<%=projection.getId()%>">
			<button name="buy" type="submit" style=width:100% value="<%= projection.getId()%>">Buy ticket</button>
			
		</form>
	
	<%} %>
	<%if (loggedInUser.getRole() == Role.ADMIN){ %>
	
	<h3>Tickets for projection</h3>
	
	<table border="1" style=width:100%>
		<tr>
			<th>Purchasing date</th>
			<th>User</th>
			
		</tr>
		
		<form action="SingleProjectionServlet" method="get" > 
			<tr>
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
				
				<td align="center">
					
				 		
				 		<fieldset> 
							<legend>Filter</legend>
								<input type="text" name="userFilter" value="<%=request.getAttribute("userFilter") %>">
				 		</fieldset> 
				 		
				 		 <fieldset>
        						<legend>Sorting order</legend>
        						
        						<input type="radio" id="ticketAsc" name="byTicket" value="asc">
    							<label for="ticketAsc">Ascending</label><br>

    							<input type="radio" id="ticketDsc" name="byTicket" value="dsc">
    							<label for="ticketDsc">Descending</label>

    					</fieldset> 
				 		
				 </td>
					
					<td align="center"><input type="submit" value="Filter"></td>
				
					<td align="center"><input type="submit" value="Sort"></td>
		
		
			</tr>
			<input type="hidden" name="id" value="<%=projection.getId()%>">
		</form>
		
		<%for (Ticket t : ticketsForProjection){ %>
			<tr>
				
				<td> <a href="TicketServlet?id=<%= t.getId()%>"><%= Utility.convertDateWithTimeToString(t.getPurchasingDate()) %></a></td>
				<td> <a href="SingleUserServlet?id=<%=t.getUser().getId()%>"> <%= t.getUser().getUsername() %></a></td>
				
				<%-- <td><%= Utility.convertDateWithTimeToString(t.getPurchasingDate()) %></td>
				<td><%= t.getUser().getUsername() %></td> --%>
			
			</tr>
			
		
		<%} %>
				
				
				
				
		
		
		</table>
		
		<form action="DeleteProjectionServlet" method="get">
			<button name="delete" style=width:100% type="submit" value="<%= projection.getId()%>">Delete projection</button>			
		</form>
		
		<%} %>

</body>
</html>