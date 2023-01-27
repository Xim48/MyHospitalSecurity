<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 12/11/22
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:url value="/Events" var="eventsURL"/>
<c:url value="/ad-Players" var="playersURL"/>
<c:url value="/ad-Teams" var="teamsURL" />
<c:url var="deletePlayer" value="/DeletePlayer" />
<%-- Navbar --%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="${eventsURL}">Eventos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${teamsURL}">Equipos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${playersURL}">Usuarios</a>
            </li>
        </ul>
    </div>
</nav>
<div class="p-5 text-black text-center">
    <h1>Bienvenido a la administración</h1>
    <br>
</div>
<div class="container mt-5">
    <div class="row">
        <div class="col-sm-4">
            <h3 class="">Banear a un jugador</h3>
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a href="${deletePlayer}" class="nav-link">Eliminar</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-8">
            <c:forEach var="p" items="${players}">
                <h3><i>${p.getEmail()}</i></h3>
                <h4><i>${p.getName()}</i></h4>
                <h4><i>${p.getMiddleLastName()} ${p.getLastName()}</i></h4>
                <h4><i>${p.getRole()}</i></h4>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
