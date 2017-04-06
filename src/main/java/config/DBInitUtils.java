package config;

import api.book.API;
import api.book.DatabaseHelper;
import model.*;

import java.io.IOException;
import java.util.List;

import static api.purchaserecord.DatabaseHelper.Table.DEFINITION_PAYMENT_RECORD;
import static api.purchaserecord.DatabaseHelper.Table.DEFINITION_PURCHASE_STATUS;

/**
 * Created by jamesji on 31/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DBInitUtils {
    public static void initDataBase(BookInsertedCallback callback) {
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

    public static void initPurchaseRecords(PurchaseRecordInsertedCallback callback) {
        List<SQLBookImpl> sqlBookList = DatabaseHelper.getInstance().queryAllBooks();
        IUser james = api.user.DatabaseHelper.getInstance().authUserByPassword("jamesji", "528491");
        for (int i = 0; i < 20; i++) {
            IBook sqlBook = sqlBookList.get((int) (sqlBookList.size() * Math.random()));
            IPurchaseRecord iPurchaseRecord = new SQLPurchaseRecordImpl(
                    0,
                    Integer.parseInt(sqlBook.getId()),                          // select one from all the books
                    (int) (Math.random() * 2 + 1),                              // purchase one or two copies of the book
                    (int) (DEFINITION_PURCHASE_STATUS.length * Math.random()),  // select one purchase status
                    (int) (DEFINITION_PAYMENT_RECORD.length * Math.random()),   // select one payment method
                    james.getUserId()
            );
            api.purchaserecord.DatabaseHelper.getInstance().insertPurchaseRecord(iPurchaseRecord);
            if (callback != null) {
                callback.entryInserted(iPurchaseRecord);
            }
        }
    }

    public interface BookInsertedCallback {
        void entryInserted(IBook entry);
    }

    public interface PurchaseRecordInsertedCallback {
        void entryInserted(IPurchaseRecord entry);
    }
}
