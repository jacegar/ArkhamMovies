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
  List<MovieCharacterDTO> likedCharacters = (List<MovieCharacterDTO>) request.getAttribute("likedCharacters");
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
      <h2>Busca una película, personaje, persona...</h2>
      <form method="post" action="/movies/searchbyTitle">
        <input type="text" name="title">
        <button>Buscar</button>
      </form>
    </div>
    <div class="characters">
      <div class="list-header">
        <h1>Personajes</h1>
          <a href="/movies/list?criteria=3">Ver más</a>
      </div>
      <ul>
        <%
          if(!characters.isEmpty()){
            for(MovieCharacterDTO c : characters){
        %>
        <li>
          <a href="/characters/character?id=<%=c.getId()%>"><img src="<%=c.getPhotoUrl()%>" alt="Foto de <%=c.getName()%>" width="200" height="300"></a>
          <a href="/characters/character?id=<%=c.getId()%>"><%=c.getName()%> <%=c.getSurname1()==null?"":c.getSurname1()%> <%=c.getSurname2()==null?"":c.getSurname2()%></a>
        </li>
        <%}
        }else{%>
        <li>No hay personajes disponibles, perdon por las molestias.</li>
        <%}%>
      </ul>
    </div>

    <%if(likedCharacters != null && !likedCharacters.isEmpty()){%>
    <div class="characters">
        <div class="list-header">
            <h1>Podrían interesarte</h1>
            <a href="/movies/list?criteria=7">Ver más</a>
        </div>
        <ul>
            <% for(MovieCharacterDTO c : likedCharacters){ %>
            <li>
                <a href="/characters/character?id=<%=c.getId()%>"><img src="<%=c.getPhotoUrl()%>" alt="Foto de <%=c.getName()%>" width="200" height="300"></a>
                <a href="/characters/character?id=<%=c.getId()%>"><%=c.getName()%> <%=c.getSurname1()==null?"":c.getSurname1()%> <%=c.getSurname2()==null?"":c.getSurname2()%></a>
            </li>
            <% } %>
        </ul>
    </div>
    <%}%>
</body>
</html>
