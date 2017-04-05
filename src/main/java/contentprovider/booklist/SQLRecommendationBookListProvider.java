package contentprovider.booklist;

import api.DatabaseHelper;
import model.IBook;
import model.SQLBookImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jamesji on 03/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLRecommendationBookListProvider implements IBookListProvider {
    @Override
    public List<? extends IBook> provideBookList() {
        Collection<SQLBookImpl> mokoto = DatabaseHelper.getInstance().queryBooksByAuthor("新海誠");
        return new ArrayList<>(mokoto);
    }
}
