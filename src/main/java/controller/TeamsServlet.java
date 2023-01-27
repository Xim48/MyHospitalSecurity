package controller;

import util.UpdateResourses;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TeamsServlet", value = "/equipos")
public class TeamsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateResourses.updateAttributes(request, response);
        if (request.getSession().getAttribute("player") != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/teams.jsp").forward(request, response);
        }
    }
}
