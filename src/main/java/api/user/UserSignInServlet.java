package api.user;

import model.IUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class UserSignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        if (username == null || userpassword == null) {
//            response.sendError(400);
            response.sendRedirect(response.encodeRedirectURL("/user/sign_in.jsp"));
            return;
        }
        IUser iUser = DatabaseHelper.getInstance().authUserByPassword(username, userpassword);
        if (iUser == null) {
//            response.sendError(400);
            response.sendRedirect(response.encodeRedirectURL("/user/sign_in.jsp"));
            return;
        }
        String token = TokenUtils.getToken(iUser);
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("token", token);
        String redirectURL;
        String paramRedirectUrl = request.getParameter("redirect_url");
        if (paramRedirectUrl != null && paramRedirectUrl.length() > 0) {
            redirectURL = paramRedirectUrl;
        } else {
            redirectURL = response.encodeRedirectURL("/user/purchase");
        }
        response.sendRedirect(redirectURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
