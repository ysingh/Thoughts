package edu.ua.cs.thoughts.database;

/**
 * Created by TaxMac on 10/8/14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.ua.cs.thoughts.database.MySQLiteHelper;
import edu.ua.cs.thoughts.entities.User;

public class UsersDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_USERNAME,
            MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD };

    public UsersDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_EMAIL, user.getEmail());
        values.put(MySQLiteHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(MySQLiteHelper.COLUMN_USERNAME, user.getUsername());
        database.insert(MySQLiteHelper.TABLE_USERS, null, values);
    }

    /**
     * Delete a user in the database with a given username.
     */
    public void deleteUser(String username) {
        database.delete(MySQLiteHelper.TABLE_USERS, MySQLiteHelper.COLUMN_USERNAME + " = " + username, null);
    }

    /**
     * Get a list of all Users in the database.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User(cursor.getString(1), cursor.getString(0), cursor.getString(2));
        return user;
    }

    /**
     * Determines whether a given email and password combination is valid. If
     * the combination is valid, returns true. Otherwise, returns false.
     */
    public boolean isRegisteredUser(String email, String password) {
        List<User> userList = getAllUsers();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                if (userList.get(i).getPassword().equals(password)) return true;
                else return false;
            }
        }
        return false;
    }

    /**
     * Returns the username of a user given his or her email.
     */
    public String getUsernameGivenEmail(String email) {
        List<User> userList = getAllUsers();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return userList.get(i).getUsername();
            }
        }
        return null;
    }
}
