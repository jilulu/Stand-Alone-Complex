package model.douban;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("max")
    @Expose
    public Integer max;
    @SerializedName("numRaters")
    @Expose
    public Integer numRaters;
    @SerializedName("average")
    @Expose
    public String average;
    @SerializedName("min")
    @Expose
    public Integer min;

}
