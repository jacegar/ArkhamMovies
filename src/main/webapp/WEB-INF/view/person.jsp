<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.*" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--Autor: Juan Acevedo García 30% -->
<%
    PersonDTO person = (PersonDTO) request.getAttribute("person");
    UserDTO user = (UserDTO) session.getAttribute("user");
    List<MovieCharacterDTO> characters = (List<MovieCharacterDTO>) request.getAttribute("characters");
    List<MoviecrewDTO> jobs = (List<MoviecrewDTO>) request.getAttribute("jobs");
    Map<Integer, MovieDTO> moviesWorked = (Map<Integer, MovieDTO>) request.getAttribute("moviesWorked");
%>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <title><%=person.getName()%></title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <%
            if (user!=null && user.getRole()==0){
        %>
        <div class="center-container">
            <a href="/characters/new?personId=<%=person.getId()%>" class="add-movie-button">Agregar un personaje</a>
            <a href="/moviecrew/new?personId=<%=person.getId()%>" class="add-movie-button">Agregar personal de producción</a>
        </div>
        <%
            }
        %>
        <h1><%=person.getName()%></h1>
        <div class="movie-container">
            <a href="/people/person?id=<%=person.getId()%>"><img src="<%=person.getPhotoUrl()%>" alt="Foto de <%=person.getName()%>" width="400" height="600"></a>
            <div class="movie-details">
                <h2>Nombre:</h2>
                <p><%=person.getName()%></p>
                <h2>Apellido:</h2>
                <p><%=(person.getSurname1()==null && person.getSurname2()==null) ? "Desconocido":(person.getSurname1()==null?"":person.getSurname1()) + " " + (person.getSurname2()==null?"":person.getSurname2())%></p>
                <h2>Género:</h2>
                <p><%=person.getGender()=='M'?"Masculino":"Femenino"%></p>
                <h2>Edad:</h2>
                <p><%=person.getAge()%> años</p>
            </div>

            <% if(characters != null && !characters.isEmpty()){ %>
            <div class="movie-characters">
                <h2>Personajes:</h2>
                <ul>
                    <%for(MovieCharacterDTO character : characters){%>
                    <li>
                        <a href="/characters/character?id=<%=character.getId()%>"><img src="<%=character.getPhotoUrl()%>" alt="Foto de <%=character.getName()%>"></a>
                        <a href="/characters/character?id=<%=character.getId()%>"><%=character.getName()%></a>
                    </li>
                    <%}%>
                </ul>
            </div>
            <% } %>

            <% if(jobs != null && !jobs.isEmpty()){%>
            <div class="movie-characters crew-list">
                <h2>Ha trabajado en:</h2>
                <ul>
                    <%for(MoviecrewDTO job : jobs){
                    MovieDTO movieWorked = moviesWorked.get(job.getMovieId());%>
                    <li class="person-jobs">
                        <a href="/movies/movie?id=<%=job.getMovieId()%>"><img src="<%=movieWorked.getPhotoUrl()%>" alt="Foto de <%=movieWorked.getTitle()%>"></a>
                        <a href="/movies/movie?id=<%=job.getMovieId()%>"><%=movieWorked.getTitle()%></a>
                        <p class="crewmember-job"><%=job.getJob()%></p>

                        <% if (user!=null && user.getRole()==0){ %>
                            <form method="post" action="/moviecrew/delete">
                                <input type="hidden" name="movieId" value="<%= job.getMovieId() %>" />
                                <input type="hidden" name="personId" value="<%= job.getPersonId() %>" />
                                <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar este trabajo en producción?')">Borrar</button>
                            </form>
                            <a href="/moviecrew/edit?movieId=<%= job.getMovieId() %>&personId=<%= job.getPersonId() %>" class="edit-button movie-crew-button">Editar</a>
                        <% } %>
                    </li>
                    <%}%>
                </ul>
            </div>
            <% } %>

        </div>

        <div class="action-buttons">
            <%
                if (user!=null && user.getRole()==0){
            %>
            <a href="/people/edit?id=<%= person.getId() %>" class="edit-button">Editar</a>
            <form method="post" action="/people/delete">
                <input type="hidden" name="id" value="<%= person.getId() %>" />
                <button class="delete-button" onclick="return confirm('¿Está seguro de que quiere borrar <%=person.getGender()=='M'?"el actor":"la actriz"%> <%= person.getName() %>?')">Borrar</button>
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
