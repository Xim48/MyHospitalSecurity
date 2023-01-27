package controller;

import db.PlayerDaoDS;
import db.UserDaoDS;
import vw.PlayerVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "signUpPlayer", value = "/signUpPlayer")
public class SignUpPlayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("emailFieldS");
        boolean type = true;
        String name = request.getParameter("playerNameField");
        String middlename = request.getParameter("middleNameField");
        String lastname = request.getParameter("lastNameField");
        String password = request.getParameter("passwordFieldS");

        UserVW newUser = new UserVW(email, password, type);
        PlayerVW newPlayer = new PlayerVW(email, password, name, middlename, lastname);
        if (new UserDaoDS().signUp(newUser) && new PlayerDaoDS().signUp(newPlayer)) {
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
