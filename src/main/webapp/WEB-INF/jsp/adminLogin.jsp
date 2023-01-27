<%--
  Created by IntelliJ IDEA.
  User: javier
  Date: 22/11/22
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Administración</title>
    <link href="css/employeeStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/employeeStyle.css" rel="stylesheet"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<c:url value="/admin-login" var="login"/>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Administración<br> Bienvenido</h2>
        <p>Por favor ingrese sus credenciales</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form name="${login}" method="post">
                <div class="form-group">
                    <c:if test="${!empty error}">
                        <h5 class="text-danger">${error}</h5>
                    </c:if>
                    <label>Usuario</label>
                    <input type="text" class="form-control" placeholder="Usuario" name="user">
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" class="form-control" placeholder="Contraseña" name="psw">
                </div>
                <button type="submit" class="btn btn-black">Entrar</button>
            </form>
<%--            <a href="register.jsp" class="btn btn-outline-secondary">Registrarme</a>--%>
        </div>
    </div>
</div>
</body>
</html>
