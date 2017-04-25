package contentprovider.bookquery;

import model.IBook;
import model.IQueryResult;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jamesji on 04/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookQueryProviderFactoryTest {
    @Test
    public void testQuery() {

        HashSet<IBook> bookHashSet = new HashSet<IBook>();

        String query = "涼宮ハルヒ";
        IQueryResult queryResult = BookQueryProviderFactory.getProvider().queryForBooks(query, 0, 10);

        assertNotNull(queryResult);
        assertNotNull(queryResult.getBookList());
        assertNotEquals(queryResult.getBookList().size(), 0);
        assertEquals(queryResult.getBookList().size(), 10);
        assertEquals(queryResult.getStart(), 0);
        assertEquals(queryResult.getCount(), 10);

        bookHashSet.addAll(queryResult.getBookList());

        IQueryResult nextTenResults = BookQueryProviderFactory.getProvider().queryForBooks(query, 10, 10);
        assertNotNull(queryResult);
        assertNotNull(queryResult.getBookList());
        bookHashSet.addAll(nextTenResults.getBookList());

        assertEquals(bookHashSet.size(), 20);
    }
}