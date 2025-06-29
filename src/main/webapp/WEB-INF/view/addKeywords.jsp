<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Enrique Ibáñez: 100%
-->
<html>
<head>
    <title>Add Keywords</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/savemovie.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="content-wrapper">
    <h1>Añadir palabras clave</h1>
    <form:form action="/keywords/save_movie" method="post" modelAttribute="movie">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><strong>Título:</strong></td>
            <td>
                <form:input path="title" disabled="true" />
            </td>
        </tr>
        <tr>
            <td><strong>Keywords:</strong></td>
            <td><form:select path="keywords" items="${keywords}" itemValue="id" itemLabel="name" size="25" /></td>
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
</div>
</body>
</html>
