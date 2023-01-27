package controller.admin;

import db.PlayerDaoDS;
import vw.PlayerVW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeletePlayer", urlPatterns = "/DeletePlayer")
public class DeletePlayerServlet extends HttpServlet {
    PlayerDaoDS dao = new PlayerDaoDS();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PlayerVW> players;
        players = dao.findAll();

        req.setAttribute("players", players);
        req.getRequestDispatcher("/WEB-INF/jsp/deletePlayer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        if (dao.deletePlayer(email)) {
            resp.sendRedirect(req.getContextPath() + "/ad-Players");
        } else {
            req.setAttribute("error", "Algo salio mal, no se pudo eliminar el equipo");
            doGet(req, resp);
        }
    }
}
