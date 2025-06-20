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
    UserDTO user = (UserDTO)request.getAttribute("user");
    UserDTO userSession = (UserDTO)session.getAttribute("user");
    List<MovieDTO> likedMovies = (List<MovieDTO>)request.getAttribute("likedMovies");
    List<MovieDTO> savedMovies = (List<MovieDTO>)request.getAttribute("savedMovies");

    int minutos = 0;
    for (MovieDTO m : likedMovies){
        minutos+= m.getRuntime()==null?0:m.getRuntime();
    }
%>
<body>
    <header>
        <h1><%= user.getNickname() %></h1>
        <% if (userSession != null && userSession.getRole() == 0) { %>
        <form method="post" action="/user/vetar">
            <div class="ban-button">
                <button>Vetar</button>
            </div>
        </form>
        <% } %>
    </header>

    <h2>Información del usuario</h2> <br/>
    <p><strong>Email:</strong> <%= user.getEmail() %> </p>
    <p><strong>Nickname:</strong> <%= user.getNickname() %> </p>
    <p><strong>Total de minutos vistos:</strong> <%= minutos %></p> <br/>

    <h2>Peliculas favoritas</h2>
    <ul>
        <% for (MovieDTO m : likedMovies) { %>
        <li>
            <a href="/movies/movie?id=<%=m.getId()%>"><img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300"></a>
            <a href="/movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
            <% if (userSession != null && userSession.getId() == user.getId()) { %>
            <a href="/movies/flipLike?movieId=<%=m.getId()%>&tipo=false">
                <img src="https://img.icons8.com/m_two_tone/512/filled-trash.png" alt="Delete" width="24" height="24">
            </a>
            <%
                }
            %>
        </li>
        <% } %>
    </ul>

    <h2>Películas guardadas</h2>
    <ul>
        <% for (MovieDTO m : savedMovies) { %>
        <li>
            <a href="/movies/movie?id=<%=m.getId()%>"><img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300"></a>
            <a href="/movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
            <% if (userSession != null && userSession.getId() == user.getId()) { %>
                <a href="/movies/flipSave?movieId=<%=m.getId()%>&tipo=false">
                    <img src="https://img.icons8.com/m_two_tone/512/filled-trash.png" alt="Delete" width="24" height="24">
                </a>
            <%
                }
            %>
        </li>
        <% } %>
    </ul>
    <div class="button-container">
        <% if (userSession != null && userSession.getId().equals(user.getId())) { %>
        <form method="post" action="/user/logout">
            <button>Cerrar Sesión</button>
        </form>
        <% } %>
        <form method="post" action="/user/atras">
            <button class="back-button">Volver</button>
        </form>
        <form method="get" action="/user/estadisticas">
            <input type="hidden" name="nickname" value="<%= user.getNickname() %>" />
            <button type="submit">Estadísticas</button>
        </form>
    </div>
</body>
</html>
