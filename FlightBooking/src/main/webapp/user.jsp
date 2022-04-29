<%-- 
    Document   : home.jsp
    Created on : Mar 31, 2022, 11:59:15 AM
    Author     : minhl
--%>
<%@page import="cs411.flightbooking.models.Flight"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" integrity="sha512-YDChav1pUAodyH1Ja7PIpEDUOoFROpZi5Lb7pY8+9+kU8UTr3J8SI8QO7SRuf4qdDKb5OI0xSt4Vk1wiYjBXgw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./css/register_form.css">
        <link rel="stylesheet" href="./css/style.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js" integrity="sha512-Vp2UimVVK8kNOjXqqj/B0Fyo96SDPj9OCSm1vmYSrLYF3mwIOBXh/yRZDVKo8NemQn1GUjjK0vFJuCSCkYai/A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script><!-- comment -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="/FlightBooking">Flight Booking</a>
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
                    </ul>
                    <div class="d-flex nav-item dropdown">
                        <button class="nav-link active dropdown-toggle" href="#" type="navbarDropdown" id="user-dropdown" role="button" data-bs-toggle="dropdown" data-bs-target="#user-dropdown" aria-expanded="false">
                            <i class="bi-person-circle"></i>
                        </button> 
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="user-dropdown">
                            <li><a class="dropdown-item" href="logout.jsp">Log Out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>

        <h1>Welcome! You login successfully</h1>

        <form action="./user/search-flight" id="search-form" method="post">    
            <div class="container px-4 px-lg-5" id="flight-search-form">

                <h1>Flight Search</h1>
                <hr></hr>
                <div class="form-inputs">
                    <label for="from" class="form-label">From</label>
                    <input type="text" class="form-control" id="from-loc" name="from" required>
                </div>
                <div class="form-inputs">
                    <label for="to" class="form-label">To</label>
                    <input type="text" class="form-control" id="to-loc" name="to" required>
                </div>
                <div class="form-inputs">
                    <label for="date" class="form-label">Date</label>
                    <div class="input-group has-validation">
                        <input type="date" class="form-control" id="time-in" name="departureTime" required>
                    </div>
                </div>
                <br>
                <div class="text-center">
                    <button id="button" class="btn btn-primary" type="submit" onclick="flight.submitValues(event)">Search</button>
                </div>
            </div>    
        </form>

        <h1> Flight History </h1>
        <hr></hr>

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
                    </tr>
                </thead>
                <tbody class="text-center">
                    <%ArrayList<Flight> flights =
                            (ArrayList<Flight>)request.getAttribute("flight-history");
                            if (flights == null || flights.isEmpty()) {%>
                    <tr>
                        <td colspan="8">
                            There is no flight to show.
                        </td>
                    </tr> <%} else {
                for(Flight flight:flights){%>
                    <tr>
                        <th scope="row"><%=flight.getId()%></th>
                        <td id="departure-loc-col"><%=flight.getDepartureLocation()%></td>
                        <td id="arrival-loc-col"><%=flight.getArrivalLocation()%></td>
                        <td id="departure-time-col"><%=flight.getDepartureTimeString()%></td>
                        <td id="arrival-time-col"><%=flight.getArrivalTimeString()%></td>
                        <td id="capacity-col"><%=flight.getCapacity()%></td>
                        <td id="available-col"><%=flight.getAvailable()%></td>
                        <td id="price-col"><%=flight.getPrice()%></td>
                    </tr>
                    <%}}%>

                </tbody>
            </table>
        </div>

        <!-- Bootstrap Script -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

        <!-- Dependencies -->
        <script src="https://cdn.jsdelivr.net/npm/fuse.js@6.5.3"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>

        <!-- For refreshing page -->
        <script>
                        if (window.history.replaceState) {
                            window.history.replaceState(null, null, window.location.href);
                        }
        </script>       

        <!-- Local JS Files -->
        <script type="text/javascript" src="script/airport_search/search.js"></script>
        <script type="text/javascript" src="script/airport_search/main.js"></script>

        <script>
                        AirportInput("#from-loc");
                        AirportInput("#to-loc");
                        
                        let form = $("#search-form");
                        let today = new Date();
                        form.find("#time-in:first").attr("min", today.toISOString().split('T')[0]);
        </script>
    </body>

</html>
