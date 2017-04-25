package api.book;

import api.Utils;
import contentprovider.bookquery.BookQueryProviderFactory;
import model.IQueryResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 11/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookSearchServlet extends HttpServlet {

    private static final int QUERY_RESULTS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        int offset = 0;
        if (query == null || query.length() == 0) {
            response.sendError(400, "No query keyword provided. ");
            return;
        }
        if (request.getParameter("offset") != null) {
            try {
                offset = Integer.parseInt(request.getParameter("offset"));
            } catch (NumberFormatException e) {
                // ignored
            }
        }
        IQueryResult queryResult = BookQueryProviderFactory.getProvider().queryForBooks(query, offset, QUERY_RESULTS_PER_PAGE);

        request.setAttribute("query", query);
        request.setAttribute("result", queryResult);

        String searchLinkPreviousPage = Utils.getSearchLinkPreviousPage(request, query, offset, queryResult.getTotal(), QUERY_RESULTS_PER_PAGE);
        String searchLinkNextPage = Utils.getSearchLinkNextPage(request, query, offset, queryResult.getTotal(), QUERY_RESULTS_PER_PAGE);
        request.setAttribute("previousPage", searchLinkPreviousPage);
        request.setAttribute("nextPage", searchLinkNextPage);

        request.getRequestDispatcher("/book/search.jsp").forward(request, response);
    }
}