package contentprovider.bookdetail;

import api.DatabaseHelper;
import model.IBook;

/**
 * Created by jamesji on 03/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLBookDetailProvider implements IBookDetailProvider {

    SQLBookDetailProvider() {

    }

    @Override
    public IBook provideBookDetails(String id) {
        return DatabaseHelper.getInstance().queryBookById(id);
    }
}
