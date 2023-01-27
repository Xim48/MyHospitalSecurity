<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="controller.admin.EventServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lag="es">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=9;IE=10;IE=11;IE=Edge,chrome=1">
        <title>Teamder</title>
        <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet"/>
    </head>
    <body>
        <header>
            <nav></nav>
        </header>
        <main>
            <div class="greetingContainer" id="greetingContainer">
                <h1>¡Bienvenido a Teamder!</h1>
                <p class="phraseContainer" id="phraseContainer">

                </p>
            </div>
            <div class="menuContainer" id="menuContainer">
            </div>
        </main>
        <footer></footer>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    </body>
</html>
