<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vetar</title>
    <link rel="stylesheet" type="text/css" href="../../css/vetar.css">
</head>
<%
    String mensaje = (String) request.getAttribute("mensaje");
%>
<body>
<form method="post" action="/user/vetar">
    Email: <input type="text" name="email">
    <button>Vetar</button>
</form>
<%
    if (mensaje!=null){
        out.println(mensaje);
    }
%>
<form method="post" action="/user/atras">
    <button class="back-button">Volver</button>
</form>
</body>
</html>
