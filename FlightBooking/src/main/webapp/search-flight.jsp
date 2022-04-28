<%-- 
    Document   : search-flight
    Created on : Apr 28, 2022, 6:13:35 PM
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
        <h1>Hello World!</h1>
          <div class="container">
        <table class="table table-hover table-responsive" id="all-flight-table">
            <thead>
                <tr class="text-center">
                    <th scope="col">ID</th>
                    <th scope="col">Departure</th>
                    <th scope="col">Arrival</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                    <th scope="col">Capacity</th>
                    <th scope="col">Available</th>
                    <th scope="col">Price</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody class="text-center">
                <%ArrayList<Flight> flights =
                        (ArrayList<Flight>)request.getAttribute("search-result");
                        if (flights.isEmpty()) {%>
                <tr>
                    <td colspan="8">
                        There is no flight to show.
                    </td>
                </tr> <%} else {
int i=0;
                for(Flight flight:flights){%>
                <tr>
            <form action="book-flight" method="post">
                    <th scope="row"><%=flight.getId()%></th>
                    <td id="departure-loc-col"><%=flight.getDepartureLocation()%></td>
                    <td id="arrival-loc-col"><%=flight.getArrivalLocation()%></td>
                    <td id="departure-time-col"><%=flight.getDepartureTimeString()%></td>
                    <td id="arrival-time-col"><%=flight.getArrivalTimeString()%></td>
                    <td id="capacity-col"><%=flight.getCapacity()%></td>
                    <td id="available-col"><%=flight.getAvailable()%></td>
                    <td id="price-col"><%=flight.getPrice()%></td>
                    <input id="flight-info" name="booked-flight-index" value="<%=i%>" hidden>
                    <td id="book-col"><button type="submit">Book now</button></td>
            </form>
                </tr>
                <%} i+= 1;}%>

            </tbody>
        </table>
    </div>
    </body>
</html>
