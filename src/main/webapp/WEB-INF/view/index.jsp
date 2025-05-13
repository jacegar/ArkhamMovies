<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: juana
  Date: 05/04/2025
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/index.css">
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Arkham Movies</title>
</head>
<%
    //Cargamos el usuario, que sera null si no está la sesión iniciada
    UserDTO user = (UserDTO) session.getAttribute("user");
    List<MovieDTO> popularMovies = (List<MovieDTO>) request.getAttribute("popularMovies");
    List<MovieDTO> recentMovies = (List<MovieDTO>) request.getAttribute("recentMovies");
%>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <%
            if (user!=null && user.getRole()==0){
        %>
        <div class="center-container">
            <a href="/movie/new" class="add-movie-button">Agregar una película</a>
        </div>
        <%
            }
        %>
        <div>
            <h2>Busca una película, actor, personaje...</h2>
            <form method="post" action="/movies/moviesbyTitle">
                <input type="text" name="title">
                <button>Buscar</button>
            </form>
        </div>
        <div class="popular">
            <div class="list-header">
                <h1>Más populares</h1>
                <a href="/movies/list?criteria=0">Ver más</a>
            </div>
            <ul>
            <%
                if(!(popularMovies == null) && !popularMovies.isEmpty()){
                    for(MovieDTO m : popularMovies){
                    %>
                    <li>
                        <a href="/movies/movie?id=<%=m.getId()%>"> <img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300"></a>
                        <a href="movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
                    </li>
                <%}
                }else{%>
                    <li>No hay películas disponibles, perdon por las molestias.</li>
                <%}%>
            </ul>
        </div>

        <%
            //Si no ha iniciado sesion no podemos recomendarle películas
            //todo implementar algoritmo recomendacion
            if(user != null){
        %>
        <div class="recommended">
            <div class="list-header">
                <h1>Para ti</h1>
                <a href="/movies/list?criteria=1">Ver más</a>
            </div>
            <ul>
                <%
                    //Coge la lista anterior, reemplazar cuando tengamos crud de usuario hecho
                    if(!(popularMovies == null) && !popularMovies.isEmpty()){
                        for(MovieDTO m : popularMovies){
                %>
                <li>
                    <a href="/movies/movie?id=<%=m.getId()%>"><img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300"></a>
                    <a href="movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
                </li>
                <%}
                }else{%>
                <li>No hay películas disponibles, perdon por las molestias.</li>
                <%}%>
            </ul>
        </div>
        <%}%>

        <div class="recent">
            <div class="list-header">
                <h1>Más recientes</h1>
                <a href="/movies/list?criteria=2">Ver más</a>
            </div>
            <ul>
                <%
                    if(!(recentMovies == null) && !recentMovies.isEmpty()){
                        for(MovieDTO m : recentMovies){
                %>
                <li>
                    <a href="/movies/movie?id=<%=m.getId()%>"><img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300"></a>
                    <a href="movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
                </li>
                <%}
                }else{%>
                <li>No hay películas disponibles, perdon por las molestias.</li>
                <%}%>
            </ul>
        </div>
    </main>

</body>
</html>
