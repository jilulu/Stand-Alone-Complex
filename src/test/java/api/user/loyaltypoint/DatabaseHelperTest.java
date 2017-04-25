package api.user.loyaltypoint;

import org.junit.Test;

/**
 * Created by jamesji on 25/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelperTest {
    @Test
    public void initUser() throws Exception {
//        DatabaseHelper.getInstance().initUser(13);
    }

    @Test
    public void queryUserLoyaltyPoints() throws Exception {
        double v = DatabaseHelper.getInstance().queryUserLoyaltyPoints(13);
        System.out.println(v);
    }

    @Test
    public void updateUserLoyaltyPoint() throws Exception {
//        DatabaseHelper.getInstance().updateUserLoyaltyPoint(13, 2);
    }

}