package api.user;

import model.IUser;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class TokenUtils {

    private static final String SALT = "MaidDragonLovesKobayashi";

    static String getToken(IUser user) {
        assert user.getUserName() != null;
        assert user.getUserPassword() != null;
        String unencrypted = user.getUserName() + user.getUserPassword() + SALT;
        return DigestUtils.sha256Hex(unencrypted);
    }
}
