package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jamesji on 03/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLBookImplTest {
    @Test
    public void testEquality() {
        assertEquals(
                new SQLBookImpl("Effective Java", "Addison-Wesley", "", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491"),
                new SQLBookImpl("Effective Java", "Addison-Wesley", "", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491")
        );
        assertNotEquals(
                new SQLBookImpl("Effective Java", "Addison-Wesley", "", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491"),
                new SQLBookImpl("Effective Python", "Addison-Wesley", "", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491")
        );
    }

    @Test
    public void testHashCode() {
        assertEquals(
                new SQLBookImpl("Effective Java", "Addison-Wesley", "", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491").hashCode(),
                new SQLBookImpl("Effective Java", "Addison-Wesley", "", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491").hashCode()
        );
        assertNotEquals(
                new SQLBookImpl("Effective Java", "Addison-Wesley", "_", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491").hashCode(),
                new SQLBookImpl("Effective Java", "Addison-Wesley", "^", "Joshua Bloch",
                        "978-0-321-35668-0", "", "USD99.99", "528491").hashCode()
        );
    }
}