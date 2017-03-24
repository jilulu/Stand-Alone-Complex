import contentprovider.booklist.BookListProviderFactory;
import model.IBook;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookListProviderFactoryTest {
    @Test
    public void testProvider() {
        List<? extends IBook> arrivals = BookListProviderFactory.getArrivalProvider().provideBookList();
        assertNotNull(arrivals);
        assertNotEquals(arrivals.size(), 0);
        List<? extends IBook> recommendations = BookListProviderFactory.getRecommendationProvider().provideBookList();
        assertNotNull(recommendations);
        assertNotEquals(recommendations.size(), 0);
    }

}
