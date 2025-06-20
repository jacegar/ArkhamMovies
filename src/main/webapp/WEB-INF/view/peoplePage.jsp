<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.PersonDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../../css/index.css">
  <link rel="stylesheet" type="text/css" href="../../css/common.css">
  <title>Arkham Movies</title>
</head>
<%
  UserDTO user = (UserDTO) session.getAttribute("user");
  List<PersonDTO> people = (List<PersonDTO>) request.getAttribute("people");
%>

<body>
  <jsp:include page="header.jsp" />
  <%
    if (user!=null && user.getRole()==0){
  %>
  <div class="center-container">
    <a href="/people/new" class="add-movie-button">Agregar un actor</a>
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
  <div class="people">
    <div class="list-header">
      <h1>Actores</h1>
      <%-->Aqui habria que anadir un enlace a la tipica pagina que muestra todos las personas y no solo algunos--%>
      <a href="/movies/list?criteria=4">Ver más</a>
    </div>
    <ul>
      <%
        if(!people.isEmpty()){
          for(PersonDTO p : people){
      %>
      <li>
        <a href="/people/person?id=<%=p.getId()%>"><img src="<%=p.getPhotoUrl()%>" alt="Foto de <%=p.getName()%>" width="200" height="300"></a>
        <a href="/people/person?id=<%=p.getId()%>"><%=p.getName()%></a>
      </li>
      <%}
      }else{%>
      <li>No hay personajes disponibles, perdon por las molestias.</li>
      <%}%>
    </ul>
  </div>
</body>
</html>
