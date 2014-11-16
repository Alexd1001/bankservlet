<%-- 
    Document   : registrar
    Created on : 07-nov-2014, 19:00:31
    Author     : adavilap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form action="MainController" method="get"> 
        Nombre:<input name="nombre">
        cedula:<input name="cedula">
        saldo:<input name="saldo">
        <input type="submit" value="registrar"> 
        </form>
    </body>
</html>
