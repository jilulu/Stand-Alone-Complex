package api.purchaserecord;

import api.user.UserManager;
import model.IPurchaseRecord;

import java.util.Collection;
import java.util.Map;

/**
 * Created by jamesji on 06/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class PurchaseRecordManager {
    private static PurchaseRecordManager mInstance;

    private PurchaseRecordManager() {

    }

    public static PurchaseRecordManager getManager() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new PurchaseRecordManager();
                }
            }
        }
        return mInstance;
    }

    public Collection<IPurchaseRecord> getUserPurchaseRecord(int userId) {
        return DatabaseHelper.getInstance().getPurchaseRecordsByUserId(userId);
    }

    public Map<IPurchaseRecord, String> getUserPurchaseRecordWithTitle(int userId) {
        return DatabaseHelper.getInstance().getPurchaseRecordsWithTitleByUserId(userId);
    }

    public IPurchaseRecord getUserPurchaseRecordById(int purchaseRecordId) {
        return DatabaseHelper.getInstance().getPurchaseRecordById(purchaseRecordId);
    }

    public int createPurchaseRecord(IPurchaseRecord record) {
        return DatabaseHelper.getInstance().insertPurchaseRecordReturnId(record);
    }
}
