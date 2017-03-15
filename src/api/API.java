package api;

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
    private static final String API_BASE_URL = "https://api.douban.com/v2/";
    private static final String API_BOOK_SEARCH_PATH = "book/search";

    private API() {

    }

    public static List<Book> queryDoubanForBooks(String queryValue) throws IOException {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(API_QUERY_KEY, queryValue);
        String queryURL = Utils.appendParamsToURL(queryMap, API_BASE_URL + API_BOOK_SEARCH_PATH);
        Request queryRequest = new Request.Builder().url(queryURL).get().build();
        Response response = OkHttpFactory.getClient().newCall(queryRequest).execute();
        SearchResponse searchResponse = GsonFactory.getGson().
                fromJson(new InputStreamReader(response.body().byteStream(), "utf-8"), SearchResponse.class);
        return searchResponse == null ? null : searchResponse.books;
    }
}
