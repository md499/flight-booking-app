<%-- 
    Document   : book-flight
    Created on : Apr 28, 2022, 7:49:41 PM
    Author     : sofi
--%>
<%@page import="cs411.flightbooking.models.Flight"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Flight flight = (Flight) request.getAttribute("booked-flight");
        if (flight == null) {%>
        <p>Flight Booked Fail </p>
        <%} else {%>
        <p>Flight Booked Success</p>
        <%}%>
        
    </body>
</html>
