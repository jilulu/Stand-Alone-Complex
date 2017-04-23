package api.purchaserecord;


import api.user.UserManager;
import model.IPurchaseRecord;
import model.IAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AdminRefundApproveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IAdmin sessionUser = UserManager.getManager().getAdminSessionUser(request);
        if (sessionUser == null) {
            request.getRequestDispatcher("/admin/signin.jsp").forward(request, response);
            return;
        }

        String errorMessage = new String("");

        int inputId = -1;

        if (request.getParameter("id") != null)
            inputId = Integer.parseInt(request.getParameter("id"));


        if (inputId >= 0)
        {
            boolean result = PurchaseRecordManager.getManager().updatePurchaseRecordStatus(inputId, 3);
            if (result)
                request.setAttribute("successMessage", "Approved one record.");
            else
                request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");
        }
        else
            request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");

        request.getRequestDispatcher("/admin/refund/view").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
