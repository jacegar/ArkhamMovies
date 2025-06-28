<%@ page import="java.util.Map" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    Map<String, Integer> estadisticas = (Map<String, Integer>) request.getAttribute("estadisticas");
    Double notaMedia = (Double) request.getAttribute("notaMedia");
    Double likesMedios = (Double) request.getAttribute("likesMedios");
    UserDTO userSession = (UserDTO)session.getAttribute("user");
%>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/analisis.css">
    <title>Arkham Movies</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main class="statistics-main">
        <div class="general-statistics-container">
            <h2>Estadísticas generales</h2>
            <div>
                <h3>Número de películas:</h3>
                <p><%=estadisticas.get("numPeliculas")%></p>
            </div>
            <div>
                <h3>Número de personajes:</h3>
                <p><%=estadisticas.get("numPersonajes")%></p>
            </div>
            <div>
                <h3>Número de personas:</h3>
                <p><%=estadisticas.get("numPersonas")%></p>
            </div>
            <div>
                <h3>Número de actores:</h3>
                <p><%=estadisticas.get("numActores")%></p>
            </div>
            <div>
                <h3>Número de trabajos en producción:</h3>
                <p><%=estadisticas.get("numTrabajosProd")%></p>
            </div>
            <div>
                <h3>Nota media de reseñas:</h3>
                <p><%= notaMedia != null ? String.format("%.2f", notaMedia) : "N/A" %></p>
            </div>
            <div>
                <h3>Presupuesto medio de películas:</h3>
                <p><%= estadisticas.get("mediaPresupuesto") %> €</p>
            </div>
            <div>
                <h3>Facturación media de películas:</h3>
                <p><%= estadisticas.get("mediaFacturacion") %> €</p>
            </div>
            <div>
                <h3>Duración media de películas:</h3>
                <p><%= estadisticas.get("mediaDuracion") %> minutos</p>
            </div>
            <div>
                <h3>Media de likes de películas:</h3>
                <p><%= likesMedios != null ? String.format("%.2f", likesMedios) : "N/A" %></p>
            </div>
            <div>
                <h3>Número de usuarios:</h3>
                <p><%= estadisticas.get("numUsuarios") %></p>
            </div>
            <% if (userSession!=null && userSession.getRole()<2){ %>
                <div>
                    <h3>Número de editores:</h3>
                    <p><%= estadisticas.get("numEditores") %></p>
                </div>
            <% } %>
            <% if (userSession != null && userSession.getRole() == 0) { %>
                <div>
                    <h3>Número de administradores:</h3>
                    <p><%= estadisticas.get("numAdministradores") %></p>
                </div>
            <% } %>
        </div>

        <div class="film-analisis-container">
            <h2>Análisis de peliculas</h2>
            <a href="/analisis/table?page=0">Películas por facturación</a>
            <a href="/analisis/table?page=1">Películas por recaudación</a>
            <a href="/analisis/table?page=2">Películas por beneficio</a>
            <a href="/analisis/table?page=3">Películas por duración</a>
            <a href="/analisis/table?page=4">Películas por nota</a>
            <a href="/analisis/table?page=5">Películas por número de likes</a>
            <a href="/analisis/table?page=6">Películas por popularidad</a>
            <a href="/analisis/table?page=7">Géneros más frecuentes</a>
            <a href="/analisis/table?page=8">Keywords más frecuentes</a>
            <a href="/analisis/table?page=9">Usuarios con más reseñas</a>
            <a href="/analisis/table?page=10">Usuarios con más likes</a>
        </div>
    </main>
</body>
</html>
