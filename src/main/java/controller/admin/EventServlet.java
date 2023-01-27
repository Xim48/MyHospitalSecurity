package controller.admin;

import db.EventDaoDS;
import vw.EventVW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EventServlet", urlPatterns = "/Events")
public class EventServlet extends HttpServlet {
    EventDaoDS dao = new EventDaoDS();
    List<EventVW> events = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // method to get the items
        events = dao.getEvents();
        // this is the response to the servlet, the way to send the information
        req.setAttribute("events", events);
        req.getRequestDispatcher("/WEB-INF/jsp/Dashboard.jsp").forward(req, res);
    }
}
