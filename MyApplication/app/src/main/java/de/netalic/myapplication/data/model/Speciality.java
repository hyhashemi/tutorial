package de.netalic.myapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Speciality implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    public Speciality(int id, String title) {
        this.mTitle = title;
        this.mId = id;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Speciality> CREATOR = new Creator<Speciality>() {
        @Override
        public Speciality createFromParcel(Parcel in) {
            return new Speciality(in);
        }

        @Override
        public Speciality[] newArray(int size) {
            return new Speciality[size];
        }
    };

    public Speciality(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
    }
}