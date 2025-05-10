<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 10/05/2025
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
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
            <%
                if (option == 0) {
            %>
                    <tr>
                        <td>Alias:</td>
                        <td><form:input path="nickname" /></td>
                    </tr>
            <%
                }
            %>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Contraseña:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr><td><br/></td></tr>
            <tr>
                <td>
                    <form:button>${option == 0 ? "Registrar" : "Iniciar sesión"}</form:button>
                </td>
            </tr>
        </table>
    </form:form>

    <form method="post" action="/user/atras">
        <button>Volver</button>
    </form>

    <p style="color: red;"><%= (error == null ? "" : error) %></p>
</body>
</html>
