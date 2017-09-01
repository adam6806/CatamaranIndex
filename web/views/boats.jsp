<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.github.adam6806.catamaranindex.database.model.BoatEntity" %>
<%@ page import="com.github.adam6806.catamaranindex.database.model.ImageEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 8/20/2017
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Catamaran Index</title>
        <script type="text/javascript" src="<c:url value='/resources/jquery-3.2.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/jquery-ui/jquery-ui.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/DataTables/datatables.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/featherlight/featherlight.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/featherlight/featherlight.gallery.js'/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui/jquery-ui.theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/DataTables/datatables.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/featherlight/featherlight.css'/>"/>
        <link rel="stylesheet" type="text/css"
              href="<c:url value='/resources/featherlight/featherlight.gallery.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/boats.css'/>"/>
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
                    <th>Year</th>
                    <th>Make/Model</th>
                    <th>Location</th>
                    <th>Description</th>
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
                    <th>Year</th>
                    <th>Make/Model</th>
                    <th>Location</th>
                    <th>Description</th>
                    <th>Image</th>
                </tr>
            </tfoot>
            <tbody>
                <%
                    List<BoatEntity> boats = (List<BoatEntity>) request.getAttribute("boats");
                    for (BoatEntity boat : boats) {
                        if (boat.getActive()) {
                        int dougRating = boat.getDougRating();
                        int adamRating = boat.getAdamRating();
                        int currentRating = dougRating + adamRating;
                        String username = (String) request.getAttribute("username");
                        int userRating;
                        if (username.equals("adam")) {
                            userRating = adamRating;
                        } else {
                            userRating = dougRating;
                        }%>

                <tr>
                    <td>
                        <select id="<%out.print(boat.getId());%>" class="rating" name="rating">
                            <option<%if (userRating == 0) out.print(" selected");%>>0</option>
                            <option<%if (userRating == 1) out.print(" selected");%>>1</option>
                            <option<%if (userRating == 2) out.print(" selected");%>>2</option>
                            <option<%if (userRating == 3) out.print(" selected");%>>3</option>
                            <option<%if (userRating == 4) out.print(" selected");%>>4</option>
                            <option<%if (userRating == 5) out.print(" selected");%>>5</option>
                            <option>Remove</option>
                        </select>
                    </td>
                    <td id="combinedRating<%out.print(boat.getId());%>"><%out.print(currentRating);%></td>
                    <td><%out.print(boat.getTimestamp());%></td>
                    <td>$<%out.print(boat.getPrice());%></td>
                    <td><%out.print(boat.getLength());%>ft</td>
                    <td><%out.print(boat.getYear());%></td>
                    <td><%out.print(boat.getMakeModel());%></td>
                    <td><%out.print(boat.getLocation());%></td>
                    <td>
                        <button id="descriptionButton<%out.print(boat.getId());%>" value="<%out.print(boat.getId());%>"
                                class="descriptionButton">Description
                        </button>
                        <br>
                        <br>
                        <a href="<%out.print(boat.getUrl());%>" target="_blank">Boat Page</a>
                    </td>
                    <td>
                        <div id="gallery<%out.print(boat.getId());%>" data-featherlight-gallery
                             data-featherlight-filter="a">
                            <%
                                boolean first = true;
                                for (ImageEntity image : boat.getImages()) {
                                    if (first) {
                            %><a href="<%out.print(image.getUrl());%>"><img src="<%out.print(image.getUrl());%>"
                                                                            class="boatImages"/></a><%
                            first = false;
                        } else {
                        %><a href="<%out.print(image.getUrl());%>"></a><%
                                }
                            }
                        %>
                        </div>
                        <script>
                            $('#gallery<%out.print(boat.getId());%>').featherlightGallery({
                                previousIcon: '&#9664;',
                                nextIcon: '&#9654;',
                                galleryFadeIn: 0,
                                galleryFadeOut: 0,
                                openSpeed: 0,
                                closeSpeed: 0
                            });
                        </script>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                <script>
                    $(document).ready(function () {
                        $('#boats').on('draw.dt', function () {
                            $('.boatImages').css("display", "block");
                        });
                        $('#boats').DataTable({
                            "pageLength": 100,
                            "columnDefs": [
                                {"width": "400", "targets": 9}
                            ]
                        });
                    });
                    $('.rating').on('change', function () {
                        var id = this.id;
                        var username = '<%out.print(request.getAttribute("username"));%>';
                        var userRating = $('#' + id).val();
                        if (userRating == 'Remove') {
                            $.ajax({
                                type: "POST",
                                url: "Boats/remove",
                                data: {
                                    id: id
                                },
                                success: function (data) {
                                    var id = data.id;
                                    var table = $('#boats').DataTable();
                                    table.row($('#combinedRating' + id).parents('tr')).remove().draw();
                                }
                            });
                        } else {
                            $.ajax({
                                type: "POST",
                                url: "Boats/setrating",
                                data: {
                                    id: id,
                                    username: username,
                                    rating: userRating
                                },
                                success: function (data) {
                                    var combinedRating = data.combinedRating;
                                    var id = data.id;
                                    var table = $('#boats').DataTable();
                                    table.cell('#combinedRating' + id).data(combinedRating).draw();
                                }
                            });
                        }
                    });
                    $('.descriptionButton').on('click', function () {
                        $(".ui-dialog-content").dialog("close");
                        var id = $(this).val();
                        $.ajax({
                            type: "POST",
                            url: "Boats/getDescription",
                            data: {
                                id: id
                            },
                            success: function (data) {
                                var description = data.description;
                                var id = data.id;
                                $('<p>' + description + '</p>').dialog({
                                    width: 650,
                                    position: {
                                        my: "center",
                                        at: "center",
                                        of: $('#descriptionButton' + id).parents('tr')
                                    },
                                });
                            }
                        });
                    });
                </script>
            </tbody>
        </table>
    </body>
</html>