<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 8/20/2017
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catamaran Index</title>
    <script type="text/javascript" src="<c:url value='/resources/jquery-ui/jquery-ui.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.min.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.theme.min.css'/>"/>
</head>
<body>
    <FORM METHOD=POST ACTION="Boats">
        What's your name?
        <select id="username" name="username">
            <option value="adam">Adam</option>
            <option value="doug">Doug</option>
        </select>
        <br>
        <P><INPUT TYPE=SUBMIT>
</FORM>
</body>
</html>
