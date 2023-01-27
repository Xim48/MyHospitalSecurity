package controller;

import util.UpdateResourses;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PlayersServlet", value = "/jugadores")
public class PlayersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateResourses.updateAttributes(request, response);
        if (request.getSession().getAttribute("team") != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/players.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

}
