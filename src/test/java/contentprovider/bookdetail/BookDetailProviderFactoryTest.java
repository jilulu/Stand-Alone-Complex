package contentprovider.bookdetail;

import api.APITest;
import api.GsonFactory;
import model.IBook;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookDetailProviderFactoryTest {
    @Test
    public void testProvider() throws Exception {
        for (String queryISBN : APITest.queryISBNs) {

            // Don't harm Douban
            Thread.sleep(1000);

            IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(queryISBN);
            System.out.println("Fetching book details for " + queryISBN);
            if (queryISBN.length() == 0 || queryISBN.equals(" ")) {
                // expect null
                System.out.println("Got result: " + iBook);
            } else {
                assertNotNull(iBook);
                System.out.println("Got result: " + GsonFactory.getGson().toJson(iBook));
            }
        }
    }
}
