package api.book;

import api.GsonFactory;
import api.OkHttpFactory;
import api.Utils;
import model.IBook;
import model.douban.Book;
import model.douban.SearchResponse;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by James Ji on 07/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class API {

    private static final String API_QUERY_KEY = "q";
    private static final String API_QUERY_START = "start";
    private static final String API_QUERY_COUNT = "count";
    private static final String API_BASE_URL = "https://api.douban.com/v2/book/";
    private static final String API_BOOK_SEARCH_PATH = "search";

    private API() {

    }

    public static SearchResponse queryDouban(String queryValue) throws IOException {
        return queryDouban(queryValue, -1, -1);
    }

    public static SearchResponse queryDouban(String queryValue, int start, int limit) throws IOException {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(API_QUERY_KEY, queryValue);
        if (start >= 0 && limit >= 0) {
            queryMap.put(API_QUERY_START, String.valueOf(start));
            queryMap.put(API_QUERY_COUNT, String.valueOf(limit));
        }
        String queryURL = Utils.appendParamsToURL(queryMap, API_BASE_URL + API_BOOK_SEARCH_PATH);
        Request queryRequest = new Request.Builder().url(queryURL).get().build();
        Response response = OkHttpFactory.getClient().newCall(queryRequest).execute();
        return GsonFactory.getGson().fromJson(
                new InputStreamReader(response.body().byteStream(), "utf-8"), SearchResponse.class
        );
    }

    public static List<Book> queryDoubanForBooks(String queryValue) throws IOException {
        SearchResponse searchResponse = queryDouban(queryValue);
        return searchResponse == null ? null : searchResponse.books;
    }

    public static IBook queryDoubanForBookDetail(String id) throws IOException {
        if (id == null || id.length() == 0) {
            return null;
        }
        String bookURL = API_BASE_URL + id;
        Request bookDetailRequest = new Request.Builder().url(bookURL).get().build();
        Response response = OkHttpFactory.getClient().newCall(bookDetailRequest).execute();
        return GsonFactory.getGson()
                .fromJson(new InputStreamReader(response.body().byteStream(), "utf-8"), Book.class);
    }
}
