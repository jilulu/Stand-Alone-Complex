package api;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by James Ji on 07/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class API {

    private static final String API_KEY_PARAM_KEY = "api_key";
    private static final String API_KEY = "d69c41d4f7a1d1fb53107f09038a77c1";
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_MOVIE_PATH = "movie/now_playing";

    private API() {

    }

    public static String getUpcoming() throws IOException {
        String path = API_BASE_URL + API_MOVIE_PATH;
        String url = Utils.appendParamsToURL(getBasicParamMap(), path);
        Request request = new Request.Builder().url(url).get().build();
        Response response = OkHttpFactory.getClient().newCall(request).execute();
        return response.body().string();
    }

    private static Map<String, String> getBasicParamMap() {
        Map<String, String> qParams = new HashMap<>();
        qParams.put(API_KEY_PARAM_KEY, API_KEY);
        return qParams;
    }
}
