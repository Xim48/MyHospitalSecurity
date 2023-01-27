//const endpoint = 'http://127.0.0.1:8080/ProyectoAppRed/api/games';
const endpoint = 'http://52.8.109.6:8080/ProyectoAppRed/api/games';
const resultsContainer = document.getElementById("resultsContainer");
const searchAddGame = document.getElementById("searchAddGame");


let resultContainer = document.createElement("div");
resultContainer.classList.add("resultContainer");
resultContainer.innerHTML =
    `<div class="resultContainer>
<h4>Nombre del juego</h4>
 <button>Añadir</button>
 </div>`;

let games, jsonGames;

function getGames() {


    let req = new XMLHttpRequest();
    req.open("GET", endpoint);
    req.send();
    req.onreadystatechange = function () {
        searchAddGame.addEventListener('keydown', function () {
            if (req.readyState == 4 && req.status == 200) {
                games = JSON.parse(req.responseText);
                resultsContainer.innerHTML = '';
                const text = searchAddGame.value.toLowerCase();
                for (let game of games) {
                    let title = game.title.toLowerCase();
                    if (title.indexOf(text) !== -1) {
                        resultsContainer.innerHTML += `
              <div class="resultContainer resultGameContainer">
                <h4>${game.title}</h4>
                <form action="addGamesPlayer" method="post">
                  <input type="hidden" name="id" value="${game.idGame}">
                  <label for="levelChooser">Selecciona tu nivel: </label>
                  <select name="level" id="levelChooser">
                    <option value="Principiante">Principiante</option>
                    <option value="Intermedio">Intermedio</option>
                    <option value="Avanzado">Avanzado</option>
                  </select>
                  <button>Añadir</button>
                </form>
              </div>`;
                    }
                }
            }
        });
    }
}

getGames();

