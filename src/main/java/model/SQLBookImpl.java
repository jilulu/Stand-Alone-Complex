package model;

/**
 * Created by jamesji on 25/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLBookImpl implements IBook{

    private String title, imprint, summary, author, isbn, coverUrl, price, id;

    public SQLBookImpl(String title, String imprint, String summary, String author, String isbn, String coverUrl, String price, String id) {
        this.title = title;
        this.imprint = imprint;
        this.summary = summary;
        this.author = author;
        this.isbn = isbn;
        this.coverUrl = coverUrl;
        this.price = price;
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImprint() {
        return imprint;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public String getCoverUrl() {
        return coverUrl;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getId() {
        return id;
    }
}
