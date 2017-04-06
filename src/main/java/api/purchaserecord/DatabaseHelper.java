package api.purchaserecord;

import api.DatabaseManager;
import model.IPurchaseRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelper {
    public static class Table {
        static final String COLUMN_ID = "id";
        static final String COLUMN_BOOK_ID = "book_id";
        static final String COLUMN_USER_ID = "user_id";
        static final String COLUMN_QUANTITY = "quantity";
        static final String COLUMN_PURCHASE_STATUS = "purchase_status";
        static final String COLUMN_PAYMENT_METHOD = "payment_method";

        public static final String[] DEFINITION_PURCHASE_STATUS = {
                "purchased",
                "delivered",
                "refund-requested",
                "refunded"
        };
        public static final String[] DEFINITION_PAYMENT_RECORD = {
                "credit card",
                "cash on delivery",
                "royalty point"
        };
    }

    private static volatile DatabaseHelper mInstance;

    private DatabaseHelper() {
        // suppressed default constructor
    }

    public static DatabaseHelper getInstance() {
        if (mInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new DatabaseHelper();
                }
            }
        }
        return mInstance;
    }

    Collection<IPurchaseRecord> getPurchaseRecordsByUserId(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;

        final Collection<IPurchaseRecord> purchaseRecords = new HashSet<IPurchaseRecord>();
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("SELECT * FROM PurchaseRecord WHERE user_id=(?)");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            SqlPurchaseRecordAdapter adapter = new SqlPurchaseRecordAdapter(new SqlPurchaseRecordAdapter.Callback() {
                @Override
                public void onPurchaseRecordAdapted(IPurchaseRecord record) {
                    purchaseRecords.add(record);
                }
            });
            adapter.adaptResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }
        return purchaseRecords;
    }

    Map<IPurchaseRecord, String> getPurchaseRecordsWithTitleByUserId(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;

        final HashMap<IPurchaseRecord, String> recordTitleMap = new HashMap<IPurchaseRecord, String>();
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement(
                    "SELECT user_pr.*, Book.title " +
                            "FROM (" +
                            "SELECT pr.id, pr.book_id, pr.quantity, pr.purchase_status, pr.payment_method " +
                            "FROM PurchaseRecord AS pr INNER JOIN [User] ON pr.user_id = [User].id WHERE [User].id = (?)" +
                            ") AS user_pr INNER JOIN Book ON user_pr.book_id = Book.id");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            SqlPurchaseRecordTitleMapAdapter adapter = new SqlPurchaseRecordTitleMapAdapter(new SqlPurchaseRecordTitleMapAdapter.Callback() {
                @Override
                public void onPairAdapted(IPurchaseRecord record, String bookTitle) {
                    recordTitleMap.put(record, bookTitle);
                }
            });
            adapter.adaptResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }
        return recordTitleMap.size() == 0 ? null : recordTitleMap;
    }

    public void insertPurchaseRecord(IPurchaseRecord iPurchaseRecord) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("INSERT INTO Shinjin.dbo.PurchaseRecord VALUES (?,?,?,?,?)");
            statement.setInt(1, iPurchaseRecord.getBookId());
            statement.setInt(2, iPurchaseRecord.getQuantity());
            statement.setInt(3, iPurchaseRecord.getPurchaseStatus());
            statement.setInt(4, iPurchaseRecord.getPaymentMethod());
            statement.setInt(5, iPurchaseRecord.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignored
                }
                try {
                    connection.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }
    }
}
