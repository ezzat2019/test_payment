package com.example.testpayment.others;

import android.os.Parcel;
import android.os.Parcelable;

public class RegiterModel implements Parcelable {
    private String email;
    private String pass;
    private String num;

    protected RegiterModel(Parcel in) {
        email = in.readString();
        pass = in.readString();
        num = in.readString();
    }

    public RegiterModel() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(pass);
        dest.writeString(num);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegiterModel> CREATOR = new Creator<RegiterModel>() {
        @Override
        public RegiterModel createFromParcel(Parcel in) {
            return new RegiterModel(in);
        }

        @Override
        public RegiterModel[] newArray(int size) {
            return new RegiterModel[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public RegiterModel(String email, String pass, String num) {
        this.email = email;
        this.pass = pass;
        this.num = num;
    }
}
