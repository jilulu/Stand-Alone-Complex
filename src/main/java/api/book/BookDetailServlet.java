package api.book;

import contentprovider.bookdetail.BookDetailProviderFactory;
import model.IBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 09/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(id);
        if (iBook == null) {
            response.sendError(400, "Illegal book ID. ");
            return;
        }
        request.setAttribute("book", iBook);
        request.setAttribute("purchaseQuantityMax", 30);
        request.getRequestDispatcher("/book/book_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
