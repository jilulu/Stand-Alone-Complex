package api;

import model.SQLBookImpl;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jamesji on 24/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelperTest {

    @Test
    public void testConnection() throws Exception {
        Connection connection = DatabaseHelper.getInstance().getDatabaseConnection();
        assertNotNull(connection);
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testQueryAllBooks() throws Exception {

        List<SQLBookImpl> sqlBookList = DatabaseHelper.getInstance().queryAllBooks();
        assertNotNull(sqlBookList);
        assertNotEquals(sqlBookList.size(), 0);

        for (SQLBookImpl sqlBook : sqlBookList) {
            assertNotNull(sqlBook);
            String s = GsonFactory.getGson().toJson(sqlBook);
            System.out.println(s);
        }
    }
}