<%@ page import="es.uma.taw.arkhammovies.entity.Person" %>
<%@ page import="es.uma.taw.arkhammovies.dto.PersonDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    PersonDTO person = (PersonDTO) request.getAttribute("person");
    UserDTO user = (UserDTO) session.getAttribute("user");
%>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <title><%=person.getName()%></title>
</head>
<body>
<jsp:include page="header.jsp" />
<main>
    <%
        if (user!=null && user.getRole()==0){
    %>
    <div class="center-container">
        <a href="/characters/new?personId=<%=person.getId()%>" class="add-movie-button">Agregar un personaje</a>
    </div>
    <%
        }
    %>
    <h1><%=person.getName()%></h1>
    <div class="movie-container">
        <a href="/people/person?id=<%=person.getId()%>"><img src="<%=person.getPhotoUrl()%>" alt="Foto de <%=person.getName()%>" width="400" height="600"></a>
        <div class="movie-details">
            <h2>Nombre:</h2>
            <p><%=person.getName()%></p>
            <%if(person.getSurname1() != null && person.getSurname2() != null){%>
            <h2>Apellido:</h2>
            <p><%=(person.getSurname1().isEmpty() && person.getSurname2().isEmpty()) ? "Desconocido":person.getSurname1() + " " + person.getSurname2()%></p>
            <%}%>
        </div>
    </div>
    <div class="action-buttons">
        <form method="post" action="/user/atras">
            <button class="back-button">Volver</button>
        </form>
    </div>
</main>
</body>
</html>
