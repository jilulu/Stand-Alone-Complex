package api.purchaserecord;

import api.GsonFactory;
import model.IPurchaseRecord;
import model.IUser;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jamesji on 06/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelperTest {
    @Test
    public void getPurchaseRecordsByUserId() {
        IUser user = api.user.DatabaseHelper.getInstance().authUserByPassword("jamesji", "528491");
        Collection<IPurchaseRecord> purchaseRecordsByUserId = DatabaseHelper.getInstance().getPurchaseRecordsByUserId(user.getUserId());
        assertNotNull(purchaseRecordsByUserId);
        assertNotEquals(purchaseRecordsByUserId.size(), 0);
        for (IPurchaseRecord aPurchaseRecordsByUserId : purchaseRecordsByUserId) {
            System.out.println(GsonFactory.getGson().toJson(aPurchaseRecordsByUserId));
        }
    }

    @Test
    public void getPurchaseRecordsWithTitleByUserId() {
        IUser user = api.user.DatabaseHelper.getInstance().authUserByPassword("jamesji", "528491");
        Map<IPurchaseRecord, String> map = DatabaseHelper.getInstance().getPurchaseRecordsWithTitleByUserId(user.getUserId());
        assertNotNull(map);
        assertNotEquals(map.size(), 0);
        System.out.println(GsonFactory.getGson().toJson(map));
    }
}