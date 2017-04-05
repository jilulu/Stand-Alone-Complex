package contentprovider.booklist;

import api.book.DatabaseHelper;
import model.IBook;
import model.SQLBookImpl;

import java.util.*;

/**
 * Created by jamesji on 03/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLArrivalBookListProvider implements IBookListProvider {

    SQLArrivalBookListProvider() {
        // suppress constructor scope to package
    }

    @Override
    public List<? extends IBook> provideBookList() {
        Collection<SQLBookImpl> results = DatabaseHelper.getInstance().queryBooksByTitle("凉宫春日");
        return new ArrayList<>(results);
    }
}
