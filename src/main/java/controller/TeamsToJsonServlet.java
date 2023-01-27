package controller;

import com.google.gson.Gson;
import db.TeamDaoDS;
import vw.TeamVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeamsToJsonServlet", value = "/api/teams")
public class TeamsToJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TeamVW> teams = new TeamDaoDS().getTeams();
        String json = new Gson().toJson(teams);
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
