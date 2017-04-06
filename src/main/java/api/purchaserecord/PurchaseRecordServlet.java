package api.purchaserecord;

import api.user.UserManager;
import model.IUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jamesji on 06/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class PurchaseRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        String redirectURL;
        if (sessionUser != null) {
            redirectURL = response.encodeRedirectURL("/user/purchase_record.jsp");
        } else {
            redirectURL = response.encodeRedirectURL("/user/sign_in.jsp");
        }
        response.sendRedirect(redirectURL);
    }
}
