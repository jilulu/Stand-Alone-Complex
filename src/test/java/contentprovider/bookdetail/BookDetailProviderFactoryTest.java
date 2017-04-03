package contentprovider.bookdetail;

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
        String[] ids = {"715", "716", "717", "718", "719"};
        for (String id : ids) {

            IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(id);
            System.out.println("Fetching book details for " + id);
            if (id.length() == 0 || id.equals(" ")) {
                // expect null
                System.out.println("Got result: " + iBook);
            } else {
                assertNotNull(iBook);
                System.out.println("Got result: " + GsonFactory.getGson().toJson(iBook));
            }
        }
    }
}
