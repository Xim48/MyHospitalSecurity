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

@WebServlet(name = "DeleteTeam", urlPatterns = "/DeleteTeam")
public class DeleteTeamServlet extends HttpServlet {
    TeamDaoDS dao = new TeamDaoDS();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TeamVW> teams;
        teams = dao.getTeams();

        req.setAttribute("teams", teams);
        req.getRequestDispatcher("/WEB-INF/jsp/deleteTeam.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (dao.deleteTeam(id)) {
            resp.sendRedirect(req.getContextPath() + "/ad-Teams");
        } else {
            req.setAttribute("error", "Algo salio mal, no se pudo  eliminar el equipo");
            doGet(req, resp);
        }
    }
}
