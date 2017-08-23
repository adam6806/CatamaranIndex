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
<%
    String name = request.getParameter("username");
    session.setAttribute("username", name);
%>
<html>
    <head>
        <title>Catamaran Index</title>
        <script type="text/javascript" src="script/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="script/jquery-ui/jquery-ui.min.js" ;></script>
        <script type="text/javascript" src="script/DataTables/datatables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="script/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="script/jquery-ui/jquery-ui.theme.min.css"/>
        <link rel="stylesheet" type="text/css" href="script/DataTables/datatables.min.css"/>
    </head>
    <body>
        <table id="boats" class="display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Your Rating</th>
                    <th>Combined Rating</th>
                    <th>Date Added</th>
                    <th>Price</th>
                    <th>Length</th>
                    <th>Make/Model</th>
                    <th>Location</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>Your Rating</th>
                    <th>Combined Rating</th>
                    <th>Date Added</th>
                    <th>Price</th>
                    <th>Length</th>
                    <th>Make/Model</th>
                    <th>Location</th>
                    <th>Image</th>
                </tr>
            </tfoot>
            <tbody>
                <%
                    ArrayList<Boat> boats = boatrequest.getBoats();
                    for (Boat boat : boats) {
                        int dougRating = boat.getDougRating();
                        int adamRating = boat.getAdamRating();
                        int currentRating = dougRating + adamRating;
                        int userRating = boat.getRating((String) session.getAttribute("username"));
                %>
                <tr>
                    <td>
                        <select id="<%out.print(boat.getId());%>" class="rating" name="rating">
                            <option<%if (userRating == 0) out.print(" selected");%>>0</option>
                            <option<%if (userRating == 1) out.print(" selected");%>>1</option>
                            <option<%if (userRating == 2) out.print(" selected");%>>2</option>
                            <option<%if (userRating == 3) out.print(" selected");%>>3</option>
                            <option<%if (userRating == 4) out.print(" selected");%>>4</option>
                            <option<%if (userRating == 5) out.print(" selected");%>>5</option>
                        </select>
                    </td>
                    <td id="combinedRating<%out.print(boat.getId());%>"><%out.print(currentRating);%></td>
                    <td><%out.print(boat.getTimestamp());%></td>
                    <td>$<%out.print(boat.getPrice());%></td>
                    <td><%out.print(boat.getLength());%>ft</td>
                    <td><%out.print(boat.getMakeModel());%></td>
                    <td><%out.print(boat.getLocation());%></td>
                    <td><a href="<%out.print(boat.getLink());%>" target="_blank"><img
                            src="<%out.print(boat.getImage());%>"/></a></td>
                </tr>
                <%
                    }
                %>
                <script>
                    $(document).ready(function () {
                        $('#boats').DataTable({
                            "pageLength": 100,

                        });
                    });
                    $('.rating').on('change', function () {
                        var id = this.id;
                        var username = '<%out.print(session.getAttribute("username"));%>';
                        var userRating = $('#' + id).val();
                        $.ajax({
                            type: "POST",
                            url: "/Boats",
                            data: {
                                id: id,
                                username: username,
                                rating: userRating
                            },
                            success: function (data, status) {
                                var combinedRating = data.combinedRating;
                                var id = data.id;
                                var table = $('#boats').DataTable();
                                table.cell('#combinedRating' + id).data(combinedRating).draw();
                            }
                        });
                    });
                </script>
            </tbody>
        </table>
    </body>
</html>