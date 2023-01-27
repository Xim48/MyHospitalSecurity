<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/globalStyles.css"/>
        <title>Teamder | Mis equipos</title>
    </head>
    <body>
        <header>
            <p class="logo">Logo</p>
        </header>
        <nav>
            <ul>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/home.png" alt=""/><a
                        href="home">Inicio</a></li>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/group.png" alt=""/><a href="#">Mis
                    equipos</a></li>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/gamepad.png" alt=""/><a
                        href="juegos">Mis juegos</a></li>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/logout.png" alt=""/><a
                        href="<c:out value="${pageContext.servletContext.contextPath}"/>/signout">Salir</a></li>
            </ul>
        </nav>
        <main>
            <div class="upperSection">
                <div class="searchbarContainer">
                    <div class="searchbar">
                        <img src="${pageContext.servletContext.contextPath}/resources/Icons/search.png" alt=""/>
                        <form action="search" method="get">
                            <input type="text" placeholder="Busca equipos o juegos"/>
                        </form>
                    </div>
                </div>
                <div class="greetingSection">
                    <h1>¡Bienvenido, ${player.getName()} ${player.getLastName()}!</h1>
                </div>
            </div>
            <div class="dashboardPage">
                <div class="teamsSectionP">
                    <h2>Tus equipos</h2>
                    <button class="addButton" id="addButton">Agregar nuevo</button>
                    <div class="cardsContainerPage">
                        <c:choose>
                            <c:when test="${!empty teamsPlayer}">
                                <c:forEach var="t" items="${teamsPlayer}">
                                    <div class="teamCard card">
                                        <h3>${t.name}</h3>
                                        <p>${t.events}</p>
                                        <form action="deleteTeamsPlayer" method="post">
                                            <input type="hidden" name="id" value="${t.idteam}">
                                            <button class="delB" type="submit">Eliminar</button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:out value="<div class=\"emptyAdvice\"><p>Aún no tienes equipos</p></div>"
                                       escapeXml="false"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </main>
        <dialog class="addNewMainWindow" id="addNewMainWindow">
            <div class="addNewWindow">
                <h3>Agregar nuevo equipo</h3>
                <button class="closeButton" id="closeAddWindowButton">Cerrar</button>
                <div class="searchbarContainer searchbarNewWindow">
                    <div class="searchbar">
                        <img src="${pageContext.servletContext.contextPath}/resources/Icons/search.png" alt=""/>
                        <div>
                            <input type="text" placeholder="Busca equipos por nombre o por evento" id="searchAddTeam"/>
                        </div>
                    </div>
                </div>
                <div class="resultsContainer" id="resultsContainer">
                </div>
            </div>
        </dialog>
        <script src="${pageContext.servletContext.contextPath}/resources/js/addNewWindow.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/js/searchTeams.js"></script>
    </body>
</html>