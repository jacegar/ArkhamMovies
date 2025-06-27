<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" type="text/css" href="../../css/header.css">
</head>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
%>
<body>
    <header>
        <h1><a href="/">Arkham Movies</a></h1>
        <div class = "wrapperEnlaces">
            <h2><a href="/">Películas</a></h2>
            <h2><a href="/characters/inicio">Personajes</a></h2>
            <h2><a href="/people/inicio">Personas</a></h2>
            <h2><a href="/user/inicio">Usuarios</a></h2>
            <%
                if (user != null && user.getRole() < 2) {
            %>
                <h2><a href="/keywords/inicio">Keywords</a></h2>
            <%
                }
            %>
        </div>
        <%
            if(user == null){
        %>
        <div class="login-container">
            <a href="/user/login" class="button-link">Iniciar sesión</a>
            <a href="/user/register" class="button-link">Registrarse</a>
        </div>
        <%} else {%>
        <div class="user-container">
            <a href="/user/${user.getNickname()}" class="button-link"><%= user.getNickname() %></a>
        </div>
        <%}%>
    </header>
</body>
</html>
