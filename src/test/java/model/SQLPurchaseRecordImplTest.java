package model;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jamesji on 07/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLPurchaseRecordImplTest {
    @Test
    public void testEquals() throws Exception {
        assertEquals(
                new SQLPurchaseRecordImpl(1, 2, 3, 0, 0, 6),
                new SQLPurchaseRecordImpl(1, 2, 3, 0, 0, 6)
        );

        assertNotEquals(
                new SQLPurchaseRecordImpl(1, 2, 3, 0, 0, 6),
                new SQLPurchaseRecordImpl(1, 3, 2, 0, 0, 6)
        );

        assertNotEquals(
                new SQLPurchaseRecordImpl(1, 2, 3, 0, 0, 6),
                new SQLPurchaseRecordImpl(1, 2, 3, 1, 0, 6)
        );

        assertNotEquals(
                new SQLPurchaseRecordImpl(1, 2, 3, 0, 0, 6),
                new SQLPurchaseRecordImpl(1, 2, 3, 0, 1, 6)
        );
    }

    @Test
    public void testHashCode() throws Exception {
        HashSet<SQLPurchaseRecordImpl> sqlPurchaseRecords = new HashSet<SQLPurchaseRecordImpl>();
        sqlPurchaseRecords.add(new SQLPurchaseRecordImpl(1, 2, 3, 1, 1, 6));
        sqlPurchaseRecords.add(new SQLPurchaseRecordImpl(1, 2, 3, 1, 1, 6));
        assertEquals(sqlPurchaseRecords.size(), 1);
        sqlPurchaseRecords.add(new SQLPurchaseRecordImpl(1, 2, 3, 1, 2, 6));
        assertEquals(sqlPurchaseRecords.size(), 2);
    }

}