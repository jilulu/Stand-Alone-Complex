package api.user;

import api.Utils;
import model.IAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        if (username == null || userpassword == null) {
            request.setAttribute("errorMessage", "Either username or password is empty. Please try again. ");
            request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
        }

        IAdmin iAdmin = DatabaseHelper.getInstance().authAdminByPassword(username, userpassword);
        if (iAdmin == null) {
            request.setAttribute("errorMessage", "Either username or password is incorrect. " +
                    "Please try again. ");
            request.getRequestDispatcher("/admin/signin.jsp").forward(request, response);
        }
        
        String token = TokenUtils.getToken(iAdmin);
        request.getSession().setAttribute("admin_username", username);
        request.getSession().setAttribute("admin_token", token);
        String redirectURL;

        redirectURL = response.encodeRedirectURL(request.getContextPath() + "/admin/index.jsp");
        response.sendRedirect(redirectURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
