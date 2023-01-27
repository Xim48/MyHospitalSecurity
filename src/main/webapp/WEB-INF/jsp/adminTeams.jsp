<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 05/09/22
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:url value="/Events" var="eventsURL"/>
<c:url value="/ad-Players" var="playersURL"/>
<c:url value="/DeleteTeam" var="deleteTeam"/>
<%-- Navbar --%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="${eventsURL}">Eventos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Equipos</a>
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
            <h3>Banear a un equipo</h3>
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="${deleteTeam}">Eliminar</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-8">
            <c:forEach var="t" items="${teams}">
                <h2>${t.getName()}</h2>
                <h5>${t.getLeader()}</h5>
                <p>${t.getUseremail()}</p>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
