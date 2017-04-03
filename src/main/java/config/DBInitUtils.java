package config;

import api.API;
import api.DatabaseHelper;
import model.IBook;
import model.IQueryResult;

import java.io.IOException;
import java.util.List;

/**
 * Created by jamesji on 31/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DBInitUtils {
    public static void initDataBase(Callback callback) {
        final String[] keywords = {"東野圭吾", "涼宮ハルヒ", "艦これ", "新海誠"};
        for (String keyword : keywords) {
            IQueryResult queryResult = null;
            try {
                queryResult = API.queryDouban(keyword, 0, 100);
                List<? extends IBook> bookList = queryResult.getBookList();
                for (IBook iBook : bookList) {
                    if (callback != null) {
                        callback.entryInserted(iBook);
                    }
                    DatabaseHelper.getInstance().insertBook(iBook);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface Callback {
        void entryInserted(IBook entry);
    }
}
