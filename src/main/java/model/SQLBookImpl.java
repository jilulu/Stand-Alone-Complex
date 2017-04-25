package model;

/**
 * Created by jamesji on 25/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLBookImpl implements IBook {

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SQLBookImpl)) {
            return false;
        }
        SQLBookImpl sqlBook = (SQLBookImpl) obj;
        return (title == null ? sqlBook.title == null : title.equals(sqlBook.title)) &&
                (imprint == null ? sqlBook.imprint == null : imprint.equals(sqlBook.imprint)) &&
                (summary == null ? sqlBook.summary == null : summary.equals(sqlBook.summary)) &&
                (author == null ? sqlBook.author == null : author.equals(sqlBook.author)) &&
                (isbn == null ? sqlBook.isbn == null : isbn.equals(sqlBook.isbn)) &&
                (coverUrl == null ? sqlBook.coverUrl == null : coverUrl.equals(sqlBook.coverUrl)) &&
                (price == null ? sqlBook.price == null : price.equals(sqlBook.price)) &&
                (id == null ? sqlBook.id == null : id.equals(sqlBook.id));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + title.hashCode();
        result = result * 31 + imprint.hashCode();
        result = result * 31 + summary.hashCode();
        result = result * 31 + author.hashCode();
        result = result * 31 + isbn.hashCode();
        result = result * 31 + coverUrl.hashCode();
        result = result * 31 + price.hashCode();
        result = result * 31 + id.hashCode();
        return result;
    }
}
