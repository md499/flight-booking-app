<%-- 
    Document   : login.jsp
    Created on : Mar 31, 2022, 5:06:54 PM
    Author     : minhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Booker</title>
    </head>
    <body>
    <div align="center">
            <h1> Login </h1>
            <form action="register" method="post">
                <table>
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                    
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td><input type="text" name="password" /></td>
                    </tr>
                </table>
                
                <input type="submit" value="Login">
            </form>
        </div>
    </body>
</html>
