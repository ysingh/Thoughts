package edu.ua.cs.thoughts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import edu.ua.cs.thoughts.database.MySQLiteHelper;
import edu.ua.cs.thoughts.entities.Thought;

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

        Thought thought = new Thought(thoughtText);
        return thought;
    }

}
