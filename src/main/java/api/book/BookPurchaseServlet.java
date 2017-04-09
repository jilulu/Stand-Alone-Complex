package api.book;

import api.Utils;
import api.purchaserecord.DatabaseHelper;
import api.user.UserManager;
import contentprovider.bookdetail.BookDetailProviderFactory;
import model.IBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by jamesji on 07/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookPurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = 0, quantity = 0;
        IBook book;
        try {
            bookId = Integer.parseInt(request.getParameter("book_id"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            // ignored
        }
        if (!(bookId > 0 && quantity > 0)) {
            response.sendError(400);
            return;
        }
        if ((book = BookDetailProviderFactory.getProvider().provideBookDetails(String.valueOf(bookId))) == null) {
            response.sendError(400);
            return;
        }
        if (UserManager.getManager().getSessionUser(request) == null) {
            Utils.forwardSignInPageWithSelfRedirect(request, response);
            return;
        }
        request.setAttribute("bookid", bookId);
        request.setAttribute("quantity", quantity);
        request.setAttribute("book", book);
        request.setAttribute("paymentMethodList", Arrays.asList(DatabaseHelper.Table.DEFINITION_PAYMENT_RECORD));
        request.getRequestDispatcher("/book/order_confirm.jsp")
                .forward(request, response);
    }
}
