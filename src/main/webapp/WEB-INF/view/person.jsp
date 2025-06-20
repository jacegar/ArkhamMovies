<%@ page import="es.uma.taw.arkhammovies.dto.PersonDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieCharacterDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    PersonDTO person = (PersonDTO) request.getAttribute("person");
    UserDTO user = (UserDTO) session.getAttribute("user");
    List<MovieCharacterDTO> characters = (List<MovieCharacterDTO>) request.getAttribute("characters");
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
                <h2>Género:</h2>
                <p><%=person.getGender()=='M'?"Masculino":"Femenino"%></p>
                <h2>Edad:</h2>
                <p><%=person.getAge()%> años</p>
            </div>

            <% if(characters != null && !characters.isEmpty()){ %>
            <div class="movie-characters">
                <h2>Personajes:</h2>
                <ul>
                    <%for(MovieCharacterDTO character : characters){%>
                    <li>
                        <a href="/characters/character?id=<%=character.getId()%>"><img src="<%=character.getPhotoUrl()%>" alt="Foto de <%=character.getName()%>"></a>
                        <a href="/characters/character?id=<%=character.getId()%>"><%=character.getName()%></a>
                    </li>
                    <%}%>
                </ul>
            </div>
            <% } %>

        </div>

        <div class="action-buttons">
            <%
                if (user!=null && user.getRole()==0){
            %>
            <a href="/people/edit?id=<%= person.getId() %>" class="edit-button">Editar</a>
            <form method="post" action="/people/delete">
                <input type="hidden" name="id" value="<%= person.getId() %>" />
                <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar <%=person.getGender()=='M'?"el actor":"la actriz"%> <%= person.getName() %>?')">Borrar</button>
            </form>
            <%
                }
            %>
            <form method="post" action="/user/atras">
                <button class="back-button">Volver</button>
            </form>
        </div>
    </main>
</body>
</html>
