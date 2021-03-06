package contentprovider.bookquery;

import api.book.API;
import model.IQueryResult;
import model.douban.SearchResponse;

import java.io.IOException;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class MockBookQueryProvider implements IBookQueryProvider {

    MockBookQueryProvider() {

    }

    @Override
    public IQueryResult queryForBooks(String query, int offset, int limit) {
        SearchResponse searchResponse = null;
        try {
            searchResponse = API.queryDouban(query, offset, limit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
