package com.example.student.moneyexpense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    private static final String name = "courses.sqlite3";
    private static final int version = 2;


    public DBhelper(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE expense (" +
                "_id integer primary key autoincrement," +
                "amount text not null," +           // credit
                "date text not null," +            // letter grade e.g. A, B+
                "category text not null);";         // grade value e.g. 4, 3.5
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS course;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
