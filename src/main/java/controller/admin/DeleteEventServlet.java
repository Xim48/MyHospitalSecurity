package controller.admin;

import db.EventDaoDS;
import vw.EventVW;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteEventServlet", urlPatterns = "/DeleteEvent")
public class DeleteEventServlet extends HttpServlet {
    EventDaoDS dao = new EventDaoDS();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        EventDaoDS dao = new EventDaoDS();

        if (dao.deleteEvent(id)) {
            res.sendRedirect(req.getContextPath() + "/Events");
        } else {
            req.setAttribute("error", "Algo salió mal, intente después");
            doGet(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EventVW> events = dao.getEvents();
        // this is the response to the servlet, the way to send the information
        req.setAttribute("events", events);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/deleteEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
