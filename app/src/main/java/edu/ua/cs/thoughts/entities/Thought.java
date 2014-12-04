package edu.ua.cs.thoughts.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TaxMac on 10/10/14.
 */

public class Thought implements Parcelable {

    public String thoughtText, username, dateTime;
    public String emotionType = "Unclassified";
    public float polarity = 0;
    public long thoughtID;

    public Thought(long thoughtID, String username, String thoughtText, String dateTime, float polarity, String emotionType) {
        this.thoughtText = thoughtText;
        this.username = username;
        this.emotionType = emotionType;
        this.thoughtID = thoughtID;
        this.dateTime = dateTime;
        this.polarity = polarity;
    }

    public String getEmotionType() {
        return emotionType;
    }

    public Thought(String s){
        this.thoughtText = s;
    }

    public void setEmotionType(String type) {
        this.emotionType = type;
    }

    public void setPolarity(float polarity) {
        this.polarity = polarity;
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
        dest.writeString(this.dateTime);
        dest.writeFloat(this.polarity);
        dest.writeString(this.emotionType);
    }

    private Thought(Parcel in) {
        this.thoughtText = in.readString();
        this.username = in.readString();
        this.thoughtID = in.readInt();
        this.dateTime = in.readString();
        this.polarity = in.readFloat();
        this.emotionType = in.readString();
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
