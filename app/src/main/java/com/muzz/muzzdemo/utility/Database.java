package com.muzz.muzzdemo.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.muzz.muzzdemo.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "messageDB";
    private static final int DB_VERSION = 1;
    private static final String MESSAGING_TABLE_NAME = "messageTable";
    private static final String ID_COL = "id";
    private static final String USER_COL = "userID";
    private static final String MESSAGE_COL = "message";
    private static final String UNIX_COL = "unix";
    private static final String STATUS_COL = "status";

    SQLiteDatabase database;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Initializing database.
        String query = "CREATE TABLE " + MESSAGING_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_COL + " TEXT,"
                + MESSAGE_COL + " TEXT,"
                + UNIX_COL + " LONG,"
                + STATUS_COL + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + MESSAGING_TABLE_NAME);
        onCreate(db);
    }

    //This method is to save a new table inside the database.
    public void createDBTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + MESSAGING_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_COL + " TEXT,"
                + MESSAGE_COL + " TEXT,"
                + UNIX_COL + " LONG,"
                + STATUS_COL + " INTEGER)";

        database = getWritableDatabase();
        database.execSQL(query);
        database.close();
    }

    //This method is use to add new message to our sqlite database.
    public void addDBMessage(String userID, String messageText, long unix, int messageStatus) {

        database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_COL, userID);
        values.put(MESSAGE_COL, messageText);
        values.put(UNIX_COL, unix);
        values.put(STATUS_COL, messageStatus);

        database.insert(MESSAGING_TABLE_NAME, null, values);
        database.close();
    }

    //This method is to get the list of the messages in the database.
    public List<MessageModel> getDBMessages() {

        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + MESSAGING_TABLE_NAME, null);

        List<MessageModel> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            MessageModel fxCurrency = new MessageModel(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getInt(4));
            data.add(fxCurrency);
        }
        cursor.close();
        database.close();

        return data;
    }
}
