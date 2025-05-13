<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.UserDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.ReviewDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        MovieDTO movie = (MovieDTO) request.getAttribute("movie");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(movie.getReleaseDate());
        boolean isLiked = (boolean) request.getAttribute("isLiked");
        boolean isSaved = (boolean) request.getAttribute("isSaved");
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<ReviewDTO> reviews = (List<ReviewDTO>) request.getAttribute("reviews");
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
            <a href="/movies/movie?id=<%=movie.getId()%>"><img src="<%=movie.getPhotoUrl()%>" alt="Foto de <%=movie.getTitle()%>"></a>
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
        <div class="review-section">
            <form method="post" action="/movies/addReview">
                <input type="text" value="" name="reviewText"/>
                <input type="radio" value= "0" name="score"/> 0
                <input type="radio" value= "1" name="score"/> 1
                <input type="radio" value= "2" name="score"/> 2
                <input type="radio" value= "3" name="score"/> 3
                <input type="radio" value= "4" name="score"/> 4
                <input type="radio" value= "5" name="score"/> 5
                <input type="radio" value= "6" name="score"/> 6
                <input type="radio" value= "7" name="score"/> 7
                <input type="radio" value= "8" name="score"/> 8
                <input type="radio" value= "9" name="score"/> 9
                <input type="radio" value= "10" name="score"/> 10 <br/>
                <input type="hidden" value="<%=movie.getId()%>" name="movieId"/>
                <input type="submit" value="Submit Review"/>
            </form>
            <%
                for(ReviewDTO review : reviews) {
            %>
                <%=review.getUser_id().getEmail()%> | <%=review.getScore()%> | <%=review.getText()%>
            <%
                if(user != null && (user.getRole() == 0 || review.getUser_id().getId() == user.getId())) {
            %>
                <a href="/movies/removeReview?movieId=<%=review.getMovie_id().getId()%>&userId=<%=review.getUser_id().getId()%>">
                    <img src="https://img.icons8.com/m_two_tone/512/filled-trash.png" alt="Delete" width="24" height="24">
                </a>
            <%
                }
            %>
            <br/>
            <%
                }
            %>
        </div>
        <div class="action-buttons">
            <%
                if (user!=null && user.getRole()==0){
            %>
                <a href="/movie/edit?id=<%= movie.getId() %>" class="edit-button">Editar</a>
                <form method="post" action="/movie/delete">
                    <input type="hidden" name="id" value="<%= movie.getId() %>" />
                    <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar la película' +
                            ' <%= movie.getTitle() %>?')">Borrar</button>
                </form>
            <%
                }
            %>
            <form method="post" action="/user/atras">
                <button class="back-button">Volver</button>
            </form>
        </div>
    </main>
</body>
</html>
