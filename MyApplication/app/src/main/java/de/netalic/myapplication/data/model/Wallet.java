package de.netalic.myapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.RealmField;

public class Wallet  extends RealmObject implements Parcelable{

    @SerializedName("currencyCode")
    @RealmField(name = "CurrencyCode")
    private String mCurrencyCode;

    @SerializedName("id")
    @RealmField(name = "Id")
    private int mId;

    @SerializedName("spendableBalance")
    @RealmField(name = "SpendableBalance")
    private double mSpendableBalance;

    @SerializedName("modifiedAt")
    @RealmField(name = "ModifiedAt")
    private String mModifiedAt;

    @SerializedName("name")
    @RealmField(name = "Name")
    private String mName;

    @SerializedName("createdAt")
    @RealmField(name = "CreatedAt")
    private String mCreatedAt;

    @SerializedName("ownerId")
    @RealmField(name = "OwnerId")
    private int mOwnerId;

    @SerializedName("balance")
    @RealmField(name = "Balance")
    private double mBalance;

    @SerializedName("address")
    @RealmField(name = "Address")
    private String mAddress;

    @SerializedName("currencySymbol")
    @RealmField(name = "CurrencySymbol")
    private String mCurrencySymbol;


    public String getName() {
        return mName;
    }

    public double getBalance() {
        return mBalance;
    }


    public void setBalance(int balance){
        this.mBalance = balance;
    }

    public void setName(String name) {
        this.mName = name;
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

    public Wallet(Parcel in) {
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

    public Wallet() {

    }

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

}
