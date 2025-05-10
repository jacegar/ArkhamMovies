<%@ page import="java.util.Locale" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: juana
  Date: 26/04/2025
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        MovieDTO movie = (MovieDTO) request.getAttribute("movie");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(movie.getReleaseDate());
    %>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <title><%=movie.getTitle()%></title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <h1><%=movie.getTitle()%></h1>
        <!--Aqui seria lo tipico que la imagen está a la izquierda y todos los datos a la derecha, pero falta el css -->
        <div>
            <img src="<%=movie.getPhotoUrl()%>" alt="Foto de <%=movie.getTitle()%>" width="400" height="600">
            <p><%=movie.getTagline()%></p>
            <div>
                <h2>Descripción</h2>
                <p>
                    <%=movie.getOverview()%>
                </p>
                <h3>Presupuesto</h3>
                <p>
                    <%=movie.getBudget()%> €
                </p>
                <h3>Fecha de estreno</h3>
                <p>
                    <%=date%>
                </p>
                <%if(!movie.getHomepage().equals("N/A")){%>
                    <a href="<%=movie.getHomepage()%>">Página de la película</a>
                <%}%>
            </div>
        </div>
    </main>
</body>
</html>
