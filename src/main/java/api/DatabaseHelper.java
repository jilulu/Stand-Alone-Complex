package api;

import model.SQLBookImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesji on 24/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelper {

    private static class Table {
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_TITLE = "title";
        private static final String COLUMN_AUTHOR = "author";
        private static final String COLUMN_PUBLISHER = "publisher";
        private static final String COLUMN_SUMMARY = "summary";
        private static final String COLUMN_ISBN = "isbn";
        private static final String COLUMN_COVER_URL = "cover_url";
        private static final String COLUMN_PRICE = "price";
    }

    private static volatile DatabaseHelper mHelper;

    private static final String DB_MASTER_PASSWORD = "XcMvSLUKEfBzDLmrKWnfZz3NkfFNuV3";
    private static final String JDBC_CONNECTION_CONFIG = "jdbc:sqlserver://sosdan.database.windows.net:1433;" +
            "database=Shinjin;user=jilulu@sosdan;password=" + DB_MASTER_PASSWORD + ";encrypt=true;" +
            "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    private DatabaseHelper() {
        // suppressed default constructor
    }

    public static DatabaseHelper getInstance() {
        if (mHelper == null) {
            synchronized (DatabaseHelper.class) {
                mHelper = new DatabaseHelper();
            }
        }
        return mHelper;
    }

    Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection(JDBC_CONNECTION_CONFIG);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SQLBookImpl> queryAllBooks() {
        Connection connection = null;
        Statement statement = null;
        List<SQLBookImpl> sqlBookList = new ArrayList<>();
        try {
            connection = getDatabaseConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT t.* FROM Shinjin.dbo.Book AS t;");
            while (resultSet.next()) {
                int id = resultSet.getInt(Table.COLUMN_ID);
                String title = resultSet.getString(Table.COLUMN_TITLE);
                String author = resultSet.getString(Table.COLUMN_AUTHOR);
                String publisher = resultSet.getString(Table.COLUMN_PUBLISHER);
                String summary = resultSet.getString(Table.COLUMN_SUMMARY);
                String isbn = resultSet.getString(Table.COLUMN_ISBN);
                String cover_url = resultSet.getString(Table.COLUMN_COVER_URL);
                double price = resultSet.getDouble(Table.COLUMN_PRICE);
                SQLBookImpl sqlBook = new SQLBookImpl(title, publisher, summary, author, isbn, cover_url,
                        String.valueOf(price),
                        String.valueOf(id)
                );
                sqlBookList.add(sqlBook);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // ignored
            }

            // close connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // ignored
            }
        }
        return sqlBookList.size() == 0 ? null : sqlBookList;
    }
}
