package com.example.classproject3.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Phone implements Parcelable {

    @NonNull
    private String mobile, home, office;

    public Phone(@NonNull String mobile, @NonNull String home, @NonNull String office) {
        this.mobile = mobile;
        this.home = home;
        this.office = office;
    }

    @NonNull
    public String getMobile() {
        return mobile;
    }

    @NonNull
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NonNull
    public String getHome() {
        return home;
    }

    @NonNull
    public void setHome(String home) {
        this.home = home;
    }

    @NonNull
    public String getOffice() {
        return office;
    }

    @NonNull
    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mobile);
        parcel.writeString(home);
        parcel.writeString(office);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    private Phone(Parcel in) {
        mobile = in.readString();
        home = in.readString();
        office = in.readString();
    }
}
