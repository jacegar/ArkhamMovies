<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--Autor: Juan Acevedo García 100% -->
<head>
    <title>Save MovieCrew</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/savemovie.css">
</head>
<%
    String error = (String)request.getAttribute("error");
%>
<body>
<jsp:include page="header.jsp" />
<div class="content-wrapper">
    <h1>${esEditar ? "Editar " : "Nuevo "}miembro de producción</h1>
    <form:form action="/moviecrew/save" method="post" modelAttribute="moviecrew">
    <input type="hidden" name="esEditar" value="${esEditar}" />
    <table>
        <tr>
            <td><strong>Trabajo*:</strong></td>
            <td><form:input path="job" size="45" maxlength="45"/></td>
        </tr>
        <tr>
            <td>
                Película:
            </td>
            <td>
                <div class="genre-checkboxes">
                    <form:select path="movieId" items="${movies}" itemLabel="title" itemValue="id"></form:select>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                Persona:
            </td>
            <td>
                <div class="genre-checkboxes">
                    <form:select path="personId" items="${people}" itemLabel="name" itemValue="id"></form:select>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <form:button>Guardar</form:button>
                </form:form>
            </td>
            <td>
                <form method="post" action="/moviecrew/atras">
                    <input hidden name="cameFromPerson" value="${cameFromPerson}" />
                    <input hidden name="personId" value="${moviecrew.getPersonId()}" />
                    <input hidden name="movieId" value="${moviecrew.getMovieId()}" />
                    <button class="back-button">Volver</button>
                </form>
            </td>
        </tr>
    </table>
    <p class="error"><%= (error == null ? "" : error) %></p>
</div>
</body>
</html>
