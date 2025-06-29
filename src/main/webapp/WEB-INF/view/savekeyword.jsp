<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Enrique Ibáñez: 100%
-->
<html>
<head>
    <title>Save Keyword</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/savemovie.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
</head>
<%
    String error = (String)request.getAttribute("error");
    boolean esEditar = (boolean)request.getAttribute("esEditar");
%>
<body>
<jsp:include page="header.jsp" />
<div class="content-wrapper">
    <h1>${esEditar ? "Editar " : "Nueva "}palabra clave</h1>
    <form:form action="/keywords/save" method="post" modelAttribute="keyword">
    <form:hidden path="id" />
    <input type="hidden" name="esEditar" value="${esEditar}" />
    <table>
        <tr>
            <td><strong>Nombre*:</strong></td>
            <td><form:input path="name" size="25" /></td>
        </tr>
        <tr>
            <td>
                <form:button>Guardar</form:button>
                </form:form>
            </td>
            <td>
                <form method="post" action="/keywords/atras">
                    <button class="back-button">Volver</button>
                </form>
            </td>
            <%
                if (esEditar) {
            %>
                  <td>
                    <form method="post" action="/keywords/delete">
                        <input type="hidden" name="id" value="${keyword.getId()}" />
                        <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar la palabra clave' +
                                ' ${keyword.getName()}?')">Borrar</button>
                    </form>
                  </td>
            <%
                }
            %>
        </tr>
    </table>
    <p class="error"><%= (error == null ? "" : error) %></p>
</div>
</body>
</html>
