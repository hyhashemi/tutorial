package de.netalic.myapplication.data.model;

import com.google.gson.annotations.SerializedName;

public class Speciality {

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    public void setId(int id){
        mId = id;
    }

    public int getId(){
        return mId;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public String getTitle(){
        return mTitle;
    }
}
