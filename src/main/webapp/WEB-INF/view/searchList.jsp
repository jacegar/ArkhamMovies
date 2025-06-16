<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.MovieCharacterDTO" %>
<%@ page import="es.uma.taw.arkhammovies.dto.PersonDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/searchList.css">
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <title>Lista de películas</title>
</head>
<%
    List<PersonDTO> people = (List<PersonDTO>) request.getAttribute("personList");
    List<MovieCharacterDTO>characters = (List<MovieCharacterDTO>) request.getAttribute("characterList");
    List<MovieDTO> movies = (List<MovieDTO>) request.getAttribute("movieList");
    Integer criteria = (Integer) request.getAttribute("criteria");
    String title = (String) request.getAttribute("title");
%>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <div>
            <h2>Filtra por nombre</h2>
            <form method="post" action="/movies/searchbyTitle">
                <input type="text" name="title" value="<%=title == null ? "" : title %>" maxlength="500">
                <input type="hidden" name="criteria" value="<%=criteria%>">
                <button>Buscar</button>
            </form>
        </div>
        <% if (movies!=null){ %>
            <div class="list-container">
                <h1>
                    <% if (criteria == 0) { %>
                        Películas más populares
                    <% } else if (criteria == 1) { %>
                        Películas para ti
                    <% } else if (criteria == 2) { %>
                        Estrenos más recientes
                    <% } else { %>
                        Películas:
                    <% } %>
                </h1>
                <ul>
                    <%
                        if (movies.isEmpty()){
                    %>
                    <li>
                        No hay películas disponibles, perdon por las molestias.
                    </li>
                    <%
                        }
                    %>
                    <% for (MovieDTO m : movies) { %>
                    <li>
                        <a href="/movies/movie?id=<%=m.getId()%>"><img src="<%=m.getPhotoUrl()%>" alt="Foto de <%=m.getTitle()%>" width="200" height="300"></a>
                        <a href="/movies/movie?id=<%=m.getId()%>"><%=m.getTitle()%></a>
                    </li>
                    <% } %>
                </ul>
            </div>
        <% } %>
        <% if (characters!=null){ %>
            <div class="list-container">
                <h1>
                    Personajes
                </h1>
                <ul>
                    <%
                        if (characters.isEmpty()){
                    %>
                    <li>
                        No hay personajes disponibles, perdon por las molestias.
                    </li>
                    <%
                        }
                    %>
                    <% for (MovieCharacterDTO c : characters) { %>
                    <li>
                        <a href="/characters/character?id=<%=c.getId()%>"><img src="<%=c.getPhotoUrl()%>" alt="Foto de <%=c.getName()%>" width="200" height="300"></a>
                        <a href="/characters/character?id=<%=c.getId()%>"><%=c.getName()%></a>
                    </li>
                    <% } %>
                </ul>
            </div>
        <% } %>
        <% if (people!=null){ %>
        <div class="list-container">
            <h1>
                Personas
            </h1>
            <ul>
                <%
                    if (people.isEmpty()){
                %>
                <li>
                    No hay personas disponibles, perdon por las molestias.
                </li>
                <%
                    }
                %>
                <% for (PersonDTO p : people) { %>
                <li>
                    <a href="/people/person?id=<%=p.getId()%>"><img src="<%=p.getPhotoUrl()%>" alt="Foto de <%=p.getName()%>" width="200" height="300"></a>
                    <a href="/people/person?id=<%=p.getId()%>"><%=p.getName()%></a>
                </li>
                <% } %>
            </ul>
        </div>
        <% } %>
        <form method="post" action="/user/atras">
            <button>Volver</button>
        </form>
    </main>
</body>
</html>
