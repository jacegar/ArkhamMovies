<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    String statName = (String) request.getAttribute("statName");
%>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Arkham Movies</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <table class="moviesTable">
            <tr>
                <th>ID</th>
                <th>Titulo</th>
                <th><%= statName %></th>
            </tr>
        </table>
    </main>
</body>
</html>
