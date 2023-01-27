<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/globalStyles.css"/>
    <title>Tus eventos | ${team.getName()}</title>
  </head>
  <body>
    <header>
      <p class="logo">Logo</p>
    </header>
    <nav>
      <ul>
        <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/home.png" alt=""/><a
                href="home">Inicio</a></li>
        <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/group.png" alt=""/><a href="jugadores">Mis
          jugadores</a></li>
        <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/gamepad.png" alt=""/><a href="#">Mis
          eventos</a></li>
        <li><img src="${pageContext.servletContext.contextPath}/resources/Icons/logout.png" alt=""/><a href="signout">Salir</a>
        </li>
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
        <div class="gamesSectionP">
          <h2>Tus eventos</h2>
          <button class="addButton" id="addButton">Agregar nuevo</button>
          <div class="cardsContainerPage">
            <c:choose>
              <c:when test="${!empty teamEvents}">
                <c:forEach var="eT" items="${teamEvents}">
                  <div class="gameCard card">
                    <h3>${eT.name}</h3>
                    <p>${eT.location} | ${eT.date} | ${eT.prize}</p>
                    <form action="deleteEventsTeam" method="post">
                      <input type="hidden" name="id" value="${eT.idevent}">
                      <button class="delB" type="submit">Eliminar</button>
                    </form>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <c:out value="<div class=\"emptyAdvice\"><p>Aún no tienes eventos</p></div>" escapeXml="false"/>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
    </main>
    <dialog class="addNewMainWindow" id="addNewMainWindow">
      <div class="addNewEventWindow">
        <h3>Agregar nuevo evento</h3>
        <button class="closeButton" id="closeAddWindowButton">Cerrar</button>
        <div class="formContainer">
          <form action="addEventsTeam" method="post">
            <label for="eventName">Nombre del evento:</label>
            <input type="text" name="eventName" id="eventName">
            <label for="eventLocation">Ubicaión del evento:</label>
            <input type="text" name="eventLocation" id="eventLocation">
            <label for="eventCatergory">Categoría:</label>
            <input type="text" name="eventCategory" id="eventCatergory">
            <label for="eventDate">Fecha:</label>
            <input type="date" name="eventDate" id="eventDate">
            <label for="eventPrize">Premio:</label>
            <input type="text" name="eventPrize" id="eventPrize">
            <button type="reset" value="cancel" id="cancelAddWindowButton">Cancelar</button>
            <button type="submit" value="agregar" id="addFormButton"/>
            Agregar</input>

          </form>
        </div>
      </div>
    </dialog>
    <script src="resources/js/addNewWindow.js"></script>
  </body>
</html>

