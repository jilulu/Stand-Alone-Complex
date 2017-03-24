package model;

import java.util.List;

/**
 * Created by jamesji on 19/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public interface IQueryResult {
    List<? extends IBook> getBookList();

    int getStart();

    int getCount();

    int getTotal();
}
