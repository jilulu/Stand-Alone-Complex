package api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamesji on 26/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class UtilsTest {
    @Test
    public void getFirstNumberInString() throws Exception {
        assertEquals(Double.compare(Utils.getFirstNumberInString("100 TWD"), 100), 0);
        assertEquals(Double.compare(Utils.getFirstNumberInString("TWD 100"), 100), 0);
        assertEquals(Double.compare(Utils.getFirstNumberInString("100.1111TWD"), 100.1111), 0);
        assertEquals(Double.compare(Utils.getFirstNumberInString("TWD100.1111"), 100.1111), 0);
        assertEquals(Double.compare(Utils.getFirstNumberInString("100"), 100), 0);
        assertEquals(Double.compare(Utils.getFirstNumberInString(""), 0), 0);
        assertEquals(Double.compare(Utils.getFirstNumberInString("税込価格：¥860 （本体：¥819）"), 860), 0);
    }

}