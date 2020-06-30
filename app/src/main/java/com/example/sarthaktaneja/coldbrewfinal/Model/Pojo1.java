package com.example.sarthaktaneja.coldbrewfinal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sarthaktaneja on 11/11/18.
 */

public class Pojo1 implements Serializable {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("image_url")
    @Expose
    private String image_url;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getImage_url ()
    {
        return image_url;
    }

    public void setImage_url (String image_url)
    {
        this.image_url = image_url;
    }

}
