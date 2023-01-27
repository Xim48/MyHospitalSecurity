<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 31/08/22
  Time: 08:55
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
<c:url value="/DeleteEvent" var="deleteURL"/>
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
    <h1>Eliminar Evento!</h1>
    <c:if test="${!empty error}">
        <p class="ui-state-error-text">${error}</p>
    </c:if>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <a href="${eventsURL}" class="btn btn-success">Regresar</a>
        </div>
        <div class="col-sm-10">
            <c:forEach var="e" items="${events}">
                <form action="${deleteURL}" method="post">
                    <input type="hidden" value="${e.getIdevent()}" name="id">
                    <div class="col-sm-8">
                        <div class="card text-center">
                            <h5 class="card-header">${e.getName()}</h5>
                            <div class="card-body">
                                <h4 class="card-title">${e.getCategory()}</h4>
                                <p class="card-text">${e.getDate()} | ${e.getPrize()}</p>
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
