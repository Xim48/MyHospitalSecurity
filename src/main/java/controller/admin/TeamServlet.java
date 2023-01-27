package controller.admin;

import db.TeamDaoDS;
import vw.TeamVW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeamServlet", urlPatterns = "/ad-Teams")
public class TeamServlet extends HttpServlet {
    TeamDaoDS dao = new TeamDaoDS();
    List<TeamVW> teams = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        teams = dao.getTeams();

        req.setAttribute("teams", teams);
        req.getRequestDispatcher("/WEB-INF/jsp/adminTeams.jsp").forward(req, resp);
    }
}
