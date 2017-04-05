package contentprovider.bookquery;

import model.IQueryResult;
import org.junit.Test;

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
        IQueryResult queryResult = BookQueryProviderFactory.getProvider().queryForBooks("涼宮ハルヒ", 0, 20);
        assertNotNull(queryResult);
        assertNotNull(queryResult.getBookList());
        assertNotEquals(queryResult.getBookList().size(), 0);
        assertEquals(queryResult.getBookList().size(), 20);
        assertEquals(queryResult.getStart(), 0);
        assertEquals(queryResult.getCount(), 20);

    }
}