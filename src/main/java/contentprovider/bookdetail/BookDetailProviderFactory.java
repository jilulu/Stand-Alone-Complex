package contentprovider.bookdetail;

/**
 * Created by jamesji on 18/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class BookDetailProviderFactory {
    private volatile static IBookDetailProvider mInstance;

    public static IBookDetailProvider getProvider() {
        if (mInstance == null) {
            synchronized (BookDetailProviderFactory.class) {
                if (mInstance == null) {
                    mInstance = new MockBookDetailProvider();
                }
            }
        }
        return mInstance;
    }
}
