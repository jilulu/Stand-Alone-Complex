package api.user;

import api.GsonFactory;
import model.IUser;
import model.SQLUserImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelperTest {
    @Test
    public void authUserByPassword() {
        IUser user = new SQLUserImpl("jamesji", "528491");
        IUser iUser = DatabaseHelper.getInstance().authUserByPassword(user.getUserName(), user.getUserPassword());
        assertNotNull(iUser);
        assertEquals(user.getUserPassword(), iUser.getUserPassword());
        assertEquals(user.getUserName(), iUser.getUserName());
        System.out.println(GsonFactory.getGson().toJson(iUser));

        IUser fakeUser = new SQLUserImpl("jamesji", "766666");
        assertNull(DatabaseHelper.getInstance().authUserByPassword(fakeUser.getUserName(), fakeUser.getUserPassword()));

        fakeUser = new SQLUserImpl("", "766666");
        assertNull(DatabaseHelper.getInstance().authUserByPassword(fakeUser.getUserName(), fakeUser.getUserPassword()));

        fakeUser = new SQLUserImpl("jamesji", "");
        assertNull(DatabaseHelper.getInstance().authUserByPassword(fakeUser.getUserName(), fakeUser.getUserPassword()));

        fakeUser = new SQLUserImpl("", "");
        assertNull(DatabaseHelper.getInstance().authUserByPassword(fakeUser.getUserName(), fakeUser.getUserPassword()));
    }

    @Test
    public void authUserByToken() {
        IUser user = new SQLUserImpl("jamesji", "528491");
        String token = TokenUtils.getToken(user);
        IUser returnedUser = DatabaseHelper.getInstance().authUserByToken(user.getUserName(), token);
        assertNotNull(returnedUser);

        returnedUser = DatabaseHelper.getInstance().authUserByToken(user.getUserName(), "something_random");
        assertNull(returnedUser);

        returnedUser = DatabaseHelper.getInstance().authUserByToken("", token);
        assertNull(returnedUser);

        returnedUser = DatabaseHelper.getInstance().authUserByToken("", "");
        assertNull(returnedUser);
    }
}