<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 02/09/22
  Time: 14:18
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
<c:url value="/ad-Teams" var="teamsURL"/>
<c:url value="/Events" var="eventsURL"/>
<c:url value="/ad-Players" var="playersURL"/>
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
    <h1>Modificar Evento!</h1>
    <c:if test="${!empty error}">
        <p class="ui-state-error-text">${error}</p>
    </c:if>
</div>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Ubicación</th>
        <th>Categoria</th>
        <th>Fecha</th>
        <th>Premio</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:url value="/updateEvent" var="updateURL"/>
    <c:forEach var="e" items="${events}">
    <tr>
            <form action="${updateURL}" method="post">
                <input type="hidden" value="${e.getIdevent()}" name="idevent">
                <td><input type="text" value="${e.getName()}" name="name" class="form-control"></td>
                <td><input type="text" value="${e.getLocation()}" name="location" id="location" class="form-control"></td>
                <td><input type="text" value="${e.getCategory()}" name="category" class="form-control"></td>
                <td><input type="date" value="${e.getDate()}" name="date" class="form-control"></td>
                <td><input type="text" value="${e.getPrize()}" name="prize" class="form-control"></td>
                <td><input type="submit" value="Modificar" class="btn btn-warning"></td>
            </form>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
