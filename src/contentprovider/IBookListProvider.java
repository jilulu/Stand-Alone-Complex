package contentprovider;

import model.Book;

import java.util.List;

/**
 * Created by jamesji on 15/03/2017.
 */
public interface IBookListProvider {
    List<Book> provideBookList();
}
