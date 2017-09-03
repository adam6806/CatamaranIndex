<%@ page import="com.github.adam6806.catamaranindex.database.model.BoatEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.github.adam6806.catamaranindex.database.model.ImageEntity" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kendra's Laptop
  Date: 9/2/2017
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Boat Scraper Results</title>
    <script type="text/javascript" src="<c:url value='/resources/jquery-ui/jquery-ui.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.min.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.theme.min.css'/>"/>
</head>
<body>
    <table id ="boats" class="display", cellspacing="0" width="100%">
        <thead>
            <th>Boat Name</th>
            <th>Boat Site</th>
            <th>First Image URL Source</th>
        </thead>
        <tfoot>
            <th>Boat Name</th>
            <th>Boat Site</th>
            <th>First Image URL Source</th>
        </tfoot>
        <tbody>
            <%
                List<BoatEntity> boats = (List<BoatEntity>) request.getAttribute("scrapedBoats");
                for(BoatEntity boat : boats) {
                    String boatName = boat.getMakeModel();
                    String boatSite = boat.getUrl();
                    Set<ImageEntity> boatImgs = boat.getImages();
                    ImageEntity firstBoatImg = boatImgs.iterator().next();
                    String boatImgSrc = firstBoatImg.getUrl();
                    %>
                <tr>
                    <td><%out.print(boatName);%></td>
                    <td><a href="<%out.print(boatSite);%>">Boat Page</a></td>
                    <td><a href="<%out.print(boatImgSrc);%>">Boat Img Src</a></td>
                </tr>
            <%  }
            %>
        </tbody>
    </table>
</body>
</html>
