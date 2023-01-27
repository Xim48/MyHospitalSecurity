<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 31/08/22
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adminitración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:url value="/ad-Teams" var="teamsURL"/>
<c:url value="/Events" var="eventsURL"/>
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
                <a class="nav-link" href="#">Usuarios</a>
            </li>
        </ul>
    </div>
</nav>
<div class="p-5 text-black text-center">
    <h1>Registrar Evento!</h1>
    <c:if test="${!empty error}">
        <p class="ui-state-error-text">${error}</p>
    </c:if>
</div>

<c:url value="/InsertEvent" var="insertURL"/>
<form action="${insertURL}" method="post">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Nombre:</label>
        <div class="col-sm-8">
            <input type="text" id="name" placeholder="name" name="name" class="form-control">
        </div>
    </div>
    <br>
    <div class="form-group row">
        <label for="location" class="col-sm-2 col-form-label">Ubicacion:</label>
        <div class="col-sm-8">
            <input type="text" id="location" placeholder="Location" name="location" class="form-control">
        </div>
    </div>
    <br>
    <div class="form-group row">
        <label for="category" class="col-sm-2 col-form-label">Categoria: </label>
        <div class="col-sm-8">
            <input type="text" id="category" placeholder="Category" name="category" class="form-control">
        </div>
    </div>
    <br>
    <div class="form-group row">
        <label for="date" class="col-sm-2 col-form-label">Fecha:</label>
        <div class="col-sm-2">
            <input type="date" id="date" name="date" class="form-control">
        </div>

        <label for="prize" class="col-sm-2 col-form-label">Premio:</label>
        <div class="col-sm-4">
            <input type="text" id="prize" name="prize" class="form-control">
        </div>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Agregar</button>
</form>
</body>
</html>
