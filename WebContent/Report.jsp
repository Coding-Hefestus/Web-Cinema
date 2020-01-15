<%@page import="model.User"%>
<%@page import="utility.Utility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Report"%>

<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%String from = (String) request.getAttribute("from"); %>
<%String to = (String) request.getAttribute("to"); %>
<%String fromTime = (String) request.getAttribute("fromTime"); %>
<%String toTime = (String) request.getAttribute("toTime"); %>
<%ArrayList<Report> sortedReports = (ArrayList<Report>) request.getAttribute("sortedReports"); %>
<%Double projections = (Double) request.getAttribute("projections"); %>
<%Double tickets = (Double) request.getAttribute("tickets"); %>
<%Double income = (Double) request.getAttribute("income"); %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report</title>
</head>
<body>
	
	<%if (from == null || to == null || fromTime == null || toTime == null){ %>
		<form action="ReportServlet" method="get">			
				<!-- From:&nbsp;<input name="fromDate" type="date" ></br>
				To:&nbsp;<input name="toDate" type="date" > </br> -->
				from:&nbsp;<input name="fromDate" type="date" value="" > 
				<input name="fromTime" type="time" value="" ></br>
							
							
				to:&nbsp;<input name="toDate" type="date" value="" > 
				<input name="toTime" type="time" value="" ></br>
				<input type="submit" value="Generate report">			
		</form>
	
							
	<%} else {%>
	

	<form action="ReportServlet" method="post">
		<input type="hidden" value="<%=from %>" name="fromDate">
		<input type="hidden" value="<%=to %>" name="toDate">
		
		<input type="hidden" value="<%=fromTime %>" name="fromTime">
		<input type="hidden" value="<%=toTime %>" name="toTime">
	<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Projections</th>
			<th>Tickets sold</th>
			<th>Income</th>
		</tr>
		<tr>
			<td align="center">
			 
    					<fieldset> 
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="movieAsc" name="byMovie" value="asc">
    							<label for="movieAsc">Ascending</label><br>

    							<input type="radio" id="movieDsc" name="byMovie" value="dsc">
    							<label for="movieDsc">Descending</label>
    					</fieldset> 
			</td>
		
		
			<td align="center">
			 
    					<fieldset> 
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="projectionAsc" name="byProjections" value="asc">
    							<label for="projectionAsc">Ascending</label><br>

    							<input type="radio" id="projectionDsc" name="byProjections" value="dsc">
    							<label for="projectionDsc">Descending</label>

    					</fieldset> 
			</td>
			
			<td align="center">
			
				<fieldset> 
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="ticketAsc" name="byTickets" value="asc">
    							<label for="ticketAsc">Ascending</label><br>

    							<input type="radio" id="projectionDsc" name="byTickets" value="dsc">
    							<label for="ticketDsc">Descending</label>

    			</fieldset> 
			
			
			</td>
			
			<td align="center">
			
				<fieldset> 
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="incomeAsc" name="byIncome" value="asc">
    							<label for="incomeAsc">Ascending</label><br>

    							<input type="radio" id="incomeDsc" name="byIncome" value="dsc">
    							<label for="incomeDsc">Descending</label>

    			</fieldset> 
				
					
			
			</td>
			
			<td align="center"><input type="submit" value="Sort"></td>
			
		</tr>
		
		 <%for (Report r : sortedReports){ %>
			<tr>
				
 				<td> <a href="SingleMovieServlet?id=<%=r.getMovieId()%>"> <%=r.getMovie() %> </a></td>	
				<td> <%=r.getProjections() %>  </td>
				<td> <%=r.getTickets() %>  </td>
				<td> <%=r.getIncome() %>  </td> 
				
			</tr>
		<%} %>
		<tr>
			<td>TOTAL</td>
			<td><%=projections.intValue() %></td>
			<td><%=tickets.intValue() %></td>
			<td><%=income %></td> 
		</tr>
	</table>
	</form>
	
	
	<%} %>
	
	<form action="MainPageAppServlet" method="get">
		<input type=submit value="Back to main page" style=width:100%>
	</form>

	<form action="UsersManagementServlet" method="get">
		<input type=submit value="Users management" style=width:100%>
	</form>
	
	
	<form action="MyProfileServlet" method="get">
		<input type=submit value="My profile" style=width:100%>
	</form>
	
	
	<form action="LogoutServlet" method="get">
		<input type=submit value="Logout" style=width:100%>
	 </form>

</body>
</html>