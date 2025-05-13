<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/profile.css">
</head>
<%
    UserDTO user = (UserDTO)session.getAttribute("user");
    List<MovieDTO> likedMovies = (List<MovieDTO>)request.getAttribute("likedMovies");
    List<MovieDTO> savedMovies = (List<MovieDTO>)request.getAttribute("savedMovies");
%>
<body>
    <header>
        <h1>Bienvenido, <%= user.getNickname() %></h1>
        <% if (user.getRole() == 0) { %>
        <form method="post" action="/user/vetar">
            <div class="ban-button">
                <button>Vetar</button>
            </div>
        </form>
        <% } %>
    </header>

    <h2>Lista de películas que te gustan</h2>
    <ul>
        <% for (MovieDTO m : likedMovies) { %>
        <li>
            <img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300">
            <a href="/movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
        </li>
        <% } %>
    </ul>

    <h2>Lista de películas guardadas</h2>
    <ul>
        <% for (MovieDTO m : savedMovies) { %>
        <li>
            <img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300">
            <a href="/movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
        </li>
        <% } %>
    </ul>
    <div class="button-container">
        <form method="post" action="/user/logout">
            <button>Cerrar Sesión</button>
        </form>
        <form method="post" action="/user/atras">
            <button class="back-button">Volver</button>
        </form>
    </div>
</body>
</html>
