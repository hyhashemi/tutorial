package de.netalic.myapplication.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public final class MyDatabase {

    private MyDatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;
    private static final String TABLE_NAME = "Speciality";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_ID = "_id";

    public MyDatabase(Context context){
        mDatabaseHelper = new MyDatabaseHelper(context);
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }
    public long createRecords(int id, String title){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, id);
        values.put(COLUMN_NAME_TITLE, title);
        return mDatabase.insert(TABLE_NAME, null, values);
    }
    public void deleteDatabase(){
        mDatabaseHelper.delete(mDatabase);
    }
}
