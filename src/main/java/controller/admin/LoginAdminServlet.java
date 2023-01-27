package controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginAdminServlet", value = "/admin-login")
public class LoginAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String psw = req.getParameter("psw");

        if (user.isEmpty() ){
            req.setAttribute("error", "Porfavor ingrese su usuario");
            doGet(req, resp);
        } else if (user.equals("admin") & psw.equals("Passw0rd")) {
            resp.sendRedirect(req.getContextPath() + "/Events");
        } else if(psw.isEmpty()){
            req.setAttribute("error", "Porfavor ingrese su contraseña");
            doGet(req, resp);
        } else {
            req.setAttribute("error", "Credenciales incorrectas");
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp").forward(req, resp);
    }
}
