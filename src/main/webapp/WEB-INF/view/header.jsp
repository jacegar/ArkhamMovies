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
        <h1>Arkham Movies</h1>
        <%
            if(user == null){
        %>
        <div class="login-container">
            <form method="post" action="/user/login">
                <input type="submit" value="Iniciar sesión">
            </form>
            <form method="post" action="/user/register">
                <input type="submit" value="Registrarse">
            </form>
        </div>
        <%} else {%>
        <div class="user-container">
            <form method="post" action="/user/${user.getNickname()}">
                <input type="submit" value="<%= user.getNickname() %>">
            </form>
        </div>
        <%}%>
    </header>
</body>
</html>
