package controller;

import com.google.gson.Gson;
import db.GameDaoDS;
import vw.GameVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GamesToJsonServlet", value = "/api/games")
public class GamesToJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GameVW> games = new GameDaoDS().findAll();
        String json = new Gson().toJson(games);
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
