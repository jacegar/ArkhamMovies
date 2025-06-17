<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Character</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/savemovie.css">
</head>
<%
    String error = (String)request.getAttribute("error");
%>
<body>
<jsp:include page="header.jsp" />
<div class="content-wrapper">
    <h1>${esEditar ? "Editar " : "Nuevo "}personaje</h1>
    <form:form action="/characters/save" method="post" modelAttribute="character">
    <form:hidden path="id" />
    <input type="hidden" name="esEditar" value="${esEditar}" />
    <table>
        <tr>
            <td><strong>Nombre*:</strong></td>
            <td><form:input path="name" size="50"/></td>
        </tr>
        <tr>
            <td>Apellido 1:</td>
            <td><form:input path="surname1" size="50" /></td>
        </tr>
        <tr>
            <td>Apellido 2:</td>
            <td><form:input path="surname2" size="50" /></td>
        </tr>
        <tr>
            <td>Portada (enlace):</td>
            <td><form:input path="photoUrl" type="url" /></td>
        </tr>
        <tr>
            <td>
                Película:
            </td>
            <td>
                <div class="genre-checkboxes">
                    <form:select path="movie" items="${movies}" itemLabel="title" itemValue="id"></form:select>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                Actor:
            </td>
            <td>
                <div class="genre-checkboxes">
                    <form:select path="person" items="${people}" itemLabel="name" itemValue="id"></form:select>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <form:button>Guardar</form:button>
                </form:form>
            </td>
            <td>
                <form method="post" action="/user/atras">
                    <button class="back-button">Volver</button>
                </form>
            </td>
        </tr>
    </table>
    <p class="error"><%= (error == null ? "" : error) %></p>
</div>
</body>
</html>
