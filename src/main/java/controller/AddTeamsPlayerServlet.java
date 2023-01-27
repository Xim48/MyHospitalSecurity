package controller;

import db.PlayerDaoDS;
import vw.PlayerVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddTeamsPlayerServlet", value = "/addTeamsPlayer")
public class AddTeamsPlayerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        PlayerVW player = (PlayerVW) request.getSession().getAttribute("player");
        if (user != null) {
            if (user.getType()) {
                int id = Integer.parseInt(request.getParameter("id"));
                PlayerDaoDS playerDao = new PlayerDaoDS();
                playerDao.addTeamById(player.getIdPlayer(), id);
                response.sendRedirect(request.getContextPath() + "/equipos");
            }
        } else response.sendRedirect(request.getContextPath() + "/home");
    }
}
