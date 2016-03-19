package com.stephenk.audionotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AudioNotes.db";

    public static final String TABLE_AUDIONOTES = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTES = "Note";
    public static final String COLUMN_TIMESTAMP = "Timestamp";

    public DBhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_AUDIONOTES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTES + " TEXT, " +
                COLUMN_TIMESTAMP + " INTEGER );";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIONOTES);
        onCreate(db);
    }

    /** *****************
     *  Database handlers
     *  *************** */

    // Add note to database
    public void addNote(String name, Integer timestamp_msec) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(COLUMN_NOTES, name);
        row.put(COLUMN_TIMESTAMP, timestamp_msec);

        db.insert(TABLE_AUDIONOTES, null, row);
        db.close();
    }
}
