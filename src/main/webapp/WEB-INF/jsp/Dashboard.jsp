<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 30/08/22
  Time: 08:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:url value="/InsertEvent" var="insertURL"/>
<c:url value="/DeleteEvent" var="deleteURL"/>
<c:url value="/updateEvent" var="updateURL"/>
<c:url value="/ad-Teams" var="teamsURL"/>
<c:url value="/ad-Players" var="playersURL"/>
<%-- Navbar --%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="#">Eventos</a>
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
    <p></p>
</div>

<div class="container mt-5">
    <div class="row">
        <div class="col-sm-4">
            <h3>Acciones sobre los eventos</h3>
            <ul class="nav nav-pills flex-column">
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="${insertURL}">Agregar</a>--%>
<%--                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="${deleteURL}">Eliminar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${updateURL}">Modificar</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-8">
            <%--    Definition to take items from the servelet--%>
            <c:forEach var="e" items="${events}">
                <%-- These methods are from the EventVW        --%>
                <h2>${e.getName()}</h2>
                <h5>${e.getLocation()}</h5>
                <p>${e.getCategory()} </p>
                <p>${e.getDate()} | ${e.getPrize()}</p>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
