<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Save Person</title>
  <link rel="stylesheet" type="text/css" href="../../css/common.css">
  <link rel="stylesheet" type="text/css" href="../../css/savemovie.css">
</head>
<%
  String error = (String)request.getAttribute("error");
%>
<body>
<jsp:include page="header.jsp" />
<div class="content-wrapper">
  <h1>${esEditar ? "Editar " : "Nueva "}persona</h1>
  <form:form action="/people/save" method="post" modelAttribute="person">
  <form:hidden path="id" />
  <input type="hidden" name="esEditar" value="${esEditar}" />
  <table>
    <tr>
      <td><strong>Nombre*:</strong></td>
      <td><form:input path="name" size="50"/></td>
    </tr>
    <tr>
      <td><strong>Apellido 1*:</strong></td>
      <td><form:input path="surname1" size="50" /></td>
    </tr>
    <tr>
      <td>Apellido 2:</td>
      <td><form:input path="surname2" size="50" /></td>
    </tr>
    <tr>
      <td><strong>Género*:</strong></td>
      <td>
        <form:radiobutton path="gender" value="M" /> M
        <form:radiobutton path="gender" value="F" /> F
      </td>
    </tr>
    <tr>
      <td><strong>Edad*:</strong></td>
      <td><form:input path="age" type="number"/></td>
    </tr>
    <tr>
      <td>Portada (enlace):</td>
      <td><form:input path="photoUrl" type="url" /></td>
    </tr>
    <tr>
      <td>
        <form:button>Guardar</form:button>
        </form:form>
      </td>
      <td>
        <form method="post" action="/people/atras">
          <input type="hidden" name="personId" value="${person != null ? person.getId() : null}" />
          <button class="back-button">Volver</button>
        </form>
      </td>
    </tr>
  </table>
  <p class="error"><%= (error == null ? "" : error) %></p>
</div>
</body>
</html>
