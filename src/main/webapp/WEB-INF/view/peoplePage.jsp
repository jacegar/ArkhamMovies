<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.PersonDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Eduardo Ariza: 65%
Juan Acevedo: 35%
-->
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../../css/index.css">
  <link rel="stylesheet" type="text/css" href="../../css/common.css">
  <title>Arkham Movies</title>
</head>
<%
  UserDTO user = (UserDTO) session.getAttribute("user");
  List<PersonDTO> actors = (List<PersonDTO>) request.getAttribute("actors");
  List<PersonDTO> crewmembers = (List<PersonDTO>) request.getAttribute("crewmembers");
%>

<body>
  <jsp:include page="header.jsp" />
  <%
    if (user!=null && user.getRole()<2){
  %>
  <div class="center-container">
    <a href="/people/new" class="add-movie-button">Agregar una persona</a>
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
  <div class="people">
    <div class="list-header">
      <h1>Actores</h1>
      <a href="/movies/list?criteria=5">Ver más</a>
    </div>
    <ul>
      <%
        if(actors != null && !actors.isEmpty()){
          for(PersonDTO p : actors){
      %>
      <li>
        <a href="/people/person?id=<%=p.getId()%>"><img src="<%=p.getPhotoUrl()%>" alt="Foto de <%=p.getName()%>" width="200" height="300"></a>
        <a href="/people/person?id=<%=p.getId()%>"><%=p.getName()%> <%=p.getSurname1()==null?"":p.getSurname1()%> <%=p.getSurname2()==null?"":p.getSurname2()%></a>
      </li>
      <%}
      }else{%>
      <li>No hay actores disponibles, perdón por las molestias.</li>
      <%}%>
    </ul>
  </div>
  <div class="people">
    <div class="list-header">
      <h1>Personal de producción</h1>
      <a href="/movies/list?criteria=6">Ver más</a>
    </div>
    <ul>
      <%
        if(crewmembers != null && !crewmembers.isEmpty()){
          for(PersonDTO p : crewmembers){
      %>
      <li>
        <a href="/people/person?id=<%=p.getId()%>"><img src="<%=p.getPhotoUrl()%>" alt="Foto de <%=p.getName()%>" width="200" height="300"></a>
        <a href="/people/person?id=<%=p.getId()%>"><%=p.getName()%> <%=p.getSurname1()==null?"":p.getSurname1()%> <%=p.getSurname2()==null?"":p.getSurname2()%></a>
      </li>
      <%}
      }else{%>
      <li>No hay personal de producción disponible, perdón por las molestias.</li>
      <%}%>
    </ul>
  </div>
</body>
</html>
