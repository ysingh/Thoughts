package edu.ua.cs.thoughts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaxMac on 10/10/14.
 */
public class ThoughtsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_THOUGHTUSER,
            MySQLiteHelper.COLUMN_THOUGHTID, MySQLiteHelper.COLUMN_TEXT};

    public ThoughtsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /* Creates new thought and inserts it into the database. Returns the
    thought that was created. */
    public Thought createThought(String thoughtText, String thoughtUser) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_THOUGHTUSER, thoughtUser);
        values.put(MySQLiteHelper.COLUMN_TEXT, thoughtText);
        long thoughtId = database.insert(MySQLiteHelper.TABLE_THOUGHTS, null, values);

        Thought thought = new Thought(thoughtUser, thoughtId, thoughtText);
        return thought;
    }

    public void deleteUser(String username) {
        database.delete(MySQLiteHelper.TABLE_USERS, MySQLiteHelper.COLUMN_USERNAME + " = " + username, null);
    }

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

}
