<%-- 
    Document   : registro
    Created on : Nov 7, 2014, 2:30:04 PM
    Author     : adavila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="get">
          <h1>Registro</h1>
          nombre:<input type="text" name="nombre">
              
          cedula:<input type="text" name="cedula"></input>
          saldo:<input type="text" name="saldo"></input>
          <button type=submit>registrar</button>
        </form>
    </body>
    
    
</html>
