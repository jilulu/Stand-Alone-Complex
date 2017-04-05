package contentprovider.bookquery;

import api.DatabaseHelper;
import model.IBook;
import model.IQueryResult;
import model.SQLBookImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jamesji on 03/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLBookQueryProvider implements IBookQueryProvider {
    SQLBookQueryProvider() {

    }

    @Override
    public IQueryResult queryForBooks(String query, int offset, int limit) {
        Collection<SQLBookImpl> sqlBooks = DatabaseHelper.getInstance().queryBooksByTitle(query, offset, limit);
        int count = DatabaseHelper.getInstance().countBooksByTitle(query);
        return new IQueryResult() {
            private List<SQLBookImpl> mBooks = new ArrayList<>(sqlBooks);

            @Override
            public List<? extends IBook> getBookList() {
                return mBooks;
            }

            @Override
            public int getStart() {
                return offset;
            }

            @Override
            public int getCount() {
                return mBooks.size();
            }

            @Override
            public int getTotal() {
                return count;
            }
        };
    }
}
