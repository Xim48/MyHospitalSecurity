package util;


import db.PlayerDaoDS;
import db.TeamDaoDS;
import vw.PlayerVW;
import vw.TeamVW;
import vw.UserVW;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateResourses {


    static public void updateAttributes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getType()) {
                PlayerVW player = new PlayerDaoDS().findByEmail(user.getEmail());
                request.getSession().setAttribute("player", player);
                request.getSession().setAttribute("teamsPlayer", player.getTeams());
                request.getSession().setAttribute("gamesPlayer", player.getGames());
            } else {
                TeamVW team = new TeamDaoDS().findByEmail(user.getEmail());
                request.getSession().setAttribute("team", team);
                request.getSession().setAttribute("teamPlayers", team.getPlayers());
                request.getSession().setAttribute("teamEvents", team.getEvents());
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

}
