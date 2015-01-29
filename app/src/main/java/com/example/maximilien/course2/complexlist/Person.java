package com.example.maximilien.course2.complexlist;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Maximilien on 22/01/2015.
 */
@AllArgsConstructor(suppressConstructorProperties=true)
@NoArgsConstructor
@Data
public class Person implements Parcelable {
    private String lastName;
    private String firstName;
    private int favoriteColor;

    public Person(Parcel in) {
        lastName = in.readString();
        firstName = in.readString();
        favoriteColor = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeInt(favoriteColor);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
