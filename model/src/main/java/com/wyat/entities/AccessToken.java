package com.wyat.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zack on 12/01/17.
 */

public class AccessToken {
    @SerializedName("token")
    @Expose
    private String AccessToken;

    public String getAccessToken() {
        return AccessToken;
    }
}
