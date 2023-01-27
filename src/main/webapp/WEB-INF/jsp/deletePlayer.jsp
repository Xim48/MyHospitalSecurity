<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 13/11/22
  Time: 08:44
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
<c:url value="/ad-Teams" var="teamsURL"/>
<c:url value="/Events" var="eventsURL"/>
<c:url value="/ad-Players" var="regresar"/>
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
                <a class="nav-link" href="${regresar}">Usuarios</a>
            </li>
        </ul>
    </div>
</nav>
<div class="p-5 text-black text-center">
    <h1>Eliminar Jugadores</h1>
    <c:if test="${!empty error}">
    <p class="ui-state-error-text">${error}</p>
    </c:if>
</div>

<div class="container mt-5">
    <div class="row">
        <div class="col-sm-2">
            <a href="${regresar}" class="btn btn-success">Regresar</a>
        </div>
        <div class="col-sm-10">
            <c:forEach var="p" items="${players}">
                <form action="" method="post">
                    <input type="hidden" value="${p.getEmail()}" name="email" id="email">
                    <div class="col-sm-8">
                        <div class="card text-center">
                            <h3 class="card-header">${p.getEmail()}</h3>
                            <div class="card-body">
                                <h3 class="card-title">${p.getName()}</h3>
                                <h4 class="card-text">${p.getMiddleLastName()} ${p.getLastName()}</h4>
                                <h4 class="card-text">${p.getRole()}</h4>
                                <input type="submit" value="Eliminar" class="btn btn-danger">
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
