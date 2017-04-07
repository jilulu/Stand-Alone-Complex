package api.purchaserecord;

import api.Utils;
import api.user.UserManager;
import contentprovider.bookdetail.BookDetailProviderFactory;
import model.IBook;
import model.IPurchaseRecord;
import model.IUser;
import model.SQLPurchaseRecordImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 08/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class PurchaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = -1, paymentMethod = -1, quantity = 1;
        try {
            bookId = Integer.parseInt(request.getParameter("bookid"));
            paymentMethod = Integer.parseInt(request.getParameter("payment_method"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            // ignored
        }
        if (bookId <= 0 || paymentMethod < 0) {
            response.sendError(400);
            return;
        }
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(String.valueOf(bookId));
        if (sessionUser == null) {
            Utils.forwardSignInPageWithSelfRedirect(request, response);
            return;
        }
        IPurchaseRecord record = new SQLPurchaseRecordImpl(
                0,
                bookId,
                quantity,
                0,
                paymentMethod,
                sessionUser.getUserId(),
                Double.parseDouble(iBook.getPrice())
        );
        int recordId = PurchaseRecordManager.getManager().createPurchaseRecord(record);
        response.getWriter().println(recordId);
        response.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
