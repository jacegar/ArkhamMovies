<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: juana
  Date: 26/04/2025
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/movieList.css">
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Lista de películas</title>
</head>
<%
  List<MovieDTO> movies = (List<MovieDTO>) request.getAttribute("movieList");
  Integer criteria = (Integer) request.getAttribute("criteria");
  String title = (String) request.getAttribute("title");
%>
<body>
  <jsp:include page="header.jsp" />
  <main>
    <div>
      <h2>Filtra por un nombre</h2>
      <form method="post" action="/movies/moviesbyTitle">
        <input type="text" name="title" value="<%=title==null?"":title%>">
        <input type="hidden" name="criteria" value="<%=criteria%>">
        <button>Buscar</button>
      </form>
    </div>
    <div>
      <%if(criteria==0){%>
      <h1>Películas más populares</h1>
      <%}else if(criteria == 1){%>
        <h1>Películas para ti</h1>
      <%} else if (criteria == 2) {%>
        <h1>Estrenos más recientes</h1>
      <%}else{%>
        <h1>Películas:</h1>
      <%}%>
        <ul>
          <%for(MovieDTO m : movies){%>
          <li>
            <img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300">
            <a href="/movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
          </li>
          <%}%>
        </ul>
    </div>
    <form method="post" action="/user/atras">
      <button>Volver</button>
    </form>
  </main>
</body>
</html>
