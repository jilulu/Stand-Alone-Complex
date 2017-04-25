package api.user.loyaltypoint;

import api.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jamesji on 25/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelper {

    private static volatile DatabaseHelper mHelper;

    private DatabaseHelper() {
        // suppressed default constructor
    }

    public static DatabaseHelper getInstance() {
        if (mHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (mHelper == null) {
                    mHelper = new DatabaseHelper();
                }
            }
        }
        return mHelper;
    }

    public void initUser(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("INSERT INTO LoyaltyPoints VALUES (?, ?)");
            statement.setInt(1, userId);
            statement.setDouble(2, 0);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignored
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignored
                }
            }

        }
    }

    public double queryUserLoyaltyPoints(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;

        double point = 0;

        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("SELECT * FROM LoyaltyPoints WHERE user_id=(?)");

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                point = resultSet.getDouble("point");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignored
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignored
                }
            }
        }

        return point;
    }

    public void updateUserLoyaltyPoint(int userId, double newLoyaltyPoint) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("UPDATE LoyaltyPoints SET point=(?) WHERE user_id=(?)");
            statement.setInt(2, userId);
            statement.setDouble(1, newLoyaltyPoint);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignored
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignored
                }
            }
        }
    }
}