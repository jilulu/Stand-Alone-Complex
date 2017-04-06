package api.purchaserecord;

import model.IPurchaseRecord;
import model.SQLPurchaseRecordImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import static api.purchaserecord.DatabaseHelper.Table.COLUMN_ID;
import static api.purchaserecord.DatabaseHelper.Table.COLUMN_BOOK_ID;
import static api.purchaserecord.DatabaseHelper.Table.COLUMN_USER_ID;
import static api.purchaserecord.DatabaseHelper.Table.COLUMN_QUANTITY;
import static api.purchaserecord.DatabaseHelper.Table.COLUMN_PURCHASE_STATUS;
import static api.purchaserecord.DatabaseHelper.Table.COLUMN_PAYMENT_METHOD;

/**
 * Created by jamesji on 06/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SqlPurchaseRecordAdapter {

    private Callback mCallback;

    public SqlPurchaseRecordAdapter(Callback callback) {
        this.mCallback = callback;
    }

    public void adaptResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null || mCallback == null) {
            return;
        }
        while (resultSet.next()) {
            int id = resultSet.getInt(COLUMN_ID);
            int bookId = resultSet.getInt(COLUMN_BOOK_ID);
            int userId = resultSet.getInt(COLUMN_USER_ID);
            int quantity = resultSet.getInt(COLUMN_QUANTITY);
            int purchaseStatus = resultSet.getInt(COLUMN_PURCHASE_STATUS);
            int paymentMethod = resultSet.getInt(COLUMN_PAYMENT_METHOD);
            IPurchaseRecord iPurchaseRecord = new SQLPurchaseRecordImpl(id, bookId, quantity, purchaseStatus, paymentMethod, userId);
            mCallback.onPurchaseRecordAdapted(iPurchaseRecord);
        }
    }

    public interface Callback {
        void onPurchaseRecordAdapted(IPurchaseRecord record);
    }
}
