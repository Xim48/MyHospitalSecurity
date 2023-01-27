package controller;

import db.TeamDaoDS;
import vw.TeamVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteEventsTeamServlet", value = "/deleteEventsTeam")
public class DeleteEventsTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        TeamVW team = (TeamVW) request.getSession().getAttribute("team");
        if (user != null) {
            if (!user.getType()) {
                int id = Integer.parseInt(request.getParameter("id"));
                TeamDaoDS teamDao = new TeamDaoDS();
                if (teamDao.deleteEventById(team.getIdteam(), id)) {
                    response.sendRedirect(request.getHeader("Referer"));
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
