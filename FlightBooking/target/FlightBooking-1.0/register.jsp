<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div align="center">
            <h1> User Registration </h1>
            <form action="register" method="post">
                <table>
                    <tr>
                        <td><label for="firstName">First Name</label></td>
                        <td><input type="text" name="firstName" /></td>
                    </tr>
                    
                    <tr>
                        <td><label for="lastName">Last Name</label></td>
                        <td><input type="text" name="lastName" /></td>
                    </tr>
                    
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input type="email" name="email" /></td>
                    </tr>
                    
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                </table>
                
                <input type="submit" value="Register">
            </form>
        </div>
    </body>
</html>
