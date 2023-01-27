package controller.admin;

import db.PlayerDaoDS;
import vw.PlayerVW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PlayerServlet", urlPatterns = "/ad-Players")
public class PlayerServlet extends HttpServlet {
    PlayerDaoDS dao = new PlayerDaoDS();
    List<PlayerVW> players = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        players = dao.findAll();
        if (players.isEmpty()){
            System.out.println("no trae nada");
        }

        req.setAttribute("players", players);
        req.getRequestDispatcher("/WEB-INF/jsp/adminPlayers.jsp").forward(req, res);
    }
}
