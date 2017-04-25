package api.user.loyaltypoint;

import api.user.UserManager;
import model.IUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 25/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class LoyaltyPointsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        if (sessionUser == null) {
            String redirectURL = response.encodeRedirectURL(request.getContextPath() + "/user/signin");
            response.sendRedirect(redirectURL);
            return;
        }
        double loyaltyPoints = DatabaseHelper.getInstance().queryUserLoyaltyPoints(sessionUser.getUserId());
        request.setAttribute("loyaltyPoints", loyaltyPoints);

        request.getRequestDispatcher("/user/loyalty_points.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
