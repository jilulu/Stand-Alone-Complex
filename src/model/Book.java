package model;

/**
 * Created by jamesji on 15/03/2017.
 */
public class Book {
    private String title;
    private String imprint;
    private String summary;
    private String author;
    private String isbn;
    private String coverUrl;

    public Book(String title, String imprint, String summary, String author, String isbn, String coverUrl) {
        this.title = title;
        this.imprint = imprint;
        this.summary = summary;
        this.author = author;
        this.isbn = isbn;
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImprint() {
        return imprint;
    }

    public String getSummary() {
        return summary;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}
