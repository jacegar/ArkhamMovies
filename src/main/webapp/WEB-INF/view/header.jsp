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
