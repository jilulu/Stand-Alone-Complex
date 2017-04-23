package contentprovider.booklist;

import api.book.DatabaseHelper;
import model.IBook;
import model.SQLBookImpl;

import java.util.*;

public class SQLAllBookListProvider implements IBookListProvider {

    SQLAllBookListProvider() {
        // suppress constructor scope to package
    }

    @Override
    public List<? extends IBook> provideBookList() {
        Collection<SQLBookImpl> results = DatabaseHelper.getInstance().queryAllBooks();
        return new ArrayList<SQLBookImpl>(results);
    }
}
