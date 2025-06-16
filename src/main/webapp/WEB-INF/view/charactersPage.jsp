<%@ page import="es.uma.taw.arkhammovies.dto.MovieCharacterDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: juana
  Date: 16/06/2025
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
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
    <div>
      <h2>Busca una película o personaje</h2>
      <form method="post" action="/movies/searchbyTitle">
        <input type="text" name="title">
        <button>Buscar</button>
      </form>
    </div>
    <div class="characters">
      <div class="list-header">
        <h1>Personajes</h1>
        <%-->Aqui habria que anadir un enlace a la tipica pagina que muestra todos los personajes y no solo algunos--%>
        <a href="/characters/inicio">Ver más</a>
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
