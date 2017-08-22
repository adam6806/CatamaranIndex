<%@ page import="com.github.adam6806.catamaranindex.boat.Boat" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 8/20/2017
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="boatrequest" class="com.github.adam6806.catamaranindex.boat.BoatRequest" scope="session"/>
<jsp:setProperty name="boatrequest" property="*"/>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <%
            ArrayList<Boat> boats = boatrequest.getBoats();
            for (Boat boat : boats) {
                out.println(boat.getMakeModel());
            }
        %>

    </body>
</html>