package edu.ua.cs.thoughts.database;

/**
 * Created by TaxMac on 10/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.entities.User;

public class DataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] userTableColumns = { MySQLiteHelper.COLUMN_USERNAME,
            MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD,
            };

    private String[] thoughtTableColumns = { MySQLiteHelper.COLUMN_THOUGHTID,
            MySQLiteHelper.COLUMN_THOUGHTUSER, MySQLiteHelper.COLUMN_TEXT, MySQLiteHelper.COLUMN_DATETIME,
            MySQLiteHelper.COLUMN_POLARITY, MySQLiteHelper.COLUMN_EMOTION};

    public DataSource(Context context) {
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
                userTableColumns, null, null, null, null, null);

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

    public List<User> getAllUserThoughts(String username) {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                userTableColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            if (user.getUsername().equals(username)) {
                users.add(user);
            }
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

    /* Creates new thought and inserts it into the database. Returns the
    thought that was created. */
    public Thought createThought(String thoughtText, String thoughtUser) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_THOUGHTUSER, thoughtUser);
        values.put(MySQLiteHelper.COLUMN_TEXT, thoughtText);

        String dateTime = getDateTime();
        values.put(MySQLiteHelper.COLUMN_DATETIME, dateTime);
        long thoughtId = database.insert(MySQLiteHelper.TABLE_THOUGHTS, null, values);

        Thought thought = new Thought(thoughtId, thoughtUser, thoughtText, dateTime, 0);
        return thought;
    }

    /**
     * get datetime
     * */
    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public ArrayList<Thought> getAllThoughts() {
        ArrayList<Thought> thoughts = new ArrayList<Thought>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_THOUGHTS,
               thoughtTableColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Thought thought = cursorToThought(cursor);
            thoughts.add(thought);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return thoughts;

    }

    private Thought cursorToThought(Cursor cursor) {
        Thought thought = new Thought(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getFloat(4));
        return thought;
    }

    public void addPolarityToThought(long thoughtID, float polarity) {
        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_POLARITY, polarity);
        database.update(MySQLiteHelper.TABLE_THOUGHTS, args, MySQLiteHelper.COLUMN_THOUGHTID + "=" + thoughtID, null);
    }
}
