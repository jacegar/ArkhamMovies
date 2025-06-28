<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Arkham Movies</title>
</head>
<%
    String statName = (String) request.getAttribute("statName");
    Map<String, Integer> movieMapInteger = (Map<String, Integer>)request.getAttribute("movieMapInteger");
    Map<String, Double> movieMapDouble = (Map<String, Double>)request.getAttribute("movieMapDouble");
%>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <% if (!movieMapInteger.isEmpty()) { %>
            <h1>Películas por <%= statName %></h1>
            <table class="moviesTable">
                <tr>
                    <th>Posición</th>
                    <th>Titulo</th>
                    <th><%= statName %></th>
                </tr>
                <%
                    int pos = 1;
                    for (Map.Entry<String, Integer> entry : movieMapInteger.entrySet()) {
                %>
                        <tr>
                            <td><%= pos %></td>
                            <td><%= entry.getKey() %></td>
                            <td><%= entry.getValue() %></td>
                        </tr>
                <%
                        pos++;
                    }
                %>
            </table>
        <% } else if (!movieMapDouble.isEmpty()) { %>
            <h1>Películas por <%= statName %></h1>
            <table class="moviesTable">
                <tr>
                    <th>Posición</th>
                    <th>Titulo</th>
                    <th><%= statName %></th>
                </tr>
                <%
                    int pos = 1;
                    for (Map.Entry<String, Double> entry : movieMapDouble.entrySet()) {
                %>
                        <tr>
                            <td><%= pos %></td>
                            <td><%= entry.getKey() %></td>
                            <td><%= entry.getValue() == null ? "N/A" : entry.getValue() %></td>
                        </tr>
                <%
                        pos++;
                    }
                %>
            </table>
        <% } %>
    </main>
</body>
</html>
