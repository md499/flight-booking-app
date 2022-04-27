<%-- 
    Document   : logout
    Created on : Apr 26, 2022, 7:31:18 PM
    Author     : minhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        session.removeAttribute("role");
        session.invalidate();
        response.sendRedirect("");
    %>
</html>
