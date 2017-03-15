package model.douban;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("large")
    @Expose
    public String large;
    @SerializedName("medium")
    @Expose
    public String medium;

}
