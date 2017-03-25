package contentprovider.bookquery;

import contentprovider.bookdetail.BookDetailProviderFactory;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookQueryProviderFactory {
    private volatile static IBookQueryProvider mInstance;

    public static IBookQueryProvider getProvider() {
        if (mInstance == null) {
            synchronized (BookDetailProviderFactory.class) {
                if (mInstance == null) {
                    mInstance = new MockBookQueryProvider();
                }
            }
        }
        return mInstance;
    }
}
