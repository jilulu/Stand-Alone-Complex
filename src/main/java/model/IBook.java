package model;

/**
 * Created by jamesji on 17/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public interface IBook {
    String getTitle();

    String getImprint();

    String getSummary();

    String getAuthor();

    String getIsbn();

    String getCoverUrl();

    String getId();

    String getPrice();
}
