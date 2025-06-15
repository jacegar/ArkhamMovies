<%@ page import="es.uma.taw.arkhammovies.dto.MovieCharacterDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <%
    MovieCharacterDTO character = (MovieCharacterDTO) request.getAttribute("character");
  %>
<head>
  <link rel="stylesheet" type="text/css" href="../../css/common.css">
  <link rel="stylesheet" type="text/css" href="../../css/movie.css">
  <title><%=character.getName()%></title>
</head>
  <body>
  <jsp:include page="header.jsp" />
  <main>
    <h1><%=character.getName()%></h1>
    <div class="movie-container">
      <a href="/movies/character?id=<%=character.getId()%>"><img src="<%=character.getPhotoUrl()%>" alt="Foto de <%=character.getName()%>" width="400" height="600"></a>
      <div class="movie-details">
        <h2>Nombre:</h2>
        <p><%=character.getName()%></p>
        <h2>Apellido:</h2>
        <p><%=(character.getSurname1().isEmpty() && character.getSurname2().isEmpty()) ? "Desconocido":character.getSurname1() + " " + character.getSurname2()%></p>
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
