package api.purchaserecord;

import api.Utils;
import api.user.UserManager;
import contentprovider.bookdetail.BookDetailProviderFactory;
import model.IBook;
import model.IPurchaseRecord;
import model.IUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;

public class PurchaseRecordActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        if (sessionUser == null) {
            Utils.forwardSignInPageWithSelfRedirect(request, response);
            return;
        }

        // action ID representation
        // 1 = refund
        int actionId = -1;
        int purchaseId = -1;

        if (request.getParameter("actionId") != null)
            actionId = Integer.parseInt(request.getParameter("actionId"));
        if (request.getParameter("purchaseId") != null)
            purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
        if ((actionId == -1) || (purchaseId == -1))
        {
            request.getRequestDispatcher("/user/purchase")
                .forward(request, response);
        }
        else if (actionId == 1)
        {
            IPurchaseRecord userPurchaseRecord = PurchaseRecordManager.getManager().getUserPurchaseRecordById(purchaseId);
            if (userPurchaseRecord != null) {
                int purchaseStatus = userPurchaseRecord.getPurchaseStatus();

                if ((purchaseStatus == 0) || (purchaseStatus == 1))
                {
                    boolean result = PurchaseRecordManager.getManager().updatePurchaseRecordStatus(purchaseId, 2);
                    if (result)
                        request.setAttribute("successMessage", "The purchase record applied for refund.");
                    else
                        request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");
                }
                else
                {
                    request.setAttribute("errorMessage", "The purchase record has already applied for refund.");
                }
                IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(String.valueOf(userPurchaseRecord.getBookId()));
                request.setAttribute("purchase_record", userPurchaseRecord);
                request.setAttribute("book", iBook);
                request.getRequestDispatcher("/user/purchase_record_details.jsp")
                        .forward(request, response);

            } else {
                response.sendError(400);
            }
        }
        else response.sendError(400);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
