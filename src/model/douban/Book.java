package model.douban;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("rating")
    @Expose
    public Rating rating;
    @SerializedName("subtitle")
    @Expose
    public String subtitle;
    @SerializedName("author")
    @Expose
    public List<String> author = null;
    @SerializedName("pubdate")
    @Expose
    public String pubdate;
    @SerializedName("tags")
    @Expose
    public List<Tag> tags = null;
    @SerializedName("origin_title")
    @Expose
    public String originTitle;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("binding")
    @Expose
    public String binding;
    @SerializedName("translator")
    @Expose
    public List<String> translator = null;
    @SerializedName("catalog")
    @Expose
    public String catalog;
    @SerializedName("pages")
    @Expose
    public String pages;
    @SerializedName("images")
    @Expose
    public Images images;
    @SerializedName("alt")
    @Expose
    public String alt;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("publisher")
    @Expose
    public String publisher;
    @SerializedName("isbn10")
    @Expose
    public String isbn10;
    @SerializedName("isbn13")
    @Expose
    public String isbn13;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("alt_title")
    @Expose
    public String altTitle;
    @SerializedName("author_intro")
    @Expose
    public String authorIntro;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("series")
    @Expose
    public Series series;
    @SerializedName("price")
    @Expose
    public String price;

}
