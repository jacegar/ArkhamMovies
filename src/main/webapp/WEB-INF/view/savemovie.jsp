<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Enrique Ibáñez: 55%
Eduardo Ariza: 25%
Juan Acevedo: 20%
-->
<html>
<head>
    <title>Save Movie</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/savemovie.css">
</head>
<%
    String error = (String)request.getAttribute("error");
%>
<body>
    <jsp:include page="header.jsp" />
    <div class="content-wrapper">
        <h1>${esEditar ? "Editar " : "Nueva "}película</h1>
        <form:form action="/movie/save" method="post" modelAttribute="movie">
            <form:hidden path="id" />
            <input type="hidden" name="esEditar" value="${esEditar}" />
            <table>
                <tr>
                    <td><strong>Título*:</strong></td>
                    <td><form:input path="title" size="50" /></td>
                </tr>
                <tr>
                    <td>Frase:</td>
                    <td><form:input path="tagline" size="50" /></td>
                </tr>
                <tr>
                    <td>Descripción:</td>
                    <td><form:textarea path="overview" rows="5" cols="40" /></td>
                </tr>
                <tr>
                    <td>Duración:</td>
                    <td><form:input path="runtime" type="number" /></td>
                </tr>
                <tr>
                    <td>Presupuesto:</td>
                    <td><form:input path="budget" type="number" /></td>
                </tr>
                <tr>
                    <td>Facturación:</td>
                    <td><form:input path="revenue" type="number" /></td>
                </tr>
                <tr>
                    <td>Estatus:</td>
                    <td><form:input path="status" size="20" maxlength="20"/></td>
                </tr>
                <tr>
                    <td><strong>Fecha de estreno*:</strong></td>
                    <td><form:input path="releaseDate" type="date" /></td>
                </tr>
                <tr>
                    <td>Página de la película (enlace):</td>
                    <td><form:input path="homepage"/></td>
                </tr>
                <tr>
                    <td>Portada (enlace):</td>
                    <td><form:input path="photoUrl" type="url" /></td>
                </tr>
                <tr>
                    <td>Popularidad:</td>
                    <td><form:input path="popularity" type="number" /></td>
                </tr>
                <tr>
                    <td>
                        Géneros:
                    </td>
                    <td>
                        <div class="genre-checkboxes">
                            <form:checkboxes path="genres" items="${generos}" itemLabel="name" itemValue="id"></form:checkboxes>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:button>Guardar</form:button>
                        </form:form>
                    </td>
                    <td>
                        <form method="post" action="/movie/atras">
                            <input type="hidden" name="prevUrl" value="${referer}" />
                            <button class="back-button">Volver</button>
                        </form>
                    </td>
                </tr>
            </table>
        <p class="error"><%= (error == null ? "" : error) %></p>
    </div>
</body>
</html>
