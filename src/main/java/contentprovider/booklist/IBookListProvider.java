package contentprovider.booklist;

import model.IBook;

import java.util.List;

/**
 * Created by jamesji on 15/03/2017.
 */
public interface IBookListProvider {
    List<? extends IBook> provideBookList();
}
