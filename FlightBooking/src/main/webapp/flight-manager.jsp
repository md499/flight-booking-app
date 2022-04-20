<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cs411.flightbooking.models.Flight"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <title>Flight Manager</title>
    </head>
    <body>
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
                <button type="button" class="btn btn-primary" id="remove-flight-btn" disabled>Remove Flight</button>
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
                                <input type="text" class="form-control" id="Location" name="departureLoc" required>
                            </div>

                            <div class="col-md-6">
                                <label for="arrivalLoc" class="form-label">Arrival</label>
                                <input type="text" class="form-control" id="Location" name="arrivalLoc" required>
                            </div>

                            <div class="col-md-12">
                                <label for="Time" class="form-label">When?</label>
                                <div class="input-group">
                                    <span class="input-group-text col-md-2">From</span>
                                    <input type="datetime-local" class="form-control" id="time-in" name="departureTime" required>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-text col-md-2">To</span>
                                    <input type="datetime-local" class="form-control" id="time-in" name="arrivalTime" required>
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
                        <h5 class="modal-title" id="add-flight-modal-label">Update Flight</h5>
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
                                <input type="text" class="form-control" id="departureLoc" name="departureLoc" required>
                            </div>

                            <div class="col-md-6">
                                <label for="arrivalLoc" class="form-label">Arrival</label>
                                <input type="text" class="form-control" id="arrivalLoc" name="arrivalLoc" required>
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
                    <%}%>

                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>

        <script type="text/javascript" src="../script/flight-manager.js"></script>

    </body>
</html>
