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
import java.sql.Date;
import java.util.List;

@WebServlet(name = "updateEvent", urlPatterns = "/updateEvent")
public class UpdateEventServlet extends HttpServlet {
    EventDaoDS dao = new EventDaoDS();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EventVW> events = dao.getEvents();
        // this is the response to the servlet, the way to send the information
        req.setAttribute("events", events);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/updateEvent.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventVW event = new EventVW();
        event.setName(req.getParameter("name"));
        event.setLocation(req.getParameter("location"));
        event.setCategory(req.getParameter("category"));
        event.setDate(Date.valueOf(req.getParameter("date")));
        event.setPrize(req.getParameter("prize"));
        event.setIdevent(Integer.parseInt(req.getParameter("idevent")));

        if (dao.updateEvent(event)) {
            resp.sendRedirect(req.getContextPath() + "/Events");
        } else {
            req.setAttribute("error", "Algo salio mal, intente despues");
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/jsp/updateEvent.jsp");
            doGet(req, resp);
        }
    }
}
