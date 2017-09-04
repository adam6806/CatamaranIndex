<%@ page import="com.github.adam6806.catamaranindex.scraper.boatsite.BoatSiteFactory" %>
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
    <title>Test Boat Scraper</title>
    <script type="text/javascript" src="<c:url value='/resources/jquery-ui/jquery-ui.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.min.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.theme.min.css'/>"/>
</head>
<body>
    <FORM METHOD=POST ACTION="testScraperResults">
        Test What Scraper?
        <select id="scraper" name="scraper">
            <option value="<%out.print(BoatSiteFactory.YACHT_WORLD);%>">Yacht World</option>
        </select>
        <br>
        <P><INPUT TYPE=SUBMIT> </P>
    </FORM>
</body>
</html>
