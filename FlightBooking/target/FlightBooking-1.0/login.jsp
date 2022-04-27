<%-- 
    Document   : login.jsp
    Created on : Mar 31, 2022, 5:06:54 PM
    Author     : minhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">        
        <link rel="stylesheet" href="./css/register_form.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Booker</title>
    </head>
    <body>        
        <div class="jumbotron d-flex align-items-center min-vh-100">
            <div class="container">
                <h1>Log In</h1>
                <hr></hr>

                <form action="login" class="row g-3 needs-validation" method="post">                    
                    <div class="col-12">
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="email-icon" style="background-color: #3a57af "><i class="bi-envelope" style="color: white"></i></span>
                            <input type="text" class="form-control" id="email-in" aria-describedby="email-icon" name="email" placeholder="Email" required>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="password-icon" style="background-color: #3a57af "><i class="bi bi-shield-shaded" style="color: white"></i></span>
                            <input type="password" class="form-control" id="password-in" aria-describedby="last-name-icon" name="password" placeholder="Password" required>
                        </div>
                    </div>

                    <div class="col-12 text-center">
                        <button type="submit" class="btn btn-primary text" id="log-in-btn" value="register">Log In</button>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script> 

        <!-- For refreshing page -->
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
    </body>
</html>
