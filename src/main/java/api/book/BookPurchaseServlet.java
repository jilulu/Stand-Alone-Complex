package api.book;

import api.Utils;
import api.user.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        if (UserManager.getManager().getSessionUser(request) == null) {
            Utils.forwardSignInPageWithSelfRedirect(request, response);
        }
        response.sendRedirect(response.encodeRedirectURL("/book/order_confirm.jsp?bookid=" + bookId + "&quantity="+quantity));
    }
}
