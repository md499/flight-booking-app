<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cs411.flightbooking.models.Flight"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootsrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">        

        <!-- CSS style sheets -->
        <link rel="stylesheet" href="../css/style.css">

        <!-- JQuerry -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <!-- For alerting symbols -->
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
    </svg>

    <title>Flight Manager</title>
</head>
<body>
    <% 
    String role = (String) session.getAttribute("role");
    if (role == null || role.equals("user")) {
    response.sendRedirect("../login.jsp");
    }
    %>

    <!-- Nav bar -->
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
                </ul>
                <div class="d-flex nav-item dropdown">
                    <button class="nav-link active dropdown-toggle" href="#" type="navbarDropdown" id="user-dropdown" role="button" data-bs-toggle="dropdown" data-bs-target="#user-dropdown" aria-expanded="false">
                        <i class="bi-person-circle"></i>
                    </button> 
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="user-dropdown">
                        <li><a class="dropdown-item" href="../logout.jsp">Log Out</a></li>
                    </ul>
                </div>
            </div>


        </div>
    </nav>

    <div align="center">
        <h1>
            All Flights
        </h1>
    </div>

    <!-- Buttons for Toggling Modals -->
    <div class="container">
        <div class="buttons text-center">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-flight-modal" id="add-flight-btn">Add Flight</button>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#update-flight-modal" id="update-flight-btn" disabled>Update Flight</button>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#remove-flight-modal" id="remove-flight-btn" disabled>Remove Flight</button>
        </div>
    </div>

    <!-- Add Flight Modal -->
    <div id="add-flight-modal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title" id="add-flight-modal-label">Add Flight</h5>
                    <!-- Close Button -->
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </button>
                </div>

                <form id="add-flight-form" method="post">
                    <input type="hidden" name="ACTION" value="add-flight" role="form">
                    <div class="modal-body row g-3">				
                        <div class="col-md-6">
                            <label for="departureLoc" class="form-label">Departure</label>
                            <input type="text" class="form-control" id="add-flight-departureLoc" name="departureLoc" required>
                        </div>

                        <div class="col-md-6">
                            <label for="arrivalLoc" class="form-label">Arrival</label>
                            <input type="text" class="form-control" id="add-flight-arrivalLoc" name="arrivalLoc" required>
                        </div>

                        <div class="col-md-12">
                            <label for="Time" class="form-label">When?</label>
                            <div class="input-group">
                                <span class="input-group-text col-md-2">From</span>
                                <input type="datetime-local" class="form-control" id="departure-time" name="departureTime" required>
                            </div>
                            <div class="input-group has-validation">
                                <span class="input-group-text col-md-2">To</span>
                                <input type="datetime-local" class="form-control" id="arrival-time" name="arrivalTime" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label for="Capcity" class="form-label">Capacity</label>
                            <input type="number" class="form-control" id="capacity-in" name="capacity" min="0" required>
                        </div>

                        <div class="col-md-6">
                            <label for="price" class="form-label">Price</label>
                            <input type="number" step="0.01" class="form-control" id="price-in" name="price" min="0" required>
                        </div>			
                    </div>

                    <div class="modal-footer">					
                        <button type="button" class="btn btn-default" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success"  id="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <!-- Update Flight Modal -->
    <div id="update-flight-modal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title" id="update-flight-modal-label">Update Flight</h5>
                    <!-- Close Button -->
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </button>
                </div>

                <form id="update-flight-form" method="post">
                    <input type="hidden" name="ACTION" value="update-flight" role="form">
                    <div class="modal-body row g-3">
                        <div class = "col-md-12">
                            <label for="flightID" class="form-label"></label>
                            <input type="text" class="form-control" id="flightID" name="flightID" hidden required>
                        </div>

                        <div class="col-md-6">
                            <label for="departureLoc" class="form-label">Departure</label>
                            <input type="text" class="form-control" id="update-flight-departureLoc" name="departureLoc" required>
                        </div>

                        <div class="col-md-6">
                            <label for="arrivalLoc" class="form-label">Arrival</label>
                            <input type="text" class="form-control" id="update-flight-arrivalLoc" name="arrivalLoc" required>
                        </div>

                        <div class="col-md-12">
                            <label for="time" class="form-label">When?</label>
                            <div id="time" class="input-group">
                                <span class="input-group-text col-md-2">From</span>
                                <input type="datetime-local" class="form-control" id="departureTime" name="departureTime" required>
                            </div>
                            <div class="input-group">
                                <span class="input-group-text col-md-2">To</span>
                                <input type="datetime-local" class="form-control" id="arrivalTime" name="arrivalTime" required>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <label for="capcity-in" class="form-label">Capacity</label>
                            <input type="number" class="form-control" id="capacity-in" name="capacity" required>
                        </div>

                        <div class="col-md-4">
                            <label for="capcity-in" class="form-label">Flights Booked</label>
                            <input type="number" class="form-control" id="num-flights-booked" name="num-flights-booked" readonly>
                        </div>

                        <div class="col-md-4">
                            <label for="price-in" class="form-label">Price</label>
                            <input type="number" step="0.01" class="form-control" id="price-in" name="price" min="0" required>
                        </div>			
                    </div>

                    <div class="modal-footer">					
                        <button type="button" class="btn btn-default" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success"  id="submit">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Remove Flight Modal -->
    <div id="remove-flight-modal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-xl modal-dialog-scrollable">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title" id="remove-flight-modal-label">Remove Flight</h5>
                    <!-- Close Button -->
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </button>
                </div>

                <form id="remove-flight-form" method="post">
                    <input type="hidden" name="ACTION" value="remove-flight" role="form">
                    <div class="modal-body row g-3">
                        <div class="container-fluid">
                            <div class="alert alert-warning d-flex align-items-center" role="alert">
                                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                <div>
                                    You are removing a flight
                                </div>
                            </div>

                            <div class = "col-md-12 text-center">
                                <p id="confirm-question">Do you want to permanently removing the following flight?</p>
                                <input type="text" class="form-control" id="flightID" name="flightID" hidden required>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">					
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">No</button>
                        <button type="submit" class="btn btn-success"  id="submit">Yes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Showing all flights  -->
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
                        (ArrayList<Flight>)request.getAttribute("flight-data");
                        if (flights.isEmpty()) {%>
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
    <script type="text/javascript" src="../script/airport_search/search.js"></script>
    <script type="text/javascript" src="../script/airport_search/main.js"></script>
    <script type="text/javascript" src="../script/flight-manager.js"></script>

    <!-- Add airport search for the input elements -->
    <script>
        AirportInput("#add-flight-departureLoc");
        AirportInput("#add-flight-arrivalLoc");
        AirportInput("#update-flight-departureLoc");
        AirportInput("#update-flight-arrivalLoc");
    </script>
</body>
</html>
