package edu.ua.cs.thoughts;

/**
 * Created by TaxMac on 10/10/14.
 */

public class Thought {

    String thoughtText;
    String username;
    int thoughtID;

    public Thought(String thoughtUser, long thoughtId, String thoughtText) {
    }

    public String getThoughtText() {
        return thoughtText;
    }

    public void setThoughtText(String thoughtText) {
        this.thoughtText = thoughtText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getThoughtID() {
        return thoughtID;
    }

    public void setThoughtID(int thoughtID) {
        this.thoughtID = thoughtID;
    }

}
