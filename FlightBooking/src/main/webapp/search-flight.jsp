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
        
        <!-- Local JS Files -->
    <script type="text/javascript" src="script/flight-search.js"></script>
        <!-- CSS style sheets -->
        <link rel="stylesheet" href="../css/style.css">
        <!-- Bootsrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">        

        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.html">Flight Booking</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            About us
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Mission</a></li>
                            <li><a class="dropdown-item" href="#">Contact</a></li>
                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="./login">Log In</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" href="./register">Register</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
          <div class="container">
             <div class="card border-light mb-3" >
<!--                <div class="card-header">Header</div>-->
                <div class="card-body">
                  <h5 class="card-title">Search Results</h5>
                  <table class="table table-hover table-responsive table-sortable" id="all-flight-table">
            <thead>
                <tr class="text-center">
                    <th scope="col">ID</th>
                    <th scope="col">Departure</th>
                    <th scope="col">Arrival</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
<!--                    <th scope="col">Capacity</th>-->
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
                    <td id="departure-loc-col" ><%=flight.getDepartureLocation()%></td>
                    <td id="arrival-loc-col"><%=flight.getArrivalLocation()%></td>
                    <td id="departure-time-col"><%=flight.getDepartureTimeString()%></td>
                    <td id="arrival-time-col"><%=flight.getArrivalTimeString()%></td>
                    <td id="capacity-col" hidden><%=flight.getCapacity()%></td>
                    <td id="available-col"><%=flight.getAvailable()%></td>
                    <td class="td-sort-asc" id="price-col" >$<%=flight.getPrice()%></td>
                    <input id="flight-info" name="booked-flight-id" value="<%=flight.getId()%>" hidden>
                    <td id="book-col"><button class="btn btn-primary" type="submit">Book now</button></td>
            </form>
                </tr>
                <%} i+= 1;}%>

            </tbody>
        </table>
                </div>
             </div> 
        
    </div>
    </body>
</html>
