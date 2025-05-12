<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        MovieDTO movie = (MovieDTO) request.getAttribute("movie");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(movie.getReleaseDate());
        boolean isLiked = (boolean) request.getAttribute("isLiked");
        boolean isSaved = (boolean) request.getAttribute("isSaved");
        UserDTO user = (UserDTO) session.getAttribute("user");
    %>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <title><%=movie.getTitle()%></title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <h1><%=movie.getTitle()%></h1>
        <div class="movie-container">
            <img src="<%=movie.getPhotoUrl()%>" alt="Foto de <%=movie.getTitle()%>">
            <div class="movie-details">
                <% if (user != null) { %>
                <div class="buttons-container">
                    <a href="/movies/flipLike?movieId=<%=movie.getId()%>">
                        <% if (!isLiked) { %>
                            <img src="https://icones.pro/wp-content/uploads/2021/02/icone-de-coeur-rouge.png" alt="Like">
                        <% } else { %>
                            <img src="https://cdn-icons-png.flaticon.com/512/4209/4209081.png" alt="Unlike">
                        <% } %>
                    </a>
                    <a href="/movies/flipSave?movieId=<%=movie.getId()%>">
                        <% if (!isSaved) { %>
                            <img src="../../img/save-instagram.png" alt="Save">
                        <% } else { %>
                            <img src="../../img/bookmark.png" alt="Unsave">
                        <% } %>
                    </a>
                </div>
                <% } %>
                <p><%=movie.getTagline()%></p>
                <h2>Descripción</h2>
                <p><%=movie.getOverview().isEmpty() ? "Sin descripción" : movie.getOverview() %></p>
                <h3>Presupuesto</h3>
                <p><%=movie.getBudget() == null ? "Desconocido" : movie.getBudget() + " €" %></p>
                <h3>Fecha de estreno</h3>
                <p><%=date%></p>
                <% if (!movie.getHomepage().equals("N/A") && !movie.getHomepage().isEmpty()) { %>
                    <a href="<%=movie.getHomepage()%>">Página de la película</a>
                <% } %>
            </div>
        </div>
        <form method="post" action="/movie/edit">
            <input type="hidden" name="id" value="<%= movie.getId() %>" />
            <button>Editar</button>
        </form>
        <form method="post" action="/user/atras">
            <button>Volver</button>
        </form>
    </main>
</body>
</html>
