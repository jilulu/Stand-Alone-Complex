package api.user;

import model.IUser;
import model.IAdmin;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jamesji on 06/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class UserManager {

    private static UserManager mInstance;

    private UserManager() {

    }

    public static UserManager getManager() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new UserManager();
                }
            }
        }
        return mInstance;
    }

    public IUser getSessionUser(HttpServletRequest request) {
        String usernameAttribute = (String) request.getSession().getAttribute("username");
        String userTokenAttribute = (String) request.getSession().getAttribute("token");

        // Avoid hitting database with null values
        if (usernameAttribute == null || userTokenAttribute == null) {
            return null;
        } else {
            return DatabaseHelper.getInstance().authUserByToken(usernameAttribute, userTokenAttribute);
        }

    }

    public IAdmin getAdminSessionUser(HttpServletRequest request) {
        String usernameAttribute = (String) request.getSession().getAttribute("admin_username");
        String userTokenAttribute = (String) request.getSession().getAttribute("admin_token");

        // Avoid hitting database with null values
        if (usernameAttribute == null || userTokenAttribute == null) {
            return null;
        } else {
            return DatabaseHelper.getInstance().authAdminByToken(usernameAttribute, userTokenAttribute);
        }

    }
}
