<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Arkham Movies</title>
</head>
<%
    Integer statNumber = (Integer)request.getAttribute("statNumber");
    String statName = (String) request.getAttribute("statName");
    Map<String, Integer> integerMap = (Map<String, Integer>)request.getAttribute("integerMap");
    Map<String, Double> doubleMap = (Map<String, Double>)request.getAttribute("doubleMap");
%>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <%
            String tipo, columna;
            switch (statNumber) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    tipo = "Películas";
                    columna = "Título";
                    break;
                case 7:
                    tipo = "Géneros";
                    columna = "Género";
                    break;
                case 8:
                    tipo = "Palabras clave";
                    columna = "Palabra clave";
                    break;
                default:
                    tipo = "";
                    columna = "";
                    break;
            }
            if (!integerMap.isEmpty()) {
        %>
            <h1><%= tipo %> por <%= statName %></h1>
            <table class="moviesTable">
                <tr>
                    <th>Posición</th>
                    <th><%= columna %></th>
                    <th><%= statName %></th>
                </tr>
                <%
                    int pos = 1;
                    for (Map.Entry<String, Integer> entry : integerMap.entrySet()) {
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
        <% } else if (!doubleMap.isEmpty()) { %>
            <h1><%= tipo %> por <%= statName %></h1>
            <table class="moviesTable">
                <tr>
                    <th>Posición</th>
                    <th><%= columna %></th>
                    <th><%= statName %></th>
                </tr>
                <%
                    int pos = 1;
                    for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
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
