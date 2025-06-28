<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    Map<String, Integer> estadisticas = (Map<String, Integer>) request.getAttribute("estadisticas");
    Double notaMedia = (Double) request.getAttribute("notaMedia");
    Double likesMedios = (Double) request.getAttribute("likesMedios");
%>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
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
            <div>
                <h3>Número de editores:</h3>
                <p><%= estadisticas.get("numEditores") %></p>
            </div>
            <div>
                <h3>Número de administradores:</h3>
                <p><%= estadisticas.get("numAdministradores") %></p>
            </div>
        </div>

        <div class="film-analisis-container">
            <h2>Análisis de peliculas</h2>
            <a>Películas por facturación</a>
            <a>Películas por recaudación</a>
            <a>Películas por beneficio</a>
            <a>Películas por duración</a>
            <a>Películas por nota</a>
            <a>Películas por número de likes</a>
            <a>Películas por popularidad</a>
            <a>Géneros más frecuentes</a>
            <a>Keywords más frecuentes</a>
            <a>Usuarios con más reseñas</a>
            <a>Usuarios con más likes</a>
            <a>Usuarios con más nota media</a>
        </div>
    </main>
</body>
</html>
