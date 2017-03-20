package contentprovider.bookdetail;

import model.IBook;

/**
 * Created by jamesji on 17/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public interface IBookDetailProvider {
    IBook provideBookDetails(String id);
}
