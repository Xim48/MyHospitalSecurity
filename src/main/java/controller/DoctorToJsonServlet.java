package controller;

import com.google.gson.Gson;
import db.PlayerDaoDS;
import vw.PlayerVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlayersToJsonServlet", value = "/api/players")
public class PlayersToJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PlayerVW> players = new PlayerDaoDS().findAll();
        String json = new Gson().toJson(players);
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
