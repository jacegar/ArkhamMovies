<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.StatDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/analisisTables.css">
    <title>Arkham Movies</title>
</head>
<%
    Integer statNumber = (Integer)request.getAttribute("statNumber");
    String statName = (String) request.getAttribute("statName");
    List<StatDTO> stats = (List<StatDTO>)request.getAttribute("stats");
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
                    tipo = "Películas ordenadas";
                    columna = "Título";
                    break;
                case 7:
                    tipo = "Géneros ordenados";
                    columna = "Género";
                    break;
                case 8:
                    tipo = "Palabras clave ordenadas";
                    columna = "Palabra clave";
                    break;
                case 9:
                case 10:
                    tipo = "Usuarios ordenados";
                    columna = "Usuario";
                    break;
                default:
                    tipo = "";
                    columna = "";
                    break;
            }
            if (stats != null && !stats.isEmpty()) {
        %>
            <h1><%= tipo %> por <%= statName %></h1>
            <table class="moviesTable">
                <tr>
                    <th>Posición</th>
                    <th><%= columna %></th>
                    <th><%= statName %></th>
                </tr>
                <%
                    String enlace;
                    int pos = 1;
                    for (StatDTO stat : stats) {
                        if (statNumber >= 0 && statNumber <= 6)
                            enlace = "/movies/movie?id=" + stat.getId();
                        else if (statNumber == 8)
                            enlace = "/movies/list?criteria=8&keyword=" + stat.getKey();
                        else if (statNumber == 9 || statNumber == 10)
                            enlace = "/user/" + stat.getKey();
                        else
                            enlace = "";
                %>
                        <tr>
                            <td><%= pos %></td>
                            <%  if (enlace.isEmpty()) { %>
                                    <td><%= stat.getKey() %></td>
                            <%  } else { %>
                                    <td><a href="<%= enlace %>"><%= stat.getKey() %></a></td>
                            <%
                                }
                            %>
                            <td><%= (stat.getIValue() != null) ? stat.getIValue() :
                                    ((stat.getDValue() != null) ? stat.getDValue() : "N/A") %></td>
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
