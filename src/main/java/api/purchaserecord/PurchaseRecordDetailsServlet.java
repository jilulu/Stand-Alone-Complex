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

/**
 * Created by jamesji on 08/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class PurchaseRecordDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        if (sessionUser == null) {
            Utils.forwardSignInPageWithSelfRedirect(request, response);
            return;
        }

        int purchaseId = -1;

        String contextPath = request.getRequestURI();
        Pattern idPattern = Pattern.compile("/user/purchase/(\\d+)");
        Matcher matcher = idPattern.matcher(contextPath);
        if (matcher.find()) {
            purchaseId = Integer.parseInt(matcher.group(1));
        }

        IPurchaseRecord userPurchaseRecord = PurchaseRecordManager.getManager().getUserPurchaseRecordById(purchaseId);

        if (userPurchaseRecord != null) {
            IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(String.valueOf(userPurchaseRecord.getBookId()));
            request.setAttribute("purchase_record", userPurchaseRecord);
            request.setAttribute("book", iBook);
            request.getRequestDispatcher("/user/purchase_record_details.jsp")
                    .forward(request, response);
        } else {
            response.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
