<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" integrity="sha512-YDChav1pUAodyH1Ja7PIpEDUOoFROpZi5Lb7pY8+9+kU8UTr3J8SI8QO7SRuf4qdDKb5OI0xSt4Vk1wiYjBXgw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./css/register_form.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js" integrity="sha512-Vp2UimVVK8kNOjXqqj/B0Fyo96SDPj9OCSm1vmYSrLYF3mwIOBXh/yRZDVKo8NemQn1GUjjK0vFJuCSCkYai/A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <div class="jumbotron d-flex align-items-center min-vh-100">
            <div class="container">
                <h1>Registration</h1>
                <hr></hr>

                <form action="register" class="row g-3 needs-validation" method="post">                    
                    <div class="col-12">
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="first-name-icon" style="background-color: #3a57af "><i class="bi bi-person" style="color: white"></i></span>
                            <input type="text" class="form-control" id="first-name-in" aria-describedby="first-name-icon" name="firstName" placeholder="First name" required>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="last-name-icon" style="background-color: #3a57af "><i class="bi bi-person" style="color: white"></i></span>
                            <input type="text" class="form-control" id="last-name-in" aria-describedby="last-name-icon" name="lastName" placeholder="Last name" required>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="email-icon" style="background-color: #3a57af "><i class="bi-envelope" style="color: white"></i></span>
                            <input type="email" class="form-control" id="email-in" aria-describedby="email-icon" name="email" placeholder="Email" required>
                            <%String error_msg = (String)request.getAttribute("registerError");
                 if (error_msg != null && !error_msg.equals("")){%>
                            <input type="text" id="email-error-msg" hidden value="<%=error_msg%>">
                            <script>
                                let input = $("#email-in");
                                let error = $("#email-error-msg");
                                let error_msg = error.val();
                                alert(error_msg);

                                input.val("");
                                error.val("");
                            </script>
                            <%} request.setAttribute("registerError", "");%>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="password-icon" style="background-color: #3a57af "><i class="bi bi-shield-shaded" style="color: white"></i></span>
                            <input type="password" class="form-control" id="password-in" aria-describedby="last-name-icon" name="password" placeholder="Password" required>
                        </div>
                    </div>

                    <div class="col-8">
                        <p>By clicking Sign Up, you agree on our <a href="#">terms and condition</a>.</p>
                    </div>
                    <div class="col-4 text-right" style="float: right">
                        <button type="submit" class="btn btn-primary" id="sign-up-btn" value="register">Sign Up</button>
                    </div>
                </form>
                <hr></hr>
                <div class="col-12 text-center">
                    <p> Already has an account? Log in <a href="./login">here</a></p>
                </div>
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
