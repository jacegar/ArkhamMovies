<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.*" %>
<%@ page import="es.uma.taw.arkhammovies.entity.Moviecrew" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <!--Autor: Juan Acevedo García 65% -->
    <%
        MovieDTO movie = (MovieDTO) request.getAttribute("movie");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(movie.getReleaseDate());
        boolean isLiked = (boolean) request.getAttribute("isLiked");
        boolean isSaved = (boolean) request.getAttribute("isSaved");
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<ReviewDTO> reviews = (List<ReviewDTO>) request.getAttribute("reviews");
        List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
        List<KeywordDTO> keywords = (List<KeywordDTO>) request.getAttribute("keywords");
        List<MovieCharacterDTO> characters = (List<MovieCharacterDTO>) request.getAttribute("characters");
        List<MoviecrewDTO> crew = (List<MoviecrewDTO>) request.getAttribute("crew");
        Map<Integer, PersonDTO> crewPeople = (Map<Integer, PersonDTO>) request.getAttribute("crewPeople");
    %>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <title><%=movie.getTitle()%></title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <%
            if (user!=null && user.getRole()<2){
        %>
        <div class="center-container">
            <a href="/characters/new?movieId=<%=movie.getId()%>" class="add-movie-button">Agregar un personaje</a>
            <a href="/moviecrew/new?movieId=<%=movie.getId()%>&cameFromPerson=false" class="add-movie-button">Agregar personal de producción</a>
            <a href="/keywords/add_to_movie?movieId=<%= movie.getId() %>" class="add-movie-button">Agregar palabras clave</a>
        </div>
        <%
            }
        %>
        <h1><%=movie.getTitle()%></h1>
        <div class="movie-container">
            <a href="/movies/movie?id=<%=movie.getId()%>"><img src="<%=movie.getPhotoUrl()%>" alt="Foto de <%=movie.getTitle()%>"></a>
            <div class="movie-details">
                <% if (user != null) { %>
                <div class="buttons-container">
                    <a href="/movies/flipLike?movieId=<%=movie.getId()%>&tipo=true">
                        <% if (!isLiked) { %>
                            <img src="https://icones.pro/wp-content/uploads/2021/02/icone-de-coeur-rouge.png" alt="Like">
                        <% } else { %>
                            <img src="https://cdn-icons-png.flaticon.com/512/4209/4209081.png" alt="Unlike">
                        <% } %>
                    </a>
                    <a href="/movies/flipSave?movieId=<%=movie.getId()%>&tipo=true">
                        <% if (!isSaved) { %>
                            <img src="../../img/save-instagram.png" alt="Save">
                        <% } else { %>
                            <img src="../../img/bookmark.png" alt="Unsave">
                        <% } %>
                    </a>
                </div>
                <% } %>
                <table>
                    <tr>
                        <td>
                            <p><%=movie.getTagline()%></p>
                            <h2>Descripción</h2>
                            <p><%=movie.getOverview().isEmpty() ? "Sin descripción" : movie.getOverview() %></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h3>Presupuesto</h3>
                            <p><%=movie.getBudget() == null ? "Desconocido" : movie.getBudget() + " €" %></p>
                        </td>
                        <td>
                            <h3>Facturación</h3>
                            <p><%=movie.getRevenue() == null ? "Desconocida" : movie.getRevenue() + " €" %></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h3>Estatus</h3>
                            <p><%=movie.getStatus() == null || movie.getStatus().isEmpty() ? "Desconocido" : movie.getStatus() %></p>
                        </td>
                        <td>
                            <h3>Duración</h3>
                            <p><%=movie.getRuntime() == null ? "Desconocida" : movie.getRuntime() + " minutos" %></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h3>Fecha de estreno</h3>
                            <p><%=date%></p>
                        </td>
                        <td>
                            <% if (!movie.getHomepage().equals("N/A") && !movie.getHomepage().isEmpty()) { %>
                            <a href="<%=movie.getHomepage()%>">Página de la película</a>
                            <% } %>
                        </td>
                    </tr>
                </table>

                <ul>
                    <p>Géneros: </p>
                    <% if(movie.getGenres() == null || movie.getGenres().isEmpty()){%>
                        <p>La pelicula no tiene géneros</p>
                    <%}else{ %>
                        <%for(GenreDTO genre : genres) {%>
                                <li>
                                    <%=genre.getName()%>
                                </li>
                        <%}
                    }%>
                </ul>

                <ul>
                    <p>Palabras clave: </p>
                    <% if(movie.getKeywords() == null || movie.getKeywords().isEmpty()){%>
                    <p>La pelicula no tiene palabras clave</p>
                    <%}else{ %>
                    <%for(KeywordDTO keyword : keywords) {%>
                    <li>
                        <a href="/movies/list?criteria=8&keyword=<%= keyword.getName() %>"><%=keyword.getName()%></a>
                    </li>
                    <%}
                    }%>
                </ul>
            </div>

            <% if(characters != null && !characters.isEmpty()){ %>
            <div class="movie-characters">
                <h2>Personajes:</h2>
                <ul>
                    <%for(MovieCharacterDTO character : characters){%>
                    <li>
                        <a href="/characters/character?id=<%=character.getId()%>"><img src="<%=character.getPhotoUrl()%>" alt="Foto de <%=character.getName()%>"></a>
                        <a href="/characters/character?id=<%=character.getId()%>"><%=character.getName()%></a>

                        <% if (user!=null && user.getRole()<2){ %>
                        <form method="post" action="/characters/delete">
                            <input type="hidden" name="id" value="<%= character.getId() %>" />
                            <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar este personaje?')">Borrar</button>
                        </form>
                        <a href="/characters/edit?id=<%= character.getId() %>" class="edit-button movie-crew-button">Editar</a>
                        <% } %>
                    </li>
                    <%}%>
                </ul>
            </div>
            <% } %>

            <% if(crew != null && !crew.isEmpty()){ %>
            <div class="movie-characters crew-list">
                <h2>Personal de producción:</h2>
                <ul>
                <%for(MoviecrewDTO crewMember : crew){%>
                    <li class="person-jobs">
                        <a href="/people/person?id=<%=crewMember.getPersonId()%>"><img src="<%=crewPeople.get(crewMember.getPersonId()).getPhotoUrl()%>" alt="Foto de <%=crewPeople.get(crewMember.getPersonId()).getName()%>"></a>
                        <a href="/people/person?id=<%=crewMember.getPersonId()%>"><%=crewPeople.get(crewMember.getPersonId()).getName()%></a>
                        <p><%=crewMember.getJob()%></p>

                        <% if (user!=null && user.getRole()<2){ %>
                            <form method="post" action="/moviecrew/delete">
                                <input type="hidden" name="movieId" value="<%= crewMember.getMovieId() %>" />
                                <input type="hidden" name="personId" value="<%= crewMember.getPersonId() %>" />
                                <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar este trabajo en producción?')">Borrar</button>
                            </form>
                            <a href="/moviecrew/edit?movieId=<%= crewMember.getMovieId() %>&personId=<%= crewMember.getPersonId() %>&cameFromPerson=false" class="edit-button">Editar</a>
                        <% } %>
                    </li>
                <%}%>
                </ul>
            </div>
            <% } %>

        </div>

        <div class="review-section">
            <h3>Añadir una reseña:</h3>
            <form method="post" action="/movies/addReview">
                <textarea value="" rows="2" cols="100" name="reviewText" maxlength="200"></textarea><br/>
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
                <input type="submit" value="Añadir reseña"/>
            </form>
            <p class="error">${error == null ? "" : error}</p>
            <h3>Reseñas de otros usuarios:</h3>
            <%
                if (reviews.isEmpty()){
            %>
                No hay reseñas para esta película aún.
            <%
                }
            %>
            <%
                for(ReviewDTO review : reviews) {
            %>
                <a href="/user/<%= review.getUser_id().getNickname() %>" class="button-link"><%= review.getUser_id().getNickname() %></a>
                | <%=review.getScore()%> | <%=review.getText()%>
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
                if (user!=null && user.getRole()<2){
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
