package controller;

import db.PlayerDaoDS;
import vw.PlayerVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteTeamsPlayerServlet", value = "/deleteTeamsPlayer")
public class DeleteTeamsPlayerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVW user = (UserVW) request.getSession().getAttribute("user");
        PlayerVW playerVW = (PlayerVW) request.getSession().getAttribute("player");
        if (user != null) {
            if (user.getType()) {
                int id = Integer.parseInt(request.getParameter("id"));
                PlayerDaoDS player = new PlayerDaoDS();
                if (player.deleteTeamById(playerVW.getIdPlayer(), id)) {
                    response.sendRedirect(request.getHeader("Referer"));
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
