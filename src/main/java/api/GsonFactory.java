package api;

import com.google.gson.Gson;

/**
 * Created by jamesji on 15/03/2017.
 */
public class GsonFactory {
    private GsonFactory() {

    }

    private volatile static Gson mInstance;

    public static Gson getGson() {
        if (mInstance == null) {
            synchronized (GsonFactory.class) {
                if (mInstance == null) {
                    mInstance = new Gson();
                }
            }
        }
        return mInstance;
    }
}
