package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import db.PlayerDaoDS;
import util.HttpRequests;
import util.UpdateResourses;
import vw.PlayerVW;
import vw.UserVW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateResourses.updateAttributes(request, response);
        if (request.getSession().getAttribute("player") != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/homePlayer.jsp").forward(request, response);
        } else if (request.getSession().getAttribute("team") != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/homeTeam.jsp").forward(request, response);
        }
    }
}
