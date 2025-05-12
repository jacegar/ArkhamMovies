<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        boolean isLiked = (boolean) request.getAttribute("isLiked");
        boolean isSaved = (boolean) request.getAttribute("isSaved");
        UserDTO user = (UserDTO) session.getAttribute("user");
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
            <!-- Hay que añadir aqui el caso de que la sesion no este iniciada-->
            <%if(user != null){%>
            <div class="buttons-container">
                <a href="/movies/flipLike?movieId=<%=movie.getId()%>">
                    <%if(!isLiked){%>
                        <img src="https://icones.pro/wp-content/uploads/2021/02/icone-de-coeur-rouge.png" width="40px" height="40px"/>
                    <%}else{%>
                        <img src="https://cdn-icons-png.flaticon.com/512/4209/4209081.png" width="40px" height="40px"/>
                    <%}%>
                </a>
                <a href="/movies/flipSave?movieId=<%=movie.getId()%>">
                    <%if(!isSaved){%>
                        <img src="../../img/save-instagram.png" width="40px" height="40px"/>
                    <%}else{%>
                        <img src="../../img/bookmark.png" width="40px" height="40px"/>
                    <%}%>
                </a>
            </div>
            <%}%>

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
