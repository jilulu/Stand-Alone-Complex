package api.book;

import api.user.UserManager;

import model.IAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminBookDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IAdmin sessionUser = UserManager.getManager().getAdminSessionUser(request);
        if (sessionUser == null) {
            request.getRequestDispatcher("/admin/signin.jsp")
                        .forward(request, response);
            return;
        }

        int inputId = -1;

        if (request.getParameter("id") != null)
            inputId = Integer.parseInt(request.getParameter("id"));

        if (inputId != -1)
        {
            boolean result = DatabaseHelper.getInstance().deleteBookById(inputId);
            if (result)
                request.setAttribute("successMessage", "One book deleted.");
            else
                request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");

            request.getRequestDispatcher("/admin/book_view.jsp")
                        .forward(request, response);
        }
        else
        {
            request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");
            request.getRequestDispatcher("/admin/book_view.jsp")
                        .forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
