<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/globalStyles.css"/>
        <title>Tus jugadores | ${team.getName()}</title>
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
                    jugadores</a></li>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/gamepad.png" alt=""/><a
                        href="eventos">Mis eventos</a></li>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/logout.png" alt=""/><a
                        href="signout">Salir</a></li>
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
                    <h1>¡Bienvenidos, ${team.getName()}!</h1>
                </div>
            </div>
            <div class="dashboardPage">
                <div class="teamsSectionP">
                    <h2>Tus jugadores</h2>
                    <button class="addButton" id="addButton">Agregar nuevo</button>
                    <div class="cardsContainerPage">
                        <c:choose>
                            <c:when test="${!empty teamPlayers}">
                                <c:forEach var="p" items="${teamPlayers}">

                                    <div class="teamCard card">
                                        <h3>${p.name} ${p.lastName}</h3>
                                        <p>Juegos:
                                            <c:forEach var="game" items="${p.games}">
                                                <c:out value="${game.title} "/>
                                            </c:forEach>
                                        </p>
                                        <form action="deletePlayersTeam" method="post">
                                            <input type="hidden" name="id" value="${p.idPlayer}">
                                            <button class="delB" type="submit">Eliminar</button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:out value="<div class=\"emptyAdvice\"><p>Aún no tienes jugadores</p></div>"
                                       escapeXml="false"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </main>
        <dialog class="addNewMainWindow" id="addNewMainWindow">
            <div class="addNewWindow">
                <h3>Agregar nuevo jugador</h3>
                <button class="closeButton" id="closeAddWindowButton">Cerrar</button>
                <div class="searchbarContainer searchbarNewWindow">
                    <div class="searchbar">
                        <img src="./resources/Icons/search.png" alt=""/>
                        <div action="search" method="get">
                            <input type="text" placeholder="Busca jugadores" id="searchAddPlayer"/>
                        </div>
                    </div>
                </div>
                <div class="resultsContainer" id="resultsContainer"></div>
            </div>
        </dialog>
        <script src="resources/js/addNewWindow.js"></script>
        <script src="resources/js/searchPlayers.js"></script>
    </body>
</html>

