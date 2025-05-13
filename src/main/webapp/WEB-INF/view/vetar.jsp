<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vetar</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" type="text/css" href="../../css/vetar.css">
</head>
<%
    String mensaje = (String) request.getAttribute("mensaje");
%>
<body>

    <header>
        <h1>Vetar un usuario:</h1>
    </header>

    <form method="post" action="/user/vetar">
        <table>
            <tr>
                <td>
                    Email:
                </td>
                <td>
                    <input type="text" name="email">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button>Vetar</button>
                </td>
            </tr>
        </table>
    </form>

    <form method="post" action="/user/atras" class="back-button">
        <button>Volver</button>
    </form>

    <p class="error"><%= (mensaje == null ? "" : mensaje) %></p>
</body>
</html>
