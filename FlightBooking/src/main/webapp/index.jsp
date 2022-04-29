<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cs411.flightbooking.models.Flight"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootsrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">        

    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />

    <!-- CSS style sheets -->
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/style.css">

    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- For alerting symbols -->
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
    </svg>

    <title>Welcome to Flight Optimizer</title>
</head>

<body id="page top">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
        <div class="container px-4 px-lg-5">
            <a class="navbar-brand" href="index.jsp">Flight Booking</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                Menu
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
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

    <!-- Masthead-->
    <header class="masthead">
        <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
            <div class="d-flex justify-content-center">
                <div class="text-center">
                    <h1 class="mx-auto my-0 text-uppercase">CS411 Flight Booker</h1>
                    <h2 class="text-white-50 mx-auto mt-2 mb-5">A place to book your flight ticket </h2>
                    <a class="btn btn-primary" href="#search-flight-section">Search Flight</a>
                </div>
            </div>
        </div>
    </header>

    <section class="about-section text-center" id="search-flight-section">
        <!--<div class="jumbotron d-flex align-items-center min-vh-100">-->
        <form action="search-flight" method="post">    
            <div class="container px-4 px-lg-5" id="flight-search-module">

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

                    <br><button id="button" class="btn btn-primary" type="submit" onclick="flight.submitValues(event)">Search</button>

                </div>    
        </form>
        <!--</div>-->

    </section>


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
    </script>

</body>
</html>