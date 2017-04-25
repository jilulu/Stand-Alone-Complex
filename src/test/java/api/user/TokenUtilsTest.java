package api.user;

import model.IUser;
import model.SQLUserImpl;
import org.junit.Test;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class TokenUtilsTest {
    @Test
    public void getToken() throws Exception {
        IUser iuser = new SQLUserImpl("jamesji", "528491");
        String token = TokenUtils.getToken(iuser);
        assert token.equals(
                TokenUtils.getToken(new SQLUserImpl("jamesji", "528491"))
        );
        assert !token.equals(
                TokenUtils.getToken(new SQLUserImpl("528491", "jamesji"))
        );
        System.out.println(token);
    }

}