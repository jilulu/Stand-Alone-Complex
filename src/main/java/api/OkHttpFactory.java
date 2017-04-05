package api;

import okhttp3.OkHttpClient;

/**
 * Created by James Ji on 07/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class OkHttpFactory {

    private OkHttpFactory() {

    }

    private volatile static OkHttpClient mClient;

    public static OkHttpClient getClient() {
        if (mClient == null) {
            synchronized (OkHttpFactory.class) {
                if (mClient == null) {
                    mClient = new OkHttpClient.Builder().build();
                }
            }
        }
        return mClient;
    }
}
