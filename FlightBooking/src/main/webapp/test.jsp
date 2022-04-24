<%-- 
    Document   : test
    Created on : Apr 23, 2022, 10:58:28 PM
    Author     : minhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
        <link rel="stylesheet" href="./css/style.css">
        
        <title>JSP Page</title>
    </head>
    <body>
        <div class="form-group">
            <label class="control-label">Enter an Airport</label>
            <input id="test" type="text" />
        </div>

        <script src="https://cdn.jsdelivr.net/npm/fuse.js@6.5.3"></script>
        <script type="text/javascript" src="./script/airport_search/search.js"></script>
        <script type="text/javascript" src="./script/airport_search/main.js"></script>
        <script>
            // import {AirportInput} from {index.js};
            AirportInput("#test");
        </script>
    </body>


</html>
