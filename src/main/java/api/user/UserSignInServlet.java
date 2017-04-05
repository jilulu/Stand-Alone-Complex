package api.user;

import model.IUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
@WebServlet(name = "UserSignInServlet")
public class UserSignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String usernameAttribute = (String) request.getSession().getAttribute("username");
//        String userTokenAttribute = (String) request.getSession().getAttribute("token");
//        if (usernameAttribute != null && userTokenAttribute != null) {
//            response.setContentType("text/plain");
//            PrintWriter writer = response.getWriter();
//            writer.print("authed");
//            writer.close();
//            return;
//        }
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        if (username == null || userpassword == null) {
            response.sendError(400);
            return;
        }
        IUser iUser = DatabaseHelper.getInstance().authUserByPassword(username, userpassword);
        if (iUser == null) {
            response.sendError(400);
            return;
        }
        String token = TokenUtils.getToken(iUser);
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("token", token);
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        writer.print(token);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
