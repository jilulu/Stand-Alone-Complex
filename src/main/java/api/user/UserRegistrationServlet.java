package api.user;

import model.SQLUserImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class UserRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("userpassword");
        SQLUserImpl sqlUser = new SQLUserImpl(userName, userPassword);
        DatabaseHelper.StatusCode statusCode = DatabaseHelper.getInstance().addUser(sqlUser);
        switch (statusCode.getCode()) {
            case DatabaseHelper.StatusCode.INVALID_COMBINATION:
            case DatabaseHelper.StatusCode.USER_NAME_EXISTS:
                String redirectUrl = "/user/registration_result.jsp?success=false&error_message=" + statusCode.getMessage();
                response.sendRedirect(response.encodeRedirectURL(redirectUrl));
                break;
            case DatabaseHelper.StatusCode.UNDETERMINED:
                redirectUrl = "/user/registration_result.jsp?success=false&error_message=Unknown error";
                response.sendRedirect(response.encodeRedirectURL(redirectUrl));
                break;
            case DatabaseHelper.StatusCode.SUCCESSFUL:
                redirectUrl = "/user/registration_result.jsp?success=true";
                Cookie usernameCookie = new Cookie("username", userName);
                usernameCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
                usernameCookie.setPath("/");
                response.addCookie(usernameCookie);
                response.sendRedirect(redirectUrl);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
