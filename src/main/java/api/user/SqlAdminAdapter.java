package api.user;

import model.IAdmin;
import model.SQLAdminImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlAdminAdapter {

    private Callback mCallback;

    public SqlAdminAdapter(Callback callback) {
        this.mCallback = callback;
    }

    public void adaptAdmin(ResultSet resultSet) throws SQLException {
        if (mCallback == null) {
            return;
        }
        if (resultSet.next()) {
            int rId = resultSet.getInt(DatabaseHelper.Table.COLUMN_ID);
            String rUsername = resultSet.getString(DatabaseHelper.Table.COLUMN_ADMIN_USER_NAME);
            String rUserPassword = resultSet.getString(DatabaseHelper.Table.COLUMN_ADMIN_PASSWORD);
            String rUserEmail = resultSet.getString(DatabaseHelper.Table.COLUMN_ADMIN_EMAIL);
            SQLAdminImpl sqlAdmin = new SQLAdminImpl(rId, rUsername, rUserPassword, rUserEmail);
            mCallback.onAdminAdapted(sqlAdmin);
        }
    }

    public interface Callback {
        void onAdminAdapted(IAdmin admin);
    }
}
