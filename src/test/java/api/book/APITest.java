package api.book;

import model.IBook;
import model.douban.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */

public class APITest {

    private static final String[] queryKeywords = {"", " ", "涼宮ハルヒ", "長門有希"};
    public static final String[] queryISBNs = {"", " ", "1370144", "1779132", "4731949", "4069947", "26850914", "21460732"};

    @Test
    public void queryDoubanForBooks() throws Exception {
        for (String queryKeyword : queryKeywords) {
            List<Book> books = API.queryDoubanForBooks(queryKeyword);
            if (!queryKeyword.equals("") && !queryKeyword.equals(" ")) {
                assertNotNull(books);
                assertNotEquals(books.size(), 0);
            }
        }
    }

    @Test
    public void queryDoubanForBookDetail() throws Exception {
        for (String queryISBN : queryISBNs) {

            // Don't harm Douban
            Thread.sleep(1000);

            IBook iBook = API.queryDoubanForBookDetail(queryISBN);
            if (!queryISBN.equals("") && !queryISBN.equals(" ")) {
                assertNotNull(iBook);
            }
        }
    }

}
