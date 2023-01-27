package controller;

import util.UpdateResourses;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GamesServlet", value = "/juegos")
public class GamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateResourses.updateAttributes(request, response);
        if (request.getSession().getAttribute("player") != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/games.jsp").forward(request, response);
        }
    }
}
