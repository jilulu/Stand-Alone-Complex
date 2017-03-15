package model.douban;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

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

}
