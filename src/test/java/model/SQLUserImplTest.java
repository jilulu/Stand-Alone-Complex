package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLUserImplTest {
    @Test
    public void testEquality() {
        assertEquals(
                new SQLUserImpl(1, "jamesji", "528491"),
                new SQLUserImpl(1, "jamesji", "528491")
        );
        assertNotEquals(
                new SQLUserImpl(1, "jamesji", "528491"),
                new SQLUserImpl(2, "jamesji", "528491")
        );
        assertNotEquals(
                new SQLUserImpl(1, "jamesji", "528491"),
                new SQLUserImpl(1, "luluji", "528491")
        );
        assertNotEquals(
                new SQLUserImpl(1, "jamesji", "528491"),
                new SQLUserImpl(1, "jamesji", "000000")
        );
    }

    @Test
    public void testHashCode() {
        assertEquals(
                new SQLUserImpl(1, "jamesji", "528491").hashCode(),
                new SQLUserImpl(1, "jamesji", "528491").hashCode()
        );
        assertNotEquals(
                new SQLUserImpl(1, "jamesji", "528491").hashCode(),
                new SQLUserImpl(2, "jamesji", "528491").hashCode()
        );
        assertNotEquals(
                new SQLUserImpl(1, "jamesji", "528491").hashCode(),
                new SQLUserImpl(1, "luluji", "528491").hashCode()
        );
        assertNotEquals(
                new SQLUserImpl(1, "jamesji", "528491").hashCode(),
                new SQLUserImpl(1, "jamesji", "000000").hashCode()
        );
    }
}