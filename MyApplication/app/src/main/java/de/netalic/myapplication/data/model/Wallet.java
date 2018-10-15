package de.netalic.myapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Wallet implements Parcelable {

    @SerializedName("currencyCode")
    private String mCurrencyCode;

    @SerializedName("id")
    private int mId;

    @SerializedName("spendableBalance")
    private double mSpendableBalance;

    @SerializedName("modifiedAt")
    private String mModifiedAt;

    @SerializedName("name")
    private String mName;

    @SerializedName("createdAt")
    private String mCreatedAt;

    @SerializedName("ownerId")
    private int mOwnerId;

    @SerializedName("balance")
    private double mBalance;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("currencySymbol")
    private String mCurrencySymbol;

    protected Wallet(Parcel in) {
        mCurrencyCode = in.readString();
        mId = in.readInt();
        mSpendableBalance = in.readDouble();
        mModifiedAt = in.readString();
        mName = in.readString();
        mCreatedAt = in.readString();
        mOwnerId = in.readInt();
        mBalance = in.readDouble();
        mAddress = in.readString();
        mCurrencySymbol = in.readString();
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel in) {
            return new Wallet(in);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCurrencyCode);
        parcel.writeInt(mId);
        parcel.writeDouble(mSpendableBalance);
        parcel.writeString(mModifiedAt);
        parcel.writeString(mName);
        parcel.writeString(mCreatedAt);
        parcel.writeInt(mOwnerId);
        parcel.writeDouble(mBalance);
        parcel.writeString(mAddress);
        parcel.writeString(mCurrencySymbol);
    }

    public String getName() {
        return mName;
    }

    public double getBalance() {
        return mBalance;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public String getAddress() {
        return mAddress;
    }
}
