package com.example.usermanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String name; //Name
    private int age; //Age
    private String address; //Address

    //Constructor
    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
        address = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //Getter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(address);
    }
}
