package api.user;

import model.SQLUserImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class UserAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("userpassword");
        SQLUserImpl sqlUser = new SQLUserImpl(userName, userPassword);
        DatabaseHelper.StatusCode statusCode = DatabaseHelper.getInstance().addUser(sqlUser);
        switch (statusCode.getCode()) {
            case DatabaseHelper.StatusCode.INVALID_COMBINATION:
            case DatabaseHelper.StatusCode.USER_NAME_EXISTS:
                request.setAttribute("errorMessage", statusCode.getMessage());
                request.getRequestDispatcher("/user/signup").forward(request, response);
                break;
            case DatabaseHelper.StatusCode.UNDETERMINED:
                request.setAttribute("errorMessage", "An unknown error occurred. Please try again. ");
                request.getRequestDispatcher("/user/signup").forward(request, response);
                break;
            case DatabaseHelper.StatusCode.SUCCESSFUL:
                String token = TokenUtils.getToken(sqlUser);
                request.getSession().setAttribute("username", userName);
                request.getSession().setAttribute("token", token);
                response.sendRedirect(response.encodeRedirectURL("/user/purchase"));
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
