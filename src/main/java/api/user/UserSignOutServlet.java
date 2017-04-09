package api.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 07/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class UserSignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object username = request.getSession().getAttribute("username");
        Object token = request.getSession().getAttribute("token");
        final String redirectUrl = response.encodeRedirectURL(request.getContextPath() + "/");
        if (username != null) {
            request.getSession().removeAttribute("username");
        }
        if (token != null) {
            request.getSession().removeAttribute("token");
        }
        response.sendRedirect(redirectUrl);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
