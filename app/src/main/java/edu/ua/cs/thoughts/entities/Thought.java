package edu.ua.cs.thoughts.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TaxMac on 10/10/14.
 */

public class Thought implements Parcelable {

    public String thoughtText, username;
    public long thoughtID;

    public Thought(long thoughtID, String username, String thoughtText) {
        this.thoughtText = thoughtText;
        this.username = username;
        this.thoughtID = thoughtID;
    }

    public Thought(String s){
        this.thoughtText = s;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thoughtText);
        dest.writeString(this.username);
        dest.writeLong(this.thoughtID);
    }

    private Thought(Parcel in) {
        this.thoughtText = in.readString();
        this.username = in.readString();
        this.thoughtID = in.readInt();
    }

    public static final Parcelable.Creator<Thought> CREATOR = new Parcelable.Creator<Thought>() {
        public Thought createFromParcel(Parcel source) {
            return new Thought(source);
        }

        public Thought[] newArray(int size) {
            return new Thought[size];
        }
    };
}
