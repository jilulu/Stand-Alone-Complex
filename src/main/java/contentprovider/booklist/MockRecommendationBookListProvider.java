package contentprovider.booklist;

import api.API;
import model.IBook;

import java.io.IOException;
import java.util.List;

/**
 * Created by jamesji on 16/03/2017.
 */
public class MockRecommendationBookListProvider implements IBookListProvider {

    MockRecommendationBookListProvider() {

    }

    @Override
    public List<? extends IBook> provideBookList() {
        List<model.douban.Book> resultBookList = null;
        try {
            resultBookList = API.queryDoubanForBooks("新海诚");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resultBookList == null || resultBookList.size() == 0) {
            return null;
        }
        resultBookList.remove(1);
        resultBookList.remove(1);
        return resultBookList;
    }
}
