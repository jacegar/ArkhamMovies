<%@ page import="es.uma.taw.arkhammovies.dto.MovieCharacterDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/index.css">
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Arkham Movies</title>
</head>
<%
  UserDTO user = (UserDTO) session.getAttribute("user");
  List<MovieCharacterDTO> characters = (List<MovieCharacterDTO>) request.getAttribute("characters");
%>

<body>
    <jsp:include page="header.jsp" />
    <%
        if (user!=null && user.getRole()==0){
    %>
    <div class="center-container">
        <a href="/characters/new" class="add-movie-button">Agregar un personaje</a>
    </div>
    <%
        }
    %>
    <div>
      <h2>Busca una película, personaje o actor</h2>
      <form method="post" action="/movies/searchbyTitle">
        <input type="text" name="title">
        <button>Buscar</button>
      </form>
    </div>
    <div class="characters">
      <div class="list-header">
        <h1>Personajes</h1>
        <%-->Aqui habria que anadir un enlace a la tipica pagina que muestra todos los personajes y no solo algunos--%>
          <a href="/movies/list?criteria=3">Ver más</a>
      </div>
      <ul>
        <%
          if(!characters.isEmpty()){
            for(MovieCharacterDTO c : characters){
        %>
        <li>
          <a href="/characters/character?id=<%=c.getId()%>"><img src="<%=c.getPhotoUrl()%>" alt="Foto de <%=c.getName()%>" width="200" height="300"></a>
          <a href="/characters/character?id=<%=c.getId()%>"><%=c.getName()%></a>
        </li>
        <%}
        }else{%>
        <li>No hay personajes disponibles, perdon por las molestias.</li>
        <%}%>
      </ul>
    </div>
</body>
</html>
