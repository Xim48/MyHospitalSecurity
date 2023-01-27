package controller;

import db.TeamDaoDS;
import db.UserDaoDS;
import vw.TeamVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "signUpTeam", value = "/signUpTeam")
public class SignUpTeamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("emailFieldS");
        boolean type = false;
        String teamName = request.getParameter("teamNameField");
        String leaderName = request.getParameter("leaderField");
        String password = request.getParameter("passwordFieldS");
        String confirmPassword = request.getParameter("confirmPasswordFieldS");

        UserVW newUser = new UserVW(email, password, type);
        TeamVW newTeam = new TeamVW(email, teamName, leaderName);
        if (new UserDaoDS().signUp(newUser) && new TeamDaoDS().signUp(newTeam)) {
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
