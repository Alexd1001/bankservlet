<%-- 
    Document   : index
    Created on : 07-nov-2014, 18:51:32
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
        <h1>Sesion</h1>
		<h2 style="color:red" ><%
		if(null!=request.getAttribute("errorMessage"))
		{
        out.println(request.getAttribute("errorMessage"));
		}
		%></h2>
        <form action="MainController" method="post"> 
        
        cedula:<input name="documentoid">
        
        <input type="submit" value="Submit"> 
        </form>
        <a href="registrar.jsp">Registrar</a>
		
        
    </body>
</html>
