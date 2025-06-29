<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Enrique Ibáñez: 100%
-->
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/user.css">
</head>
<%
    int option = (int)request.getAttribute("option");
    String error = (String)request.getAttribute("error");
%>
<body>

    <header>
        <h1>${option == 0 ? "Regístrate" : "Iniciar sesión"}</h1>
    </header>

    <form:form method="post" action="/user/${formAction}" modelAttribute="user">
        <table>
            <tr>
                <td>Alias:</td>
                <td><form:input path="nickname" /></td>
            </tr>
            <%
                if (option == 0) {
            %>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email" /></td>
                    </tr>
            <%
                }
            %>
            <tr>
                <td>Contraseña:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <form:button>${option == 0 ? "Registrar" : "Iniciar sesión"}</form:button>
                </td>
            </tr>
        </table>
    </form:form>

    <form method="post" action="/user/atras" class="back-button">
        <button>Volver</button>
    </form>

    <div class="redirection-link">
        <%
            if (option == 0) {
        %>
                ¿Ya tienes cuenta? <a href="/user/login">Inicia sesión</a>
        <%
            } else {
        %>
                ¿No tienes cuenta? <a href="/user/register">Regístrate</a>
        <%
            }
        %>
    </div>

    <p class="error"><%= (error == null ? "" : error) %></p>
</body>
</html>
