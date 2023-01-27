package controller;

import db.PlayerDaoDS;
import vw.PlayerVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddGamesPlayerServlet", value = "/addGamesPlayer")
public class AddGamesPlayerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        PlayerVW player = (PlayerVW) request.getSession().getAttribute("player");
        if (user != null) {
            if (user.getType()) {
                int id = Integer.parseInt(request.getParameter("id"));
                String level = request.getParameter("level");
                PlayerDaoDS playerDao = new PlayerDaoDS();
                playerDao.addGameById(player.getIdPlayer(), id, level);
                response.sendRedirect(request.getContextPath() + "/juegos");
            }
        } else response.sendRedirect(request.getContextPath() + "/home");

    }
}
