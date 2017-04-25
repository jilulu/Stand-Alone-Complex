package api;

import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void testEncodeURL() {
        String[] urls = {"https://google.com/search?q=something", "https://google.com/search?q=something&redirect=somethingElse"};
        for (String url : urls) {
            String encoded = Utils.encodeURL(url);
            assertNotNull(encoded);
            assertNotEquals(encoded, url);
            assert !encoded.contains("?");
            assert !encoded.contains("&");
            assert !encoded.contains("/");
            assert !encoded.contains(":");
        }
    }

    @Test
    public void testDecodeURL() {
        String[] urls = {"https://google.com/search?q=something", "https://google.com/search?q=something&redirect=somethingElse"};
        for (String url : urls) {
            assertEquals(
                    Utils.decodeURL(Utils.encodeURL(url)),
                    url
            );
        }
    }

    @Test
    public void testGetSearchLinkPreviousPage() {
        String query = "someQuery";
        final int total = 12, limit = 10;
        assertNull(Utils.getSearchLinkPreviousPage(query, 0, total, limit));
        for (int i = 1; i < 11; i++) {
            assertEquals(Utils.getSearchLinkPreviousPage(query, 1, total, limit), "/book/search?q=someQuery&offset=0");
        }
        assertEquals(Utils.getSearchLinkPreviousPage(query, 11, total, limit), "/book/search?q=someQuery&offset=1");
    }

    @Test
    public void testGetSearchLinkNextPage() {
        String query = "someQuery";
        final int total = 12, limit = 10;

        for (int i = 0; i < 2; i++) {
            assertEquals(Utils.getSearchLinkNextPage(query, i, total, limit),
                    "/book/search?q=someQuery&offset=" + (i + limit));
        }
        for (int i = 2; i < 12; i++) {
            assertNull(Utils.getSearchLinkNextPage(query, i, total, limit));
        }

//        System.out.println(Utils.getSearchLinkNextPage(query, 10, 13, 10));
    }
}