<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/globalStyles.css"/>
        <title>Teamder | ${player.getName()} ${player.getLastName()}</title>
    </head>
    <body>
        <header>
            <p class="logo">Logo</p>
        </header>
        <nav>
            <ul>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/home.png" alt=""/><a href="#">Inicio</a>
                </li>
                <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/group.png" alt=""/><a
                        href="equipos">Mis equipos</a></li>
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
                    <h1>¬°Bienvenido, ${player.getName()} ${player.getLastName()}!</h1>
                </div>
            </div>
            <div class="dashboard">
                <div class="teamsSection">
                    <h2>Tus equipos</h2>
                    <div class="cardsContainer">
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
                                <c:out value="<div class=\"emptyAdvice\"><p>A√ļn no tienes equipos</p></div>"
                                       escapeXml="false"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="gamesSection">
                    <h2>Tus juegos</h2>
                    <div class="cardsContainer">
                        <c:choose>
                            <c:when test="${!empty gamesPlayer}">
                                <c:forEach var="gP" items="${gamesPlayer}">
                                    <div class="gameCard card">
                                        <h3>${gP.title}</h3>
                                        <p>${gP.desc}</p>
                                        <form action="deleteGamesPlayer" method="post">
                                            <input type="hidden" name="id" value="${gP.idGame}">
                                            <button class="delB" type="submit">Eliminar</button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:out value="<div class=\"emptyAdvice\"><p>A√ļn no tienes juegos</p></div>"
                                       escapeXml="false"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
