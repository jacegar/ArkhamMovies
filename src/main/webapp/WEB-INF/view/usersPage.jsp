<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Enrique Ibáñez: 100%
-->
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../../css/index.css">
  <link rel="stylesheet" type="text/css" href="../../css/common.css">
  <title>Arkham Movies</title>
</head>
<%
  String busqueda = (String)request.getAttribute("busqueda");
  List<UserDTO> usuarios = (List<UserDTO>) request.getAttribute("usuarios");
%>
<body>
  <jsp:include page="header.jsp" />

  <div>
    <h2>Busca un usuario</h2>
    <form method="post" action="/user/search">
      <input type="text" name="busqueda" value="<%= busqueda != null ? busqueda : "" %>">
      <button>Buscar</button>
    </form>
  </div>

  <%
    if (usuarios != null) {
      for (UserDTO usuario : usuarios) {
  %>
        <a class="user-link" href="/user/<%= usuario.getNickname() %>"><%= usuario.getNickname() %></a>
  <%
      }
    }
  %>
</body>
</html>
