package de.netalic.myapplication.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public final class MyDatabase {

    private MyDatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //TODO(Hanieh) Android team convention CEP
    private static final String TABLE_NAME = "Speciality";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_ID = "_id";

    public MyDatabase(Context context){
        mDatabaseHelper = new MyDatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public long createRecords(int id, String title){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, id);
        values.put(COLUMN_NAME_TITLE, title);
        return mSqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public void deleteDatabase(){
        mSqLiteDatabase.delete(TABLE_NAME, null, null);
    }
}
