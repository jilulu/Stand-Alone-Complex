package api.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminSignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object username = request.getSession().getAttribute("admin_username");
        Object token = request.getSession().getAttribute("admin_token");
        if (username != null) {
            request.getSession().removeAttribute("admin_username");
        }
        if (token != null) {
            request.getSession().removeAttribute("admin_token");
        }
        request.getRequestDispatcher("/admin/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
