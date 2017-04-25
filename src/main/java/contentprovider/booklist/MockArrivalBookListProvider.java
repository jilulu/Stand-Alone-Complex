package contentprovider.booklist;

import api.book.API;
import model.IBook;

import java.io.IOException;
import java.util.List;

/**
 * Created by jamesji on 15/03/2017.
 */
public class MockArrivalBookListProvider implements IBookListProvider {

    MockArrivalBookListProvider() {

    }

    @Override
    public List<? extends IBook> provideBookList() {
        List<model.douban.Book> resultBookList = null;
        try {
            resultBookList = API.queryDoubanForBooks("凉宫春日");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resultBookList == null || resultBookList.size() == 0) {
            return null;
        }
        return resultBookList;
    }
}
