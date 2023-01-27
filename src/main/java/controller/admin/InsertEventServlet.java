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

@WebServlet(name = "InsertEventServlet", urlPatterns = "/InsertEvent")
public class InsertEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventVW event = new EventVW();
        event.setName(req.getParameter("name"));
        event.setLocation(req.getParameter("location"));
        event.setCategory(req.getParameter("category"));
        event.setDate(Date.valueOf(req.getParameter("date")));
        event.setPrize(req.getParameter("prize"));

        EventDaoDS dao = new EventDaoDS();
        if (dao.insertEvent(event)) {
            resp.sendRedirect(req.getContextPath() + "/Events");
        } else {
            req.setAttribute("error", "No se pudo registrar");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/insertEvent.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/insertEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
