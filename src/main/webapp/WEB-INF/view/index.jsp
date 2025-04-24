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
    <link rel="stylesheet" type="text/css" href="../../css/index.css">
    <title>Title</title>
</head>
<%
    //Cargamos el usuario, que sera null si no está la sesión iniciada
    User user = (User) session.getAttribute("user");
    List<Movie> popularMovies = (List<Movie>) request.getAttribute("popularMovies");
%>
<body>
    <header>
        <h1>Arkham Movies</h1>
        <%
            if(user == null){
        %>
            <!--Por ahora sin acciones porque no están implementadas-->
        <div class="login-container">
            <form>
                <input type="submit" value="Iniciar sesión">
            </form>
            <form>
                <input type="submit" value="Registrarse">
            </form>
        </div>
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
        <div class="tendency">
            <div class="tendency-title">
                <h1>Más populares</h1>
                <a>Ver más</a>
            </div>
            <ul>
            <%
                if(!popularMovies.isEmpty()){
                //Mostramos i películas
                    for(Movie m : popularMovies){
                    %>
                    <li>
                        <img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300">
                        <a><%=m.getTitle()%></a>
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
