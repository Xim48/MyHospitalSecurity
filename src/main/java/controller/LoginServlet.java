package controller;

import db.PlayerDaoDS;
import db.TeamDaoDS;
import db.UserDaoDS;
import vw.PlayerVW;
import vw.TeamVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("loginError", 0);
        UserVW user = new UserDaoDS().findByLogin(request.getParameter("emailFieldL"), request.getParameter("passwordFieldL"));
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            session.setAttribute("loginError", 1);
            response.sendRedirect(request.getContextPath());
        }
    }
}
