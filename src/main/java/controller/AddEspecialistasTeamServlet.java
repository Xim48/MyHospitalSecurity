package controller;

import db.PlayerDaoDS;
import db.TeamDaoDS;
import vw.TeamVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddPlayersTeamServlet", value = "/addPlayersTeam")
public class AddPlayersTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        TeamVW team = (TeamVW) request.getSession().getAttribute("team");
        if (user != null) {
            if (!user.getType()) {
                int id = Integer.parseInt(request.getParameter("id"));
                PlayerDaoDS playerDao = new PlayerDaoDS();
                playerDao.addTeamById(id, team.getIdteam());
                response.sendRedirect(request.getContextPath() + "/jugadores");
            }
        } else response.sendRedirect(request.getContextPath() + "/home");
    }
}
