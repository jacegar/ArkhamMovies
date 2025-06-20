<%@ page import="es.uma.taw.arkhammovies.dto.MovieCharacterDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.PersonDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <!--Autor: Juan Acevedo García 40% -->
  <%
    MovieCharacterDTO character = (MovieCharacterDTO) request.getAttribute("character");
    UserDTO user = (UserDTO) session.getAttribute("user");
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
    PersonDTO person = (PersonDTO) request.getAttribute("person");
  %>
<head>
  <link rel="stylesheet" type="text/css" href="../../css/common.css">
  <link rel="stylesheet" type="text/css" href="../../css/movie.css">
  <link rel="stylesheet" type="text/css" href="../../css/character.css">
  <title><%=character.getName()%></title>
</head>
<body>
  <jsp:include page="header.jsp" />
  <main>
    <h1><%=character.getName()%></h1>
    <div class="character-container">
      <a href="/characters/character?id=<%=character.getId()%>"><img src="<%=character.getPhotoUrl()%>" alt="Foto de <%=character.getName()%>" width="400" height="600"></a>
      <div class="movie-details">
        <div class="name-container-character">
          <h2>Nombre:</h2>
          <p><%=character.getName()%></p>
          <h2>Apellido:</h2>
          <p><%=(character.getSurname1()==null && character.getSurname2()==null) ? "Desconocido":character.getSurname1() + " " + character.getSurname2()%></p>
        </div>

          <div class="movie-container-character">
            <h2>Aparece en:</h2>
            <a href="/movies/movie?id=<%=movie.getId()%>"> <img src="<%=movie.getPhotoUrl()%>" alt="Foto de <%=movie.getTitle()%>" width="140" height="210"></a>
            <a href="/movies/movie?id=<%=movie.getId()%>"><%=movie.getTitle()%></a>
          </div>

          <%if(person != null){%>
          <div class="person-container-character">
            <h2>Interpretado por:</h2>
            <a href="/people/person?id=<%=person.getId()%>"><img src="<%=person.getPhotoUrl()%>" alt="Foto de <%=person.getName()%>" width="140" height="210"></a>
            <a href="/people/person?id=<%=person.getId()%>"><%=person.getName()%></a>
          </div>
          <%}%>
        </div>
      </div>

    <div class="action-buttons">
      <%
        if (user!=null && user.getRole()==0){
      %>
      <a href="/characters/edit?id=<%= character.getId() %>" class="edit-button">Editar</a>
      <form method="post" action="/characters/delete">
        <input type="hidden" name="id" value="<%= character.getId() %>" />
        <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar el personaje' +
                ' <%= character.getName() %>?')">Borrar</button>
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
