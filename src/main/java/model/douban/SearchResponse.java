package model.douban;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import model.IBook;
import model.IQueryResult;

import java.util.List;

public class SearchResponse implements IQueryResult {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("start")
    @Expose
    public Integer start;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("books")
    @Expose
    public List<Book> books = null;

    @Override
    public List<? extends IBook> getBookList() {
        return books;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getTotal() {
        return total;
    }
}
