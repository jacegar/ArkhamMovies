<%@ page import="es.uma.taw.arkhammovies.dto.KeywordDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/index.css">
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Arkham Movies</title>
</head>
<%
    List<KeywordDTO> keywords = (List<KeywordDTO>)request.getAttribute("keywords");
%>
<body>
    <jsp:include page="header.jsp" />
    <div class="center-container">
        <a href="/keywords/new" class="add-movie-button">Agregar una palabra clave</a>
    </div>

    <br/>

    <%
        for (KeywordDTO keyword : keywords) {
    %>
            <a class="user-link" href="/keywords/<%= keyword.getName() %>"><%= keyword.getName() %></a>
    <%
        }
    %>
</body>
</html>
