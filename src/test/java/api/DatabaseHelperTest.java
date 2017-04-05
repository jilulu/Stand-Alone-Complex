package api;

import model.SQLBookImpl;
import org.junit.Test;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testQueryBooksByTitle() {
        Collection<SQLBookImpl> suzumiya = DatabaseHelper.getInstance().queryBooksByTitle("涼宮ハルヒ");
        assertNotEquals(suzumiya.size(), 0);
//        suzumiya.forEach(l -> System.out.println(GsonFactory.getGson().toJson(l)));
        Collection<SQLBookImpl> kantai = DatabaseHelper.getInstance().queryBooksByTitle("艦これ");
        assertNotEquals(kantai.size(), 0);
//        kantai.forEach(s -> System.out.println(GsonFactory.getGson().toJson(s)));
        Collection<SQLBookImpl> noRecord = DatabaseHelper.getInstance().queryBooksByTitle("528491");
        assertEquals(noRecord.size(), 0);
    }

    @Test
    public void testQueryBooksByTitleWithLimits() {
        Collection<SQLBookImpl> suzumiya = DatabaseHelper.getInstance().queryBooksByTitle("涼宮ハルヒ", 0, 20);
        assertEquals(suzumiya.size(), 20);
    }

    @Test
    public void testSelectCountByTitle() {
        int count = DatabaseHelper.getInstance().countBooksByTitle("涼宮ハルヒ");
        assertNotEquals(count, 0);
//        System.out.println(count);
    }

    @Test
    public void testQueryBooksByAuthor() {
        Collection<SQLBookImpl> xinhaicheng = DatabaseHelper.getInstance().queryBooksByAuthor("新海诚");
        assertNotEquals(xinhaicheng.size(), 0);
//        xinhaicheng.forEach(r -> System.out.println(GsonFactory.getGson().toJson(r)));
        Collection<SQLBookImpl> makoto = DatabaseHelper.getInstance().queryBooksByAuthor("新海誠");
        assertNotEquals(makoto.size(), 0);
//        makoto.forEach(r -> System.out.println(GsonFactory.getGson().toJson(r)));
    }

    @Test
    public void testQueryBooksById() {
        SQLBookImpl sqlBook = DatabaseHelper.getInstance().queryBookById("700");
        assertNotNull(sqlBook);
//        System.out.println(GsonFactory.getGson().toJson(sqlBook));
    }
}