<%@ page import="es.uma.taw.arkhammovies.entity.User" %>
<%@ page import="es.uma.taw.arkhammovies.entity.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: juana
  Date: 05/04/2025
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    //Cargamos el usuario, que sera null si no está la sesión iniciada
    User user = (User) session.getAttribute("user");
    List<Movie> recommendedMovies = (List<Movie>) request.getAttribute("recommendedMovies");
%>
<body>
    <header>
        <h1>Arkham Movies</h1>
        <%
            if(user == null){
        %>
            <!--Por ahora sin acciones porque no están implementadas-->
            <form>
                <input type="submit" value="Iniciar sesión">
            </form>
            <form>
                <input type="submit" value="Registrarse">
            </form>
        <%}%>
    </header>
    <main>
        <div>
            <h2>Busca una película, actor, personaje...</h2>
            <form method="post">
                <input type="text" value="">
                <input type="submit" value="Buscar">
            </form>
        </div>
        <div>
            <h1>Más populares</h1>
            <a>Ver más</a>
        </div>
        <ul>
            <li>Aqui deberia ir una pelicula</li>
        </ul>
    </main>

</body>
</html>
