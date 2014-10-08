package edu.ua.cs.thoughts;

import java.util.ArrayList;

/**
 * A globally accessible database of users implemented
 * using the Singleton Design Pattern.
 *
 * Created by Tarif Haque on 10/8/14.
 */
public class UserDatabase {

    private static UserDatabase users;
    ArrayList<User> userList;

    private UserDatabase() {
        userList = new ArrayList<User>();
    }

    public static synchronized UserDatabase getInstance() {
        if (users == null) {
            users = new UserDatabase();
        }
        return users;
    }

    /* Add a new user to the database. */
    public void addUser(User user) {
        userList.add(user);
    }

}
