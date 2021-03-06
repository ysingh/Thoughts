package edu.ua.cs.thoughts.entities;

/**
 * Created by Tarif Haque on 10/8/14.
 */

public class User {

    private String email;
    private String password;
    private String username;

    public User(String email, String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return username + " - " + email;
    }

}
