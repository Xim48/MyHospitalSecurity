package controller;

import util.UpdateResourses;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EventsServlet", value = "/eventos")
public class EventsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateResourses.updateAttributes(request, response);
        if (request.getSession().getAttribute("team") != null)
            request.getRequestDispatcher("/WEB-INF/jsp/events.jsp").forward(request, response);
    }
}
