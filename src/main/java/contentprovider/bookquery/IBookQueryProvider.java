package contentprovider.bookquery;

import model.IQueryResult;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public interface IBookQueryProvider {
    IQueryResult queryForBooks(String query, int offset, int limit);
}
