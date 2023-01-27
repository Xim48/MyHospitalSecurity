package controller;

import db.EventDaoDS;
import db.PlayerDaoDS;
import vw.EventVW;
import vw.TeamVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddEventsTeamServlet", value = "/addEventsTeam")
public class AddEventsTeamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        TeamVW team = (TeamVW) request.getSession().getAttribute("team");
        if (user != null) {
            if (!user.getType()) {
                EventVW event = new EventVW();
                event.setName(request.getParameter("eventName"));
                event.setLocation(request.getParameter("eventLocation"));
                event.setCategory(request.getParameter("eventCategory"));
                event.setDate(Date.valueOf(request.getParameter("eventDate")));
                event.setPrize(request.getParameter("eventPrize"));
                EventDaoDS eventDao = new EventDaoDS();
                if (eventDao.addEventTeam(team.getIdteam(), event))
                    response.sendRedirect(request.getContextPath() + "/eventos");
            }
        } else response.sendRedirect(request.getContextPath() + "/home");
    }
}
