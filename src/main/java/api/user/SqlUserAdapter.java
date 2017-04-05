package api.user;

import model.IUser;
import model.SQLUserImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SqlUserAdapter {

    private Callback mCallback;

    public SqlUserAdapter(Callback callback) {
        this.mCallback = callback;
    }

    public void adaptUser(ResultSet resultSet) throws SQLException {
        if (mCallback == null) {
            return;
        }
        if (resultSet.next()) {
            int rId = resultSet.getInt(DatabaseHelper.Table.COLUMN_ID);
            String rUsername = resultSet.getString(DatabaseHelper.Table.COLUMN_USER_NAME);
            String rUserPassword = resultSet.getString(DatabaseHelper.Table.COLUMN_USER_PASSWORD);
            String rUserPurchaseRecord = resultSet.getString(DatabaseHelper.Table.COLUMN_PURCHASE_RECORD);
            SQLUserImpl sqlUser = new SQLUserImpl(rId, rUsername, rUserPassword, rUserPurchaseRecord);
            mCallback.onUserAdapted(sqlUser);
        }
    }

    public interface Callback {
        void onUserAdapted(IUser user);
    }
}
