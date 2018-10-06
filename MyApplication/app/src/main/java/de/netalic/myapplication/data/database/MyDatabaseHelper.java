package de.netalic.myapplication.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Speciality";
    private static final String DATABASE_CREATE =  "CREATE TABLE Speciality(Id integer NOT NULL UNIQUE,Title text NOT NULL);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS Speciality";
    MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }
}
