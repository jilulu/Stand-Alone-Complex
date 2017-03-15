package contentprovider;

import api.API;
import model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesji on 15/03/2017.
 */
public class MockArrivalBookListProvider implements IBookListProvider {

    @Override
    public List<Book> provideBookList() {
        List<model.douban.Book> suzumiyaHaruhi = null;
        try {
            suzumiyaHaruhi = API.queryDoubanForBooks("凉宫春日");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (suzumiyaHaruhi == null || suzumiyaHaruhi.size() == 0) {
            return null;
        }
        List<Book> bookList = new ArrayList<>(suzumiyaHaruhi.size());
        for (model.douban.Book book : suzumiyaHaruhi) {
            Book plainBook = new Book(book.title, book.publisher, book.summary,
                    book.author.toString().replace("[", "").replace("]", ""), book.isbn10, book.images.large);
            bookList.add(plainBook);
        }
        return bookList;
    }
}
