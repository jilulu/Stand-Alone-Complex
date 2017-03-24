package contentprovider.bookdetail;

import api.API;
import model.IBook;

import java.io.IOException;

/**
 * Created by jamesji on 18/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class MockBookDetailProvider implements IBookDetailProvider {
    MockBookDetailProvider() {
        // disallow usages outside package
    }

    @Override
    public IBook provideBookDetails(String id) {
        try {
            return API.queryDoubanForBookDetail(id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
