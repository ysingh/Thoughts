package edu.ua.cs.thoughts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_THOUGHTS = "thoughts";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_THOUGHTID = "thoughtid";
    public static final String COLUMN_THOUGHTUSER = "username";

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statements
    private static final String USERS_CREATE =
            "create table " + TABLE_USERS +
            "(" + COLUMN_USERNAME + " text unique, "
            + COLUMN_EMAIL + " text not null, "
            + COLUMN_PASSWORD + " text not null)";

    private static final String THOUGHTS_CREATE =
            "create table " + TABLE_THOUGHTS +
                    "(" + COLUMN_THOUGHTID + " integer primary key autoincrement, "
                    + COLUMN_THOUGHTUSER + " text not null, "
                    + COLUMN_TEXT + " text)";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(USERS_CREATE);
        database.execSQL(THOUGHTS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THOUGHTS);
        onCreate(db);
    }

}
