package api;

import model.SQLBookImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jamesji on 03/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SqlBookAdapter {

    private Callback callback;

    public SqlBookAdapter(Callback callback) {
        this.callback = callback;
    }

    public void adaptResultSet(ResultSet resultSet) throws SQLException {
        if (callback == null) {
            return;
        }
        while (resultSet.next()) {
            int id = resultSet.getInt(DatabaseHelper.Table.COLUMN_ID);
            String bookTitle = resultSet.getString(DatabaseHelper.Table.COLUMN_TITLE);
            String author = resultSet.getString(DatabaseHelper.Table.COLUMN_AUTHOR);
            String publisher = resultSet.getString(DatabaseHelper.Table.COLUMN_PUBLISHER);
            String summary = resultSet.getString(DatabaseHelper.Table.COLUMN_SUMMARY);
            String isbn = resultSet.getString(DatabaseHelper.Table.COLUMN_ISBN);
            String cover_url = resultSet.getString(DatabaseHelper.Table.COLUMN_COVER_URL);
            double price = resultSet.getDouble(DatabaseHelper.Table.COLUMN_PRICE);
            SQLBookImpl sqlBook = new SQLBookImpl(bookTitle, publisher, summary, author, isbn, cover_url,
                    String.valueOf(price),
                    String.valueOf(id)
            );
            callback.onBookAdapted(sqlBook);
        }
    }

    public interface Callback {
        void onBookAdapted(SQLBookImpl book);
    }
}
